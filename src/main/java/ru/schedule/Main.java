package ru.schedule;

import ru.schedule.models.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.schedule.services.*;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Logger logger = LoggerFactory.getLogger("ru.schedule");
        String url = "jdbc:sqlite:src\\main\\resources\\School.db";
        ConnectionPoint connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection(url);
        TemplateDAO<Room, Integer> crudDAOTemplate = new RoomDAOImpl(connectionPoint);
        crudDAOTemplate.create(new Room(1111));
        crudDAOTemplate.update(new Room(4, 111));
        crudDAOTemplate.delete(new Room(4,1111));
        connectionPoint.closeConnection();

    }
}

