package test;

import daos.QuestionDAO;
import dtos.Question;
import dtos.Option;
import dtos.Tag;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        try {
//            ArrayList<Question> list = QuestionDAO.getQuestions();
//            for (Question q: list) {
//                System.out.println(q.getContent());
//                for (QuestionOption o: q.getOptions()) {
//                    System.out.println(o.getContent());
//                }
//            }



//            Question q = new Question("c59f56e6-edf6-11ec-aa55-025171f2d1bb", "2 + 2 = ?", "SAMC", 20);
//            ArrayList<Tag> tags = new ArrayList<>();
//            ArrayList<Option> options = new ArrayList<>();
//            tags.add(new Tag("math"));
//            tags.add(new Tag("prj321"));
//            tags.add(new Tag("servlet"));
//            options.add(new Option(q.getQuestionId(), "4", true));
//            options.add(new Option(q.getQuestionId(), "5", false));
//            options.add(new Option(q.getQuestionId(), "6", false));
//            q.setTags(tags);
//            q.setOptions(options);
//
//            Question insertedQuestion = QuestionDAO.createQuestion(q, "c59f56e6-edf6-11ec-aa55-025171f2d1bb");
//            System.out.println(insertedQuestion.getQuestionId());


        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
