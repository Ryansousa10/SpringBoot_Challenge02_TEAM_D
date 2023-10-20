package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.dto.AddressRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
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
}
