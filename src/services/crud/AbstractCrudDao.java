package services.crud;

import services.ConnectionPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractCrudDao<T, K extends Number> {

    private Connection connection;
    private String table;
    private final List<String> fields = new ArrayList<>();
    private String selectSql;

    public AbstractCrudDao(
            ConnectionPoint connectionPoint,
            String aTable,
            String... aFields
    ) {
        this.connection = connectionPoint.getConnection();
        this.table = aTable;
        initFields(aFields);
    }

    public void initFields(String... aFields) {
        fields.clear();
        fields.addAll(Arrays.asList(aFields));
        selectSql = parseSelectSql();
    }

    protected abstract T getRecord(ResultSet resultSet) throws SQLException;

    protected abstract List<String> getValues(T record);

    public T findById(K id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(selectSql + parseWhereId(id.toString()));
        ResultSet resultSet = statement.executeQuery();
        T result = null;
        if (resultSet.next()) result = getRecord(resultSet);
        return result;
    }

    public List<T> findAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        List<T> result = new ArrayList();
        while (resultSet.next()) {
            result.add(getRecord(resultSet));
        }
        return result;
    }

    public void create(T record) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(parseInsertSql(record));
        statement.executeUpdate();
    }

    public void update(T record) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(parseUpdateSql(record));
        statement.executeUpdate();
    }

    public void delete(T record) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(parseDeleteSql(record));
        statement.executeUpdate();
    }

    protected String parseSelectSql() {
        return String.format(SELECT_SQL, String.join(", ", fields), table);
    }

    protected String parseInsertSql(T record) {
        String parsedFields = String.join(", ", fields.subList(1, fields.size() - 1));
        String parsedValues = String.join(", ", getValues(record).subList(1, fields.size() - 1));
        return INSERT_SQL.format(table, parsedFields, parsedValues);
    }

    protected String parseUpdateSql(T record) {
        List<String> values = getValues(record);
        Iterator iteratorValues = values.iterator();
        String fieldValues = fields.stream()
                .map(field -> field + " = " + iteratorValues.next())
                .skip(1)
                .collect(Collectors.joining(", "));
        return String.format(UPDATE_SQL, table, fieldValues) + parseWhereId(values.get(0));
    }

    protected String parseDeleteSql(T record) {
        List<String> values = getValues(record);
        return String.format(DELETE_SQL, table) + parseWhereId(values.get(0));
    }

    protected String parseWhereId(String idValue) {
        return "where " + fields.get(0) + " = " + idValue;
    }

    private static final String SELECT_SQL = "select % \nfrom % \n";
    private static final String INSERT_SQL = "insert into % (%) \nvalues (%)";
    private static final String UPDATE_SQL = "update % \nset % \n";
    private static final String DELETE_SQL = "delete from % \n";
}
