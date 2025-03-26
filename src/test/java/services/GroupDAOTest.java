package services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.schedule.models.Group;
import ru.schedule.services.ConnectionPoint;
import ru.schedule.services.GroupDAOImpl;
import ru.schedule.services.TemplateDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GroupDAOTest {

    private static ConnectionPoint connectionPoint;
    private static TemplateDAO<Group, Integer> crudDAOTemplate;

    @BeforeAll
    static void setup() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:src\\main\\resources\\SchoolTest.db";
        connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection(url);
        crudDAOTemplate = new GroupDAOImpl(connectionPoint);
        crudDAOTemplate.create(new Group(7, "A", 1));
        crudDAOTemplate.create(new Group(7, "A", 2));
        crudDAOTemplate.create(new Group(10, "B", 1));
    }

    @Test
    void findByIdTest() throws SQLException {
        assertEquals(new Group(7, "A", 1),crudDAOTemplate.findById(1));
        assertEquals(new Group(7, "A", 2),crudDAOTemplate.findById(2));
        assertEquals(new Group(10, "B", 1),crudDAOTemplate.findById(3));
        assertNotEquals(new Group(10, "B", 1),crudDAOTemplate.findById(4));
    }

    @Test
    void findAllTest() throws SQLException {
        List<Group> rooms = new ArrayList<>();
        rooms.add(new Group(7, "A", 1));
        rooms.add(new Group(7, "A", 2));
        rooms.add(new Group(10, "B", 1));
        assertEquals(rooms,crudDAOTemplate.findAll());
    }

    @Test
    void creatTest() throws SQLException {
        crudDAOTemplate.create(new Group(10, "B", 2));
        assertEquals(new Group(10, "B", 2),crudDAOTemplate.findById(4));
        crudDAOTemplate.delete(new Group(4,10, "B", 2));
    }

    @Test
    void updateTest() throws SQLException {
        crudDAOTemplate.create(new Group(4,10, "B", 2));
        crudDAOTemplate.update(new Group(4,11, "B", 2));
        assertEquals(new Group(11, "B", 2),crudDAOTemplate.findById(4));
        crudDAOTemplate.delete(new Group(4,11, "B", 2));
    }

    @Test
    void deleteTest() throws SQLException {
        crudDAOTemplate.create(new Group(10, "B", 2));
        crudDAOTemplate.delete(new Group(4,10, "B", 2));
        assertNotEquals(new Group(10, "B", 2),crudDAOTemplate.findById(4));
    }

    @AfterAll
    static void tear() throws SQLException {
        crudDAOTemplate.delete(new Group(1, 7, "A", 1));
        crudDAOTemplate.delete(new Group(2, 7, "A", 2));
        crudDAOTemplate.delete(new Group(3, 10, "B", 1));
        connectionPoint.closeConnection();
    }
}
