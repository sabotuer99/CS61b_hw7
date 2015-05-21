package hw7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
    // YOUR CODE HERE
	private String username;

    public Username() {
        // YOUR CODE HERE
    	if(Math.random() > .6){
    		username = new StringBuilder()
    						.append(randomChar())
    						.append(randomChar())
    						.toString();
    	} else {
    		username = new StringBuilder()
							.append(randomChar())
							.append(randomChar())
							.append(randomChar())
							.toString();
    	}
    }

    public Username(String reqName) {
        // YOUR CODE HERE
    	if(reqName == null)
    		throw new NullPointerException("Requested username is null!");
    	
    	if(reqName.length() > 3 || reqName.length() < 2)
    		throw new IllegalArgumentException("Username must be between 2 and 3 characters long!");
    	
    	Pattern p = Pattern.compile("[0-9A-Za-z]{2,3}");
    	Matcher m = p.matcher(reqName);
    	if(m.matches()){
    		username = reqName;
    	} else {
    		throw new IllegalArgumentException("Username must contain only alphanumeric characters!");
    	}
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Username) {
        	Username other = (Username) o;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() { 
        // YOUR CODE HERE
		String hashString;
    	if(username.length() == 2)
    		hashString = " " + username.toUpperCase();
    	else
    		hashString = username.toUpperCase();
    	
    	int hashInt = 0;
    	
    	for(int i = 0; i <= 2; i += 1){
    		hashInt += (hashString.charAt(i) - 40) * (Math.pow(100, i));
    	}
    	 
        return hashInt;
    }

    @Override
    public String toString() {
    	return username;
    }
    
    public static void main(String[] args) {
        // You can put some simple testing here.
    }

	public String getUsername() {
		return username;
	}
	
	private char randomChar() {
		double select = Math.random();
/*		char rand;
		double charCount = 57 - 48 + 90 - 65 + 122 - 97;
		if(select < (57.0 - 48.0)/charCount){
	    	rand = (char) (Math.floor(Math.random() * (57 - 48 + 1)) + 48);
		} else if (select > 1.0 - (90.0 - 65.0)/charCount) {
	    	rand = (char) (Math.floor(Math.random() * (90 - 65 + 1)) + 65);		
		} else {
	    	rand = (char) (Math.floor(Math.random() * (122 - 97 + 1)) + 97);
		}*/
		int randIndex = (int)(Math.random() * 62);
		
		return domainChar(randIndex);
	}
	
	private char domainChar(int index){
		//int is between 0 and 61
		//values between 0 and 9 return 0 - 9
		//values between 10 and 35
		//values between 36 and 61
		if(index < 10){
			return (char)(index + 48);
		} else if (index < 36) {
			return (char)((index - 10) + 65);
		} else {
			return (char)((index - 36) + 97);
		}
	}
}