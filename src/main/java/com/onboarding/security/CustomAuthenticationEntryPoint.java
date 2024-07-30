package com.onboarding.security;

import com.onboarding.exceptio_handling.exceptions.InvalidPasswordException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        System.out.println("Exception: " + authException);

        if (authException instanceof UsernameNotFoundException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Username not found\"}");
        } else if (authException instanceof InvalidPasswordException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Password incorrect\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"status\": 403, \"error\": \"Forbidden\", \"message\": \"Authentication failed\"}");
        }
    }
}
