package com.compassuol.sp.challenge.msproducts.controller;

import com.compassuol.sp.challenge.msproducts.controller.exception.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") long id,
                                                @RequestBody @Valid ProductDTO productDTO) {

        var product = productService.findProductByIdService(id);
        if (product.isEmpty()) throw new ProductNotFoundException("product not found");

        var savedProduct = productService.updateProductService(product.get(), productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
