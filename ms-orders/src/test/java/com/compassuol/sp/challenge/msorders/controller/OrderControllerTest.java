package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.AddressRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testUpdateOrder() {
        Long orderId = 1L;
        RequestOrderDTO request = new RequestOrderDTO();

        request.setProducts(Arrays.asList(new OrderProductsModel(1L, 2L), new OrderProductsModel(2L, 3L)));
        request.setAddress(new AddressRequestDTO("123 Main St", 42, "12345-678"));
        request.setPayment_method(PaymentTypeEnum.CREDIT_CARD);

        OrderModel orderModel = new OrderModel();

        when(orderService.updateOrderService(orderId, request)).thenReturn(orderModel);

        ResponseEntity<OrderModel> response = orderController.updateOrder(orderId, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderModel, response.getBody());
    }

    @Test
    void testUpdateOrderService() {
        // Mocking data
        Long orderId = 1L;
        RequestOrderDTO requestDTO = new RequestOrderDTO();
        OrderModel updatedOrder = new OrderModel();
        updatedOrder.setStatus(StatusOrderEnum.SENT);

        Mockito.when(orderService.updateOrderService(orderId, requestDTO)).thenReturn(updatedOrder);
        ResponseEntity<OrderModel> responseEntity = orderController.updateOrder(orderId, requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(StatusOrderEnum.SENT, responseEntity.getBody().getStatus());
    }

    @Test
    void testUpdateOrderServiceOrderNotFound() {

        Long orderId = 1L;
        RequestOrderDTO requestDTO = new RequestOrderDTO();

        Mockito.when(orderService.updateOrderService(orderId, requestDTO))
                .thenThrow(new OrderNotFoundException("Pedido não encontrado"));
        assertThrows(OrderNotFoundException.class, () -> orderController.updateOrder(orderId, requestDTO));
    }
}
