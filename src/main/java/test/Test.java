package test;

import daos.QuestionDAO;
import daos.UserDAO;
import dtos.Question;
import dtos.QuestionOption;
import utils.DBUtils;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        try {
            ArrayList<Question> list = QuestionDAO.getQuestions();
//            for (Question q: list) {
//                System.out.println(q.getContent());
//                for (QuestionOption o: q.getOptions()) {
//                    System.out.println(o.getContent());
//                }
//            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
