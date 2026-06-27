package view;

import model.User;
import javax.swing.*;

public class MainFrame extends JFrame {

    private User currentUser;
    private JTabbedPane tabbedPane;

    public MainFrame(User user) {
        this.currentUser = user;

        setTitle("Spare Parts Database");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu accountMenu = new JMenu("Account");

        JMenuItem profileItem = new JMenuItem("My profile");

        profileItem.addActionListener(e -> new ProfileFrame(currentUser));

        accountMenu.add(profileItem);

        menuBar.add(accountMenu);

        setJMenuBar(menuBar);

        tabbedPane = new JTabbedPane();

        String role = currentUser.getRoleName();
        System.out.println("Роль пользователя: '" + role + "'"); // отладка

        boolean isAdmin = role != null && role.toLowerCase().contains("admin");

        if (isAdmin) {
            tabbedPane.addTab("Suppliers", new SupplierPanel(currentUser));
            tabbedPane.addTab("Parts", new PartPanel(currentUser));
            tabbedPane.addTab("Prices", new PricePanel(currentUser));
            tabbedPane.addTab("Deliveries", new DeliveryPanel(currentUser));
            tabbedPane.addTab("Delivery Items", new DeliveryItemPanel(currentUser));
        } else {
            tabbedPane.addTab("Suppliers", new SupplierPanel(currentUser));
            tabbedPane.addTab("Parts", new PartPanel(currentUser));
        }

        add(tabbedPane);
        setVisible(true);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}