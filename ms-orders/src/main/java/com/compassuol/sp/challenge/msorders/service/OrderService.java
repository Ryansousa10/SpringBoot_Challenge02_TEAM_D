package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.*;
import com.compassuol.sp.challenge.msorders.model.AddressModel;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.proxy.ViaCepProxy;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductsProxy proxy;
    private final ViaCepProxy viaCepProxy;
    private final ParseToDTO parseToDTO;

    public List<OrderModel> getOrdersByStatusSortedByDate(StatusOrderEnum status) {
        if (status == null) {
            return orderRepository.findOrdersByCreateDateDesc();
        }
        return orderRepository.findOrdersByStatusAndCreateDateDesc(status);
    }

    public Optional<OrderModel> findBy(Long id) {
        return orderRepository.findById(id);
    }

    public CreateOrderResponseDTO createOrderService(RequestOrderDTO request) {
        double subtotalValue = 0.0;
        for (OrderProductsModel productsModel : request.getProducts()) {
            try {
                ProductModelDTO product = proxy.getProductById(productsModel.getProduct_id());
                subtotalValue += productsModel.getQuantity() * product.getValue();
            } catch (FeignException ex) {
                throw new BusinessErrorException("cannot find product id or your connection with" +
                        " products microservice is falling");
            }
        }
        try {
            ViaCepAddressDTO cep = viaCepProxy.getViaCepAddress(request.getAddress().getPostalCode());
            AddressModel address = getAddressModel(request, cep);
            OrderModel order = getOrderModel(request, address, subtotalValue);
            OrderModel model = orderRepository.save(order);
            return new CreateOrderResponseDTO(model);
        } catch (ParseException ex) {
            throw new RuntimeException();
        }
    }

    public OrderModel cancelOrderByIdService(Long id, CancelOrderRequestDTO cancelOrderRequest) {
        return orderRepository.findById(id)
                .map(order -> {
                    if (order.getStatus() == StatusOrderEnum.SENT) {
                        throw new OrderCancellationNotAllowedException("O pedido não pode ser cancelado, pois já foi enviado.");
                    }
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    if (order.getCreate_date() != null) {
                        long daysBetween = ChronoUnit.DAYS.between(order.getCreate_date().toLocalDate(), currentDateTime);
                        if (daysBetween > 90) {
                            throw new OrderCancellationNotAllowedException("O pedido não pode ser cancelado, pois tem mais de 90 dias de criação.");
                        }
                    } else {
                        throw new OrderCancellationNotAllowedException("A data de criação do pedido é nula.");
                    }
                    order.setStatus(StatusOrderEnum.CANCELED);
                    order.setCancel_reason(cancelOrderRequest.getCancelReason());
                    order.setCancel_date(currentDateTime);
                    order.setSubtotal_value(order.getSubtotal_value());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));
    }

    public OrderModel updateOrderService(Long id, RequestOrderDTO request) {
        OrderModel order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));

        if (order.getStatus() == StatusOrderEnum.CANCELED)
            throw new BusinessErrorException("pedido com status de cancelado não pode ser atualizado.");

        ViaCepAddressDTO cep = viaCepProxy.getViaCepAddress(request.getAddress().getPostalCode());
        OrderModel updateOrder = setOrderUpdates(order,request,cep);
        updateOrder.setStatus(StatusOrderEnum.SENT);
        return orderRepository.save(updateOrder);
    }

    private AddressModel getAddressModel(RequestOrderDTO request, ViaCepAddressDTO cep) {
        return AddressModel.builder()
                .number(request.getAddress().getNumber())
                .complement(cep.getComplemento())
                .city(cep.getLocalidade())
                .state(cep.getUf())
                .postalCode(cep.getCep())
                .street(request.getAddress().getStreet())
                .build();
    }

    private OrderModel getOrderModel(RequestOrderDTO request, AddressModel address, double subtotal)
            throws ParseException {
        return new OrderModel(request.getProducts(), address, request.getPayment_method(),
                subtotal, StatusOrderEnum.CONFIRMED, "");
    }

    private OrderModel setOrderUpdates(OrderModel order, RequestOrderDTO request, ViaCepAddressDTO cep) {
        order.setPayment_method(request.getPayment_method());
        AddressModel address = getAddressModel(request, cep);
        order.setAddress(address);
        order.setProducts(request.getProducts());
        return order;
    }
}