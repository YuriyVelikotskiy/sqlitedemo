package services;

import models.ModelHaveId;
import models.Room;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class TemplateDAO {

    Connection connection;
    public TemplateDAO(ConnectionPoint connectionPoint){
        connection = connectionPoint.getConnection();
    }
    public final <T> T findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(setQueryId());
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return getResult(resultSet);
    }
    public Map getAll() throws SQLException {
        Map map = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(setQueryAll());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ModelHaveId result = getResult(resultSet);
            map.put(result.getId(), result);
        }
        return map;
    }

    protected abstract String setQueryAll();
    protected abstract <T> T getResult(ResultSet resultSet) throws SQLException;
    protected abstract String setQueryId();
}
