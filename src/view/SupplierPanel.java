package view;

import controller.SupplierController;
import model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {

    private SupplierController controller;

    private JTable table;
    private DefaultTableModel model;

    private JButton loadButton;
    private JButton addButton;
    private JButton deleteButton;

    public SupplierPanel() {

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

        buttonPanel.add(loadButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        loadButton.addActionListener(e -> loadData());

        addButton.addActionListener(e -> addSupplier());

        deleteButton.addActionListener(e -> deleteSupplier());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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

        String name =
                JOptionPane.showInputDialog(this, "Supplier name:");

        if (name == null || name.isBlank())
            return;

        String address =
                JOptionPane.showInputDialog(this, "Address:");

        if (address == null || address.isBlank())
            return;

        String phone =
                JOptionPane.showInputDialog(this, "Phone:");

        if (phone == null || phone.isBlank())
            return;

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

            JOptionPane.showMessageDialog(
                    this,
                    "Select a supplier."
            );

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
}