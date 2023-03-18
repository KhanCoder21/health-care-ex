package com.arsad.exception;

/* Created by Arsad on 2023-03-18 02:44 */
public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException() {
    }

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
