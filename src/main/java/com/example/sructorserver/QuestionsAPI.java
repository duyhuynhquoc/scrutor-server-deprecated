package com.example.sructorserver;

import daos.QuestionDAO;
import dtos.Question;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/questions")
public class QuestionsAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Question> getQuestions(@Context HttpHeaders headers) {
        String teacherId = headers.getRequestHeader("userId").get(0);
        ArrayList<Question> list = QuestionDAO.getQuestions(teacherId);
        return list;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Question createQuestion(@Context HttpHeaders headers, Question q){
        String teacherId = headers.getRequestHeader("userId").get(0);
        Question newQuestion = QuestionDAO.createQuestion(q, teacherId);
        return newQuestion;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateQuestion(@Context HttpHeaders headers, Question q){
        String teacherId = headers.getRequestHeader("userId").get(0);
        boolean result = QuestionDAO.updateQuestion(q, teacherId);
        return result;
    }

    @DELETE
    @Path("{questionId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteQuestion(@Context HttpHeaders headers, @PathParam("questionId") String questionId){
        String teacherId = headers.getRequestHeader("userId").get(0);
        boolean result = QuestionDAO.deleteQuestion(questionId, teacherId);
        return result;
    }

}
