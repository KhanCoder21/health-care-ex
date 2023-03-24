package com.arsad.exception;

import java.io.Serial;

public class AppointmentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AppointmentNotFoundException() {
        super();
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }

}
