package com.fundallassessment.app.configurations;


import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final   JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String email;
        final String jwtToken;
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        email = jwtUtils.extractUsername(jwtToken);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(()-> new RuntimeException("User does not exist"));
            UserPrincipal userPrincipal = new UserPrincipal(user);
            final boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, user.getEmail());
            if (isTokenValid) {
                UsernamePasswordAuthenticationToken authToken =                        new UsernamePasswordAuthenticationToken(
                        userPrincipal.getUsername(), null, userPrincipal.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new JwtException("Token is not valid");
            }
        }
        filterChain.doFilter(request, response);
    }
}
