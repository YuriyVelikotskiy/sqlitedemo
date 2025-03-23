package services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.schedule.models.Teacher;
import ru.schedule.services.ConnectionPoint;
import ru.schedule.services.TeacherDAOImpl;
import ru.schedule.services.TemplateDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TeacherDAOTest {
    private static ConnectionPoint connectionPoint;
    private static TemplateDAO<Teacher, Integer> crudDAOTemplate;

    @BeforeAll
    static void setup() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:src\\main\\resources\\SchoolTest.db";
        connectionPoint = new ConnectionPoint();
        connectionPoint.openConnection(url);
        crudDAOTemplate = new TeacherDAOImpl(connectionPoint);
        crudDAOTemplate.create(new Teacher("Yuriy", "Yurievich", "Yuriev"));
        crudDAOTemplate.create(new Teacher("Andrey", "Andreevich", "Andreev"));
        crudDAOTemplate.create(new Teacher("Maxim", "Maxievich", "Maximov"));
    }

    @Test
    void findByIdTest() throws SQLException {
        assertEquals(new Teacher(1,"Yuriy", "Yurievich", "Yuriev"),crudDAOTemplate.findById(1));
        assertEquals(new Teacher(2,"Andrey", "Andreevich", "Andreev"),crudDAOTemplate.findById(2));
        assertEquals(new Teacher(3,"Maxim", "Maxievich", "Maximov"),crudDAOTemplate.findById(3));
        assertNotEquals(new Teacher(3,"Maxim", "Maxievich", "Maximov"),crudDAOTemplate.findById(1));
    }

    @Test
    void findAllTest() throws SQLException {
        List<Teacher> rooms = new ArrayList<>();
        rooms.add(new Teacher(1,"Yuriy", "Yurievich", "Yuriev"));
        rooms.add(new Teacher(2,"Andrey", "Andreevich", "Andreev"));
        rooms.add(new Teacher(3,"Maxim", "Maxievich", "Maximov"));
        assertEquals(rooms,crudDAOTemplate.findAll());
    }

    @Test
    void creatTest() throws SQLException {
        crudDAOTemplate.create(new Teacher(4,"Raxim", "Raxievich", "Raximov"));
        assertEquals(new Teacher(4,"Raxim", "Raxievich", "Raximov"),crudDAOTemplate.findById(4));
        crudDAOTemplate.delete(new Teacher(4,"Raxim", "Raxievich", "Raximov"));
    }

    @Test
    void updateTest() throws SQLException {
        crudDAOTemplate.create(new Teacher(4,"Raxim", "Raxievich", "Raximov"));
        crudDAOTemplate.update(new Teacher(4,"Taxim", "Taxievich", "Taximov"));
        assertEquals(new Teacher(4,"Taxim", "Taxievich", "Taximov"),crudDAOTemplate.findById(4));
        crudDAOTemplate.delete(new Teacher(4,"Taxim", "Taxievich", "Taximov"));
    }

    @Test
    void deleteTest() throws SQLException {
        crudDAOTemplate.create(new Teacher(4,"Raxim", "Raxievich", "Raximov"));
        crudDAOTemplate.delete(new Teacher(4,"Raxim", "Raxievich", "Raximov"));
        assertNotEquals(new Teacher(4,"Raxim", "Raxievich", "Raximov"),crudDAOTemplate.findById(4));
    }

    @AfterAll
    static void tear() throws SQLException {
        crudDAOTemplate.delete(new Teacher(1, "Yuriy", "Yurievich", "Yuriev"));
        crudDAOTemplate.delete(new Teacher(2, "Andrey", "Andreevich", "Andreev"));
        crudDAOTemplate.delete(new Teacher(3, "Maxim", "Maxievich", "Maximov"));
        connectionPoint.closeConnection();
    }
}
