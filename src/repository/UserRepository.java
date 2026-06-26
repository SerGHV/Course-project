package repository;

import database.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Connection connection;

    public UserRepository() {
        connection = new DBConnection().getConnection();
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                User user = new User();

                user.setUserId(result.getInt("user_id"));
                user.setLogin(result.getString("login"));
                user.setPasswordHash(result.getString("password_hash"));
                user.setFullName(result.getString("full_name"));
                user.setRoleName(result.getString("role_name"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}