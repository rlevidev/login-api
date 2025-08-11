package com.rlevi.login_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rlevi.login_api.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

    /*
     * Busca usuários pelo email.
     * "Optional" lida com a possibilidade de não tem email no banco de dados.
     */
    Optional<Login> findByEmail(String email);

    /*
     * Verifica se já existe usuário com esse email no banco de dados.
     */
    boolean existsByEmail(String email);
}
