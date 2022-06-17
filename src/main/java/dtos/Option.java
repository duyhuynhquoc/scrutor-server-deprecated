package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Option {

    private String optionId;
    private String questionId;
    private String content;
    private boolean isCorrect;

    public Option() {
        this.optionId = UUID.randomUUID().toString();
        this.questionId = null;
        this.content = "";
        this.isCorrect = false;
    }

    public Option(String content, boolean isCorrect) {
        this.optionId = UUID.randomUUID().toString();
        this.questionId = null;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public Option(String questionId, String content, boolean isCorrect) {
        this.optionId = UUID.randomUUID().toString();
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public Option(String optionId, String questionId, String content, boolean isCorrect) {
        this.optionId = optionId;
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
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

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
