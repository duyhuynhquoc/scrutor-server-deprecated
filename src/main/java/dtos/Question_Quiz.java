package dtos;

public class Question_Quiz {
    private int questionQuizId;
    private int questionId;
    private int quizId;
    private int point;

    public Question_Quiz() {
    }

    public Question_Quiz(int questionQuizId, int questionId, int quizId, int point) {
        this.questionQuizId = questionQuizId;
        this.questionId = questionId;
        this.quizId = quizId;
        this.point = point;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionQuizId() {
        return questionQuizId;
    }

    public void setQuestionQuizId(int questionQuizId) {
        this.questionQuizId = questionQuizId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
