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
    private JButton addButton;
    private JButton deleteButton;

    public DeliveryItemPanel(){

        controller=new DeliveryItemController();

        setLayout(new BorderLayout());

        model=new DefaultTableModel(
                new String[]{
                        "ID",
                        "Delivery",
                        "Price",
                        "Part",
                        "Quantity"
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

            DeliveryItem item=new DeliveryItem();

            item.setDeliveryId(Integer.parseInt(JOptionPane.showInputDialog("Delivery ID")));

            item.setPriceId(Integer.parseInt(JOptionPane.showInputDialog("Price ID")));

            item.setPartId(Integer.parseInt(JOptionPane.showInputDialog("Part ID")));

            item.setQuantity(Integer.parseInt(JOptionPane.showInputDialog("Quantity")));

            controller.addDeliveryItem(item);

            loadData();

        });

        deleteButton.addActionListener(e->{

            int row=table.getSelectedRow();

            if(row>=0){

                controller.deleteDeliveryItem((Integer)model.getValueAt(row,0));

                loadData();

            }

        });

    }

    private void loadData(){

        model.setRowCount(0);

        List<DeliveryItem> list=controller.getAllDeliveryItems();

        for(DeliveryItem item:list){

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