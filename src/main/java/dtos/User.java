package dtos;

public class User {

    private String userId;
    private String fullName;
    private String password;
    private String email;
    private String role;
    private String classId;
    private String quizId;
    private String userQuizId;
    private int grade;

    public User() {
    }

    public User(String userId, String fullName, String password, String email, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.role = role;
    }   

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
