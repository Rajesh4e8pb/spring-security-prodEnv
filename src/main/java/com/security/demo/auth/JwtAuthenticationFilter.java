package com.security.demo.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwt;

    public JwtAuthenticationFilter(JwtUtil jwt) {
        this.jwt = jwt;
    }

    // 🔥 THIS IS THE MISSING PART
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getRequestURI();

        return path.startsWith("/h2-console")
                || path.startsWith("/auth/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest r,
                                    HttpServletResponse s,
                                    FilterChain c)
            throws ServletException, IOException {

        String h = r.getHeader("Authorization");

        if (h != null && h.startsWith("Bearer ")) {

            String t = h.substring(7);

            var auth = new UsernamePasswordAuthenticationToken(
                    jwt.extractUsername(t),
                    null,
                    jwt.extractRoles(t)
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList()
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(auth);
        }

        c.doFilter(r, s);
    }
}
