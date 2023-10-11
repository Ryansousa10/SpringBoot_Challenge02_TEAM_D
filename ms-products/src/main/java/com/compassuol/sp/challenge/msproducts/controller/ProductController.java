package com.compassuol.sp.challenge.msproducts.controller;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ErrorResponse;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<ProductModel> getProducts() {
        List<ProductModel> products = productService.getAllProducts();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado.");
        }
        return products;
    }


    // GET Exceptions TESTS
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
        return new ErrorResponse(404, "NOT FOUND", ex.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex) {
        return new ErrorResponse(500, "INTERNAL SERVER ERROR", ex.getMessage(), null);
    }

}
