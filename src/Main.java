import view.AuthFrame;
import controller.LoginController;
import controller.RegisterController;
import model.User;
import view.MainFrame;
import database.DBConnection;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        AuthFrame auth = new AuthFrame();

        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();

        auth.getLoginPanel().getLoginButton().addActionListener(e -> {

            String login = auth.getLoginPanel().getLoginField().getText();
            String password = new String(auth.getLoginPanel().getPasswordField().getPassword());

            User user = loginController.login(login, password);

            if (user != null) {

                JOptionPane.showMessageDialog(null, "Welcome, " + user.getFullName() + "!");

                auth.dispose();

                MainFrame mainFrame = new MainFrame(user);

                mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        DBConnection.getInstance().closeConnection();
                    }
                });

            } else {
                JOptionPane.showMessageDialog(null, "Invalid login or password");
            }
        });

        auth.getRegisterPanel().getRegisterButton().addActionListener(e -> {

            String login = auth.getRegisterPanel().getLoginField().getText();
            String fullName = auth.getRegisterPanel().getFullNameField().getText();
            String password = new String(auth.getRegisterPanel().getPasswordField().getPassword());

            boolean ok = registerController.register(login, fullName, password);

            if (ok) {
                JOptionPane.showMessageDialog(null, "Registered successfully!");
                auth.showLogin();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Weak password!\nMin 8 chars, 1 digit, 1 uppercase, 1 special char");
            }
        });
    }
}