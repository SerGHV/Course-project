package view;

import controller.DeliveryController;
import controller.SupplierController;
import model.Delivery;
import model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class DeliveryPanel extends JPanel {

    private DeliveryController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton, addButton, deleteButton;
    private JComboBox<Supplier> supplierBox;

    public DeliveryPanel() {
        controller = new DeliveryController();
        setLayout(new BorderLayout());

        // Заполняем ComboBox поставщиками
        supplierBox = new JComboBox<>();
        SupplierController supplierController = new SupplierController();
        for (Supplier s : supplierController.getAllSuppliers()) {
            supplierBox.addItem(s);
        }

        model = new DefaultTableModel(new String[]{"ID", "Supplier", "Date"}, 0);
        table = new JTable(model);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Supplier:"));
        topPanel.add(supplierBox);

        JPanel buttons = new JPanel();
        loadButton = new JButton("Load");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        buttons.add(loadButton);
        buttons.add(addButton);
        buttons.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadData());

        addButton.addActionListener(e -> {
            Supplier selected = (Supplier) supplierBox.getSelectedItem();
            if (selected == null) return;
            String dateStr = JOptionPane.showInputDialog("Date YYYY-MM-DD");
            if (dateStr == null || dateStr.isBlank()) return;
            Delivery delivery = new Delivery();
            delivery.setSupplierId(selected.getSupplierId());
            delivery.setDeliveryDate(Date.valueOf(dateStr));
            controller.addDelivery(delivery);
            loadData();
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (Integer) model.getValueAt(row, 0);
                controller.deleteDelivery(id);
                loadData();
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        List<Delivery> list = controller.getAllDeliveries();
        for (Delivery d : list) {
            model.addRow(new Object[]{d.getDeliveryId(), d.getSupplierId(), d.getDeliveryDate()});
        }
    }
}