package view;

import controller.PartController;
import model.Part;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PartPanel extends JPanel {

    private PartController controller;
    private JTable table;
    private DefaultTableModel model;
    private JButton loadButton;

    public PartPanel() {

        controller = new PartController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Article Number"}, 0
        );

        table = new JTable(model);

        loadButton = new JButton("Load parts");

        loadButton.addActionListener(e -> loadData());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadData() {

        model.setRowCount(0);

        List<Part> parts = controller.getAllParts();

        for (Part p : parts) {
            model.addRow(new Object[]{
                    p.getPartId(),
                    p.getPartName(),
                    p.getArticleNumber()
            });
        }
    }
}