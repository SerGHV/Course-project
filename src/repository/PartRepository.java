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

        String sql = "SELECT * FROM parts";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Part part = new Part();

                part.setPartId(rs.getInt("part_id"));
                part.setPartName(rs.getString("part_name"));
                part.setArticleNumber(rs.getString("article_number"));

                list.add(part);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Part findById(int id) {

        String sql = "SELECT * FROM parts WHERE part_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Part(
                        rs.getInt("part_id"),
                        rs.getString("part_name"),
                        rs.getString("article_number")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(Part part) {

        String sql =
                "INSERT INTO parts(part_name,article_number) VALUES(?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, part.getPartName());
            ps.setString(2, part.getArticleNumber());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM parts WHERE part_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}