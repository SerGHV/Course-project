package controller;

public class PasswordValidator {

    private static final String PATTERN =
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";

    public static boolean isValid(String password) {
        return password != null && password.matches(PATTERN);
    }
}