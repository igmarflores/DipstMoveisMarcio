package com.trabalhofinal.filme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhofinal.filme.exception.UsuarioNotFoundException;
import com.trabalhofinal.filme.model.Usuario;
import com.trabalhofinal.filme.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    public Usuario criarUser(Usuario user) {
        return userRepository.save(user);
    }

    public Usuario buscarUserPorId(Long id) throws UsuarioNotFoundException {
        Optional<Usuario> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new UsuarioNotFoundException("user com id : " + id + " não existe");
        }
    }

    public List<Usuario> buscarTodosUsers() {
        return userRepository.findAll();
    }

    public Usuario loginUserPorEmail(Usuario user) throws UsuarioNotFoundException {
        Optional<Usuario> opt = userRepository.findByEmail(user.getEmail());
        if (opt.isPresent()) {
            if (user.getSenha() == opt.get().getSenha()) {
                return opt.get();
            }
            return null;
        } else {
            throw new UsuarioNotFoundException("user com email : " + user.getEmail() + " não existe");
        }
    }

}
