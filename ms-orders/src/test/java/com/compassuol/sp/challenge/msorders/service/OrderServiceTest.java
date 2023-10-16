package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
    }

    @Test
    public void testCancelOrderByIdServiceOrderNotFound() {

        Long orderId = 1L;
        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();
        try {
            orderService.cancelOrderByIdService(orderId, cancelOrderRequest);
        } catch (OrderNotFoundException e) {
            assertEquals("Pedido não encontrado", e.getMessage());
        }
    }

    @Test
    public void testCancelOrderByIdServiceOrderSent() {

        Long orderId = 1L;
        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setStatus(StatusOrderEnum.SENT);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();

        try {
            orderService.cancelOrderByIdService(orderId, cancelOrderRequest);
        } catch (OrderCancellationNotAllowedException e) {
            assertEquals("O pedido não pode ser cancelado, pois já foi enviado.", e.getMessage());
        }
    }

    @Test
    public void testCancelOrderByIdServiceOrderOver90Days() {

        Long orderId = 1L;
        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setStatus(StatusOrderEnum.CREATED);
        order.setCreate_date(new Date(System.currentTimeMillis() - 91 * 24 * 60 * 60 * 1000));

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();

        try {
            orderService.cancelOrderByIdService(orderId, cancelOrderRequest);
        } catch (OrderCancellationNotAllowedException e) {
            assertEquals("O pedido não pode ser cancelado, pois tem mais de 90 dias de criação.", e.getMessage());
        }
    }

    @Test
    public void testCancelOrderByIdServiceSuccess() {

        Long orderId = 1L;
        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setStatus(StatusOrderEnum.CREATED);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();
        cancelOrderRequest.setCancelReason("Motivo do cancelamento");

        OrderModel canceledOrder = orderService.cancelOrderByIdService(orderId, cancelOrderRequest);

        assertEquals(StatusOrderEnum.CANCELED, canceledOrder.getStatus());
        assertEquals("Motivo do cancelamento", canceledOrder.getCancel_reason());
    }
}
