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

    public List<Part> findAll() {

        List<Part> list = new ArrayList<>();

        String sql = "SELECT part_id, part_name, article_number FROM parts";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Part p = new Part();

                p.setPartId(rs.getInt("part_id"));
                p.setPartName(rs.getString("part_name"));
                p.setArticleNumber(rs.getString("article_number"));

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}