package com.trabalhofinal.filme.exception;

import com.trabalhofinal.filme.model.Movie;

public class MovieNotFoundException extends Exception{
    
    private static final long serialVersionUID = 1L;

	public MovieNotFoundException(String message) {
		super(message);
	}
}
