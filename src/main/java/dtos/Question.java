package dtos;

import java.util.ArrayList;

public class Question {

    private String questionId;
    private String userId;
    private String content;
    private String type;
    private int difficulty;
    private ArrayList<Tag> tags;
    private ArrayList<QuestionOption> options;

    public Question() {
        this.questionId = "";
        this.userId = "";
        this.content = "";
        this.type = "";
        this.difficulty = 0;
        this.tags = new ArrayList<>();
        this.options = new ArrayList<>();
    }

    public Question(String questionId, String userId, String content, String type, int difficulty, ArrayList<Tag> tags, ArrayList<QuestionOption> options) {
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.difficulty = difficulty;
        this.tags = tags;
        this.options = options;
    }

    public Question(String questionId, String userId, String content, String type, int difficulty) {
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.difficulty = difficulty;
        this.tags = new ArrayList<>();
        this.options = new ArrayList<>();
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<QuestionOption> options) {
        this.options = options;
    }

    public boolean addOption(QuestionOption o){
        this.options.add(o);

        return true;
    }
}
