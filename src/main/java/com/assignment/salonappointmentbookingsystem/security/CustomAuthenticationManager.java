package com.assignment.salonappointmentbookingsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static org.apache.naming.ResourceRef.AUTH;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomUserDetailService customUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getName() != null && authentication.getCredentials() != null) {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(authentication.getName());
            return new UsernamePasswordAuthenticationToken(userDetails, AUTH);
        }
        throw new BadCredentialsException("Invalid credentials");
    }
}
