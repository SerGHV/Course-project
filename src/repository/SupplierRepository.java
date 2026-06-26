package repository;

import database.DBConnection;
import model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

    private Connection connection;

    public SupplierRepository(DBConnection dbConnection) {
        connection = dbConnection.getConnection();
    }

    public List<Supplier> getAllSuppliers() {

        List<Supplier> suppliers = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();

            String query =
                    "SELECT supplier_id, supplier_name, address, phone_number " +
                            "FROM suppliers";

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {

                suppliers.add(
                        new Supplier(
                                result.getInt("supplier_id"),
                                result.getString("supplier_name"),
                                result.getString("address"),
                                result.getString("phone_number")
                        )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return suppliers;
    }
}