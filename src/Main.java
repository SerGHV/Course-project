import view.LoginFrame;
import view.MainFrame;
import view.SupplierPanel;
import view.PartPanel;

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

                frame.dispose();

                MainFrame mainFrame = new MainFrame();

                mainFrame.getTabbedPane().addTab("Suppliers", new SupplierPanel());
                mainFrame.getTabbedPane().addTab("Parts", new PartPanel());
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid login or password");
            }
        });
    }
}