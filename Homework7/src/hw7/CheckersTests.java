package hw7;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CheckersTests {

	@Test
	public void PeiceHash_Default_ReturnsHashcode() {
		//Arrange
		Piece test1 = new Piece(false, 1, 1, null, false);
		Piece test2 = new Piece(true, 1, 1, null, false);
		Piece test3 = new Piece(false, 0, 1, null, false);
		Piece test4 = new Piece(false, 1, 0, null, false);
		Piece test5 = new Piece(false, 1, 1, null, true);
		
		//Act
		int result1 = test1.hashCode();
		int result2 = test2.hashCode();
		int result3 = test3.hashCode();
		int result4 = test4.hashCode();
		int result5 = test5.hashCode();
		
		//Assert
/*		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);*/
	}

	@Test
	public void BoardHash_DefaultVsOnePiece_DiffHashcodes() {
		//Arrange
		Board test1 = new Board();
		Piece[][] p1 = new Piece[test1.SIZE][test1.SIZE];
		
		p1[0][0] = new Piece(false, 0, 0, null, false);
		Board test2 = new Board(p1);
		
		//Act
		int result1 = test1.hashCode();
		int result2 = test2.hashCode();
		
		//Assert
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void BoardHash_TinyPieceVariation_DiffHashcodes() {
		//Arrange
		Piece[][] p1 = new Piece[8][8];	
		p1[0][0] = new Piece(false, 0, 0, null, false);
		Board test1 = new Board(p1);
		
		Piece[][] p2 = new Piece[8][8];	
		p2[0][0] = new Piece(true, 0, 0, null, false);
		Board test2 = new Board(p2);
		
		//Act
		int result1 = test1.hashCode();
		int result2 = test2.hashCode();
		
		//Assert
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void BoardHash_SamePieceAdjacent_DiffHashcodes() {
		//Arrange
		Piece[][] p1 = new Piece[8][8];	
		p1[0][0] = new Piece(false, 0, 0, null, false);
		Board test1 = new Board(p1);
		
		Piece[][] p2 = new Piece[8][8];	
		p2[0][1] = new Piece(false, 0, 1, null, false);
		Board test2 = new Board(p2);
		
		//Act
		int result1 = test1.hashCode();
		int result2 = test2.hashCode();
		
		//Assert
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void BoardHash_SamePieceOppositeCorners_DiffHashcodes() {
		//Arrange
		Piece[][] p1 = new Piece[8][8];	
		p1[0][0] = new Piece(false, 0, 0, null, false);
		Board test1 = new Board(p1);
		
		Piece[][] p2 = new Piece[8][8];	
		p2[7][7] = new Piece(false, 0, 1, null, false);
		Board test2 = new Board(p2);
		
		//Act
		int result1 = test1.hashCode();
		int result2 = test2.hashCode();
		
		//Assert
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void BoardHash_10000Boards_LessThan10PercentCollisions() {
		//Arrange
		ArrayList<Integer> hashes = new ArrayList<Integer>();
		Integer collisions = 0;
		
		//Act
		for(int i = 0; i < 10000; i += 1){
			Board test = new Board(randPieces(8));
			int testHash = test.hashCode();
			//System.out.println(" [" + testHash + "] ");
			if(hashes.contains(testHash))
				collisions += 1;
			else
				hashes.add(testHash);
		}
		//System.out.println(collisions);
		
		//Assert
		assertTrue(collisions < 1000);
	}
	
	private Piece[][] randPieces(int size){
		Piece[][] pieces = new Piece[size][size];
		for(int i = 0; i < size; i += 1){
			for(int j = 0; j < size; j += 1){
				if(Math.random() < (24.0/64.0)){
					pieces[i][j] = new Piece(randBool(), i, j, null, randBool());
				}
			}
		}
		return pieces;
	}
	
	private boolean randBool(){
		return Math.random() < 0.5;
	}

}
