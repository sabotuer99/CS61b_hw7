package hw7;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyHashMapTests {

	@Test
	public void Ctor_DefaultCtor_DoesNotCrash() {
		new MyHashMap<Integer, Integer>();
	}
	
	@Test
	public void Ctor_SizedCtor_DoesNotCrash() {
		new MyHashMap<Integer, Integer>(10);
	}
	
	@Test
	public void Ctor_SizeLoadCtor_DoesNotCrash() {
		new MyHashMap<Integer, Integer>(10, 2.0f);
	}
	
	@Test
	public void Put_AddValue_SizeIncreases() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(10, 2.0f);
		
		//Act
		map.put(1, 1);
		
		//Assert
		assertEquals(1, map.size());
	}
	
	@Test
	public void Put_AddDuplicateValue_SizeStaysSame() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(10, 2.0f);
		
		//Act
		map.put(1, 1);
		map.put(1, 1);
		
		//Assert
		assertEquals(1, map.size());
	}
	
	@Test
	public void Put_TwoUniqueKeys_SizeIsTwo() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(10, 2.0f);
		
		//Act
		map.put(1, 1);
		map.put(2, 1);
		
		//Assert
		assertEquals(2, map.size());
	}
	
	@Test
	public void Put_ExceedsLoadFactor_ResizeSuccessful() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 0.25f);
		
		//Act
		map.put(1, 1);
		map.put(2, 1);
		map.put(3, 1);
		map.put(4, 1);
		
		//Assert
		assertEquals(4, map.size());
	}
	
	@Test
	public void Get_NothingInMap_ReturnsNull() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		
		//Act
		Integer result = map.get(0);
		
		//Assert
		assertNull(result);
	}
	
	@Test
	public void Get_KeyNotInMap_ReturnsNull() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 1);
		
		//Act
		Integer result = map.get(0);
		
		//Assert
		assertNull(result);
	}

	
	@Test
	public void Get_KeyInMap_ReturnsValue() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		
		//Act
		int result = map.get(1);
		
		//Assert
		assertEquals(100, result);
	}
	
	@Test
	public void Get_KeyInMapWithOtherKeys_ReturnsValue() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 1.0f);
		map.put(1, 100);
		map.put(2, 101);
		map.put(3, 102);
		map.put(4, 103);
		map.put(5, 104);
		map.put(6, 105);
		
		//Act
		int result1 = map.get(1);
		int result2 = map.get(6);
		
		//Assert
		assertEquals(100, result1);
		assertEquals(105, result2);
	}
	
	//Interesting, but not a well formed unit test
/*	@Test
	public void ToString_RandomData_ReturnsMapStructuredString() {
		//Arrange
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>(1, 4.0f);
		
		for(int i = 0; i < 100; i += 1){
			map.put((int)(Math.random() * 1000), i);
			if(i % 20 == 0)
				System.out.print(map.toString() + "\n");
		}
		
	}*/
}
