package dtos;

import java.util.Date;
//import java.sql.Date;

public class Quiz {

    private String quizId;
    private String quizName;
    private String description;
    private Date startAt;
    private Date endAt;
    private int time;
    private String userId;

    public Quiz() {
    }

    public Quiz(String quizId, String quizName, String description, Date startAt, Date endAt, int time, String userId) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.time = time;
        this.userId = userId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
