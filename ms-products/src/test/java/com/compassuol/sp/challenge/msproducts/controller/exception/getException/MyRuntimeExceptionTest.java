package com.compassuol.sp.challenge.msproducts.controller.exception.getException;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.MyRuntimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyRuntimeExceptionTest {

    @Test
    public void testMyRuntimeExceptionWithMessage() {
        String errorMessage = "This is a custom runtime exception";
        MyRuntimeException exception = assertThrows(MyRuntimeException.class, () -> {
            throw new MyRuntimeException(errorMessage);
        });

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
