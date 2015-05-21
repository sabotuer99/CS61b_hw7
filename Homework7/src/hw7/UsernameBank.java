package hw7;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameBank {

    // Instance variables (remember, they should be private!)
	HashMap<Username, String> emails;
	HashMap<String, Username> usernames;
	HashMap<String, Integer> badEmails;
	HashMap<String, Integer> badUsernames;
    // YOUR CODE HERE

    public UsernameBank() {
    	emails = new HashMap<Username, String>();
    	usernames = new HashMap<String, Username>();
        badEmails = new HashMap<String, Integer>();
        badUsernames = new HashMap<String, Integer>();
    }

    public void generateUsername(String username, String email) {
        if(username == null || email == null)
        	throw new NullPointerException();
        
        //this will throw IllegalArgumentException if username is invalid
        Username uname = new Username(username);
        
        if(emails.get(uname) != null){
        	throw new IllegalArgumentException("Username already exists!");
        } else {
        	emails.put(uname, email);
        	usernames.put(email, uname);
        }
    }

    public String getEmail(String username) {
        if(username == null)
        	throw new NullPointerException();
        
        String email;
        
        try{
        	email = emails.get(new Username(username));
        } catch(Exception ex){
        	email = null;
        }
        
        if(!regexMatch("[0-9A-Za-z]{2,3}", username) || email == null){
        	recordBadUsername(username.toString());
        	return null;
        }
        
        return email;
    }

    public String getUsername(String userEmail)  {
        if(userEmail == null)
        	throw new NullPointerException();
        
        Username username;
        
        try{
        	username = usernames.get(userEmail);
        } catch(Exception ex){
        	username = null;
        }
        
        if(!regexMatch(".*[@].*", userEmail) || username == null){
        	recordBadEmail(userEmail);
        	return null;
        }
        
        return username.toString();
    }

    public Map<String, Integer> getBadEmails() {
        return badEmails;
    }

    public Map<String, Integer> getBadUsernames() {
        return badUsernames;
    }

    public String suggestUsername() {
        // YOUR CODE HERE
    	for(int i = 0; i < 10000; i += 1){
	    	Username attempt = new Username();   	
	    	if(emails.get(attempt) == null){
	    		System.out.println("Suggestion attempts: " + (i+1));
	    		return attempt.getUsername();
	    	}
    	}
    		return null;
    }

    // The answer is somewhere in between 3 and 1000.
    public static final int followUp() {
        // YOUR CODE HERE
    	// there are 62 characters, which can be recorded in 6 bits
    	// an int has 32 bits, so you could encode up to 5 characters
    	// without overlap, though maybe more with compression...
        return 5;
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadUsername(String username) {
    	updateDatabase(badUsernames, username);
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {
    	updateDatabase(badEmails, email);
    }
    
    private void updateDatabase(Map<String, Integer> db, String value){
        Integer count = db.get(value);
    	
    	if(count == null){
    		db.put(value, 1);
    	} else {
    		db.put(value, count + 1);
    	}
    }
    
    private boolean regexMatch(String pattern, String value){
    	//Pattern p = Pattern.compile("[0-9A-Za-z]{2,3}");
    	//Matcher m = p.matcher(reqName);
    	Pattern p = Pattern.compile(pattern);
    	Matcher m = p.matcher(value);
    	return m.matches();
    }
}