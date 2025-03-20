import models.Room;
import services.*;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

