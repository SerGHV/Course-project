package view;

import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel container;

    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;

    public AuthFrame() {

        setTitle("Authorization System");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        loginFrame = new LoginFrame(this);
        registerFrame = new RegisterFrame(this);

        container.add(loginFrame, "login");
        container.add(registerFrame, "register");

        add(container);

        cardLayout.show(container, "login");

        setVisible(true);
    }

    public void showLogin() {
        cardLayout.show(container, "login");
    }

    public void showRegister() {
        cardLayout.show(container, "register");
    }

    public LoginFrame getLoginPanel() {
        return loginFrame;
    }

    public RegisterFrame getRegisterPanel() {
        return registerFrame;
    }
}