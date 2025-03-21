package services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.schedule.models.Room;
import ru.schedule.services.ConnectionPoint;
import ru.schedule.services.RoomDAOImpl;
import ru.schedule.services.TemplateDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GroupDAOImplTest {

    private static ConnectionPoint connectionPoint;
    static TemplateDAO<Room, Integer> crudDAOTemplate;

    @BeforeAll
    static void setup() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:src\\main\\resources\\SchoolTest.db";
        connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection(url);
        crudDAOTemplate = new RoomDAOImpl(connectionPoint);
    }

    @Test
    void findByIdTest() throws SQLException {
        assertEquals(new Room(1,111),crudDAOTemplate.findById(1));
        assertEquals(new Room(2,222),crudDAOTemplate.findById(2));
        assertEquals(new Room(3,666),crudDAOTemplate.findById(3));
        assertNotEquals(new Room(1,111),crudDAOTemplate.findById(4));
    }

    @Test
    void findAllTest() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1,111));
        rooms.add(new Room(2,222));
        rooms.add(new Room(3,666));
        assertEquals(rooms,crudDAOTemplate.findAll());
    }

    @AfterAll
    static void tear() throws SQLException {
        connectionPoint.closeConnection();
    }
}
