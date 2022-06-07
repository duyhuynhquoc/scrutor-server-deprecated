package dtos;

public class QuestionBank {
    private Question question;
    private QuestionOption questionoption;

    public QuestionBank() {
    }

    public QuestionBank(Question question, QuestionOption questionoption) {
        this.question = question;
        this.questionoption = questionoption;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionOption getQuestionoption() {
        return questionoption;
    }

    public void setQuestionoption(QuestionOption questionoption) {
        this.questionoption = questionoption;
    }
}
