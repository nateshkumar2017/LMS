package com.mlms.config;

import com.mlms.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtFilter  extends OncePerRequestFilter {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtGeneratorValidator jwtgenVal;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
//
//        String token = null;
//        String userName = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7);
//            userName = jwtgenVal.extractUsername(token);
//        }
//
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = userService.loadUserByUsername(userName);
//
//            if (jwtgenVal.validateToken(token, userDetails)) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = jwtgenVal.getAuthenticationToken(token, SecurityContextHolder.getContext().getAuthentication(), userDetails);
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }


    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
         String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtgenVal.extractUsername(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userService.loadUserByUsername(userName);

            if (jwtgenVal.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = jwtgenVal.getAuthenticationToken(token, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
