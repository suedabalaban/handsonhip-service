package com.handsonhip.config;

import com.handsonhip.service.JwtService;
import com.handsonhip.service.MemberService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberService memberService;

    public JwtAuthenticationFilter(JwtService jwtService, MemberService userService) {
        this.jwtService = jwtService;
        this.memberService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = null;
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (token != null && jwtService.validateToken(token, memberService.loadUserByUsername(jwtService.extractUsername(token)))) {
            UserDetails userDetails = memberService.loadUserByUsername(jwtService.extractUsername(token));
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );
        }

        filterChain.doFilter(request, response);
    }
}
