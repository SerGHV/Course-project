import controller.LoginController;
import model.User;

import view.LoginFrame;
import view.MainFrame;
import view.SupplierPanel;
import view.PartPanel;
import view.PricePanel;
import view.DeliveryPanel;
import view.DeliveryItemPanel;

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

                JOptionPane.showMessageDialog(frame, "Success!");

                frame.dispose();

                MainFrame mainFrame = new MainFrame();

                mainFrame.getTabbedPane().addTab("Suppliers", new SupplierPanel());
                mainFrame.getTabbedPane().addTab("Parts", new PartPanel());
                mainFrame.getTabbedPane().addTab("Prices", new PricePanel());
                mainFrame.getTabbedPane().addTab("Deliveries", new DeliveryPanel());
                mainFrame.getTabbedPane().addTab("Delivery Items", new DeliveryItemPanel());

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid login or password",
                        "Authorization",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}