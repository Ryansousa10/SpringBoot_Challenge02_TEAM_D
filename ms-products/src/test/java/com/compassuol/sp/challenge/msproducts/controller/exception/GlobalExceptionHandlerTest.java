package com.compassuol.sp.challenge.msproducts.controller.exception;

import com.compassuol.sp.challenge.msproducts.controller.exception.GlobalExceptionHandler;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductFetchException;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductNotFoundException;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @Test
    public void testHandleProductNotFoundException() {
        // Cria uma instância de GlobalExceptionHandler
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        // Cria uma instância de ProductNotFoundException para simular a exceção
        ProductNotFoundException productNotFoundException = new ProductNotFoundException("Test not found message");

        // Chama o método handleProductNotFoundException
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleProductNotFoundException(productNotFoundException);

        // Verifica o código de status da resposta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        // Verifica o corpo da resposta
        ErrorResponse errorResponse = response.getBody();
        assertEquals(404, errorResponse.getCode());
        assertEquals("NOT FOUND", errorResponse.getStatus());
        assertEquals("Test not found message", errorResponse.getMessage());
        assertEquals(null, errorResponse.getDetails());
    }

    @Test
    public void testHandleProductFetchException() {
        // Cria uma instância de GlobalExceptionHandler
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        // Cria uma instância de ProductFetchException para simular a exceção
        ProductFetchException productFetchException = new ProductFetchException("Test fetch error");

        // Chama o método handleProductFetchException
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleProductFetchException(productFetchException);

        // Verifica o código de status da resposta
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verifica o corpo da resposta
        ErrorResponse errorResponse = response.getBody();
        assertEquals(500, errorResponse.getCode());
        assertEquals("Internal Server Error", errorResponse.getStatus());
        assertEquals("Test fetch error", errorResponse.getMessage());
        assertTrue(errorResponse.getDetails().isEmpty());
    }

    @Test
    public void testHandleRuntimeException() {
        // Crie uma instância de GlobalExceptionHandler
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        // Crie uma instância de RuntimeException para simular a exceção
        RuntimeException runtimeException = new RuntimeException("Test runtime error");

        // Chame o método handleRuntimeException
        ErrorResponse errorResponse = globalExceptionHandler.handleRuntimeException(runtimeException);

        // Verifique o código de status da resposta
        assertEquals(500, errorResponse.getCode());
        assertEquals("INTERNAL SERVER ERROR", errorResponse.getStatus());
        assertEquals("Test runtime error", errorResponse.getMessage());
        // Certifique-se de que os detalhes são nulos (ou seja, não contém detalhes)
        assertEquals(null, errorResponse.getDetails());
    }
}