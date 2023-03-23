package com.arsad.exception;

import java.io.Serial;

public class PatientNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PatientNotFoundException() {
        super();
    }

    public PatientNotFoundException(String message) {
        super(message);
    }

}
