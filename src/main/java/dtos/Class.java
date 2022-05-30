package dtos;

public class Class {
    private String classId;
    private String teacherId;

    public Class() {
    }

    public Class(String classId, String teacherId) {
        this.classId = classId;
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "classId='" + classId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
