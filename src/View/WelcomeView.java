package View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import DAO.UserDAO;
import Model.User;
import Service.GenerateOTP;
import Service.sendOTP;

public class WelcomeView {
	
	public void WelcomeScreen() throws SQLException {
		
		
	        Scanner sc = new Scanner(System.in);
	        System.out.println("******************************************************************************");
	        System.out.println("Welcome to the FileHider App");
	        System.out.println("Press 1 to Login");
	        System.out.println("Press 2 to SignUp");
	        System.out.println("Press 0 to Exit");
	        System.out.println("******************************************************************************");
	        int UserInput=sc.nextInt();
	        
	        switch(UserInput) {
	        
	        case 1 -> ToLogin(); 

	        case 2 -> ToSignUp();
	        
	        case 0 -> System.exit(0);
	        }
	}
	 //Methods for the above:       
	 public static void ToLogin() {
	          
		    Scanner sc = new Scanner(System.in);
		    
		    System.out.println("Enter your name:");
		    
		    String name = sc.nextLine();
		    
            System.out.println("Enter your Email:");
		    
		    String UserEmail = sc.nextLine();
            System.out.println("Enter your password:");
		    
		    String UserPwd = sc.nextLine();
		    
		    try {
				if(UserDAO.isExists(UserEmail)&& UserDAO.isCorrect(UserEmail, UserPwd)) {
					
					
					System.out.println("hey "+name+"....Welcome back!!..." );
					UserMenu userMenu = new UserMenu(UserEmail,name);
					try {
						userMenu.Home();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
					else if(UserDAO.isExists(UserEmail)||UserDAO.isCorrect(UserEmail, UserPwd)) {
						
						System.out.println("Invalid UserName or Password" );
					}
					else {
						
						System.out.println("User doesnt exist ,Please sign up!");
				    }
					}
					
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	}
	 
	 //To signup
	 public static void ToSignUp() throws SQLException {
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println("Sign up to start the app! ");
		 System.out.println("Enter you name:");
		 String Username=sc.nextLine();
		 System.out.println("Enter you email:");
		 String Useremail=sc.nextLine();
		
		if(UserDAO.isExists(Useremail))	{
			System.out.println("User already exists!, please try again!");
			}
		else {
				
	     String GetOTP = GenerateOTP.getOTP();
	     sendOTP.SendOTP(Useremail, GetOTP);
	     
				 System.out.println("Please enter the OTP:");
				 String UserOTP = sc.nextLine();
				 
				 if(UserOTP.equals(GetOTP)) {
					 
					 System.out.println("Enter your new password:");
					 String Userpwd=sc.nextLine();
					 System.out.println("Please enter the password again to confirm:");
					 String Confirmpwd=sc.nextLine();
					 
					 if(Userpwd.equals(Confirmpwd)) {

			                // Generate a unique salt for the user
			                String salt = BCrypt.gensalt();

			                // Hash the password using BCrypt and the generated salt
			                String hashedPwd = BCrypt.hashpw(Userpwd, salt);

			                // Now you can store 'hashedPwd' and 'salt' in your database
			                // along with other user details like 'name', 'email', etc.
						 
						 User user = new User(Username, Useremail,hashedPwd ,salt );
						
						 user.sethpwd(hashedPwd);
						    user.setSalt(salt);
						
						int SavedUser=UserDAO.SaveUser(user);
						
				            if(SavedUser>0) {
				                System.out.println("User registered Successfully");
				            }else{
				            	System.out.println("Error Occured,Please try again");
				            }
				        } else {
				            System.out.println("Password Mismatch,Please enter the correct password");
				        }
				 }
				 else {
					 System.out.println("Invalid OTP");
				 }
		}
				 
			 }
		 } 


		 
		

		 
		 
	 
