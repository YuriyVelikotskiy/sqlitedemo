package models;

public class Teacher {
    private int teacherId;
    private String name;
    private String secondName;
    private String lastName;

    public Teacher(int teacherId, String name, String secondName, String lastName) {
        this.teacherId = teacherId;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "model.Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
