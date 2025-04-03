package com.algebra.geometrica.geometricAlgebra.serviceImpl;

import com.algebra.geometrica.geometricAlgebra.entity.User;
import com.algebra.geometrica.geometricAlgebra.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private Key key;
    private final long EXPIRATION_TIME = 86400000; // 24 horas

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    /**
     * Genera el token JWT del usuario
     * @param user
     * @return
     */
    @Override
    public String generateTokenJWT (User user){
        return Jwts.builder()
                .setSubject(user.getUser_name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateTokenJWT(String user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    /**
     * Valida el token generado para el usuario
     * @param tokenJWT
     * @return
     */
    @Override
    public boolean validateToken (String tokenJWT){
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(tokenJWT);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    /**
     * extrae el nombre o login del usuario al cual se le genero el token
     * @param tokenName
     * @return
     */
    @Override
    public String extractUserName(String tokenName){
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(tokenName)
                .getBody()
                .getSubject();
    }

    @Override
    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    @Override
    public Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

}