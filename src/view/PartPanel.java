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
    private JButton addButton;
    private JButton deleteButton;

    public PartPanel() {

        controller = new PartController();

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID","Name","Article Number"},
                0
        );

        table = new JTable(model);

        JPanel buttons = new JPanel();

        loadButton = new JButton("Load");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        buttons.add(loadButton);
        buttons.add(addButton);
        buttons.add(deleteButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadData());

        addButton.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Part name");

            String article = JOptionPane.showInputDialog("Article number");

            if(name != null && article != null){

                Part part = new Part();

                part.setPartName(name);
                part.setArticleNumber(article);

                controller.addPart(part);

                loadData();
            }
        });

        deleteButton.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row >= 0){

                int id = (Integer) model.getValueAt(row,0);

                controller.deletePart(id);

                loadData();
            }
        });
    }

    private void loadData(){

        model.setRowCount(0);

        List<Part> list = controller.getAllParts();

        for(Part p : list){

            model.addRow(new Object[]{
                    p.getPartId(),
                    p.getPartName(),
                    p.getArticleNumber()
            });

        }

    }

}