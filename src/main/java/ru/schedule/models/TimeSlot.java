package ru.schedule.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlot {
    private final long slotId;
    private final DayOfWeek dayOfWeek;
    private final LocalTime startOfLesson;
    private final LocalTime endOfLesson;

    public TimeSlot(long slotId, DayOfWeek dayOfWeek, LocalTime startOfLesson, LocalTime endOfLesson) {
        this.slotId = slotId;
        this.endOfLesson = endOfLesson;
        this.dayOfWeek = dayOfWeek;
        this.startOfLesson = startOfLesson;
    }

    public long getSlotId() {
        return slotId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartOfLesson() {
        return startOfLesson;
    }

    public LocalTime getEndOfLesson() {
        return endOfLesson;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s - %s", dayOfWeek,startOfLesson, endOfLesson);
    }
}
