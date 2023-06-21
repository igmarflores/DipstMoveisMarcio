package com.trabalhofinal.filme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.trabalhofinal.filme.exception.MovieNotFoundException;
import com.trabalhofinal.filme.model.Movie;
import com.trabalhofinal.filme.repository.MovieRepository;

public class MovieService {
    
    @Autowired
	private MovieRepository movieRepository;
	
	public Movie criarMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public Movie buscarMoviePorId(Long id) throws MovieNotFoundException {
		Optional<Movie> opt = movieRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new MovieNotFoundException("filme com id : " + id + " não existe");
		}
	}

	public List<Movie> buscarTodosMovies() {
		return movieRepository.findAll(); 
	}

	public Movie alterarMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public void apagarMovie(Long id) throws MovieNotFoundException {
		Movie movie = buscarMoviePorId(id);
		movieRepository.delete(movie);
	}

}
