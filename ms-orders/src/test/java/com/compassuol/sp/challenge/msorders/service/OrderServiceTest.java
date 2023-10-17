package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.ViaCepAddress;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.*;
import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.REQUEST_ORDER_DTO;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductsProxy proxy;

    @Before
    public void setUp() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());
    }

    @Test
    public void CreateOrder_withInvalidData_ReturnsNull() throws ParseException, JsonProcessingException {
        when(proxy.getProductById(anyLong())).thenReturn(PRODUCT_MODEL_DTO);

        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(VIA_CEP);

        ObjectMapper objectMapper = mock(ObjectMapper.class);
        when(objectMapper.readValue(anyString(), eq(ViaCepAddress.class))).thenReturn(VIA_CEP_ADDRESS);

        OrderModel result = orderService.createOrderService(REQUEST_ORDER_DTO);
        assertNull(result);
    }

    @Test
    public void testCancelOrderByIdServiceOrderNotFound() {
        Long orderId = 1L;
        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();

        when(orderRepository.findById(Math.toIntExact(orderId))).thenReturn(Optional.empty());

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
        order.setId(Math.toIntExact(orderId));
        order.setStatus(StatusOrderEnum.SENT);

        when(orderRepository.findById(Math.toIntExact(orderId))).thenReturn(Optional.of(order));

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
        order.setId(Math.toIntExact(orderId));
        order.setStatus(StatusOrderEnum.CREATED);

        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis() - 91L * 24L * 60L * 60L * 1000L);
        order.setCreate_date(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));

        when(orderRepository.findById(Math.toIntExact(orderId))).thenReturn(Optional.of(order));

        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();

        try {
            orderService.cancelOrderByIdService(orderId, cancelOrderRequest);
        } catch (OrderCancellationNotAllowedException e) {
            assertEquals("O pedido não pode ser cancelado, pois tem mais de 90 dias de criação.", e.getMessage());
        }
    }


}
