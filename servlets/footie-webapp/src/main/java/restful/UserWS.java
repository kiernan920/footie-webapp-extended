package restful;

import dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import remote.ClockInterface;
import tableEntities.User;
import utility.HashPassword;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;


import java.util.List;


@Path("/users")
@Stateless
@LocalBean
public class UserWS {

	@EJB
    private UserDAO userDAO;

//	@EJB
//	private Clock clock;

	//DEMO2d
	@EJB(lookup = "ejb://ejbs//RemoteClock!remote.ClockInterface")
	private ClockInterface clockInterface;

//	@EJB(lookup = "java:global/sameJvmEjb/Clock!remote.ClockInterfaceTwo")
//	private ClockInterfaceTwo sameJvmEjbClock;

	private Logger logger = LoggerFactory.getLogger(UserWS.class);

	@GET
	@Path("/test")
	public Response test() {
		logger.info("Time to meet " + clockInterface.getTimeToMeet());
	    //logger.info("Time to meet " + clock.getTimeToMeet());
//		logger.info("Time to meet 2 " + sameJvmEjbClock.getTimeToMeet());
		return Response.status(200).entity("{\"response\":\"testResponse\"}").build();
	}

	@GET
	@Path("/check/{username}/{password}")
	public Response checkUserNameAndPassword(
			@PathParam("username") String username,
			@PathParam("password") String password) {
		logger.info("verifying username and pasword");
		final String SALT = "SALT";
		HashPassword hp = new HashPassword();
		String hashedPassword = hp.hashPassword(password+SALT);
		String userType = userDAO.checkUsernameAndPasswordReturnType(
				username, hashedPassword);
		Response serverResponse = null;
		if (userType != null) {
			serverResponse = Response.status(200)
					.entity("{\"response\":\"" + userType + "\"}")
					.build();
		} else {
			serverResponse = Response.status(200)
					.entity("{\"response\":\"User Information Invalid\"}")
					.build();
		}
		return serverResponse;
	}
	

	@GET
	@Path("{firstname}/{lastname}/{gender}/{address}/{phone}/{email}/{username}/{password}/{usertype}")
	public Response getUserDatabyId(@PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname, @PathParam("gender") String gender,
			@PathParam("address") String address, @PathParam("phone") String phone, 
			@PathParam("email") String email, @PathParam("username") String username,
			@PathParam("password") String password, @PathParam("usertype") String usertype) {
		final String SALT = "SALT";
		User user = new User();
		user.setFirstname(firstname); user.setLastname(lastname); user.setGender(gender);
		user.setAddress(address); user.setPhone(phone); user.setEmail(email);
		user.setUsername(username);  user.setUsertype(usertype);
		HashPassword hp = new HashPassword();
		String hashedPassword = hp.hashPassword(password + SALT);
		user.setPassword(hashedPassword);
		boolean userNameExists = userDAO.checkUsernameExists(user);
		if(userNameExists)
			return Response.status(200).entity("{\"response\":\"UserName Already Exists\"}").build();
		
		userDAO.save(user);
		return Response.status(200).entity("{\"response\":\"User added to database\"}").build();
	}
	
	@POST
	public void addUserData(List<User> userList) {
		userDAO.addUserData(userList);
	}
}
