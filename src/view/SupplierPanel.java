package view;

import controller.SupplierController;
import model.Supplier;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {

    private SupplierController controller;
    private User currentUser;

    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton, addButton, deleteButton, updateButton;

    public SupplierPanel(User user) {
        this.currentUser = user;
        controller = new SupplierController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Address", "Phone"},
                0
        );
        table = new JTable(model);

        JPanel buttonPanel = new JPanel();
        loadButton = new JButton("Load");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");

        buttonPanel.add(loadButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        loadButton.addActionListener(e -> loadData());
        addButton.addActionListener(e -> addSupplier());
        deleteButton.addActionListener(e -> deleteSupplier());
        updateButton.addActionListener(e -> updateSupplier());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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
        List<Supplier> suppliers = controller.getAllSuppliers();
        for (Supplier s : suppliers) {
            model.addRow(new Object[]{
                    s.getSupplierId(),
                    s.getSupplierName(),
                    s.getAddress(),
                    s.getPhoneNumber()
            });
        }
    }

    private void addSupplier() {
        String name = JOptionPane.showInputDialog(this, "Supplier name:");
        if (name == null || name.isBlank()) return;
        String address = JOptionPane.showInputDialog(this, "Address:");
        if (address == null || address.isBlank()) return;
        String phone = JOptionPane.showInputDialog(this, "Phone:");
        if (phone == null || phone.isBlank()) return;

        Supplier supplier = new Supplier();
        supplier.setSupplierName(name);
        supplier.setAddress(address);
        supplier.setPhoneNumber(phone);
        controller.addSupplier(supplier);
        loadData();
    }

    private void deleteSupplier() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a supplier.");
            return;
        }
        int id = (Integer) model.getValueAt(row, 0);
        int answer = JOptionPane.showConfirmDialog(
                this,
                "Delete selected supplier?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );
        if (answer == JOptionPane.YES_OPTION) {
            controller.deleteSupplier(id);
            loadData();
        }
    }

    private void updateSupplier() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a supplier.");
            return;
        }
        Supplier supplier = new Supplier();
        supplier.setSupplierId((Integer) model.getValueAt(row, 0));

        String name = JOptionPane.showInputDialog("Supplier name:", model.getValueAt(row, 1));
        if (name == null || name.isBlank()) return;
        String address = JOptionPane.showInputDialog("Address:", model.getValueAt(row, 2));
        if (address == null || address.isBlank()) return;
        String phone = JOptionPane.showInputDialog("Phone:", model.getValueAt(row, 3));
        if (phone == null || phone.isBlank()) return;

        supplier.setSupplierName(name);
        supplier.setAddress(address);
        supplier.setPhoneNumber(phone);

        controller.updateSupplier(supplier);
        loadData();
    }
}