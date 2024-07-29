package com.onboarding.exceptio_handling.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
