package services;

import models.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateDAOTeacherImpl extends TemplateDAO {
    public TemplateDAOTeacherImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint);
    }

    @Override
    protected String setQueryAll() {
        return "SELECT teacher_id, name, second_name, last_name FROM teachers";
    }

    @Override
    protected Teacher getResult(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getInt("teacher_id"),
                resultSet.getString("name"),
                resultSet.getString("second_name"),
                resultSet.getString("last_name"));
    }

    @Override
    protected String setQueryId() {
        return "SELECT teacher_id, name, second_name, last_name FROM teachers WHERE teacher_id = ?";
    }
}
