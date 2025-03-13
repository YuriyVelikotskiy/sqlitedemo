package models;

public class Room {
    private int roomId;
    private int roomNumber;

    public Room(int room_id, int roomNumber) {
        this.roomId = room_id;
        this.roomNumber = roomNumber;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = this.roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
