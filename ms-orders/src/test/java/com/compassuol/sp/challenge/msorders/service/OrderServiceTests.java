package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.dto.ViaCepAddress;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.VIA_CEP_ADDRESS;
import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.REQUEST_ORDER_DTO;
import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.PRODUCT_MODEL_DTO;
import static com.compassuol.sp.challenge.msorders.constants.ConstantOrders.VIA_CEP;
import static org.mockito.Mockito.*;


@SpringBootTest
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductsProxy proxy;
    @Test
    public void getTestFindById() {
        Long orderId = 1L;
        OrderModel mockOrder = new OrderModel();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Optional<OrderModel> result = orderService.findBy(orderId);

        assertTrue(result.isPresent());
        assertEquals(mockOrder, result.get());
        verify(orderRepository, times(1)).findById(orderId);
    }
    @Test
    public void testFindByIdWithInvalidId() {
        Long invalidId = -1L;

        when(orderRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<OrderModel> result = orderService.findBy(invalidId);

        assertFalse(result.isPresent()); // Deve retornar Optional.empty() para um ID inválido
    }

    @Test
    public void getTestFindByIdNotFound() {
        Long orderId = 2L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        Optional<OrderModel> result = orderService.findBy(orderId);

        assertFalse(result.isPresent());
        verify(orderRepository, times(1)).findById(orderId);
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
}
