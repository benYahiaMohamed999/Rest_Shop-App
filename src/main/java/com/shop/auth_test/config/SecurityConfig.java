package com.shop.auth_test.config;

import com.shop.auth_test.filter.JwtAuthenticationFilter;
import com.shop.auth_test.services.userDetailsImpl;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final userDetailsImpl userDetailsImpl;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(userDetailsImpl userDetailsImpl,
                          JwtAuthenticationFilter jwtAuthenticationFilter
                         ) {
        this.userDetailsImpl = userDetailsImpl;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{


        /*return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req->req
                        .requestMatchers("/login/**","/register/**")
                        .permitAll()
                        .requestMatchers("/only_admin/**")
                        .hasAnyAuthority("ADMIN")
                        .anyRequest()
                        .authenticated()
                ).userDetailsService(
                        userDetailsImpl//tok IMPl
                ).sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();*/






        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(req->req
                .requestMatchers("/Authentication").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/api/V1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()


                .requestMatchers("/register").permitAll()

                .requestMatchers("/login").permitAll()


                .requestMatchers("/only_admin").hasAnyAuthority("USER")
                .requestMatchers("/demo").permitAll()
                .anyRequest().authenticated()

        );
        http.sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);


       return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();

    }
}
