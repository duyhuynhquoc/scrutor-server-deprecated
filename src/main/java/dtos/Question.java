package dtos;

public class Question {

    private String questionId;
    private String userId;
    private String questionContent;
    private String type;
    private int difficulty;
    private String questionTagId;
    private String tagId;
    private String questionOptionId;
    private String questionOptionContent;
    private boolean isCorrect;

    public Question() {
    }

    public Question(String questionId, String userId, String questionContent, String type, int difficulty) {
        this.questionId = questionId;
        this.userId = userId;
        this.questionContent = questionContent;
        this.type = type;
        this.difficulty = difficulty;
    }

    public Question(String questionTagId, String questionId, String tagId) {
        this.questionId = questionId;
        this.questionTagId = questionTagId;
        this.tagId = tagId;
    }

    public Question(String questionOptionId, String questionId, String questionOptionContent, boolean isCorrect) {
        this.questionId = questionId;
        this.questionOptionId = questionOptionId;
        this.questionOptionContent = questionOptionContent;
        this.isCorrect = isCorrect;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionTagId() {
        return questionTagId;
    }

    public void setQuestionTagId(String questionTagId) {
        this.questionTagId = questionTagId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(String questionOptionId) {
        this.questionOptionId = questionOptionId;
    }

    public String getQuestionOptionContent() {
        return questionOptionContent;
    }

    public void setQuestionOptionContent(String questionOptionContent) {
        this.questionOptionContent = questionOptionContent;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

}
