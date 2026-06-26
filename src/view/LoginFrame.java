package view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JPanel {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton goToRegisterButton;

    public LoginFrame(AuthFrame parent) {

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("  Login:"));
        loginField = new JTextField();
        add(loginField);

        add(new JLabel("  Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        goToRegisterButton = new JButton("Register");
        add(goToRegisterButton);

        goToRegisterButton.addActionListener(e -> parent.showRegister());
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}