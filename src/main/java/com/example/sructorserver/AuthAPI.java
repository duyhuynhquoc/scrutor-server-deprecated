package com.example.sructorserver;

import daos.QuestionDAO;
import daos.UserDAO;
import dtos.Question;
import dtos.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/auth")
public class AuthAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User login(User u) {
        User user = UserDAO.getUserByEmailAndPassword(u.getEmail(), u.getPassword());
        return user;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createQuestion(User u){
        User user = UserDAO.createUser(u);
        return user;
    }

//
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public boolean updateQuestion(@Context HttpHeaders headers, Question q){
//        String teacherId = headers.getRequestHeader("userId").get(0);
//        boolean result = QuestionDAO.updateQuestion(q, teacherId);
//        return result;
//    }
//
//    @DELETE
//    @Path("{questionId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public boolean deleteQuestion(@Context HttpHeaders headers, @PathParam("questionId") String questionId){
//        String teacherId = headers.getRequestHeader("userId").get(0);
//        boolean result = QuestionDAO.deleteQuestion(questionId, teacherId);
//        return result;
//    }

}
