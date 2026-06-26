package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;

    public MainFrame() {

        setTitle("Spare Parts Database");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        add(tabbedPane);

        setVisible(true);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}