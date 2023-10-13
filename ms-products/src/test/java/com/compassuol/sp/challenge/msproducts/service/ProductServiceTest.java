package com.compassuol.sp.challenge.msproducts.service;

import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.VALID_PRODUCT;
import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.VALID_PRODUCT_DTO;
import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.INVALID_PRODUCT;
import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.INVALID_PRODUCT_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        // Configurar o comportamento simulado do repositório
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("name1", "Product 1", 10.0));
        products.add(new ProductModel("name2", "Product 2", 20.0));

        when(productRepository.findAll()).thenReturn(products);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductModel> products = productService.getAllProducts();

        // Verificar se a lista de produtos não está vazia
        assertEquals(2, products.size());

        // Verificar se os detalhes dos produtos correspondem aos dados simulados
        assertEquals("name1", products.get(0).getName());
        assertEquals(10.0, products.get(0).getValue());
        assertEquals("name2", products.get(1).getName());
        assertEquals(20.0, products.get(1).getValue());
    }

    @Test
    public void updateProduct_withValidData_ReturnsProduct() {
        when(productRepository.save(VALID_PRODUCT)).thenReturn(VALID_PRODUCT);
        ProductModel product = productService.updateProductService(VALID_PRODUCT, VALID_PRODUCT_DTO);
        assertEquals(VALID_PRODUCT, product);
    }

    @Test
    public void updateProduct_withInvalidData_ReturnsNull() {
        when(productRepository.save(INVALID_PRODUCT)).thenReturn(null);
        ProductModel product = productService.updateProductService(INVALID_PRODUCT, INVALID_PRODUCT_DTO);
        assertNull(product);
    }

    @Test
    public void testDeleteProductById() {
        long productId = 1;
        ProductModel product = new ProductModel();
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProductById(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testDeleteProductByIdNotFound() {
        long productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Use uma expressão lambda para capturar a exceção lançada
        org.junit.jupiter.api.Assertions.assertThrows(ProductNotFoundException.class, () -> {
        productService.deleteProductById(productId);});
    }
}
