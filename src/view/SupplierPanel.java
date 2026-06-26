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

    public SupplierPanel() {

        controller = new SupplierController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Address", "Phone"}, 0
        );

        table = new JTable(model);

        loadButton = new JButton("Load suppliers");

        loadButton.addActionListener(e -> loadData());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
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
}