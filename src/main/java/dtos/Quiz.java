package dtos;

import java.util.Date;
//import java.sql.Date;

public class Quiz {

    private int quizId;
    private String content;
    private Date startAt;
    private Date endAt;
    private int time;
    private String teacherId;

    public Quiz() {
    }

    public Quiz(int quizId, String content, Date startAt, Date endAt, int time, String teacherId) {
        this.quizId = quizId;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.time = time;
        this.teacherId = teacherId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "quizId=" + quizId +
                ", content='" + content + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", time=" + time +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
