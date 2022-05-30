package dtos;

public class Teacher {
    private String teacherId;
    private String fullName;
    private String password;
    private String email;

    public Teacher() {
    }

    public Teacher(String teacherId, String fullName, String password, String email) {
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
                "teacherId='" + teacherId + '\'' +
                ", fullname='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
