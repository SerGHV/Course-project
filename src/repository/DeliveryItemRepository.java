package repository;

import database.DBConnection;
import model.DeliveryItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryItemRepository {

    private Connection connection;

    public DeliveryItemRepository() {
        connection = new DBConnection().getConnection();
    }

    public List<DeliveryItem> getAllDeliveryItems() {

        List<DeliveryItem> deliveryItems = new ArrayList<>();

        String sql = "SELECT * FROM delivery_items";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                DeliveryItem item = new DeliveryItem();

                item.setDeliveryItemId(result.getInt("delivery_item_id"));
                item.setDeliveryId(result.getInt("delivery_id"));
                item.setPriceId(result.getInt("price_id"));
                item.setPartId(result.getInt("part_id"));
                item.setQuantity(result.getInt("quantity"));

                deliveryItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveryItems;
    }
}