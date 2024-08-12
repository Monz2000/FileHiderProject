package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcConnection {
	
	    private static final String url="jdbc:mysql://localhost:3306/HideFileProject";
	    private static final String username="root";
	    private static final String password= "Monika02!";
		
	    public static Connection getConnection() throws SQLException {
			//System.out.println("connected");
	    
			return DriverManager.getConnection(url,username,password);
		}

	}



