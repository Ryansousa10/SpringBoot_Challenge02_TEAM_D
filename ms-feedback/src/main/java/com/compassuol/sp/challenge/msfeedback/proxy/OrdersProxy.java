package com.compassuol.sp.challenge.msfeedback.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-orders", url = "localhost:8000")
public interface OrdersProxy {

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id);
}
