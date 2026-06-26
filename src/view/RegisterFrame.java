package view;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JPanel {

    private JTextField loginField;
    private JTextField fullNameField;
    private JPasswordField passwordField;

    private JButton registerButton;
    private JButton goToLoginButton;

    public RegisterFrame(AuthFrame parent) {

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("  Login:"));
        loginField = new JTextField();
        add(loginField);

        add(new JLabel("  Full Name:"));
        fullNameField = new JTextField();
        add(fullNameField);

        add(new JLabel("  Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerButton = new JButton("Register");
        add(registerButton);

        goToLoginButton = new JButton("Back to Login");
        add(goToLoginButton);

        goToLoginButton.addActionListener(e -> parent.showLogin());
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JTextField getFullNameField() {
        return fullNameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}