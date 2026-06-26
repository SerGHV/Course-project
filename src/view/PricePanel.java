package view;

import controller.PriceController;
import model.Price;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PricePanel extends JPanel {

    private PriceController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton;

    public PricePanel() {

        controller = new PriceController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Supplier",
                        "Part",
                        "Date",
                        "Price"
                },
                0
        );

        table = new JTable(model);

        loadButton = new JButton("Load prices");

        loadButton.addActionListener(e -> loadData());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadData() {

        model.setRowCount(0);

        List<Price> prices = controller.getAllPrices();

        for (Price p : prices) {

            model.addRow(new Object[]{
                    p.getPriceId(),
                    p.getSupplierId(),
                    p.getPartId(),
                    p.getPriceDate(),
                    p.getPriceValue()
            });
        }
    }
}