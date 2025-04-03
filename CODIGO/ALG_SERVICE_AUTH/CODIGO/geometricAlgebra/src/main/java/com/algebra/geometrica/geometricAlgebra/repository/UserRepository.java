package com.algebra.geometrica.geometricAlgebra.repository;

import com.algebra.geometrica.geometricAlgebra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


/**
 * Repositorio para realizar las consultas y actualizaciones a la BD
 *
 * @author esteban
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

    @Query( value = "SELECT * FROM users u WHERE u.user_name = :user_name", nativeQuery = true)
    public User findByUserLogin(@Param("user_name") String user_name);
}
