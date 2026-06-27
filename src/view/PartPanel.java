package view;

import controller.PartController;
import model.Part;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PartPanel extends JPanel {

    private PartController controller;
    private User currentUser;

    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton, addButton, deleteButton, updateButton;

    public PartPanel(User user) {
        this.currentUser = user;
        controller = new PartController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Article Number"},
                0
        );
        table = new JTable(model);

        JPanel buttons = new JPanel();
        loadButton = new JButton("Load");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");

        buttons.add(loadButton);
        buttons.add(addButton);
        buttons.add(deleteButton);
        buttons.add(updateButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadData());
        addButton.addActionListener(e -> addPart());
        deleteButton.addActionListener(e -> deletePart());
        updateButton.addActionListener(e -> updatePart());

        String role = currentUser.getRoleName();
        if (role != null) {
            if (role.equalsIgnoreCase("USER")) {
                addButton.setEnabled(false);
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
            } else if (role.equalsIgnoreCase("MANAGER")) {
                deleteButton.setEnabled(false);
            }
        }
    }

    private void loadData() {
        model.setRowCount(0);
        List<Part> list = controller.getAllParts();
        for (Part p : list) {
            model.addRow(new Object[]{
                    p.getPartId(),
                    p.getPartName(),
                    p.getArticleNumber()
            });
        }
    }

    private void addPart() {
        String name = JOptionPane.showInputDialog("Part name");
        if (name == null || name.isBlank()) return;
        String article = JOptionPane.showInputDialog("Article number");
        if (article == null || article.isBlank()) return;

        Part part = new Part();
        part.setPartName(name);
        part.setArticleNumber(article);
        controller.addPart(part);
        loadData();
    }

    private void deletePart() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a part.");
            return;
        }
        int id = (Integer) model.getValueAt(row, 0);
        controller.deletePart(id);
        loadData();
    }

    private void updatePart() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a part.");
            return;
        }
        Part part = new Part();
        part.setPartId((Integer) model.getValueAt(row, 0));
        String name = JOptionPane.showInputDialog("Part name:", model.getValueAt(row, 1));
        if (name == null || name.isBlank()) return;
        String article = JOptionPane.showInputDialog("Article number:", model.getValueAt(row, 2));
        if (article == null || article.isBlank()) return;
        part.setPartName(name);
        part.setArticleNumber(article);
        controller.updatePart(part);
        loadData();
    }
}