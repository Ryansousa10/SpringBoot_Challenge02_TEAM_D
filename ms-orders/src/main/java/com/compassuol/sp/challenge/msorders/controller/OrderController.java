package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.ProductNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // http://localhost:8000/orders?status=CONFIRMED
    @GetMapping
    public ResponseEntity<List<OrderModel>> getOrdersByStatusAndSort(
            @RequestParam(name = "status", required = false) StatusOrderEnum status) {
        return ResponseEntity.ok(orderService.getOrdersByStatusSortedByDate(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de pedido inválido.");
        }

        Optional<OrderModel> order = orderService.findBy(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            throw new ProductNotFoundException("Pedido com ID " + id + " não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody @Valid RequestOrderDTO request) throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrderService(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderModel> updateOrder(@PathVariable Long id, @RequestBody @Valid RequestOrderDTO request) {
        var response = orderService.updateOrderService(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderModel> cancelOrderById(@PathVariable Long id, @RequestBody CancelOrderRequestDTO cancelOrderRequest) {
        OrderModel canceledOrder = orderService.cancelOrderByIdService(id, cancelOrderRequest);
        return ResponseEntity.ok(canceledOrder);
    }
}
