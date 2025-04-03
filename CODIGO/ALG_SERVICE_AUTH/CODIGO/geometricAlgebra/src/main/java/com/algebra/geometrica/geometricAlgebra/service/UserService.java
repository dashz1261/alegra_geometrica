package com.algebra.geometrica.geometricAlgebra.service;

import com.algebra.geometrica.geometricAlgebra.DTO.LoginRequest;
import com.algebra.geometrica.geometricAlgebra.entity.User;
import org.springframework.stereotype.Component;


@Component
public interface UserService {
    /**
     * Busca un usuario por nombre de login
     * @param user
     * @return
     */
    public User findByUserName(LoginRequest user);

    /**
     * Busca un usuario por nombre de login
     * @param user
     * @return
     */
    public User findByUserName(User user);

    /**
     * Guarda un usuario en la BD
     * @param user
     * @return
     */
    public User save(User user);
}
