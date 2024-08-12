package Service;
import java.security.SecureRandom;
public class GenerateOTP {
	
	private static final int otpLength = 5;
	
	
	public static String getOTP() {
		
		StringBuilder otp = new StringBuilder();
		
		SecureRandom r =  new SecureRandom();
		
		for(int i =0;i<=otpLength;i++) {
			
			int digit= r.nextInt(10);
			
			otp.append(digit);
		}
		
		return otp.toString();
		
	}

}
