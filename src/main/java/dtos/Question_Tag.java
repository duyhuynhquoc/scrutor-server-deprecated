package dtos;

public class Question_Tag {
    private int questionId;
    private int tagId;

    public Question_Tag() {
    }

    public Question_Tag(int questionId, int tagId) {
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
}
