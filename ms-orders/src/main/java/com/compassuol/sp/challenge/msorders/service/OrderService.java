package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.ProductModelDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.dto.ViaCepAddress;
import com.compassuol.sp.challenge.msorders.model.AddressModel;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<OrderModel> getAllOrdersService() {
        //para implementer
        return orderRepository.findAll();
    }

    public Optional<OrderModel> findBy(Long id) {
        return orderRepository.findById(id);
    }

    public OrderModel createOrderService(RequestOrderDTO request) throws ParseException {
        ViaCepAddress cepObject;
        double subtotalValue = 0.0;
        //sum products value per id
        for (OrderProductsModel productsModel : request.getProducts()) {
            ProductModelDTO product = proxy.getProductById(productsModel.getProduct_id());
            subtotalValue += productsModel.getQuantity() * product.getValue();
        }
        //get full address
        try {
            String apiUrl = "http://viacep.com.br/ws/" + request.getAddress().getPostalCode() + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            cepObject = objectMapper.readValue(object, ViaCepAddress.class);
        } catch (BusinessErrorException | JsonProcessingException ex) {
            throw new BusinessErrorException("postalCode has an error");
        }
        //set fields
        assert cepObject != null;
        AddressModel address = getAddressModel(request, cepObject);

        //send to database
        OrderModel order = new OrderModel(request.getProducts(), address, request.getPayment_method(), subtotalValue,
                StatusOrderEnum.CONFIRMED, "");
        return orderRepository.save(order);
    }

    public void updateOrderService() {
        //para implementer
    }

    public OrderModel cancelOrderByIdService(Long id, CancelOrderRequestDTO cancelOrderRequest) {
        OrderModel order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));

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
    }

    private AddressModel getAddressModel(RequestOrderDTO request, ViaCepAddress cepObject) {
        return AddressModel.builder()
                .number(request.getAddress().getNumber())
                .complement(cepObject.getComplemento())
                .city(cepObject.getLocalidade())
                .state(cepObject.getUf())
                .postalCode(cepObject.getCep())
                .street(request.getAddress().getStreet())
                .build();
    }
}