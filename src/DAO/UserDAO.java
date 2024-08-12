package DAO;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

import Model.User;
import dbConnection.jdbcConnection;

public class UserDAO {
	

	//checking if the email exists or not
	public static boolean isExists(String email) throws SQLException {
		
		String query = "select User_EmailAddress from users;";
		
		Connection con= jdbcConnection.getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		
		ResultSet rs =pst.executeQuery();
		
		while(rs.next())
		{
			String e =rs.getString(1);
			
			if(e.equals(email)) {
				
				return true;
			}
			
		}
		return false;
		
	}
	// saving user info
	public static int SaveUser(User user) throws SQLException {
		
		String query="insert into users values(default,?,?,?,?);";
		Connection con= jdbcConnection.getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1,user.getName());
		pst.setString(2,user.getEmail());
		pst.setString(3,user.getHashedpwd());
		pst.setString(4,user.getSalt());
		
		return pst.executeUpdate();
	}
	
	public static boolean isCorrect(String email, String password) throws SQLException {
	    String query = "select hashed_password from users where User_EmailAddress = ?";
	    
	    try (Connection con = jdbcConnection.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {
	        
	        pst.setString(1, email);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	        	
	        	 
	                  String storedHashedPassword = rs.getString("hashed_password");
	                  return BCrypt.checkpw(password, storedHashedPassword);
	              }
	        	
	        }
	    
	    
	    return false;
	}
}


