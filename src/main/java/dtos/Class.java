package dtos;

public class Class {
    private String classId;
    private String role;

    public Class() {
    }

    public Class(String classId, String role) {
        this.classId = classId;
        this.role = role;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId='" + classId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
