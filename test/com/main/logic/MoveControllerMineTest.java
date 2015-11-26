package com.main.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MoveControllerMineTest {
	MoveController testObj;
	String testStr;
	@Before
	public void setUp() throws Exception {
	testObj = new MoveController();
	testStr = "";
	}

	@After
	public void tearDown() throws Exception {
	testObj = null;
	}

	@Test
	public void testBoardConf() {
		testStr=testObj.boardConfGenerator("1001101", "2022002");
		assertEquals("hbbrbbhrhhbbbbrhhbrhhbrbrhrhhbhhhhbbbbhbrhbhbbrhb", testStr);
	}
	
	@Test
	public void testBeadConf(){
		testStr = testObj.beadConfGenerator("000010002000000000012000000000000000010000000000", "hbbrbbhrhhbbbbrhhbrhhbrbrhrhhbhhhhbbbbhbrhbhbbrhb");
		assertEquals("000010000000000000000000000000000000010000000000", testStr);
	}
	
	//Check Elimination Two Players 
	@Test
	public void testCheckEliminationTwoPlayersOne(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("1");
		testBoard.setPlayerTwo(true);
		testBoard.setPlayerOne(false);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.two);

	}
	
	@Test
	public void testCheckEliminationTwoPlayersTwo(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("2");
		testBoard.setPlayerTwo(false);
		testBoard.setPlayerOne(true);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.one);
		
	}

	//Check Elimination Three Players
	
	@Test
	public void testCheckEliminationThreePlayersOne(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("1");
		testBoard.setPlayerTwo(true);
		testBoard.setPlayerOne(false);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.two);

	}
	
	@Test
	public void testCheckEliminationThreePlayersTwo(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("2");
		testBoard.setPlayerTwo(false);
		testBoard.setPlayerThree(true);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.three);

	}
	
	@Test
	public void testCheckEliminationThreePlayersThree(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("3");
		testBoard.setPlayerThree(false);
		testBoard.setPlayerOne(true);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.one);
		
	}
	
	//Check Elimination Four Players
	
	@Test
	public void testCheckEliminationFourPlayersOne(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("1");
		testBoard.setPlayerTwo(true);
		testBoard.setPlayerOne(false);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.two);
		
	}
	@Test
	public void testCheckEliminationFourPlayersTwo(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("2");
		testBoard.setPlayerTwo(false);
		testBoard.setPlayerThree(true);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.three);
		
	}
	
	@Test
	public void testCheckEliminationFourPlayersThree(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("3");
		testBoard.setPlayerThree(false);
		testBoard.setPlayerFour(true);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.four);
		
	}
	
	@Test
	public void testCheckEliminationFourPlayersFour(){
		Board testBoard=new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("4");
		testBoard.setPlayerFour(false);
		testBoard.setPlayerOne(true);
		testObj.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.one);

	}
	
	
	
}
