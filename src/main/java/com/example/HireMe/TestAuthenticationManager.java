package com.example.HireMe;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class TestAuthenticationManager implements AuthenticationManager {
    private final UserDetailsService userDetailsService;


    public TestAuthenticationManager(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticate(Authentication authentication){
        String principal = (String) authentication.getPrincipal();
        UserDetails x = userDetailsService.loadUserByUsername(principal);
         PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken = new PreAuthenticatedAuthenticationToken(x,"test");
         return preAuthenticatedAuthenticationToken;
    }
}
