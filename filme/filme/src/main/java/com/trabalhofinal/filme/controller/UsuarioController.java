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

import com.trabalhofinal.filme.exception.UsuarioNotFoundException;
import com.trabalhofinal.filme.model.Usuario;
import com.trabalhofinal.filme.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @PostMapping
    public ResponseEntity<Usuario> criarUser(@RequestBody Usuario user) {
        Usuario userGravado = userService.criarUser(user);
        return new ResponseEntity<>(userGravado, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/users/1
    @GetMapping("buscar/{id}")
    public ResponseEntity<Usuario> buscarUserPorId(@PathVariable("id") Long id) throws UsuarioNotFoundException {
        Usuario userGravado = userService.buscarUserPorId(id);
        return new ResponseEntity<>(userGravado, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> loginUser(@RequestBody Usuario user) throws UsuarioNotFoundException {
        Usuario userGravado = userService.loginUserPorEmail(user);
        return new ResponseEntity<>(userGravado, HttpStatus.OK);
    }

}
