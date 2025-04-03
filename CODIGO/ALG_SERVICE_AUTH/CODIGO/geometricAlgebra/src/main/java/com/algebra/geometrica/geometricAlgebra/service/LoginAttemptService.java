package com.algebra.geometrica.geometricAlgebra.service;

import org.springframework.stereotype.Component;


@Component
public interface LoginAttemptService {

    public void loginFailed(String username);

    public boolean isBlocked(String username);
}
