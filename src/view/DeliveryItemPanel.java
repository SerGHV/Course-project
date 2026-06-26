package view;

import controller.DeliveryController;
import controller.DeliveryItemController;
import controller.PartController;
import controller.PriceController;
import model.Delivery;
import model.DeliveryItem;
import model.Part;
import model.Price;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DeliveryItemPanel extends JPanel {

    private DeliveryItemController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton, addButton, deleteButton;
    private JComboBox<Delivery> deliveryBox;
    private JComboBox<Price> priceBox;
    private JComboBox<Part> partBox;

    public DeliveryItemPanel() {
        controller = new DeliveryItemController();
        setLayout(new BorderLayout());

        deliveryBox = new JComboBox<>();
        priceBox = new JComboBox<>();
        partBox = new JComboBox<>();

        DeliveryController dc = new DeliveryController();
        PriceController pc = new PriceController();
        PartController partC = new PartController();
        for (Delivery d : dc.getAllDeliveries()) deliveryBox.addItem(d);
        for (Price p : pc.getAllPrices()) priceBox.addItem(p);
        for (Part p : partC.getAllParts()) partBox.addItem(p);

        model = new DefaultTableModel(new String[]{"ID", "Delivery", "Price", "Part", "Quantity"}, 0);
        table = new JTable(model);

        JPanel top = new JPanel();
        top.add(new JLabel("Delivery:"));
        top.add(deliveryBox);
        top.add(new JLabel("Price:"));
        top.add(priceBox);
        top.add(new JLabel("Part:"));
        top.add(partBox);

        JPanel buttons = new JPanel();
        loadButton = new JButton("Load");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        buttons.add(loadButton);
        buttons.add(addButton);
        buttons.add(deleteButton);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadData());

        addButton.addActionListener(e -> {
            Delivery d = (Delivery) deliveryBox.getSelectedItem();
            Price price = (Price) priceBox.getSelectedItem();
            Part part = (Part) partBox.getSelectedItem();
            if (d == null || price == null || part == null) return;
            String qtyStr = JOptionPane.showInputDialog("Quantity");
            if (qtyStr == null || qtyStr.isBlank()) return;
            DeliveryItem item = new DeliveryItem();
            item.setDeliveryId(d.getDeliveryId());
            item.setPriceId(price.getPriceId());
            item.setPartId(part.getPartId());
            item.setQuantity(Integer.parseInt(qtyStr));
            controller.addDeliveryItem(item);
            loadData();
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (Integer) model.getValueAt(row, 0);
                controller.deleteDeliveryItem(id);
                loadData();
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        List<DeliveryItem> items = controller.getAllDeliveryItems();
        for (DeliveryItem item : items) {
            model.addRow(new Object[]{item.getDeliveryItemId(), item.getDeliveryId(), item.getPriceId(), item.getPartId(), item.getQuantity()});
        }
    }
}