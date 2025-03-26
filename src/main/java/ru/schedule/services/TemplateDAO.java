package ru.schedule.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TemplateDAO<R, K> {
    private final Connection connection;
    private final String name;
    private final List<String> fields;

    Logger logger = LoggerFactory.getLogger("ru.schedule.services.DAO");

    public TemplateDAO(ConnectionPoint connectionPoint, String nameOfTable, List<String> namesOfFields) {
        this.connection = connectionPoint.getConnection();
        this.name = nameOfTable;
        this.fields = namesOfFields;
    }

    protected abstract R getRecord(ResultSet resultSet) throws SQLException;

    protected abstract List<String> getValue(R record);

    public R findById(K id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(prepareSelectById(id.toString()));
        ResultSet resultSet = statement.executeQuery();
        return getRecord(resultSet);
    }

    public List<R> findAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(prepareSelectAll());
        ResultSet resultSet = statement.executeQuery();
        List<R> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(getRecord(resultSet));
        }
        return result;
    }

    public void create(R record) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(prepareCreate(record));
        ResultSet resultSet = statement.executeQuery();
        logger.debug("Запись {} создана!!!", getRecord(resultSet).toString());
        resultSet.close();
    }

    public void update(R record) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(prepareUpdate(record));
        ResultSet resultSet = statement.executeQuery();
        logger.debug("Запись {} обнавлена!!!", getRecord(resultSet).toString());
        resultSet.close();
    }

    public void delete(R record) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(prepareDelete(record));
        ResultSet resultSet = statement.executeQuery();
        logger.debug("Запись {} удалена!!!", getRecord(resultSet).toString());
        resultSet.close();
    }

    private String prepareSelectById(String id) {
        return prepareSelectAll().concat(" WHERE ").concat(fields.get(0)).concat("= ").concat(id);
    }

    private String prepareSelectAll() {
        return String.format(SELECT, String.join(", ", fields), name);
    }

    private String prepareCreate(R record) {
        String prepareFields = String.join(", ", fields.subList(1, fields.size()));
        String prepareValues = String.join("', '", getValue(record).subList(1, fields.size()));
        return String.format(CREATE, name, prepareFields, prepareValues);
    }

    private String prepareUpdate(R record) {
        List<String> prepareValues = getValue(record);
        Iterator<String> iteratorValues = prepareValues.iterator();
        String prepare = fields.stream()
                .map(f -> f + " = '" + iteratorValues.next() + "'")
                .skip(1)
                .collect(Collectors.joining(", "));
        return String.format(UPDATE, name, prepare, prepareWhereId(prepareValues.get(0)));
    }

    private String prepareDelete(R record) {
        List<String> prepareValues = getValue(record);
        return String.format(DELETE, name, prepareWhereId(prepareValues.get(0)));
    }
    private String prepareWhereId(String id){
        return "where ".concat(fields.get(0)).concat(" = ").concat(id);
    }
    private static final String SELECT = "select %s from %s";
    private static final String CREATE = "insert into %s (%s) values ('%s') returning *";
    private static final String UPDATE = "update %s set %s %s returning *";
    private static final String DELETE = "delete from %s %s returning *";
}
