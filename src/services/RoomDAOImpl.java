package services;

import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoomDAOImpl implements RoomDAO {

    private final Connection connection;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public RoomDAOImpl(ConnectionPoint connectionPoint) {
        connection = connectionPoint.getConnection();
    }

    public static final String roomById = "SELECT room_id, room_number FROM class_rooms WHERE room_id = ?";
    public static final String allRooms = "SELECT room_id, room_number FROM class_rooms";

    @Override
    public Room getClassRoomById(int id) throws SQLException {
        statement = connection.prepareStatement(roomById);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        return new Room(resultSet.getInt("room_id"),
                resultSet.getInt("room_number"));
    }
    @Override
    public Map<Integer, Room> getAllRooms() throws SQLException {
        Map<Integer,Room> map = new HashMap<>();
        statement = connection.prepareStatement(allRooms);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Room room = new Room(resultSet.getInt("room_id"),
                    resultSet.getInt("room_number"));
            map.put(room.getId(), room);
        }
        return map;
    }
}
