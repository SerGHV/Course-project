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

    public User findByLoginAndPasswordHash(String login, String passwordHash) {

        String sql = "SELECT * FROM users " +
                "WHERE login = ? AND password_hash = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);
            statement.setString(2, passwordHash);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                return new User(
                        result.getInt("user_id"),
                        result.getString("login"),
                        result.getString("password_hash"),
                        result.getString("full_name"),
                        result.getString("role_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                User user = new User(
                        result.getInt("user_id"),
                        result.getString("login"),
                        result.getString("password_hash"),
                        result.getString("full_name"),
                        result.getString("role_name")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}