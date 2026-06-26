package repository;

import database.DBConnection;
import model.DeliveryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryItemRepository {

    private Connection connection;

    public DeliveryItemRepository() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<DeliveryItem> findAll() {

        List<DeliveryItem> list = new ArrayList<>();

        String sql = "SELECT * FROM delivery_items";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                DeliveryItem item = new DeliveryItem();

                item.setDeliveryItemId(rs.getInt("delivery_item_id"));
                item.setDeliveryId(rs.getInt("delivery_id"));
                item.setPriceId(rs.getInt("price_id"));
                item.setPartId(rs.getInt("part_id"));
                item.setQuantity(rs.getInt("quantity"));

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public DeliveryItem findById(int id) {

        String sql = "SELECT * FROM delivery_items WHERE delivery_item_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new DeliveryItem(
                        rs.getInt("delivery_item_id"),
                        rs.getInt("delivery_id"),
                        rs.getInt("price_id"),
                        rs.getInt("part_id"),
                        rs.getInt("quantity")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(DeliveryItem item) {

        String sql = """
            INSERT INTO delivery_items
            (delivery_id,price_id,part_id,quantity)
            VALUES (?,?,?,?)
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, item.getDeliveryId());
            ps.setInt(2, item.getPriceId());
            ps.setInt(3, item.getPartId());
            ps.setInt(4, item.getQuantity());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM delivery_items WHERE delivery_item_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}