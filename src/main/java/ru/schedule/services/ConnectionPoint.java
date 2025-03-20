package ru.schedule.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoint {

    public static Connection connection;
    Logger logger = LoggerFactory.getLogger("ru.schedule.services.ConnectionPoint");
    public Connection getConnection() {
        return connection;
    }
    public void openConnection() throws ClassNotFoundException, SQLException {
        connection = null;
        logger.info("Установка подключения!!!");
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\School.db");
        if (connection != null) {
            logger.info("Подключение установлено!!!");
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
        logger.info("Подключение остановлено!!!");
    }
}
