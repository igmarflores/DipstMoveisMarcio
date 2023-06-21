package com.trabalhofinal.filme.exception;

import com.trabalhofinal.filme.model.User;

public class UserNotFoundException extends Exception{
    
    private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
