package services;

import models.Group;
import models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SubjectDAOImpl extends TemplateDAO<Subject, Integer>{

    private static final String nameOfTable = "subjects";
    private static final List<String> fieldsOfTable = Arrays.asList("subject_id", "subject");

    public SubjectDAOImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint,
                nameOfTable,
                fieldsOfTable);
    }

    @Override
    protected Subject getRecord(ResultSet resultSet) throws SQLException {
        return new Subject(resultSet.getLong(fieldsOfTable.get(0)),
                resultSet.getString(fieldsOfTable.get(1)));
    }

    @Override
    protected List<String> getValue(Subject record) {
        return Arrays.asList(String.valueOf(record.getSubjectId()),
                String.valueOf(record.getSubject()));
    }
}
