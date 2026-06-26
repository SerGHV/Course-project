package controller;

import model.User;
import repository.UserRepository;

import java.security.MessageDigest;

public class LoginController {

    private UserRepository repository;

    public LoginController() {
        repository = new UserRepository();
    }

    public User login(String login, String password) {

        String hash = hashPassword(password);

        return repository.findByLoginAndPasswordHash(login, hash);
    }

    private String hashPassword(String password) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");

            byte[] bytes =
                    md.digest(password.getBytes());

            StringBuilder builder = new StringBuilder();

            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }

            return builder.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}