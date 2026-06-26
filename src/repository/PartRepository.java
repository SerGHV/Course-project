package repository;

import database.DBConnection;
import model.Part;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartRepository {

    private Connection connection;

    public PartRepository() {
        connection = new DBConnection().getConnection();
    }

    public List<Part> getAllParts() {

        List<Part> parts = new ArrayList<>();

        String sql = "SELECT * FROM parts ORDER BY part_name";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Part part = new Part();

                part.setPartId(result.getInt("part_id"));
                part.setPartName(result.getString("part_name"));
                part.setArticleNumber(result.getString("article_number"));

                parts.add(part);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }

    public List<Part> findAll() {

        List<Part> parts = new ArrayList<>();

        String sql = "SELECT * FROM parts";

        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Part part = new Part(
                        result.getInt("part_id"),
                        result.getString("part_name"),
                        result.getString("article_number")
                );

                parts.add(part);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }

}