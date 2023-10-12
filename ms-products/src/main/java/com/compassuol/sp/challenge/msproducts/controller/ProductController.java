package com.compassuol.sp.challenge.msproducts.controller;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> getProducts() {
        return productService.getAllProducts();
    }



    }


    