package com.algebra.geometrica.geometricAlgebra.serviceImpl;

import com.algebra.geometrica.geometricAlgebra.service.LoginAttemptService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private final long LOCK_TIME = TimeUnit.MINUTES.toMillis(5); // Bloqueo de 5 minutos

    private final Map<String, Integer> attempts = new HashMap<>();
    private final Map<String, Long> lockTime = new HashMap<>();

    @Override
    public void loginFailed(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        int MAX_ATTEMPTS = 5;
        if (attempts.get(username) >= MAX_ATTEMPTS) {
            lockTime.put(username, System.currentTimeMillis());
        }
    }

    @Override
    public boolean isBlocked(String username) {

        if (!lockTime.containsKey(username)) return false;
        if (System.currentTimeMillis() - lockTime.get(username) > LOCK_TIME) {
            attempts.remove(username);
            lockTime.remove(username);
            return false;
        }
        return true;
    }
}
