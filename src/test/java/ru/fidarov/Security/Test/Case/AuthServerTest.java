package ru.fidarov.Security.Test.Case;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthServerTest {

    @MockBean
    private RedisTemplate<String, String> redisTemplate;

    @MockBean
    private ValueOperations<String, String> valueOperations;

    @Test
    public void testAddUserToCache() {
        AuthService authService = new AuthService(redisTemplate);

        String username = "testUser";
        String password = "testPassword";
        String userKey = "user:" + username;

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        authService.addUserToCache(username, password);

        verify(valueOperations, times(1)).set(eq(userKey), eq(password));
    }

    @Test
    public void testIsUserInCache() {
        AuthService authService = new AuthService(redisTemplate);

        String username = "testUser";
        String password = "testPassword";
        String userKey = "user:" + username;

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        when(valueOperations.get(eq(userKey))).thenReturn(password);

        boolean result = authService.isUserInCache(username, password);

        assertTrue(result);
    }
}
