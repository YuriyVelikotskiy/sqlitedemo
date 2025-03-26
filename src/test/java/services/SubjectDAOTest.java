package services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.schedule.models.Subject;
import ru.schedule.services.ConnectionPoint;
import ru.schedule.services.SubjectDAOImpl;
import ru.schedule.services.TemplateDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SubjectDAOTest {
    private static ConnectionPoint connectionPoint;
    private static TemplateDAO<Subject, Integer> crudDAOTemplate;

    @BeforeAll
    static void setup() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:src\\main\\resources\\SchoolTest.db";
        connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection(url);
        crudDAOTemplate = new SubjectDAOImpl(connectionPoint);
        crudDAOTemplate.create(new Subject("Math"));
        crudDAOTemplate.create(new Subject("Phy"));
        crudDAOTemplate.create(new Subject("Rus"));
    }

    @Test
    void findByIdTest() throws SQLException {
        assertEquals(new Subject(1,"Math"),crudDAOTemplate.findById(1));
        assertEquals(new Subject(2,"Phy"),crudDAOTemplate.findById(2));
        assertEquals(new Subject(3,"Rus"),crudDAOTemplate.findById(3));
        assertNotEquals(new Subject(1,"Rus"),crudDAOTemplate.findById(1));
    }

    @Test
    void findAllTest() throws SQLException {
        List<Subject> rooms = new ArrayList<>();
        rooms.add(new Subject(1,"Math"));
        rooms.add(new Subject(2,"Phy"));
        rooms.add(new Subject(3,"Rus"));
        assertEquals(rooms,crudDAOTemplate.findAll());
    }

    @Test
    void creatTest() throws SQLException {
        crudDAOTemplate.create(new Subject("Chem"));
        assertEquals(new Subject(4,"Chem"),crudDAOTemplate.findById(4));
        crudDAOTemplate.delete(new Subject(4,"Chem"));
    }

    @Test
    void updateTest() throws SQLException {
        crudDAOTemplate.create(new Subject("Chem"));
        crudDAOTemplate.update(new Subject(4,"CoSi"));
        assertEquals(new Subject(4,"CoSi"),crudDAOTemplate.findById(4));
        crudDAOTemplate.delete(new Subject(4,"CoSi"));
    }

    @Test
    void deleteTest() throws SQLException {
        crudDAOTemplate.create(new Subject("Chem"));
        crudDAOTemplate.delete(new Subject(4,"Chem"));
        assertNotEquals(new Subject(4,"Chem"),crudDAOTemplate.findById(4));
    }

    @AfterAll
    static void tear() throws SQLException {
        crudDAOTemplate.delete(new Subject(1,"Math"));
        crudDAOTemplate.delete(new Subject(2,"Phy"));
        crudDAOTemplate.delete(new Subject(3,"Rus"));
        connectionPoint.closeConnection();
    }
}
