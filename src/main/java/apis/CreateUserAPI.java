package apis;

import daos.UserDAO;
import dtos.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/createuser")
public class CreateUserAPI {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User createANewAccount(String userId, String fullname, String password, String email, String role) throws Exception{
        UserDAO u = new UserDAO();
        User newAccount = u.createAccount(userId, fullname, password, email, role);
        return newAccount;
    }
}
