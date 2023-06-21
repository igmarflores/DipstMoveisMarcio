package com.trabalhofinal.filme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhofinal.filme.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);
}
