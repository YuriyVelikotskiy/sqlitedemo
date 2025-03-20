package ru.schedule.models;

import java.util.Objects;

public class Teacher {
    private long teacherId;
    private String name;
    private String secondName;
    private String lastName;

    public Teacher(String name, String secondName, String lastName) {
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public Teacher(long teacherId, String name, String secondName, String lastName) {
        this.teacherId = teacherId;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public long getTeacherId() {
        return teacherId;
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
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(name, teacher.name) && Objects.equals(secondName, teacher.secondName) && Objects.equals(lastName, teacher.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, lastName);
    }
}
