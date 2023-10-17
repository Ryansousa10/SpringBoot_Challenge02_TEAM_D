package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetOrderByIdValidId() {
        Long orderId = 1L;
        OrderModel mockOrder = new OrderModel();
        when(orderService.findBy(orderId)).thenReturn(Optional.of(mockOrder));

        ResponseEntity<?> responseEntity = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockOrder, responseEntity.getBody());
    }

    @Test
    public void testGetOrderByIdInvalidId() {
        Long invalidOrderId = -1L;

        ResponseEntity<?> responseEntity = orderController.getOrderById(invalidOrderId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ID de pedido inválido.", responseEntity.getBody());
    }

    @Test
    public void testGetOrderByIdNotFound() {
        Long nonExistentOrderId = 2L;
        when(orderService.findBy(nonExistentOrderId)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = orderController.getOrderById(nonExistentOrderId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Pedido com ID " + nonExistentOrderId + " não encontrado.", responseEntity.getBody());
    }
}
