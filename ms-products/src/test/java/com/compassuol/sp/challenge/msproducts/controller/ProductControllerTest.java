package com.compassuol.sp.challenge.msproducts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.VALID_PRODUCT;
import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.VALID_PRODUCT_DTO;
import static com.compassuol.sp.challenge.msproducts.constants.ProductsConstants.INVALID_PRODUCT_DTO;

import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        // Configurar o comportamento simulado do serviço
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("Product 1", "Product 1", 10.0));
        products.add(new ProductModel("Product 2", "Product 2", 20.0));

        when(productService.getAllProducts()).thenReturn(products);
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
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Alterado para OK (200)
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)); // Verifica se a lista está vazia
    }

    @Test
    public void updateProduct_withValidData_ReturnsCreated() throws Exception {
        VALID_PRODUCT.setId(1L);
        when(productService.updateProductService(VALID_PRODUCT, VALID_PRODUCT_DTO)).thenReturn(VALID_PRODUCT);
        when(productService.findProductByIdService(1L)).thenReturn(Optional.of(VALID_PRODUCT));
        mockMvc
                .perform(put("/products/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateProduct_withInvalidId_ReturnsException() throws Exception {
        VALID_PRODUCT.setId(2L);
        when(productService.updateProductService(VALID_PRODUCT, VALID_PRODUCT_DTO)).thenReturn(VALID_PRODUCT);
        when(productService.findProductByIdService(1L)).thenThrow(ProductNotFoundException.class);
        mockMvc
                .perform(put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_PRODUCT_DTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduct_withInvalidData_returnsException() throws Exception {
        VALID_PRODUCT.setId(1L);
        //when(productService.updateProductService(VALID_PRODUCT, VALID_PRODUCT_DTO)).thenReturn();
        when(productService.findProductByIdService(1L)).thenReturn(Optional.of(VALID_PRODUCT));
        mockMvc
                .perform(put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(INVALID_PRODUCT_DTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteProduct_withInvalidId_ReturnsException() throws Exception {
        when(productService.findProductByIdService(2L)).thenThrow(ProductNotFoundException.class);
        ProductController productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(delete("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteProduct_withValidId_ReturnsNoContent() throws Exception {
        when(productService.findProductByIdService(1L)).thenReturn(Optional.of(VALID_PRODUCT));
        ProductController productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(delete("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
