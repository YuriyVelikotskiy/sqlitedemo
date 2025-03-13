package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoint {

    public static Connection connection;
    public Connection getConnection() {
        return connection;
    }
    public void openConnection() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:resources\\School.db");
        if (connection != null) {
            System.out.println("Подключение установлено!!!");
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Подключение остановлено!!!");
    }
}
