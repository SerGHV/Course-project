package view;

import controller.DeliveryController;
import model.Delivery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class DeliveryPanel extends JPanel {

    private DeliveryController controller;

    private JTable table;
    private DefaultTableModel model;

    private JButton loadButton;
    private JButton addButton;
    private JButton deleteButton;

    public DeliveryPanel(){

        controller=new DeliveryController();

        setLayout(new BorderLayout());

        model=new DefaultTableModel(
                new String[]{
                        "ID",
                        "Supplier",
                        "Date"
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

            Delivery delivery=new Delivery();

            delivery.setSupplierId(Integer.parseInt(JOptionPane.showInputDialog("Supplier ID")));

            delivery.setDeliveryDate(Date.valueOf(JOptionPane.showInputDialog("Date YYYY-MM-DD")));

            controller.addDelivery(delivery);

            loadData();

        });

        deleteButton.addActionListener(e->{

            int row=table.getSelectedRow();

            if(row>=0){

                controller.deleteDelivery((Integer)model.getValueAt(row,0));

                loadData();

            }

        });

    }

    private void loadData(){

        model.setRowCount(0);

        List<Delivery> list=controller.getAllDeliveries();

        for(Delivery d:list){

            model.addRow(new Object[]{
                    d.getDeliveryId(),
                    d.getSupplierId(),
                    d.getDeliveryDate()
            });

        }

    }

}