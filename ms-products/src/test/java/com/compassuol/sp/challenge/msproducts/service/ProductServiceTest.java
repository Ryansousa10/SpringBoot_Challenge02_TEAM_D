package com.compassuol.sp.challenge.msproducts.service;

import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        Mockito.when(productRepository.findAll()).thenReturn(products);
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


}
