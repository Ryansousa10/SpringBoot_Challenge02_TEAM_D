package com.compassuol.sp.challenge.msproducts.controller.exception;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ErrorResponse;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductFetchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleProductFetchException() {
        // Crie uma instância de ProductFetchException com os detalhes desejados
        ProductFetchException productFetchException = new ProductFetchException("Mensagem de erro");

        // Chame o método handleProductFetchException do manipulador
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleProductFetchException(productFetchException);

        // Verifique se a resposta HTTP é 500 (Internal Server Error)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        // Verifique se o corpo da resposta contém a mensagem de erro
        assertEquals("Mensagem de erro", responseEntity.getBody().getMessage());

        // Outras verificações, como código, status e detalhes, podem ser adicionadas conforme necessário
    }
}
