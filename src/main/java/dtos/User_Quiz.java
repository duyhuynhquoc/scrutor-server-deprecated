package dtos;

public class User_Quiz {
    private String userQuizId;
    private String quizId;
    private String userId;
    private String grade;

    public User_Quiz() {
    }

    public User_Quiz(String userQuizId, String quizId, String userId, String grade) {
        this.userQuizId = userQuizId;
        this.quizId = quizId;
        this.userId = userId;
        this.grade = grade;
    }

    public String getUserQuizId() {
        return userQuizId;
    }

    public void setUserQuizId(String userQuizId) {
        this.userQuizId = userQuizId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
