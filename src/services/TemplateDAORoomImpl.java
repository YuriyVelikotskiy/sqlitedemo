package services;

import models.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateDAORoomImpl extends TemplateDAO {
    public TemplateDAORoomImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint);
    }

    @Override
    protected Room getResult(ResultSet resultSet) throws SQLException {
        return new Room(resultSet.getInt("room_id"),
                resultSet.getInt("room_number"));
    }

    @Override
    protected String setQuery() {
       return "SELECT room_id, room_number FROM class_rooms WHERE room_id = ?";
    }
}
