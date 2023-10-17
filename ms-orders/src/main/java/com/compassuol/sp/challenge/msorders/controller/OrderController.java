package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductsProxy proxy;

    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        //para implementer
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrdersService());
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
