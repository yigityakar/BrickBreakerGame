package domain.validity;

public class InputValidity {

	
	public boolean isValidObstacleNumber(String str) { 
		  try {  
		    int input = Integer.parseInt(str);  
		    if (input < 0 || input > 250) {
		    	return false;
		    }
		    
		    
		    return true;
		  } catch(NumberFormatException e){  
		    
			return false;  
		  }  
	}
	
	public boolean isValidCredential(String username, String password) throws NullPointerException {
		String illegalCharacters = ". # % & { } < > * ? / $ ! : @ | = ` ' \\ \" ";
		
		if(username=="") {
			
			throw new NullPointerException();
		}
		
		
		int index =0;
		while(index<illegalCharacters.length()) {
			if(username.contains(illegalCharacters.substring(index, index+1))   || password.contains(illegalCharacters.substring(index, index+1))) {
				return false;
			}	
			
			index++;
		}
		
		if(password.toLowerCase().contentEquals(password)) {
			
			return false;
		}
		
		
		
		
		
		
		return true;
	}
}
