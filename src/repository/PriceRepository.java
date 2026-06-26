package repository;

import database.DBConnection;
import model.Price;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PriceRepository {

    private Connection connection;

    public PriceRepository() {
        connection = new DBConnection().getConnection();
    }

    public List<Price> getAllPrices() {

        List<Price> prices = new ArrayList<>();

        String sql = "SELECT * FROM prices ORDER BY price_date DESC";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Price price = new Price();

                price.setPriceId(result.getInt("price_id"));
                price.setSupplierId(result.getInt("supplier_id"));
                price.setPartId(result.getInt("part_id"));
                price.setPriceDate(result.getDate("price_date"));
                price.setPriceValue(result.getDouble("price_value"));

                prices.add(price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prices;
    }
}