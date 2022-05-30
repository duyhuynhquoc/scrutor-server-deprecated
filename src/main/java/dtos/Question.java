package dtos;

public class Question {

    private int questionId;
    private String content;
    private String type;
    private int difficulty;
    private String teacherId;

    public Question() {
    }

    public Question(int questionId, String content, String type, int difficulty, String teacherId) {
        this.questionId = questionId;
        this.content = content;
        this.type = type;
        this.difficulty = difficulty;
        this.teacherId = teacherId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" + "questionId=" + questionId + ", content=" + content + ", type=" + type + ", dificulty=" + difficulty + ", teacherId=" + teacherId + '}';
    }

}