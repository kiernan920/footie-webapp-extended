package dao;

import tableEntities.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class UserDAO extends GenericJPADAO<User, Long> {
	
	@PersistenceContext(unitName = "Tutorial")
	private EntityManager em;

    public UserDAO() {
		super(User.class);
	}
 
	public User getUserDataById(int id) {
        return em.find(User.class, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addUserData(List<User> userList) {
        for (User userEntity : userList) {
            em.persist(userEntity);
        }
    }
    
    public boolean checkUsernameExists(User user){
    	List<User> userList = findAll();
    	for(User u : userList){
    		if(u.getUsername().equals(user.getUsername())){
    			return true;
    		}
    	}
		return false;
    }
    
    public String checkUsernameAndPasswordReturnType(String username, String password){
    	String userType = null;
    	List<User> userList = findAll();
    	for(User u : userList){
    		if(u.getUsername().equals(username)){
    			if(u.getPassword().equals(password)){
    				userType = u.getUsertype();
        			return userType;
        		}
    		}	
    	}
		return userType;
    }
}