package ru.fidarov.Security.Test.Case;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody AuthRequest request) {
        authService.addUserToCache(request.username(), request.passwordOrHash());
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest request) {
        if (authService.isUserInCache(request.username(), request.passwordOrHash())) {
            return "Да";
        } else {
            return "Нет";
        }
    }
}
