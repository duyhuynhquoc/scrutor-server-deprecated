package com.example.sructorserver;

import daos.QuestionDAO;
import dtos.Question;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/questions")
public class QuestionsAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Question> getBooks() {
        ArrayList<Question> list = QuestionDAO.getQuestions();
        return list;
    }
}