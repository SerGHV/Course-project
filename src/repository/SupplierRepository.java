package repository;

import database.DBConnection;
import model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

    private Connection connection;

    public SupplierRepository() {
        connection = new DBConnection().getConnection();
    }

    public List<Supplier> getAllSuppliers() {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM suppliers ORDER BY supplier_name";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Supplier supplier = new Supplier();

                supplier.setSupplierId(result.getInt("supplier_id"));
                supplier.setSupplierName(result.getString("supplier_name"));
                supplier.setAddress(result.getString("address"));
                supplier.setPhoneNumber(result.getString("phone_number"));

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public List<Supplier> findAll() {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";

        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Supplier supplier = new Supplier(
                        result.getInt("supplier_id"),
                        result.getString("supplier_name"),
                        result.getString("address"),
                        result.getString("phone_number")
                );

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }
}