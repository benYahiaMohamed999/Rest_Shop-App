package com.shop.auth_test.filter;

import com.shop.auth_test.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull  HttpServletResponse response,
           @NonNull  FilterChain filterChain) throws ServletException, IOException {

         String authHeder=request.getHeader("Authorization");
        if(authHeder==null || !authHeder.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;

        }
        String token=authHeder.substring(7);
        String username= jwtService.extractUsername(token);
        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails1=userDetailsService.loadUserByUsername(username);

            if(jwtService.isValide(token,userDetails1)){
                UsernamePasswordAuthenticationToken authtoken=
                        new UsernamePasswordAuthenticationToken(
                                userDetails1,
                                null,
                                userDetails1.getAuthorities());

                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authtoken);


            }
        }

        filterChain.doFilter(request,response);

    }
}
