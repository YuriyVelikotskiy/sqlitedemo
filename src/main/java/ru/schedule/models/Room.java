package ru.schedule.models;

import java.util.Objects;

public class Room {
    private long roomId;
    private int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(long room_id, int roomNumber) {
        this.roomId = room_id;
        this.roomNumber = roomNumber;
    }

    public long getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(roomNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
