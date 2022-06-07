package dtos;

public class Question_Quiz {
    private String questionQuizId;
    private String questionId;
    private String quizId;
    private int point;

    public Question_Quiz() {
    }

    public Question_Quiz(String questionQuizId, String questionId, String quizId, int point) {
        this.questionQuizId = questionQuizId;
        this.questionId = questionId;
        this.quizId = quizId;
        this.point = point;
    }

    public String getQuestionQuizId() {
        return questionQuizId;
    }

    public void setQuestionQuizId(String questionQuizId) {
        this.questionQuizId = questionQuizId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
