package com.compassuol.sp.challenge.msorders.controller;

import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
    public void getOrderById() {
        //para implementer
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
