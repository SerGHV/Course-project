package repository;

import database.DBConnection;
import model.Delivery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository {

    private Connection connection;

    public DeliveryRepository() {
        connection = new DBConnection().getConnection();
    }

    public List<Delivery> getAllDeliveries() {

        List<Delivery> deliveries = new ArrayList<>();

        String sql = "SELECT * FROM deliveries ORDER BY delivery_date DESC";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Delivery delivery = new Delivery();

                delivery.setDeliveryId(result.getInt("delivery_id"));
                delivery.setSupplierId(result.getInt("supplier_id"));
                delivery.setDeliveryDate(result.getDate("delivery_date"));

                deliveries.add(delivery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveries;
    }

    public List<Delivery> findAll() {

        List<Delivery> deliveries = new ArrayList<>();

        String sql = "SELECT * FROM deliveries";

        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Delivery delivery = new Delivery(
                        result.getInt("delivery_id"),
                        result.getInt("supplier_id"),
                        result.getDate("delivery_date")
                );

                deliveries.add(delivery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveries;
    }
}