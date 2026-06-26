package repository;

import database.DBConnection;
import model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

    private Connection connection;

    public SupplierRepository() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<Supplier> findAll() {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM suppliers ORDER BY supplier_name";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {

                suppliers.add(new Supplier(
                        result.getInt("supplier_id"),
                        result.getString("supplier_name"),
                        result.getString("address"),
                        result.getString("phone_number")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public Supplier findById(int id) {

        String sql = "SELECT * FROM suppliers WHERE supplier_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("address"),
                        rs.getString("phone_number")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(Supplier supplier) {

        String sql = "INSERT INTO suppliers (supplier_name, address, phone_number) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getAddress());
            ps.setString(3, supplier.getPhoneNumber());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM suppliers WHERE supplier_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}