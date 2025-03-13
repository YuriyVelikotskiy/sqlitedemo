package models;

public class Room {
    private int room_id;
    private int room_number;

    public Room(int room_id, int room_number) {
        this.room_id = room_id;
        this.room_number = room_number;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_number='" + room_number + '\'' +
                '}';
    }
}
