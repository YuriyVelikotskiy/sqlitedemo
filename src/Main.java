import models.Room;
import services.*;

import java.sql.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionPoint connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection();

        RoomDAO roomDAO = new RoomDAOImpl(connectionPoint);
        TeacherDAO teacherDAO = new TeacherDAOImpl(connectionPoint);
        System.out.println(teacherDAO.getTeacherById(1).toString());
        System.out.println(teacherDAO.getTeacherById(2).toString());
        System.out.println(teacherDAO.getTeacherById(3).toString());
        System.out.println(teacherDAO.getTeacherById(4).toString());
        System.out.println(teacherDAO.getTeacherById(5).toString());
        System.out.println("-------------------------------------");

        Map map = new RoomDAOImpl(connectionPoint).getAllRooms();
        for (int i =1;i <= map.size();i++){
            System.out.println(map.get(i));
        }

        Map mapT = new TeacherDAOImpl(connectionPoint).getAllTeachers();
        for (int i =1;i <= mapT.size();i++){
            System.out.println(mapT.get(i));
        }


        connectionPoint.closeConnection();
    }
}

