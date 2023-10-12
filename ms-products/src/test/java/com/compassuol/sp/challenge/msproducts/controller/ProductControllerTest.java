package com.compassuol.sp.challenge.msproducts.controller;

import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        // Configurar o comportamento simulado do serviço
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("Product 1", "Product 1", 10.0));
        products.add(new ProductModel("Product 2", "Product 2", 20.0));

        Mockito.when(productService.getAllProducts()).thenReturn(products);
    }

    @Test
    public void testGetProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].value").value(20.0));
    }

    @Test
    public void testGetProductsEmpty() throws Exception {
        // Simular um serviço que retorna uma lista vazia
        Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Alterado para OK (200)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)); // Verifica se a lista está vazia
    }
}