package Service;

import java.sql.SQLException;



import DAO.UserDAO;
import Model.User;

public class UserService {
	
	  public static Integer saveUserInfo(User user) throws SQLException{
		 
		        if (UserDAO.isExists(((User) user).getEmail())) {
		            return 0;
		        } else {
		            return UserDAO.SaveUser(user);
		        }
		}
	}


