package controller;

import model.User;
import repository.UserRepository;

public class LoginController {

    private UserRepository repository;

    public LoginController() {
        repository = new UserRepository();
    }

    public User login(String login, String passwordHash) {
        return repository.findByLoginAndPasswordHash(login, passwordHash);
    }
}