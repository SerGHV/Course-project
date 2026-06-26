package repository;

import database.DBConnection;
import model.Delivery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository {

    private Connection connection;

    public DeliveryRepository() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<Delivery> findAll() {

        List<Delivery> list = new ArrayList<>();

        String sql = "SELECT * FROM deliveries";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Delivery delivery = new Delivery();

                delivery.setDeliveryId(rs.getInt("delivery_id"));
                delivery.setSupplierId(rs.getInt("supplier_id"));
                delivery.setDeliveryDate(rs.getDate("delivery_date"));

                list.add(delivery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Delivery findById(int id) {

        String sql = "SELECT * FROM deliveries WHERE delivery_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Delivery(
                        rs.getInt("delivery_id"),
                        rs.getInt("supplier_id"),
                        rs.getDate("delivery_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(Delivery delivery) {

        String sql = """
            INSERT INTO deliveries
            (supplier_id, delivery_date)
            VALUES (?, ?)
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, delivery.getSupplierId());
            ps.setDate(2, delivery.getDeliveryDate());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM deliveries WHERE delivery_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}