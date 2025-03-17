package services;

import models.ModelHaveId;
import models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class TemplateDAO {

    Connection connection;

    public TemplateDAO(ConnectionPoint connectionPoint) {
        connection = connectionPoint.getConnection();
    }

    public final <T> T findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(getIdQuery());
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        return getResult(resultSet);
    }

    public final List getAll() throws SQLException {
        List list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(getAllQuery());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ModelHaveId result = getResult(resultSet);
            list.add(result);
        }
        return list;
    }

    public final <T> void create(T inPut) throws SQLException {
        List list = getAll();
        boolean isInTable = false;
        for (Object i : list) {
            if (i.equals(inPut)) {
                isInTable = true;
                break;
            }
        }
        if (!isInTable) {
            PreparedStatement statement = connection.prepareStatement(createQuery());
            createStatement(statement, inPut);
            statement.executeUpdate();
            System.out.println("Объект " + inPut.toString() + " добавлен!!!");
        } else {
            System.out.println("Объект " + inPut.toString() + " уже есть в таблице!!!");
        }
    }

    protected abstract <T> void createStatement(PreparedStatement statement, T inPut) throws SQLException;

    protected abstract <T> T getResult(ResultSet resultSet) throws SQLException;

    protected abstract String getAllQuery();

    protected abstract String getIdQuery();

    protected abstract String createQuery();
}
