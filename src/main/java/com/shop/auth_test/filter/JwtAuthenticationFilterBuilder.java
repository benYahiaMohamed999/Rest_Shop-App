package com.shop.auth_test.filter;

import com.shop.auth_test.services.JwtService;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtAuthenticationFilterBuilder {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilterBuilder setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
        return this;
    }

    public JwtAuthenticationFilterBuilder setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }

    public JwtAuthenticationFilter createJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }
}