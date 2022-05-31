package dtos;

public class Student {
    private String studentId;
    private String fullName;
    private String password;
    private String email;

    public Student() {
    }

    public Student(String studentId, String fullName, String password, String email) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "teacherId='" + studentId + '\'' +
                ", fullname='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
