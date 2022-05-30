package dtos;

public class Question_Quiz {
    private int questionId;
    private int quizId;

    public Question_Quiz() {
    }

    public Question_Quiz(int questionId, int quizId) {
        this.questionId = questionId;
        this.quizId = quizId;
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
}
