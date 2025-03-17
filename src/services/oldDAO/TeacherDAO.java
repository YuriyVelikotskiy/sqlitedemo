package services.oldDAO;

import models.Teacher;

import java.sql.*;
import java.util.Map;


public interface TeacherDAO {
    Teacher getTeacherById(int id) throws SQLException;
    Map<Integer, Teacher> getAllTeachers() throws SQLException;
}

