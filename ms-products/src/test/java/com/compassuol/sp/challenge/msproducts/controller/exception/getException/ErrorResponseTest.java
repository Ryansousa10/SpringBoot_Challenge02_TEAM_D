package com.compassuol.sp.challenge.msproducts.controller.exception.getException;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class ErrorResponseTest {

    private ErrorResponse errorResponse;

    @BeforeEach
    public void setUp() {
        errorResponse = new ErrorResponse(404, "NOT FOUND", "Resource not found", Arrays.asList("Details 1", "Details 2"));
    }

    @Test
    public void testErrorResponseConstructor() {
        assertNotNull(errorResponse);
        assertEquals(404, errorResponse.getCode());
        assertEquals("NOT FOUND", errorResponse.getStatus());
        assertEquals("Resource not found", errorResponse.getMessage());

        List<String> details = errorResponse.getDetails();
        assertNotNull(details);
        assertEquals(2, details.size());
        assertEquals("Details 1", details.get(0));
        assertEquals("Details 2", details.get(1));
    }

    @Test
    public void testDefaultConstructor() {
        ErrorResponse defaultErrorResponse = new ErrorResponse();
        assertNotNull(defaultErrorResponse);
        assertEquals(0, defaultErrorResponse.getCode());
        assertNull(defaultErrorResponse.getStatus());
        assertNull(defaultErrorResponse.getMessage());
        assertNull(defaultErrorResponse.getDetails());
    }
}

