package application;

public class LockV2 {
    
    public static String lockString(String fullName) {
    	
    	try {
    		//Creates a character array to store and convert Full Name;
    		char unique[] = fullName.toCharArray();
    		
    		//Allows us to modify the string. In this case we can modify, insert or delete characters.
    		StringBuilder sb = new StringBuilder();
    		
    		
    		for(char displace : unique) {
    			//5 characters from the original set of chars.
    			displace += 5;
    			
    			//concatenates characters from String Builder.
    			sb.append(displace);
    		}
    		return sb.toString();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    	//return values are returned as output of this method.
    	return " ";
    }
}
