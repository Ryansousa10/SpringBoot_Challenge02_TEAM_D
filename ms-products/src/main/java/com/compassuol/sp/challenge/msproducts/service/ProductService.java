package com.compassuol.sp.challenge.msproducts.service;

import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel updateProductService(ProductModel productModel, ProductDTO productDTO) {
        productModel.setName(productDTO.getName());
        productModel.setDescription(productDTO.getDescription());
        productModel.setValue(productDTO.getValue());

        return productRepository.save(productModel);
    }

    public Optional<ProductModel> findProductByIdService(long id) {
        return productRepository.findById(id);
    }
    public void deleteProductById(long id) {
        ProductModel existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        productRepository.delete(existingProduct);
    }

}
