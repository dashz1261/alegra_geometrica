package com.algebra.geometrica.geometricAlgebra.service;

import com.algebra.geometrica.geometricAlgebra.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public interface JwtService {

    /**
     * Genera el token JWT del usuario
     * @param user
     * @return
     */
    public String generateTokenJWT (User user);

    /**
     * Genera el token JWT del usuario
     * @param user
     * @return
     */
    public String generateTokenJWT (String user);

    /**
     * Valida el token generado para el usuario
     * @param tokenJWT
     * @return
     */
    public boolean validateToken (String tokenJWT);

    /**
     * extrae el nombre o login del usuario al cual se le genero el token
     * @param tokenName
     * @return
     */
    public String extractUserName(String tokenName);

    /**
     * extrae el token para refrescar el usuario
     * @param request
     * @return
     */
    public String extractToken(HttpServletRequest request);

    /**
     * extrae la fecha de expiración del token
     * @param token
     * @return
     */
    public Date extractExpiration(String token);

}
