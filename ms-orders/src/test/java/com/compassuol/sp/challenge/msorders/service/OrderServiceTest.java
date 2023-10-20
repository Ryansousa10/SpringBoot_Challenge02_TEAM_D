package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.proxy.ViaCepProxy;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Mock
    private ViaCepProxy viaCepProxy;

    @Before
    public void setUp() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
    }

    @Test
    public void CreateOrder_withValidData_ReturnsNotNull() throws ParseException {
        var orderModelCaptor = ArgumentCaptor.forClass(OrderModel.class);
        when(proxy.getProductById(anyLong())).thenReturn(PRODUCT_MODEL_DTO);
        when(viaCepProxy.getViaCepAddress(anyString())).thenReturn(VIA_CEP_ADDRESS_DTO);

        when(orderRepository.save(orderModelCaptor.capture())).thenReturn(ORDER_RESPONSE);

        OrderModel order = orderService.createOrderService(REQUEST_ORDER_DTO);
        assertNotNull(order);
    }

    @Test
    public void testCancelOrderByIdServiceOrderNotFound() {
        long orderId = 1L;
        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        try {
            orderService.cancelOrderByIdService(orderId, cancelOrderRequest);
        } catch (OrderNotFoundException e) {
            assertEquals("Pedido não encontrado", e.getMessage());
        }
    }

    @Test
    public void testCancelOrderByIdServiceOrderSent() {

        long orderId = 1L;
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
        long orderId = 1L;
        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setStatus(StatusOrderEnum.CREATED);

        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis() - 91L * 24L * 60L * 60L * 1000L);
        order.setCreate_date(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        CancelOrderRequestDTO cancelOrderRequest = new CancelOrderRequestDTO();

        try {
            orderService.cancelOrderByIdService(orderId, cancelOrderRequest);
        } catch (OrderCancellationNotAllowedException e) {
            assertEquals("O pedido não pode ser cancelado, pois tem mais de 90 dias de criação.", e.getMessage());
        }
    }
    @Test
    public void getTestFindByIdNotFound() {
        long orderId = 2;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        Optional<OrderModel> result = orderService.findBy(orderId);

        assertFalse(result.isPresent());
        verify(orderRepository, times(1)).findById(orderId);
    }
    @Test
    public void getTestFindById() {
        long orderId = 1;
        OrderModel mockOrder = new OrderModel();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Optional<OrderModel> result = orderService.findBy(orderId);

        assertTrue(result.isPresent());
        Assertions.assertEquals(mockOrder, result.get());
        verify(orderRepository, times(1)).findById(orderId);
    }
    @Test
    public void testFindByIdWithInvalidId() {
        long invalidId = -1;

        when(orderRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<OrderModel> result = orderService.findBy(invalidId);

        assertFalse(result.isPresent());
    }

    @Test
    public void UpdateOrder_withValidData_ReturnsOrder() {
        //var orderModelCaptor = ArgumentCaptor.forClass(OrderModel.class);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(ORDER_RESPONSE));
        when(viaCepProxy.getViaCepAddress(anyString())).thenReturn(VIA_CEP_ADDRESS_DTO);

        OrderModel response = orderService.updateOrderService(2L, REQUEST_ORDER_DTO);
        assertNotNull(response);
    }

    @Test
    public void UpdateOrder_withInvalidStatus_returnsException() {
        OrderModel order = ORDER_RESPONSE;
        order.setStatus(StatusOrderEnum.CANCELED);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> orderService.updateOrderService(1L, REQUEST_ORDER_DTO))
                .isInstanceOf(BusinessErrorException.class);
    }

    @Test
    public void UpdateOrder_withInvalidId_returnsException() {
        when(orderRepository.findById(1L)).thenThrow(OrderNotFoundException.class);

        assertThatThrownBy(() -> orderService.updateOrderService(1L, REQUEST_ORDER_DTO))
                .isInstanceOf(OrderNotFoundException.class);
    }
}
