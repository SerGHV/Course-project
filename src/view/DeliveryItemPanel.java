package view;

import controller.DeliveryItemController;
import model.DeliveryItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DeliveryItemPanel extends JPanel {

    private DeliveryItemController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton;

    public DeliveryItemPanel() {

        controller = new DeliveryItemController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Delivery",
                        "Price",
                        "Part",
                        "Quantity"
                },
                0
        );

        table = new JTable(model);

        loadButton = new JButton("Load delivery items");

        loadButton.addActionListener(e -> loadData());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadData() {

        model.setRowCount(0);

        List<DeliveryItem> items = controller.getAllDeliveryItems();

        for (DeliveryItem item : items) {

            model.addRow(new Object[]{
                    item.getDeliveryItemId(),
                    item.getDeliveryId(),
                    item.getPriceId(),
                    item.getPartId(),
                    item.getQuantity()
            });
        }
    }
}