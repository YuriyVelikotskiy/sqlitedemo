package services;

import models.Group;
import models.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class GroupDAOImpl extends TemplateDAO<Group, Integer> {

    private static final String nameOfTable = "students_groups";
    private static final List<String> fieldsOfTable = Arrays.asList("group_id", "group_year", "group_letter", "group_grade");

    public GroupDAOImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint,
                nameOfTable,
                fieldsOfTable);
    }

    @Override
    protected Group getRecord(ResultSet resultSet) throws SQLException {
        return new Group(resultSet.getLong(fieldsOfTable.get(0)),
                resultSet.getInt(fieldsOfTable.get(1)),
                resultSet.getString(fieldsOfTable.get(2)),
                resultSet.getInt(fieldsOfTable.get(3)));
    }

    @Override
    protected List<String> getValue(Group record) {
        return Arrays.asList(String.valueOf(record.getGroupId()),
                String.valueOf(record.getGroupYear()),
                String.valueOf(record.getGroupLetter()),
                String.valueOf(record.getGroupGrade()));
    }
}
