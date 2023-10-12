package com.compassuol.sp.challenge.msproducts.controller;

import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductModel> getProducts() {
        return productService.getAllProducts();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") long id,
                                                @RequestBody @Valid ProductDTO productDTO) {
        var product = productService.findProductByIdService(id);
        if (product.isEmpty()) throw new ProductNotFoundException("Product not found");

        var savedProduct = productService.updateProductService(product.get(), productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ProductNotFoundException ex) {
            throw new ProductNotFoundException("Product not found" +id);
        }
    }
}
