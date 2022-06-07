package dtos;

public class User_Class {
    private String userClassId;
    private String userId;
    private String classId;

    public User_Class() {
    }

    public User_Class(String userClassId, String userId, String classId) {
        this.userClassId = userClassId;
        this.userId = userId;
        this.classId = classId;
    }

    public String getUserClassId() {
        return userClassId;
    }

    public void setUserClassId(String userClassId) {
        this.userClassId = userClassId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
