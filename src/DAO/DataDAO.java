package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import Model.Data;
import dbConnection.jdbcConnection;

public class DataDAO {
	
        public static void ShowHiddenFiles(String email) throws SQLException {
		
		String query=("select File_id,FileName from data where User_EmailAddress=?;");
		
		Connection con=jdbcConnection.getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1,email);
		
		ResultSet rs=pst.executeQuery();
		
		
		if(rs.next()){
			
			int FileId=rs.getInt(1);
			String fileName=rs.getString(4);
			
			System.out.println("fileId: "+ FileId + ","+ "fileName: "+ fileName);
		}
		else {
			System.out.println("No hidden files");
		}
		}
	
	

// to get hidden file

   public static List<Data> getHiddenFiles(String email) throws SQLException{
	   
	   String query ="Select * from data where User_EmailAddress=?;";
	   Connection con =jdbcConnection.getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1,email);
		
		ResultSet rs = pst.executeQuery();
		
		List<Data> files=new ArrayList<>();
		
		while(rs.next()) {
			
			int fileId = rs.getInt(1);
			String Name=rs.getString(2);
			String Email=rs.getString(3);
			String fileName=rs.getString(4);
			String FilePath=rs.getString(5);
			
			files.add(new Data(Name,fileId,Email,fileName,FilePath));
					
		}
		return files;
		}
   
   
   
   //ToHideFiles
   
   public static int hideFile(Data data) throws SQLException, IOException {
	    String query = "INSERT INTO data(User_Name, User_EmailAddress, FileName, FilePath, bin_data) VALUES (?, ?, ?, ?, ?)";
	    
	    Connection con = jdbcConnection.getConnection();
	     PreparedStatement pst = con.prepareStatement(query);
	         

	        // Set parameters for the prepared statement
	        pst.setString(1, data.getName());
	        pst.setString(2, data.getEmail());
	        pst.setString(3, data.getFileName());
	        pst.setString(4, data.getFilePath());
	        
	        File file= new File(data.getFilePath());
	        InputStream fs = new FileInputStream(data.getFilePath());
	        pst.setBinaryStream(5, fs,fs.available());

	        // Execute the update
	        int result = pst.executeUpdate();
            fs.close();
	        file.delete();
		     return result;
   }
   
   
   
   
   
   
	        //To restore hidden file to file system
   
   public static void restoreHiddenFile(int fileID) throws SQLException, IOException {
	    String query = "SELECT User_Name, User_EmailAddress, FileName, FilePath, bin_data FROM data WHERE File_id = ?;";

	    try (Connection con = jdbcConnection.getConnection();
	         PreparedStatement pst = con.prepareStatement(query)) {

	        pst.setInt(1, fileID);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                String name = rs.getString("User_Name");
	                String email = rs.getString("User_EmailAddress");
	                String fileName = rs.getString("FileName");
	                String filePath = rs.getString("FilePath");
	                Blob binData = rs.getBlob("bin_data");

	                // Restore file to the file system
	                try (InputStream fs = binData.getBinaryStream();
	                     FileOutputStream fo = new FileOutputStream(filePath)) {

	                    byte[] buffer = new byte[1024];
	                    int bytesRead;

	                    while ((bytesRead = fs.read(buffer)) != -1) {
	                        fo.write(buffer, 0, bytesRead);
	                    }
	                }

	                // Update the database to remove FilePath and bin_data
	                try (PreparedStatement deletePst = con.prepareStatement("DELETE FROM data WHERE File_id = ?;")) {
	                    deletePst.setInt(1, fileID);
	                    deletePst.executeUpdate();
	                }
	            } else {
	                System.out.println("File not found for File ID: " + fileID);
	            }
	        }
	    }
   }
}

	


   


