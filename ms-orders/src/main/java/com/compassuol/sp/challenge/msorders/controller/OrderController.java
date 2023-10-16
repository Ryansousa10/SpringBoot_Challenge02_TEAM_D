package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductsProxy proxy;

    @GetMapping
    public void getAllOrders() {
        //para implementer
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Optional<OrderModel> order = orderService.findby(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            String errorMessage = "Pedido com ID " + id + " não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @PostMapping
    public void createOrder() {
        //para implementer
    }

    @PutMapping("/{id}")
    public void updateOrder() {
        //para implementer
    }

    @PostMapping("/{id}/cancel")
    public void cancelOrderById() {
        //para implementer
    }
}
