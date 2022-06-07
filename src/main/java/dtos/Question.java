package dtos;

public class Question {

    private String questionId;
    private String questionContent;
    private String type;
    private int difficulty;
    private String userId;

    public Question() {
    }

    public Question(String questionId, String questionContent, String type, int difficulty, String userId) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.type = type;
        this.difficulty = difficulty;
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}