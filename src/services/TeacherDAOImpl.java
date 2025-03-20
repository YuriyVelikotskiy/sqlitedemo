package services;

import models.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TeacherDAOImpl extends TemplateDAO<Teacher, Integer> {
    private static final String nameOfTable = "teachers";
    private static final List<String> fieldsOfTable = Arrays.asList("teacher_id", "name", "second_name", "last_name");

    public TeacherDAOImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint,
                nameOfTable,
                fieldsOfTable);
    }

    @Override
    protected Teacher getRecord(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getLong(fieldsOfTable.get(0)),
                resultSet.getString(fieldsOfTable.get(1)),
                resultSet.getString(fieldsOfTable.get(2)),
                resultSet.getString(fieldsOfTable.get(3)));
    }

    @Override
    protected List<String> getValue(Teacher record) {
        return Arrays.asList(String.valueOf(record.getTeacherId()),
                String.valueOf(record.getName()),
                String.valueOf(record.getSecondName()),
                String.valueOf(record.getLastName()));
    }
}
