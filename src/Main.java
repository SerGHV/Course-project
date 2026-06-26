import view.AuthFrame;
import controller.LoginController;
import controller.RegisterController;
import model.User;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        AuthFrame auth = new AuthFrame();

        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();

        // LOGIN
        auth.getLoginPanel().getLoginButton().addActionListener(e -> {

            String login = auth.getLoginPanel().getLoginField().getText();
            String password = new String(auth.getLoginPanel().getPasswordField().getPassword());

            User user = loginController.login(login, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Success!");
                auth.dispose();
                // дальше MainFrame
            } else {
                JOptionPane.showMessageDialog(null, "Invalid login/password");
            }
        });

        // REGISTER
        auth.getRegisterPanel().getRegisterButton().addActionListener(e -> {

            String login = auth.getRegisterPanel().getLoginField().getText();
            String fullName = auth.getRegisterPanel().getFullNameField().getText();
            String password = new String(auth.getRegisterPanel().getPasswordField().getPassword());

            boolean ok = registerController.register(login, fullName, password);

            if (ok) {
                JOptionPane.showMessageDialog(null, "Registered!");
                auth.showLogin();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Weak password!");
            }
        });
    }
}