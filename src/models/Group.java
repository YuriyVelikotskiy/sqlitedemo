package models;

import java.util.Objects;

public class Group {
    private long groupId;
    private int groupYear;
    private String groupLetter;
    private int groupGrade;

    public Group(int groupYear, String groupLetter, int groupGrade) {
        this.groupYear = groupYear;
        this.groupLetter = groupLetter;
        this.groupGrade = groupGrade;
    }

    public Group(long groupId, int groupYear, String groupLetter, int groupGrade) {
        this.groupId = groupId;
        this.groupYear = groupYear;
        this.groupLetter = groupLetter;
        this.groupGrade = groupGrade;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public int getGroupYear() {
        return groupYear;
    }

    public void setGroupYear(int groupYear) {
        this.groupYear = groupYear;
    }

    public String getGroupLetter() {
        return groupLetter;
    }

    public void setGroupLetter(String groupLetter) {
        this.groupLetter = groupLetter;
    }

    public int getGroupGrade() {
        return groupGrade;
    }

    public void setGroupGrade(int groupGrade) {
        this.groupGrade = groupGrade;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupYear=" + groupYear +
                ", groupLetter=" + groupLetter +
                ", groupGrade=" + groupGrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupYear == group.groupYear && groupGrade == group.groupGrade && Objects.equals(groupLetter, group.groupLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupYear, groupLetter, groupGrade);
    }
}
