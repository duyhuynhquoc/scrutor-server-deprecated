package apis;

import daos.UserDAO;
import dtos.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserAPI {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User loginAsTeacher(String userId, String password) throws Exception{
        UserDAO u = new UserDAO();

        User teacher = u.getTeacher(userId, password);
        User student = u.getStudent(userId, password);
        if(teacher != null){
            return teacher;
        }
        return student;
    }

}
