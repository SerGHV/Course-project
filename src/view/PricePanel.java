package view;

import controller.PriceController;
import model.Price;

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

    private JButton loadButton;
    private JButton addButton;
    private JButton deleteButton;

    public PricePanel(){

        controller=new PriceController();

        setLayout(new BorderLayout());

        model=new DefaultTableModel(
                new String[]{
                        "ID",
                        "Supplier",
                        "Part",
                        "Date",
                        "Price"
                },0);

        table=new JTable(model);

        JPanel buttons=new JPanel();

        loadButton=new JButton("Load");
        addButton=new JButton("Add");
        deleteButton=new JButton("Delete");

        buttons.add(loadButton);
        buttons.add(addButton);
        buttons.add(deleteButton);

        add(new JScrollPane(table),BorderLayout.CENTER);
        add(buttons,BorderLayout.SOUTH);

        loadButton.addActionListener(e->loadData());

        addButton.addActionListener(e->{

            Price price=new Price();

            price.setSupplierId(Integer.parseInt(JOptionPane.showInputDialog("Supplier ID")));

            price.setPartId(Integer.parseInt(JOptionPane.showInputDialog("Part ID")));

            price.setPriceDate(Date.valueOf(JOptionPane.showInputDialog("Date YYYY-MM-DD")));

            price.setPriceValue(new BigDecimal(JOptionPane.showInputDialog("Price")));

            controller.addPrice(price);

            loadData();

        });

        deleteButton.addActionListener(e->{

            int row=table.getSelectedRow();

            if(row>=0){

                controller.deletePrice((Integer)model.getValueAt(row,0));

                loadData();

            }

        });

    }

    private void loadData(){

        model.setRowCount(0);

        List<Price> prices=controller.getAllPrices();

        for(Price p:prices){

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