package hw7;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class UsernameBankTests {

	@Test
	public void Ctor_DefaultCtor_DoesNotCrash() {
		new UsernameBank();
	}
	
	@Test(expected=NullPointerException.class)
	public void GetEmail_UsernameIsNull_ThrowNullPointerException() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.getEmail(null);
		
		//Assert
		//nothing to do
	}
	
	@Test
	public void GetEmail_UsernameIsNotValid_ReturnNullRecordUsername() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		String result = sut.getEmail("zzzzz");
		
		//Assert
		assertNull(result);
		assertEquals(1, (int) sut.getBadUsernames().get("zzzzz"));
	}
	
	@Test
	public void GetEmail_EmailNotFound_ReturnNullRecordUsername() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		String result = sut.getEmail("zzz");
		
		//Assert
		assertNull(result);
		assertEquals(1, (int) sut.getBadUsernames().get("zzz"));
	}
	
	@Test
	public void GetEmail_EmailFound_ReturnEmail() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		sut.generateUsername("zzz", "test@test.com");
		
		//Act
		String result = sut.getEmail("zzz");
		
		//Assert
		assertEquals("test@test.com", result);
	}
	
	@Test(expected=NullPointerException.class)
	public void GetUsername_NullEmail_ThrowException() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.getUsername(null);
		
		//Assert
		//nothing to do
	}
	
	@Test
	public void GetUsername_InvalidEmail_ReturnNullRecordEmail() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		String result = sut.getUsername("test");
		
		//Assert
		assertNull(result);
		assertEquals(1, (int) sut.getBadEmails().get("test"));
	}
	
	@Test
	public void GetUsername_InvalidEmailTwice_ReturnNullRecordEmailTwice() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.getUsername("test");
		String result = sut.getUsername("test");
		
		//Assert
		assertNull(result);
		assertEquals(2, (int) sut.getBadEmails().get("test"));
	}
	
	@Test
	public void GetUsername_UsernameFound_ReturnUsernameString() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		sut.generateUsername("zzz", "test@test.com");
		
		//Act
		String result = sut.getUsername("test@test.com");
		
		//Assert
		assertEquals("zzz", result);
	}
	
	@Test
	public void GenerateUsername_ValidInput_EntryCreated() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.generateUsername("zzz", "test@test.com");
		
		//Assert
		//nothing to do
	}
	
	@Test(expected=NullPointerException.class)
	public void GenerateUsername_NullInput_ThrowException() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.generateUsername(null, "test@test.com");
		
		//Assert
		//nothing to do
	}

	@Test(expected=IllegalArgumentException.class)
	public void GenerateUsername_UsernameInvalid_ThrowException() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.generateUsername("zzzaaa", "test@test.com");
		
		//Assert
		//nothing to do
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void GenerateUsername_UsernameExists_ThrowException() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		sut.generateUsername("zzz", "test@test.com");
		sut.generateUsername("zzz", "test@test.com");
		
		//Assert
		//nothing to do
	}
	
	@Test
	public void SuggestUsername_EmptyBank_ReturnsValidUsername() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		//Act
		String result = sut.suggestUsername();
		
		//Assert
		assertTrue(regexMatch("[0-9A-Za-z]{2,3}", result));
	}
	
	@Test
	public void SuggestUsername_MostlyFullBank_ReturnsValidUsername() {
		//Arrange
		UsernameBank sut = new UsernameBank();
		
		for(int i = 0; i < 32; i += 1){                // this triple for loop populates
			for(int j = 0; j < 36; j += 1){            // the userbank with all the two char usernames
				for(int k = 0; k < 36; k += 1){        // and all the three digit usernames with a first 
		    		String un = new StringBuilder()    // char from A to V (so only W.. X.. Y.. and Z..
						.append(domainChar(i))         // usernames would be valid suggestions
						.append(domainChar(j))
						.append(domainChar(k))
						.toString();
		    		
					sut.generateUsername(un, un + "@test.com");
					
					if(i == 0){
						String sh = new StringBuilder()
						.append(domainChar(j))
						.append(domainChar(k))
						.toString();
			    		sut.generateUsername(sh, sh + "@test.com");
					}
				}
			}
		}
				
		//Act
		String result = sut.suggestUsername();
		System.out.println(result);
		
		//Assert
		assertTrue(regexMatch("[W-Zw-z][0-9A-Za-z]{2}", result));
	}
	
    private boolean regexMatch(String pattern, String value){
    	//Pattern p = Pattern.compile("[0-9A-Za-z]{2,3}");
    	//Matcher m = p.matcher(reqName);
    	Pattern p = Pattern.compile(pattern);
    	Matcher m = p.matcher(value);
    	return m.matches();
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
