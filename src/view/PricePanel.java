package view;

import controller.PartController;
import controller.PriceController;
import controller.SupplierController;
import model.Part;
import model.Price;
import model.Supplier;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class PricePanel extends JPanel {

    private PriceController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton, addButton, deleteButton, updateButton;
    private JComboBox<Supplier> supplierBox;
    private JComboBox<Part> partBox;

    public PricePanel(User user) {
        controller = new PriceController();
        setLayout(new BorderLayout());

        supplierBox = new JComboBox<>();
        partBox = new JComboBox<>();

        SupplierController sc = new SupplierController();
        PartController pc = new PartController();
        for (Supplier s : sc.getAllSuppliers()) supplierBox.addItem(s);
        for (Part p : pc.getAllParts()) partBox.addItem(p);

        model = new DefaultTableModel(new String[]{"ID", "Supplier", "Part", "Date", "Price"}, 0);
        table = new JTable(model);

        JPanel top = new JPanel();
        top.add(new JLabel("Supplier:"));
        top.add(supplierBox);
        top.add(new JLabel("Part:"));
        top.add(partBox);

        JPanel buttons = new JPanel();
        loadButton = new JButton("Load");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        buttons.add(loadButton);
        buttons.add(addButton);
        buttons.add(deleteButton);
        buttons.add(updateButton);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadData());

        addButton.addActionListener(e -> {
            Supplier s = (Supplier) supplierBox.getSelectedItem();
            Part p = (Part) partBox.getSelectedItem();
            if (s == null || p == null) return;
            String dateStr = JOptionPane.showInputDialog("Date YYYY-MM-DD");
            if (dateStr == null || dateStr.isBlank()) return;
            String priceStr = JOptionPane.showInputDialog("Price");
            if (priceStr == null || priceStr.isBlank()) return;
            Price price = new Price();
            price.setSupplierId(s.getSupplierId());
            price.setPartId(p.getPartId());
            price.setPriceDate(Date.valueOf(dateStr));
            price.setPriceValue(new BigDecimal(priceStr));
            controller.addPrice(price);
            loadData();
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (Integer) model.getValueAt(row, 0);
                controller.deletePrice(id);
                loadData();
            }
        });

        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a price.");
                return;
            }
            Price price = new Price();
            price.setPriceId((Integer) model.getValueAt(row, 0));

            String supplierIdStr = JOptionPane.showInputDialog("Supplier ID:", model.getValueAt(row, 1));
            if (supplierIdStr == null || supplierIdStr.isBlank()) return;
            String partIdStr = JOptionPane.showInputDialog("Part ID:", model.getValueAt(row, 2));
            if (partIdStr == null || partIdStr.isBlank()) return;
            String dateStr = JOptionPane.showInputDialog("Date (YYYY-MM-DD):", model.getValueAt(row, 3));
            if (dateStr == null || dateStr.isBlank()) return;
            String priceStr = JOptionPane.showInputDialog("Price:", model.getValueAt(row, 4));
            if (priceStr == null || priceStr.isBlank()) return;

            price.setSupplierId(Integer.parseInt(supplierIdStr));
            price.setPartId(Integer.parseInt(partIdStr));
            price.setPriceDate(Date.valueOf(dateStr));
            price.setPriceValue(new BigDecimal(priceStr));

            controller.updatePrice(price);
            loadData();
        });
    }

    private void loadData() {
        model.setRowCount(0);
        List<Price> prices = controller.getAllPrices();
        for (Price p : prices) {
            model.addRow(new Object[]{p.getPriceId(), p.getSupplierId(), p.getPartId(), p.getPriceDate(), p.getPriceValue()});
        }
    }
}