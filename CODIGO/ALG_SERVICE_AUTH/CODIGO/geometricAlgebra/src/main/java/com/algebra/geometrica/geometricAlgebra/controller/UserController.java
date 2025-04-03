package com.algebra.geometrica.geometricAlgebra.controller;

import com.algebra.geometrica.geometricAlgebra.DTO.LoginRequest;
import com.algebra.geometrica.geometricAlgebra.entity.User;
import com.algebra.geometrica.geometricAlgebra.service.JwtService;
import com.algebra.geometrica.geometricAlgebra.service.LoginAttemptService;
import com.algebra.geometrica.geometricAlgebra.service.UserService;
import com.algebra.geometrica.geometricAlgebra.utils.AppPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador REST encargado de atender todas las solicitudes de autenticación del LIBGDX
 *
 * @author esteban
 * @since 26/03/2025
 * @version 1.0.0
 *
 */

@RestController
@RequestMapping(AppPaths.Auth.AUTH_PATH)
@Tag(name = "Auth Principal controller")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    /**
     * Realiza la validación para el inicio de sesión del usuario
     * @param user
     * @return
     * @throws Exception
     */

    @Operation(summary = AppPaths.Security.SECURITY_LOGIN_PATH, operationId = "login")
    @PostMapping(value = AppPaths.Auth.AUTH_LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest user) throws Exception{

        if (loginAttemptService.isBlocked(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Demasiados intentos fallidos. Intente más tarde.");
        }

        // Buscar el usuario para ver si ya existe
        User userFind = userService.findByUserName(user);

        // Revisa que exista el usuario y que las contraseñas coincidan
        if (userFind != null && new BCryptPasswordEncoder().matches(user.getPassword(), userFind.getUser_pass())){

            // Crea el token para el usuario
            String tokenUser = jwtService.generateTokenJWT(userFind);
            return ResponseEntity.ok(tokenUser);
        }else{
            loginAttemptService.loginFailed(user.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

    }

    /**
     * Realiza el registro del usuario con todos sus datos en la BD
     * @param user
     * @return
     * @throws Exception
     */
    @Operation(summary = AppPaths.Security.SECURITY_REGISTER_PATH, operationId = "register")
    @PostMapping(value = AppPaths.Auth.AUTH_REGISTER)
    public ResponseEntity<?> register (@RequestBody User user) throws Exception{

        // Buscar el usuario para ver si ya existe
        User userFind = userService.findByUserName(user);

        if(userFind != null){
            return  ResponseEntity.badRequest().body("El usuario ya se encuentra registrado");
        }

        // Encripta la contraseña para ser enviada a la BD
        user.setUser_pass(new BCryptPasswordEncoder().encode(user.getUser_pass()));

        // Crea el usuario nuevo con todos los datos registrados
        userService.save(user);

        return ResponseEntity.ok("Usuario creado correctamente");

    }

    /**
     * Realiza el refresco del token del usuario cuando pasa mucho tiempo
     * @param request
     * @return
     * @throws Exception
     */
    @Operation(summary = AppPaths.Security.SECURITY_REFRESH_PATH, operationId = "refresh")
    @PostMapping(value = AppPaths.Auth.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken (HttpServletRequest request) throws Exception{
        String tokenJWT = jwtService.extractToken(request);

        if(tokenJWT != null && jwtService.validateToken(tokenJWT)){
            String newToken = jwtService.generateTokenJWT(jwtService.extractUserName(tokenJWT));
            return ResponseEntity.ok(newToken);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid o expiration");
    }

}
