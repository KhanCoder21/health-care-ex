package com.arsad.exception;

import java.io.Serial;

public class SpecializationNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public SpecializationNotFoundException() {
		super();
	}

	public SpecializationNotFoundException(String message) {
		super(message);
	}

}
