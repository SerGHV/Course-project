package controller;

import model.User;
import repository.UserRepository;

import java.security.MessageDigest;

public class LoginController {

    private UserRepository userRepository;

    public LoginController() {
        userRepository = new UserRepository();
    }

    public User login(String login, String password) {

        String hash = hashPassword(password);

        return userRepository.findByLoginAndPasswordHash(login, hash);
    }

    private String hashPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}