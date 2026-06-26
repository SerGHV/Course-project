package controller;

import model.User;
import repository.UserRepository;

import java.util.List;

public class UserController {

    private UserRepository repository;

    public UserController() {
        repository = new UserRepository();
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}