package Main;

import java.sql.SQLException;


import View.WelcomeView;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           
		  WelcomeView w =new WelcomeView();
	        do {
	            WelcomeView welcomeView = new WelcomeView();
				try {
					welcomeView.WelcomeScreen();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } while (true);
	    }
	}


