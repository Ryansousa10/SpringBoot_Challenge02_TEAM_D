package com.compassuol.sp.challenge.msproducts.service;

import com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<ProductModel> findProductByIdService(long id) {
        return productRepository.findById(id);
    }

    public ProductModel updateProductService(ProductModel productModel, ProductDTO productDTO) {
        productModel.setName(productDTO.getName());
        productModel.setDescription(productDTO.getDescription());
        productModel.setValue(productDTO.getValue());

        return productRepository.save(productModel);
    }
}
