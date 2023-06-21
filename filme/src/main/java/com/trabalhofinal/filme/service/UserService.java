package com.trabalhofinal.filme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.trabalhofinal.filme.exception.UserNotFoundException;
import com.trabalhofinal.filme.model.User;
import com.trabalhofinal.filme.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User criarUser(User user) {
        return userRepository.save(user);
    }

    public User buscarUserPorId(Long id) throws UserNotFoundException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new UserNotFoundException("user com id : " + id + " não existe");
        }
    }

    public List<User> buscarTodosUsers() {
        return userRepository.findAll();
    }

    public User loginUserPorEmail(User user) throws UserNotFoundException {
        Optional<User> opt = userRepository.findByEmail(user.getEmail());
        if (opt.isPresent()) {
            if (user.getSenha() == opt.get().getSenha()){
                return opt.get();
            }
            return null;
        } else {
            throw new UserNotFoundException("user com email : " + user.getEmail() + " não existe");
        }
    }

}
