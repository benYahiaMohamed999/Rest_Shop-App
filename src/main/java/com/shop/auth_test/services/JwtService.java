package com.shop.auth_test.services;

import com.shop.auth_test.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String Secret_key="e23261a741f184485b7884253ccf0193c3a5864db619903192dd92a112b7c87023f7a3dba29538cf5f88937fbd7af93e867bb09c38c1499c49cac77c22385b1f";//64 bit


    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);

    }

    public boolean isValide(String token, UserDetails user){
        String username=extractUsername(token);
        return (username.equals(user.getUsername()))&& !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {

        return extratExpiration(token).before(new Date());

    }

    private Date extratExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    public <T>T extractClaim(String token, Function<Claims,T>resolver){
        Claims claims=extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(Secret_key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateToken(UserEntity user){
        String token= Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    private SecretKey getSigninKey(){
        byte[] keyByte= Decoders.BASE64URL.decode(Secret_key);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
