package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection connection;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/course.shd",
                    "root",
                    ""
            );
            System.out.println("Database connected.");

            // Проверка имени базы данных
            String dbName = connection.getCatalog();
            System.out.println("Подключены к базе данных: " + dbName);

            if ("course.shd".equals(dbName)) {
                System.out.println("✅ Это та самая база.");
            } else {
                System.out.println("❌ Внимание: подключились к другой базе!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}