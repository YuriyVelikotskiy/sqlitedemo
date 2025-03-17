package services.oldDAO;

import models.Room;
import models.Teacher;

import java.sql.SQLException;
import java.util.Map;

public interface RoomDAO {
    Room getClassRoomById(int id) throws SQLException;
    Map<Integer, Room> getAllRooms() throws SQLException;
}
