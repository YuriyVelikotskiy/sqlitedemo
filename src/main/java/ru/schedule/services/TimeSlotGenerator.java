package ru.schedule.services;

import ru.schedule.models.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotGenerator {
    static List <TimeSlot> timeSlots = new ArrayList<>();
    static int timeSlotNumber = 0;
    static LocalTime[] times = new LocalTime[]{
            LocalTime.of(8,0),
            LocalTime.of(8, 50),
            LocalTime.of(9,40),
            LocalTime.of(10,30),
            LocalTime.of(11,30),
            LocalTime.of(12,20),
            LocalTime.of(13,10),
            LocalTime.of(14,0),
            LocalTime.of(14,50),
            LocalTime.of(15,40),
            LocalTime.of(16,30),
            LocalTime.of(17,20)};

    public TimeSlotGenerator() {
        generator();
    }

    private static void generator(){
        for (int i = 0; i < 6; i++) {
            for (LocalTime time: times) {
                timeSlots.add(new TimeSlot(timeSlotNumber + i, DayOfWeek.of(i+1), time, time.plusMinutes(45)));
            }
        }
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }
}
