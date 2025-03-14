package services;

import models.Room;

import java.sql.*;

public abstract class TemplateDAO {

    Connection connection;
    public TemplateDAO(ConnectionPoint connectionPoint){
        connection = connectionPoint.getConnection();
    }
    public final <T> T findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(setQuery());
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return getResult(resultSet);
    }
    protected abstract <T> T getResult(ResultSet resultSet) throws SQLException;
    protected abstract String setQuery();
}
