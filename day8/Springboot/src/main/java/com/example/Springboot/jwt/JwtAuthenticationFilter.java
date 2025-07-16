package com.example.Springboot.jwt;

import com.example.Springboot.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//OncePerRequestFilter: Ensures this filter runs once per HTTP request.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
//  Used to load user details from the database (via username).
    CustomUserDetailsService userDetailsService;

    @Autowired
//   Used to extract & validate JWT.
    JwtTokenProvider jwtTokenProvider;

    @Override
//    This is the method that Spring Security automatically calls for every request.
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//         A Servlet receives that request, processes it, and returns the response (HTML, JSON, etc.).


//        Step 1: Get the Authorization header
//          Tries to get the Authorization header from the HTTP request.
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
//        looks like ,-> Authorization: Bearer eyJhbGciOiJIUzI1NiIs...


//        Step 2: Extract token & username
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7); // // Removes "Bearer
            username = jwtTokenProvider.getUsernameFromToken(token);
        }


//        Step 3: If username exists & not already authenticated
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

//            Loads user (from DB) using your CustomUserDetailsService
//              This contains their:
//              - Username
//              - Password (hashed)
//              - Roles → Authorities
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


//                JWT is not expired
//                JWT has correct signature
            if(jwtTokenProvider.validateToken(token)){
//              Creates an Authentication object using user details
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

//        Passes the request to the next filter in the chain
//        If authentication is successful, the request reaches the controller
        filterChain.doFilter(request,response);
    }
}

//If you skip this, Spring won’t know who the user is, and all secured endpoints will return 401 Unauthorized.