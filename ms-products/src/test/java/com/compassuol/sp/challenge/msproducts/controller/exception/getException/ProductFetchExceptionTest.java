package com.compassuol.sp.challenge.msproducts.controller.exception.getException;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductFetchException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFetchExceptionTest {

    @Test
    public void testProductFetchException() {
        String errorMessage = "This is a product fetch exception message";
        ProductFetchException exception = new ProductFetchException(errorMessage);

        assertNotNull(exception);
        assertEquals(500, exception.getCode());
        assertEquals("Internal Server Error", exception.getStatus());
        assertEquals(errorMessage, exception.getMessage());
        assertTrue(exception.getDetails().isEmpty());
    }
}
