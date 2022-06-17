package dtos;

public class QuestionBank {
    private Question question;
    private Option questionoption;

    public QuestionBank() {
    }

    public QuestionBank(Question question, Option questionoption) {
        this.question = question;
        this.questionoption = questionoption;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getQuestionoption() {
        return questionoption;
    }

    public void setQuestionoption(Option questionoption) {
        this.questionoption = questionoption;
    }
}
