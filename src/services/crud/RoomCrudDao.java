package services.crud;

import models.Room;
import services.ConnectionPoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class RoomCrudDao extends AbstractCrudDao<Room, Integer> {

    public RoomCrudDao(ConnectionPoint connectionPoint) {
        super(
                connectionPoint,
                "class_rooms",
                "room_id", "room_number"
        );
    }

    @Override
    protected Room getRecord(ResultSet resultSet) throws SQLException {
        return new Room(
                resultSet.getInt("room_id"),
                resultSet.getInt("room_number")
        );
    }

    @Override
    protected List<String> getValues(Room record) {
        return Stream.of(record.getId(), record.getRoomNumber())
                .map(it -> Integer.toString(it))
                .toList();
    }
}
