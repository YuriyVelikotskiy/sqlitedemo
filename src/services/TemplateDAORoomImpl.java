package services;

import models.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TemplateDAORoomImpl extends TemplateDAO {
    public TemplateDAORoomImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint);
    }

    @Override
    protected <T> void createStatement(PreparedStatement statement, T inPut) throws SQLException {
        Room room = (Room) inPut;
        statement.setObject(1, room.getRoomNumber());
    }

    @Override
    protected String getAllQuery() {
        return "SELECT room_id, room_number FROM class_rooms";
    }

    @Override
    protected Room getResult(ResultSet resultSet) throws SQLException {
        return new Room(resultSet.getInt("room_id"),
                resultSet.getInt("room_number"));
    }

    @Override
    protected String getIdQuery() {
       return "SELECT room_id, room_number FROM class_rooms WHERE room_id = ?";
    }

    @Override
    protected String createQuery() {
        return "INSERT INTO class_rooms ( room_number ) VALUES ( ? )";
    }
}
