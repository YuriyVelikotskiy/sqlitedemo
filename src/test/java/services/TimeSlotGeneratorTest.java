package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.schedule.models.TimeSlot;
import ru.schedule.services.TimeSlotGenerator;

import java.util.Iterator;
import java.util.List;

public class TimeSlotGeneratorTest {
    static TimeSlotGenerator timeSlotGenerator;
    @BeforeAll
    static void setup(){
        timeSlotGenerator = new TimeSlotGenerator();

    }
    @Test
    void getTimeSlotsTest(){
        List <TimeSlot> timeSlots = timeSlotGenerator.getTimeSlots();
        for (TimeSlot timeSlot : timeSlots) {
            System.out.println(timeSlot.toString());
        }
    }
}
