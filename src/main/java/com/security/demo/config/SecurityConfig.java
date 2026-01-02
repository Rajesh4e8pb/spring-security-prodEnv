
package com.security.demo.config;

import com.security.demo.auth.JwtAuthenticationFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
 @Bean
 SecurityFilterChain filterChain(HttpSecurity http,
                                 JwtAuthenticationFilter jwtFilter) throws Exception {

     http
             // Disable CSRF for H2 console
             .csrf(csrf -> csrf.disable())

             // Allow H2 console frames
             .headers(headers ->
                     headers.frameOptions(frame -> frame.disable())
             )

             .authorizeHttpRequests(auth -> auth
                     // ✅ Allow H2 console
                     .requestMatchers("/h2-console/**").permitAll()

                     // ✅ Allow login API
                     .requestMatchers("/auth/login").permitAll()

                     // All other requests need authentication
                     .anyRequest().authenticated()
             )

             // JWT filter
             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

     return http.build();
 }
 @Bean PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
 }
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config,
            DaoAuthenticationProvider provider) throws Exception {

        AuthenticationManager manager = config.getAuthenticationManager();
        return manager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
