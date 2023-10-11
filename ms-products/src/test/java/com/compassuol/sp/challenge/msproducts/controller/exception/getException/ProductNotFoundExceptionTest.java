package com.compassuol.sp.challenge.msproducts.controller.exception.getException;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductNotFoundExceptionTest {

    @Test
    public void testProductNotFoundException() {
        String errorMessage = "This is a product not found exception message";
        ProductNotFoundException exception = new ProductNotFoundException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
