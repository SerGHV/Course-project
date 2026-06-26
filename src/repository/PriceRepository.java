package repository;

import database.DBConnection;
import model.Price;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriceRepository {

    private Connection connection;

    public PriceRepository() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<Price> findAll() {

        List<Price> list = new ArrayList<>();

        String sql = "SELECT * FROM prices";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Price price = new Price();

                price.setPriceId(rs.getInt("price_id"));
                price.setSupplierId(rs.getInt("supplier_id"));
                price.setPartId(rs.getInt("part_id"));
                price.setPriceDate(rs.getDate("price_date"));
                price.setPriceValue(rs.getBigDecimal("price_value"));

                list.add(price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Price findById(int id) {

        String sql = "SELECT * FROM prices WHERE price_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Price(
                        rs.getInt("price_id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("part_id"),
                        rs.getDate("price_date"),
                        rs.getBigDecimal("price_value")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(Price price) {

        String sql = """
            INSERT INTO prices
            (supplier_id,part_id,price_date,price_value)
            VALUES (?,?,?,?)
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, price.getSupplierId());
            ps.setInt(2, price.getPartId());
            ps.setDate(3, price.getPriceDate());
            ps.setBigDecimal(4, price.getPriceValue());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM prices WHERE price_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}