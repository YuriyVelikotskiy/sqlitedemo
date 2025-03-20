package services;

import models.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RoomDAOImpl extends TemplateDAO<Room, Integer> {
    private static final String nameOfTable = "class_rooms";
    private static final List<String> fieldsOfTable = Arrays.asList("room_id", "room_number");

    public RoomDAOImpl(ConnectionPoint connectionPoint) {
        super(connectionPoint,
                nameOfTable,
                fieldsOfTable);
    }

    @Override
    protected Room getRecord(ResultSet resultSet) throws SQLException {
        return new Room(resultSet.getLong(fieldsOfTable.get(0)),
                resultSet.getInt(fieldsOfTable.get(1)));
    }

    @Override
    protected List<String> getValue(Room record) {
        return Arrays.asList(String.valueOf(record.getId()), String.valueOf(record.getRoomNumber()));
    }
}
