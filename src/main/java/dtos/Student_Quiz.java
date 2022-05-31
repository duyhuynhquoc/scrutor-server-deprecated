package dtos;

public class Student_Quiz {
    private int quizId;
    private String studentId;
    private int grade;

    public Student_Quiz() {
    }

    public Student_Quiz(int quizId, String studentId, int grade) {
        this.quizId = quizId;
        this.studentId = studentId;
        this.grade = grade;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
