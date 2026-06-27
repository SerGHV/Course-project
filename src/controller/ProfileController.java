package controller;

import model.User;
import repository.UserRepository;

import java.security.MessageDigest;

public class ProfileController {

    private UserRepository repository;

    public ProfileController() {
        repository = new UserRepository();
    }

    public boolean updateProfile(User user,
                                 String login,
                                 String fullName,
                                 String password) {

        if (!PasswordValidator.isValid(password)) {
            return false;
        }

        user.setLogin(login);
        user.setFullName(fullName);
        user.setPasswordHash(hash(password));

        repository.update(user);

        return true;
    }

    private String hash(String password) {

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