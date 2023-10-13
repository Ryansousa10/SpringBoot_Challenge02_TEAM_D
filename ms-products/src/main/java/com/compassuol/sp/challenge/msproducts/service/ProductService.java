package com.compassuol.sp.challenge.msproducts.service;

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

    public boolean isProductExistsByName(String name) {
        // Implemente a lógica para verificar se um produto com o mesmo nome já existe no banco de dados.
        // Você pode usar o método findByNome do seu repositório, se ele existir, ou implementar sua própria lógica de verificação.
        // Por exemplo:
        return productRepository.existsByName(name);
    }

    public ProductModel createProductService(ProductModel productModel) {
        // Implemente a lógica para criar um novo produto no banco de dados.
        // Por exemplo:
        return productRepository.save(productModel);
    }
}
