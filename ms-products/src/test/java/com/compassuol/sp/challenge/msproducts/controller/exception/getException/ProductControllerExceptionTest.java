package com.compassuol.sp.challenge.msproducts.controller.exception.getException;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ErrorResponse;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.MyRuntimeException;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import com.compassuol.sp.challenge.msproducts.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerExceptionTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    @Test
    public void testSuccess() {
        // Configurar o comportamento do mock para retornar uma lista de produtos
        when(productService.getAllProducts()).thenReturn(Arrays.asList(new ProductModel(), new ProductModel()));

        // Fazer uma chamada ao endpoint que deve retornar com sucesso
        ResponseEntity<List<ProductModel>> response = restTemplate.exchange("/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductModel>>() {
        });

        // Verificar se o código de status é 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testProductNotFoundException() {
        // Configurar o comportamento do mock para lançar uma exceção personalizada
        when(productService.getAllProducts()).thenThrow(new ProductNotFoundException("Nenhum produto encontrado."));

        // Fazer uma chamada ao endpoint que deve lançar a exceção
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity("/products", ErrorResponse.class);

        // Verificar se o código de status é 404 (NOT FOUND)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verificar a estrutura da resposta de erro
        ErrorResponse errorResponse = response.getBody();
        assertEquals(404, errorResponse.getCode());
        assertEquals("NOT FOUND", errorResponse.getStatus());
        assertEquals("Nenhum produto encontrado.", errorResponse.getMessage());
        // Detalhes devem estar vazios no caso de ProductNotFoundException
        assertNull(errorResponse.getDetails());
    }

    @Test
    public void testRuntimeException() {
        // Configurar o comportamento do mock para lançar uma exceção personalizada
        when(productService.getAllProducts()).thenThrow(new MyRuntimeException("Ocorreu um erro inesperado."));

        // Fazer uma chamada ao endpoint que deve lançar a exceção
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity("/products", ErrorResponse.class);

        // Verificar se o código de status é 500 (INTERNAL SERVER ERROR)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar a estrutura da resposta de erro
        ErrorResponse errorResponse = response.getBody();
        assertEquals(500, errorResponse.getCode());
        assertEquals("INTERNAL SERVER ERROR", errorResponse.getStatus());
        assertEquals("Ocorreu um erro inesperado.", errorResponse.getMessage());
    }
}
