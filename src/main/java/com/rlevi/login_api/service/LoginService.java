package com.rlevi.login_api.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rlevi.login_api.model.Login;
import com.rlevi.login_api.repository.LoginRepository;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Login registerUser(String email, String name, String pass) {
        if (loginRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado");
        }

        String passEncrypt = passwordEncoder.encode(pass);
        Login user = new Login(email, name, passEncrypt);

        return loginRepository.save(user);
    }

    public Login autenticatorUser(String email, String pass){
        Optional<Login> userOpt = loginRepository.findByEmail(email);

        if (userOpt.isEmpty()){
            throw new RuntimeException("Usuário não encontrado.");
        }

        Login user = userOpt.get();

        /*
         * "matches" -> Verifica se a senha digitada corresponde à senha salva no banco
         * "!" quer dizer não 
         * Então: "!passwordEncoder.matches(...)" -> "Se a senha digitada não bate com a senha salva no banco..."
         */
        if (!passwordEncoder.matches(pass, user.getPass())) {
            throw new RuntimeException("Senha ou email incorreto.");
        }

        return user;
    }
}
