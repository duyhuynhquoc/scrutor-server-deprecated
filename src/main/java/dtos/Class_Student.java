package dtos;

public class Class_Student {
    private String classId;
    private String studentId;

    public Class_Student() {
    }

    public Class_Student(String classId, String studentId) {
        this.classId = classId;
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Class_StudentDTO{" +
                "classId='" + classId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
