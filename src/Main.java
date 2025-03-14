import services.*;

import java.sql.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionPoint connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection();

        TemplateDAO templateDAORoom = new TemplateDAORoomImpl(connectionPoint);
        System.out.println(templateDAORoom.findById(1).toString());

        TemplateDAO templateDAOTeacher = new TemplateDAOTeacherImpl(connectionPoint);
        System.out.println(templateDAOTeacher.findById(1).toString());
        System.out.println("-------------------------------------");

        RoomDAO roomDAO = new RoomDAOImpl(connectionPoint);
        TeacherDAO teacherDAO = new TeacherDAOImpl(connectionPoint);
        System.out.println(teacherDAO.getTeacherById(1).toString());
        System.out.println(teacherDAO.getTeacherById(2).toString());
        System.out.println(teacherDAO.getTeacherById(3).toString());
        System.out.println(teacherDAO.getTeacherById(4).toString());
        System.out.println(teacherDAO.getTeacherById(5).toString());
        System.out.println("-------------------------------------");

        Map map = new TemplateDAORoomImpl(connectionPoint).getAll();
        for (int i =1;i <= map.size();i++){
            System.out.println(map.get(i));
        }

        Map mapT = new TemplateDAOTeacherImpl(connectionPoint).getAll();
        for (int i =1;i <= mapT.size();i++){
            System.out.println(mapT.get(i));
        }


        connectionPoint.closeConnection();
    }
}

