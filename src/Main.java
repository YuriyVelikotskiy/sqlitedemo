import models.Room;
import models.Teacher;
import services.*;

import java.sql.*;
import java.util.List;
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

        TemplateDAO newRoom = new TemplateDAORoomImpl(connectionPoint);
        newRoom.create(new Room(666));
        TemplateDAO newTeacher = new TemplateDAOTeacherImpl(connectionPoint);
        newTeacher.create(new Teacher("Юрий","Юрьевич","Юрьев"));

        List list = new TemplateDAORoomImpl(connectionPoint).getAll();
        for (int i = 0;i < list.size();i++){
                System.out.println(list.get(i));
        }



        List listT = new TemplateDAOTeacherImpl(connectionPoint).getAll();
        for (int i =0;i < listT.size();i++){
            System.out.println(listT.get(i));
        }

        connectionPoint.closeConnection();
    }
}

