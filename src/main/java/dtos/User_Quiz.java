package dtos;

public class User_Quiz {
    private int quizId;
    private String userId;
    private int grade;

    public User_Quiz() {
    }

    public User_Quiz(int quizId, String userId, int grade) {
        this.quizId = quizId;
        this.userId = userId;
        this.grade = grade;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
