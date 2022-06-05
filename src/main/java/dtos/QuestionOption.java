package dtos;

public class QuestionOption {

    private String questionOptionId;
    private String questionId;
    private String content;
    private String isCorrect;

    public QuestionOption() {
    }

    public QuestionOption(String questionOptionId, String questionId, String content, String isCorrect) {
        this.questionOptionId = questionOptionId;
        this.questionId = questionId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }
}
