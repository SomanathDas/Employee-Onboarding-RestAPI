package com.onboarding.security;

import com.onboarding.entity.User;
import com.onboarding.exceptio_handling.exceptions.InvalidPasswordException;
import com.onboarding.exceptio_handling.exceptions.InvalidRoleException;
import com.onboarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        System.out.println("username: "+username+" password: "+password);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password for username: " + username);
        }

        if (!user.getRoles().name().equals("EMPLOYEE") && !user.getRoles().name().equals("HR")) {
            throw new InvalidRoleException("Invalid role for username: " + username);
        }

        return new UsernamePasswordAuthenticationToken(user, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRoles().name())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
