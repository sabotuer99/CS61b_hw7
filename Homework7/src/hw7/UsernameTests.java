package hw7;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class UsernameTests {

	@Test
	public void HashCode_Called_ReturnsHashcode() {
		//Arrange
		Username username = new Username("abc");
		
		//Act
		int hash = username.hashCode();
		
		//Assert
		assertTrue(hash > 0);
	}
	
	@Test
	public void HashCode_CalledOnUniqueUsernames_ReturnsDifferentHashcodes() {
		//Arrange
		Username username1 = new Username("abc");
		Username username2 = new Username("zzz");
		
		//Act
		int hash1 = username1.hashCode();
		int hash2 = username2.hashCode();
		
		//Assert
		assertTrue(hash1 != hash2);
	}
	
	@Test
	public void HashCode_DifferOnlyInCase_ReturnsSameHashcodes() {
		//Arrange
		Username username1 = new Username("ZZZ");
		Username username2 = new Username("zzz");
		
		//Act
		int hash1 = username1.hashCode();
		int hash2 = username2.hashCode();
		
		//Assert
		assertTrue(hash1 == hash2);
	}

	@Test
	public void Username_DefaultCtor_DoesNotCrash() {
		new Username();
	}
	
	//slow test and I question it's reliability, though it proved useful during
	//development
	/*@Test
	public void Username_DefaultCtor_ProducesConsistentResults() {
		String output = "";
		
		//Generate a bunch of usernames
		for(int i = 0; i < 10000; i += 1){
			output += new Username().getUsername();
		}
		
		int totalCount = 0;
		int charSelection = 0;
		ArrayList<Integer> counts = new ArrayList<Integer>();
		
		//count the occurences of valid characters
		for(int i = 48; i <= 57; i += 1){
			int thisCount = StringUtils.countMatches(output, (char)i);
			totalCount += thisCount;
			counts.add(thisCount);
			charSelection += 1;
		}
		for(int i = 65; i <= 90; i += 1){
			int thisCount = StringUtils.countMatches(output, (char)i);
			totalCount += thisCount;
			counts.add(thisCount);
			charSelection += 1;
		}
		for(int i = 97; i <= 122; i += 1){
			int thisCount = StringUtils.countMatches(output, (char)i);
			totalCount += thisCount;
			counts.add(thisCount);
			charSelection += 1;
		}
		
		int averageCount = totalCount / charSelection;
		
		System.out.println(averageCount);
		//make sure every individual count is no more than 15% off of average
		for(Integer count : counts){
			assertTrue(count > averageCount * 0.85);
			assertTrue(count < averageCount * 1.15);
		}
	}*/
	
	@Test
	public void Username_DefaultCtor_GeneratesRandomUsername() {
		//Arrange
		
		//Act
		Username sut = new Username();
		
		//Assert
		assertNotNull(sut.getUsername());
	}
	
	@Test
	public void Username_DefaultCtor_Generates2To3CharUsername() {
		//Arrange
		
		//Act
		Username sut = new Username();
		
		//Assert
		assertTrue(sut.getUsername().length() >= 2 && sut.getUsername().length() <= 3);
	}

	@Test
	public void Username_PassValidUsername_Works() {
		new Username("abc");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Username_PassTooLongUsername_ThrowsException() {
		new Username("blarg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Username_PassTooShortUsername_ThrowsException() {
		new Username("a");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Username_PassUsernameWithSpecialChars_ThrowsException() {
		new Username("a;s");
	}
	
	@Test(expected=NullPointerException.class)
	public void Username_PassNullUsername_ThrowsException() {
		new Username(null);
	}

	@Test
	public void Equals_TwoEqualUsernames_ReturnsTrue() {
		//Arrange
		Username username1 = new Username("ZZZ");
		Username username2 = new Username("zzz");
		
		//Act
		boolean isEqual = username1.equals(username2);
		
		//Assert
		assertTrue(isEqual);
	}
	
	@Test
	public void Equals_TwoDifferentUsernames_ReturnsFalse() {
		//Arrange
		Username username1 = new Username("abc");
		Username username2 = new Username("zzz");
		
		//Act
		boolean isEqual = username1.equals(username2);
		
		//Assert
		assertFalse(isEqual);
	}

}
