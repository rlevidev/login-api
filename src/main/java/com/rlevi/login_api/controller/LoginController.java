package com.rlevi.login_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rlevi.login_api.model.Login;
import com.rlevi.login_api.service.LoginService;


@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Login> register(@RequestBody Login login) {
        Login newUser = service.registerUser(
            login.getEmail(),
            login.getName(),
            login.getPass()
        );
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        try {
            Login user = service.autenticatorUser(
                login.getEmail(),
                login.getPass()
            );

            return ResponseEntity.ok("Login realizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
