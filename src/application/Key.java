package application;

public class Key {

	//Create an instance variable.
//    private String fullName;
//
//    Key(String fullName) {
//        this.fullName = fullName;
//    }
//    public void setfullName(String fullName) {
//        this.fullName = fullName;
//    }
    
    public static String unlockString(String fullName) {
    	
    	try {
    		char unique[] = fullName.toCharArray();
    		StringBuilder sb = new StringBuilder();
    		for(char displace : unique) {
    			displace -= 5;
    			sb.append(displace);
    			
    		}
    		return sb.toString();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return " ";
    }
}
