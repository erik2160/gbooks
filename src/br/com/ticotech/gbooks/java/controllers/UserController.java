package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.User;
import br.com.ticotech.gbooks.java.repository.UserRepository;

import java.util.Objects;

public class UserController {
    private final UserRepository userRepository;
    private User user;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public int verify(String id, String password){
        if (userRepository.getUser(id) == null){
            return -1;
        }
        else {
            if (Objects.equals(userRepository.getUser(id).getPassword(), password)){
                this.user = userRepository.getUser(id);
                return 1;
            }
            return 0;
        }
    }
    public String getName(){
        return user.getName();
    }

    public int getAccessType() {
        return user.getRole();
    }

    public String getRoleName(){
        if (user.getRole() == 1){
            return "Cashier";
        }
        else return "Manager";
    }
}
