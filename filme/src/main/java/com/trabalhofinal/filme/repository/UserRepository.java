package com.trabalhofinal.filme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhofinal.filme.model.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
