package com.trabalhofinal.filme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhofinal.filme.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
