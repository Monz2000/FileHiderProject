package View;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import DAO.DataDAO;
import Model.Data;

public class UserMenu {
	
	 private String Name;
	private String email;

	  public  UserMenu(String email, String Name) {
	        this.email = email;
	        this.Name=Name;
	    }
       public UserMenu() {
    	   
       }
	    public void Home() throws SQLException, IOException {
	    	
	            System.out.println("Hey!! "+Name+" ...Choose your options below");
	            System.out.println("Press 1 to Hide a new file");
	            System.out.println("Press 2 to Show Hidden files ");
	            System.out.println("Press 3 to Restore a file");
	            System.out.println("Press 0 to exit");
	            System.out.println("******************************************************************");                   
	            Scanner sc = new Scanner(System.in);
	            int UserInput = sc.nextInt();
	            
	            switch(UserInput) {
	            
	            
	            case 1 -> {
	                sc.nextLine(); // Consume the newline character

	                System.out.println("Enter the File path:");
	                String userPath = sc.nextLine(); // Trim to remove leading/trailing whitespaces

	                // Print the absolute path for verification
	                File fileToHide = new File(userPath);
	                //System.out.println("Absolute Path: " + fileToHide.getAbsolutePath());

	                // Check if the file exists
	                if (!fileToHide.exists()) {
	                    System.out.println("File not found at the specified path.");
	                    break; // or handle accordingly
	                }

	                // a method in DataDAO to hide a file
	                Data file = new Data(this.Name, 100, this.email, fileToHide.getName(), userPath);
	                int FileHidden=DataDAO.hideFile(file) ;
	                if(FileHidden>0) {
					System.out.println("File hidden successfully.");
					
				
					}
	                else {
	                	System.out.println("Oooops....Try again!");
	                	
	                }
	                break;
	            }
	            
	            case 2 -> 
	            {
	            	List<Data> hiddenFiles = DataDAO.getHiddenFiles(this.email);

	                if (hiddenFiles.isEmpty()) {
	                    System.out.println("No hidden files found for the given email.");
	                   
	                } else {
	                    System.out.println("Hidden files for " + email + ":");
	                    for (Data file : hiddenFiles) {
	                        System.out.println("File ID: " + file.getId());
	                        System.out.println("Name: " + file.getName());
	                        System.out.println("Email: " + file.getEmail());
	                        System.out.println("FileName: " + file.getFileName());
	                        System.out.println("FilePath: " + file.getFilePath());
	                        System.out.println("-----------------------------");
	                    }
	                  
	                }
	            }
	            
	            case 3 -> {
	                System.out.println("Restoring Hidden Files...");

	                
	                List<Data> hiddenFiles = DataDAO.getHiddenFiles(this.email);

	                if (hiddenFiles.isEmpty()) {
	                    System.out.println("No hidden files found.");
	                   
	                } else {
	                    System.out.println("Select a file to restore:");
	                    for (Data hiddenFile : hiddenFiles) {
	                        System.out.println(hiddenFile.getId() + ". " + hiddenFile.getFileName());
	                    }
                        sc.nextLine();
                        System.out.println("Enter the file id to restore:");
	                    int selectedId = sc.nextInt();
	                    boolean fileFound = false;

	                    for (Data hiddenFile : hiddenFiles) {
	                        if (hiddenFile.getId() == selectedId) {
	                            
	                            	fileFound = true;
	                            	
	                            	if(fileFound) {
	                                  DataDAO.restoreHiddenFile (selectedId);
	                                  System.out.println("File restored successfully.");
	                                
	                            	}
	                            	else {
	                            		System.out.println(" Error restoring....");
	                            	
	                            	}
	                            
	                        }
	                        else {
	                        	System.out.println("Invalid file ID.");
	                        	
	                        }
	                    }
	                }
	            }
	                                
	                            	
	                            	
	                            
	            case 0 -> {
                    System.exit(0);
                }
	
	            }
	        }
}
	    
