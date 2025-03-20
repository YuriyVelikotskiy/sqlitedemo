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

        ConnectionPoint connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection();

        System.out.println("-------------------------------------");
        TemplateDAO<Room, Integer> crudDAOTemplate = new RoomDAOImpl(connectionPoint);
        System.out.println(crudDAOTemplate.findById(1).toString());
        crudDAOTemplate.create(new Room(1111));
        crudDAOTemplate.update(new Room(4, 111));
        crudDAOTemplate.delete(new Room(4,1111));

        List<Room> listR = new RoomDAOImpl(connectionPoint).findAll();
        for (Room room : listR) {
            System.out.println(room);
        }

        connectionPoint.closeConnection();

    }
}

