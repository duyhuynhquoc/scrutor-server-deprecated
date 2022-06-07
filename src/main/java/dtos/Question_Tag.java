package dtos;

public class Question_Tag {
    private String questionTagId;
    private String questionId;
    private String tagId;

    public Question_Tag() {
    }

    public Question_Tag(String questionTagId, String questionId, String tagId) {
        this.questionTagId = questionTagId;
        this.questionId = questionId;
        this.tagId = tagId;
    }

    public String getQuestionTagId() {
        return questionTagId;
    }

    public void setQuestionTagId(String questionTagId) {
        this.questionTagId = questionTagId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
