package controller;

import model.User;
import repository.UserRepository;

import java.security.MessageDigest;

public class RegisterController {

    private UserRepository repository;

    public RegisterController() {
        repository = new UserRepository();
    }

    public boolean register(String login, String fullName, String password) {

        if (!PasswordValidator.isValid(password)) {
            return false;
        }

        String hash = hashPassword(password);

        User user = new User();
        user.setLogin(login);
        user.setFullName(fullName);
        user.setPasswordHash(hash);
        user.setRoleName("USER");

        repository.save(user);

        return true;
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