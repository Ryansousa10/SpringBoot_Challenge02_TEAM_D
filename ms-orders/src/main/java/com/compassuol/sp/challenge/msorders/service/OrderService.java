package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.dto.*;
import com.compassuol.sp.challenge.msorders.model.AddressModel;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderModel findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    public List<OrderModel> getAllOrdersService() {
        // Implemente este método de acordo com suas necessidades.
        return orderRepository.findAll();
    }

    public OrderModel updateOrder(OrderModel order, @Valid RequestOrderUpdateDTO updateOrderDTO) {
        if (updateOrderDTO.getPaymentMethod() != null) {
            order.setPaymentMethod(updateOrderDTO.getPaymentMethod());
        }

        if (updateOrderDTO.getProducts() != null) {
            // Converta os produtos de ProductModelDTO para OrderProductsModel
            List<OrderProductsModel> orderProducts = new ArrayList<>();
            for (ProductModelDTO productDTO : updateOrderDTO.getProducts()) {
                OrderProductsModel orderProduct = convertToOrderProductsModel(productDTO);
                orderProducts.add(orderProduct);
            }
            order.setProducts(orderProducts);
        }

        if (updateOrderDTO.getAddress() != null) {
            // Converta o endereço de AddressDTO para AddressModel
            AddressModel addressModel = convertToAddressModel(updateOrderDTO.getAddress());
            order.setAddress(addressModel);
        }

        // Atualize a data de atualização
        order.setUpdateDate(new Date());

        // Salve a atualização no banco de dados
        return orderRepository.save(order);
    }

    private OrderProductsModel convertToOrderProductsModel(ProductModelDTO productDTO) {
        OrderProductsModel orderProductsModel = new OrderProductsModel();
        orderProductsModel.setName(productDTO.getName());
        // Outros mapeamentos, se necessário.
        return orderProductsModel;
    }

    private AddressModel convertToAddressModel(AddressDTO addressDTO) {
        AddressModel addressModel = new AddressModel();
        addressModel.setStreet(addressDTO.getStreet());
        // Outros mapeamentos, se necessário.
        return addressModel;
    }

    public Double calculateTotalValue(OrderModel order, List<ProductUpdateDTO> products, UpdateOrderDTO updateOrderDTO) {
        Double subtotalValue = 0.0;

        // Calcule o subtotal com base nos produtos.
        for (ProductUpdateDTO product : products) {
            Double productPrice = product.getPrice();
            int productQuantity = product.getQuantity();
            subtotalValue += productPrice * productQuantity;
        }

        // Aplique o desconto de 5% para pagamento PIX.
        if (PaymentTypeEnum.PIX.equals(updateOrderDTO.getPaymentMethod())) {
            Double discount = 0.05 * subtotalValue;
            return subtotalValue - discount;
        } else {
            return subtotalValue;
        }
    }
}
