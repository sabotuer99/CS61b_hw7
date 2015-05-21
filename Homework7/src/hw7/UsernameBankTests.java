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
	
    private boolean regexMatch(String pattern, String value){
    	//Pattern p = Pattern.compile("[0-9A-Za-z]{2,3}");
    	//Matcher m = p.matcher(reqName);
    	Pattern p = Pattern.compile(pattern);
    	Matcher m = p.matcher(value);
    	return m.matches();
    }
	
}
