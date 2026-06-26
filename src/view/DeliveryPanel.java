package view;

import controller.DeliveryController;
import model.Delivery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DeliveryPanel extends JPanel {

    private DeliveryController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton;

    public DeliveryPanel() {

        controller = new DeliveryController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Supplier ID", "Delivery Date"},
                0
        );

        table = new JTable(model);

        loadButton = new JButton("Load deliveries");

        loadButton.addActionListener(e -> loadData());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadData() {

        model.setRowCount(0);

        List<Delivery> deliveries = controller.getAllDeliveries();

        for (Delivery d : deliveries) {

            model.addRow(new Object[]{
                    d.getDeliveryId(),
                    d.getSupplierId(),
                    d.getDeliveryDate()
            });
        }
    }
}