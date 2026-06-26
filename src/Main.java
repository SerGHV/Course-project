import view.LoginFrame;
import controller.LoginController;
import model.User;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        LoginFrame frame = new LoginFrame();
        LoginController controller = new LoginController();

        frame.getLoginButton().addActionListener(e -> {

            String login = frame.getLoginField().getText();
            String password = new String(frame.getPasswordField().getPassword());

            User user = controller.login(login, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Success!");

                frame.dispose(); // закрыть login

                new view.MainFrame(); // открыть главное окно
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid login or password");
            }
        });
    }
}