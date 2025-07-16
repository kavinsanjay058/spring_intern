package com.example.Springboot.jwt;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

//@Component - Makes this class a Spring-managed bean, so it can be autowired wherever needed.
@Component
public class JwtTokenProvider {
//    Injects a secret key (from application.properties) used to sign and verify JWT tokens.

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationMilliseconds;

    public Key secretKey(){
//        It converts your secret string (from app.jwt-secret) into a secure cryptographic key that can be used to sign and validate JWT tokens using HMAC SHA algorithms (like HS256).
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());

    }

    //    Generates a signed JWT token for an authenticated user.
    public String generateToken(Authentication authenticate) {
//        Retrieves the logged-in user's details.
        UserDetails userPrincipal = (UserDetails) authenticate.getPrincipal();

//        Then builds a JWT:
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // who the token is about
                .setIssuedAt(new Date()) // who the token is about
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMilliseconds)) // expiry
                .signWith(secretKey() , SignatureAlgorithm.HS256) // signing
                .compact();
    }

    //   To extract the username (subject) from a JWT token — but only after verifying the token is valid and untampered.
    public String getUsernameFromToken(String token) {

//        Jwts.parserBuilder()
//              - This creates a JWT parser builder.
//              - Think of it like setting up a token decoder that will:
//       Verify the signature
//      Parse the claims (data)
//      Reject tampered or expired tokens
        return Jwts.parserBuilder()
//                This sets the key used to verify the signature of the token.
                .setSigningKey(secretKey())
//                This builds the final JWT parser object, ready to use.
                .build()
//                This is where the token is parsed and validated.
                .parseClaimsJws(token)
//                Extracts the payload.
                .getBody()
//                Returns the username (sub in JWT).
                .getSubject();
    }



    //   Checks if the token:
//      - Has a valid signature
//     - Is not expired
//     - Is well-formed
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }
}