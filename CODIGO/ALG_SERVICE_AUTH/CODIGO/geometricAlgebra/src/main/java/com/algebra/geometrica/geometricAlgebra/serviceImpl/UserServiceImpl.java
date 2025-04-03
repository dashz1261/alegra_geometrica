package com.algebra.geometrica.geometricAlgebra.serviceImpl;

import com.algebra.geometrica.geometricAlgebra.DTO.LoginRequest;
import com.algebra.geometrica.geometricAlgebra.entity.User;
import com.algebra.geometrica.geometricAlgebra.repository.UserRepository;
import com.algebra.geometrica.geometricAlgebra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Busca un usuario por nombre de login
     * @param user
     * @return
     */
    @Override
    public User findByUserName(LoginRequest user) {

        return userRepository.findByUserLogin(user.getUsername());
    }

    @Override
    public User findByUserName(User user) {
        return userRepository.findByUserLogin(user.getUser_name());
    }

    /**
     * Guarda un usuario en la BD
     * @param user
     * @return
     */
    @Override
    public User save(User user) {

        return userRepository.save(user);
    }
}
