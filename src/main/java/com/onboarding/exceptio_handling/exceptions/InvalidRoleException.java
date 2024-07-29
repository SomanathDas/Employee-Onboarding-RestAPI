package com.onboarding.exceptio_handling.exceptions;

import org.springframework.security.access.AccessDeniedException;

public class InvalidRoleException extends AccessDeniedException {
    public InvalidRoleException(String message) {
        super(message);
    }
}
