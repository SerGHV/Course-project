package repository;

import database.DBConnection;
import model.User;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Connection connection;

    public UserRepository() {
        connection = DBConnection.getInstance().getConnection();
    }

    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY user_id";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

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

    public User findById(int id) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                User user = new User();

                user.setUserId(result.getInt("user_id"));
                user.setLogin(result.getString("login"));
                user.setPasswordHash(result.getString("password_hash"));
                user.setFullName(result.getString("full_name"));
                user.setRoleName(result.getString("role_name"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User findByLoginAndPasswordHash(String login, String passwordHash) {

        String sql = """
                SELECT *
                FROM users
                WHERE login = ?
                AND password_hash = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, passwordHash);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                User user = new User();

                user.setUserId(result.getInt("user_id"));
                user.setLogin(result.getString("login"));
                user.setPasswordHash(result.getString("password_hash"));
                user.setFullName(result.getString("full_name"));
                user.setRoleName(result.getString("role_name"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(User user) {

        String sql = """
                INSERT INTO users
                (login, password_hash, full_name, role_name)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getRoleName());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}