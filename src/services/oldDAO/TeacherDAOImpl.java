package services.oldDAO;

import models.Teacher;
import services.ConnectionPoint;
import services.oldDAO.TeacherDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TeacherDAOImpl implements TeacherDAO {
    private final Connection connection;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public TeacherDAOImpl(ConnectionPoint connectionPoint) {
        connection = connectionPoint.getConnection();
    }

    private static final String teacherById = "SELECT teacher_id, name, second_name, last_name FROM teachers WHERE teacher_id = ?";
    private static final String allTeachers = "SELECT teacher_id, name, second_name, last_name FROM teachers";

    @Override
    public Teacher getTeacherById(int id) throws SQLException {
        statement = connection.prepareStatement(teacherById);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        return new Teacher(resultSet.getInt("teacher_id"),
                resultSet.getString("name"),
                resultSet.getString("second_name"),
                resultSet.getString("last_name"));
    }

    @Override
    public Map<Integer, Teacher> getAllTeachers() throws SQLException {
        Map<Integer, Teacher> map = new HashMap<>();
        statement = connection.prepareStatement(allTeachers);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Teacher teacher = new Teacher(resultSet.getInt("teacher_id"),
                    resultSet.getString("name"),
                    resultSet.getString("second_name"),
                    resultSet.getString("last_name"));
            map.put(teacher.getId(), teacher);
        }
        return map;
    }
}
