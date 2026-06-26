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

    public User getUserById(int id) {
        return repository.findById(id);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void deleteUser(int id) {
        repository.delete(id);
    }
}