package services;

import models.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TemplateDAOTeacherImpl extends TemplateDAO {
    public TemplateDAOTeacherImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint);
    }

    @Override
    protected <T> void createStatement(PreparedStatement statement, T inPut) throws SQLException {
        Teacher teacher = (Teacher) inPut;
        statement.setObject(1, teacher.getName());
        statement.setObject(2,teacher.getSecondName());
        statement.setObject(3,teacher.getLastName());
    }

    @Override
    protected String getAllQuery() {
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
    protected String getIdQuery() {
        return "SELECT teacher_id, name, second_name, last_name FROM teachers WHERE teacher_id = ?";
    }

    @Override
    protected String createQuery() {
        return "INSERT INTO teachers ( name, second_name, last_name ) VALUES ( ?, ?, ? )";
    }
}
