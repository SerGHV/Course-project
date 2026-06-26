package view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {

        setTitle("Authorization");
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));

        panel.add(new JLabel("Login:"));
        loginField = new JTextField();
        panel.add(loginField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel());

        loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);

        setVisible(true);
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