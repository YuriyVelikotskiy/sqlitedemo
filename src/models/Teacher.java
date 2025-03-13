package models;

public class Teacher {
    private int teacher_id;
    private String name;
    private String second_name;
    private String last_name;

    public Teacher(int teacher_id, String name, String second_name, String last_name) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.second_name = second_name;
        this.last_name = last_name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "model.Teacher{" +
                "teacher_id=" + teacher_id +
                ", name='" + name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
