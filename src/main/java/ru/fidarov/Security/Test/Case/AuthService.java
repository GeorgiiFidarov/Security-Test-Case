package ru.fidarov.Security.Test.Case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String USER_KEY_PREFIX = "user:";

    @Autowired
    public AuthService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addUserToCache(String username, String password) {
        String userKey = USER_KEY_PREFIX + username;
        redisTemplate.opsForValue().set(userKey, password);
        redisTemplate.expire(userKey, 1, TimeUnit.HOURS); // Установите срок действия сессии
    }

    public boolean isUserInCache(String username, String passwordOrHash) {
        String userKey = USER_KEY_PREFIX + username;
        String storedPassword = redisTemplate.opsForValue().get(userKey);
        return storedPassword != null && storedPassword.equals(passwordOrHash);
    }
}
