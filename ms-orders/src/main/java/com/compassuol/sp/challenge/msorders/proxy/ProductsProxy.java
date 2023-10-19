package com.compassuol.sp.challenge.msorders.proxy;

import com.compassuol.sp.challenge.msorders.dto.ProductModelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-products", url = "localhost:8080")
public interface ProductsProxy {
    @GetMapping("/products/{productId}")
    public ProductModelDTO getProductById(@PathVariable Long productId);
}