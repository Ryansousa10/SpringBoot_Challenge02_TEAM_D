package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.dto.*;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(
            @PathVariable Long id,
            @RequestBody @Valid RequestOrderUpdateDTO updateOrderDTO
    ) {
        // Etapa 1: Verifique se o pedido com o ID existe.
        OrderModel existingOrder = orderService.findOrderById(id);

        if (existingOrder == null) {
            // Trate o cenário em que o pedido não foi encontrado.
            return ResponseEntity.notFound().build();
        }

        // Etapa 2: Validar o campo "status" usando o enum StatusOrderEnum
        StatusOrderEnum newStatus = updateOrderDTO.getStatus();
        if (newStatus != null) {
            // Realize a validação ou tratamento adequado com o enum StatusOrderEnum.
            // Por exemplo, você pode verificar se o novo status é válido.
            if (!isValidStatus(newStatus)) {
                return ResponseEntity.badRequest().body("Status not valid");
            }
            existingOrder.setStatus(newStatus);
        }

        // Etapa 2: Converta os dados do DTO em objetos do modelo.
        List<OrderProductsModel> orderProducts = new ArrayList<>();
        for (ProductModelDTO productDTO : updateOrderDTO.getProducts()) {
            OrderProductsModel orderProduct = convertToOrderProductsModel(productDTO);
            orderProducts.add(orderProduct);
        }

        // Etapa 3: Atualize os campos do pedido com base nos objetos do modelo.
        existingOrder.setProducts(orderProducts);
        existingOrder.setPaymentMethod(updateOrderDTO.getPaymentMethod());

        // Atualize a data de atualização com a data e hora atuais no formato ISO 8601.
        existingOrder.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));


        // Etapa 4: Atualize o pedido chamando um método do serviço.
        OrderModel updatedOrder = orderService.updateOrder(existingOrder, updateOrderDTO);

        // Etapa 5: Crie um DTO de resposta para formatar a resposta.
        OrderResponseDTO responseDTO = new OrderResponseDTO(updatedOrder);

        return ResponseEntity.ok(responseDTO.toString());
    }

    private OrderProductsModel convertToOrderProductsModel(ProductModelDTO productDTO) {
        OrderProductsModel orderProductsModel = new OrderProductsModel();
        orderProductsModel.setName(productDTO.getName());
        // Defina outros campos de acordo com sua lógica.
        return orderProductsModel;
    }

    private boolean isValidStatus(StatusOrderEnum status) {
        // Verifique se o status é válido com base nas enumerações definidas em StatusOrderEnum.
        return status == StatusOrderEnum.CONFIRMED || status == StatusOrderEnum.SENT || status == StatusOrderEnum.CANCELED;
    }
}
