package com.compassuol.sp.challenge.msfeedback.proxy;

import com.compassuol.sp.challenge.msfeedback.dto.OrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-orders", url = "localhost:8000")
public interface OrdersProxy {
    @GetMapping("/orders/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id);
}
