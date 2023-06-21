package com.trabalhofinal.filme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhofinal.filme.exception.MovieNotFoundException;
import com.trabalhofinal.filme.exception.UserNotFoundException;
import com.trabalhofinal.filme.model.Movie;
import com.trabalhofinal.filme.model.User;
import com.trabalhofinal.filme.service.MovieService;
import com.trabalhofinal.filme.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @PostMapping("/relacionar")
    public ResponseEntity<Movie> relacionarMovie(@RequestBody Movie movie, @RequestParam("userId") Long userId)
            throws UserNotFoundException {
        User user = userService.buscarUserPorId(userId); // Busca o usuário pelo ID
        movie.getUsers().add(user); // Associa o usuário ao filme
        Movie movieGravado = movieService.criarMovie(movie); // Salva o filme
        return new ResponseEntity<>(movieGravado, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Movie> criarMovie(@RequestBody Movie movie) {
        Movie movieGravado = movieService.criarMovie(movie);
        return new ResponseEntity<>(movieGravado, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/movies/1
    @GetMapping("buscar/{id}")
    public ResponseEntity<Movie> buscarMoviePorId(@PathVariable("id") Long id) throws MovieNotFoundException {
        Movie movieGravado = movieService.buscarMoviePorId(id);
        return new ResponseEntity<>(movieGravado, HttpStatus.OK);
    }

    // http://localhost:8080/api/movies
    @GetMapping
    public ResponseEntity<List<Movie>> buscarTodosMovies() {
        List<Movie> movies = movieService.buscarTodosMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // http://localhost:8080/api/movies/1
    @PutMapping("{id}")
    public ResponseEntity<Movie> alterarMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        movie.setId(id);
        Movie movieAlterado = movieService.alterarMovie(movie);
        return new ResponseEntity<>(movieAlterado, HttpStatus.OK);
    }

    // http://localhost:8080/api/movies/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> removerMovie(@PathVariable("id") Long id) throws MovieNotFoundException {
        movieService.apagarMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
