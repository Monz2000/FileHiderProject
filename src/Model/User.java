package Model;

public class User {
	
	private String name;
	
	private String email;
	
	private String hashedpwd;
	
	private String salt;
	
	
	
	public User(String name,String email,String hpwd, String salt) {
		
		this.name=name;
		this.email=email;
		this.hashedpwd=hpwd;
		this.salt=salt;
		
	}
	
	public String getName() {
		
		return name;
	}
	
    public void setName(String name) {
		
		this.name= name;
	}
    
     public String getEmail() {
		
		return email;
	}
     
     public void setEmail(String email) {
 		
 		this.email= email;
 	}
     
     public String getHashedpwd() {
 		
 		return hashedpwd;
 	}
      
      public void sethpwd(String hpwd) {
  		
  		this.hashedpwd= hpwd;
  	}
 	
      public String getSalt() {
  		
  		return salt;
  	}
       
       public void setSalt(String salt) {
   		
   		this.salt= salt;
   	}
       
   
}
