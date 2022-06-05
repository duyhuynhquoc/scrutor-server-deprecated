package dtos;

public class Question_Tag {
    private int questionTagId;
    private int questionId;
    private int tagId;

    public Question_Tag() {
    }

    public Question_Tag(int questionTagId, int questionId, int tagId) {
        this.questionTagId = questionTagId;
        this.questionId = questionId;
        this.tagId = tagId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getQuestionTagId() {
        return questionTagId;
    }

    public void setQuestionTagId(int questionTagId) {
        this.questionTagId = questionTagId;
    }
}
