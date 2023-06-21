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
import org.springframework.web.bind.annotation.RestController;

import com.trabalhofinal.filme.exception.UserNotFoundException;
import com.trabalhofinal.filme.model.User;
import com.trabalhofinal.filme.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> criarUser(@RequestBody User user) {
        User userGravado = userService.criarUser(user);
        return new ResponseEntity<>(userGravado, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/users/1
    @GetMapping("buscar/{id}")
    public ResponseEntity<User> buscarUserPorId(@PathVariable("id") Long id) throws UserNotFoundException {
        User userGravado = userService.buscarUserPorId(id);
        return new ResponseEntity<>(userGravado, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) throws UserNotFoundException {
        User userGravado = userService.loginUserPorEmail(user);
        return new ResponseEntity<>(userGravado, HttpStatus.OK);
    }

}
