package com.compassuol.sp.challenge.msproducts.controller;

import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNameExistsException;
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
import java.util.Optional;

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
        if (product.isEmpty()) throw new ProductNotFoundException("product not found");

        var savedProduct = productService.updateProductService(product.get(), productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId) {

        Optional<ProductModel> product = productService.findProductByIdService(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            throw new ProductNotFoundException("Product Not Found");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        // Verifica se o nome do produto já existe no banco de dados
        if (productService.isProductExistsByName(productDTO.getName())) {
            throw new ProductNameExistsException("Produto com o mesmo nome já existe.");
        }

        // Cria um novo produto com os dados fornecidos no DTO
        ProductModel newProduct = new ProductModel(productDTO.getName(), productDTO.getDescription(), productDTO.getValue());

        // Salva o novo produto no banco de dados
        ProductModel savedProduct = productService.createProductService(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
