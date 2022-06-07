package dtos;

public class QuestionOption {

    private String questionOptionId;
    private String questionId;
    private String questionOptionContent;
    private boolean isCorrect;

    public QuestionOption() {
    }

    public QuestionOption(String questionOptionId, String questionId, String questionOptionContent, boolean isCorrect) {
        this.questionOptionId = questionOptionId;
        this.questionId = questionId;
        this.questionOptionContent = questionOptionContent;
        this.isCorrect = isCorrect;
    }

    public String getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(String questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionOptionContent() {
        return questionOptionContent;
    }

    public void setQuestionOptionContent(String questionOptionContent) {
        this.questionOptionContent = questionOptionContent;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
