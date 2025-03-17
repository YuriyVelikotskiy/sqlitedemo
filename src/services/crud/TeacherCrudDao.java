package services.crud;

import models.Teacher;
import services.ConnectionPoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class TeacherCrudDao extends AbstractCrudDao<Teacher, Integer> {

    public TeacherCrudDao(ConnectionPoint connectionPoint) {
        super(
                connectionPoint,
                "teachers",
                "teacher_id", "name", "second_name", "last_name"
        );
    }

    @Override
    protected Teacher getRecord(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getInt("teacher_id"),
                resultSet.getString("name"),
                resultSet.getString("second_name"),
                resultSet.getString("last_name"));
    }

    @Override
    protected List<String> getValues(Teacher record) {
        return Stream.of(
                Integer.toString(record.getId()),
                quoted(record.getName()),
                quoted(record.getSecondName()),
                quoted(record.getLastName())
        ).toList();
    }

    private String quoted(String string) { return "'" + string + "'"; }
}
