package com.main.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMoveController {
	
	MoveController tester;
	Board testBoard;
	
	@Before
	public void setUp(){
		tester = new MoveController();
		testBoard= new Board();
	}
	
	

	@After
	public void tearDown(){
		tester =null;
		testBoard=null;
	}
	
	
	
	@Test
	public void testBarConfGeneratorHorizontalOneInner() {
		assertEquals("xoxoxox",tester.barConfGenerator("h", "1", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalOneCenter() {
		assertEquals("oxoxoxo",tester.barConfGenerator("h", "1", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalOneOuter() {
		assertEquals("xoxoxox",tester.barConfGenerator("h", "1", '2'));	
	}

	
	@Test
	public void testBarConfGeneratorHorizontalTwoInner() {
		assertEquals("xooxoox",tester.barConfGenerator("h", "2", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalTwoCenter() {
		assertEquals("ooxooxo",tester.barConfGenerator("h", "2", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalTwoOuter() {
		assertEquals("oxooxox",tester.barConfGenerator("h", "2", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalThreeInner() {
		assertEquals("xoooxoo",tester.barConfGenerator("h", "3", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalThreeCenter() {
		assertEquals("oooxooo",tester.barConfGenerator("h", "3", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalThreeOuter() {
		assertEquals("ooxooox",tester.barConfGenerator("h", "3", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalFourInner() {
		assertEquals("xoxoxox",tester.barConfGenerator("h", "4", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalFourCenter() {
		assertEquals("oxoxoxo",tester.barConfGenerator("h", "4", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalFourOuter() {
		assertEquals("xoxoxox",tester.barConfGenerator("h", "4", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalFiveInner() {
		assertEquals("xoooooo",tester.barConfGenerator("h", "5", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalFiveCenter() {
		assertEquals("ooooooo",tester.barConfGenerator("h", "5", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalFiveOuter() {
		assertEquals("oooooox",tester.barConfGenerator("h", "5", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalSixInner() {
		assertEquals("xxoooxo",tester.barConfGenerator("h", "6", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalSixCenter() {
		assertEquals("xoooxox",tester.barConfGenerator("h", "6", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalSixOuter() {
		assertEquals("oooxoxx",tester.barConfGenerator("h", "6", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalSevenInner() {
		assertEquals("xooxoxo",tester.barConfGenerator("h", "7", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalsevenCenter() {
		assertEquals("ooxoxox",tester.barConfGenerator("h", "7", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorHorizontalSevenOuter() {
		assertEquals("oxoxoxx",tester.barConfGenerator("h", "7", '2'));	
	}
	
	
	@Test
	public void testBarConfGeneratorVerticalOneInner() {
		assertEquals("xooooxo",tester.barConfGenerator("v", "1", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalOneCenter() {
		assertEquals("ooooxox",tester.barConfGenerator("v", "1", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalOneOuter() {
		assertEquals("oooxoxx",tester.barConfGenerator("v", "1", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalTwoInner() {
		assertEquals("xoooxxo",tester.barConfGenerator("v", "2", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalTwoCenter() {
		assertEquals("oooxxoo",tester.barConfGenerator("v", "2", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalTwoOuter() {
		assertEquals("ooxxoox",tester.barConfGenerator("v", "2", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalThreeInner() {
		assertEquals("xoxooxo",tester.barConfGenerator("v", "3", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalThreeCenter() {
		assertEquals("oxooxox",tester.barConfGenerator("v", "3", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalThreeOuter() {
		assertEquals("xooxoxx",tester.barConfGenerator("v", "3", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalFourInner() {
		assertEquals("xooxxoo",tester.barConfGenerator("v", "4", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalFourCenter() {
		assertEquals("ooxxooo",tester.barConfGenerator("v", "4", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalFourOuter() {
		assertEquals("oxxooox",tester.barConfGenerator("v", "4", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalFiveInner() {
		assertEquals("xxoooxo",tester.barConfGenerator("v", "5", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalFiveCenter() {
		assertEquals("xoooxox",tester.barConfGenerator("v", "5", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalFiveOuter() {
		assertEquals("oooxoxx",tester.barConfGenerator("v", "5", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalSixInner() {
		assertEquals("xxooooo",tester.barConfGenerator("v", "6", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalSixCenter() {
		assertEquals("xooooox",tester.barConfGenerator("v", "6", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalSixOuter() {
		assertEquals("oooooxx",tester.barConfGenerator("v", "6", '2'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalSevenInner() {
		assertEquals("xooxoox",tester.barConfGenerator("v", "7", '0'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalSevenCenter() {
		assertEquals("ooxooxo",tester.barConfGenerator("v", "7", '1'));	
	}
	
	@Test
	public void testBarConfGeneratorVerticalSevenOuter() {
		assertEquals("oxooxox",tester.barConfGenerator("v", "7", '2'));	
	}
	
	
	
	@Test
	public void testSetMovingPlayerA(){
		
		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("1");
		tester.setMovingPlayer(testBoard);
		assertEquals("2",testBoard.getMovingPlayer());
	}
	
	@Test
	public void testSetMovingPlayerB(){
		
		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("2");
		tester.setMovingPlayer(testBoard);
		assertEquals("1",testBoard.getMovingPlayer());
	}
	
	@Test
	public void testSetMovingPlayerC(){
		
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("3");
		tester.setMovingPlayer(testBoard);
		assertEquals("1",testBoard.getMovingPlayer());
	}
	
	@Test
	public void testSetMovingPlayerD(){
		
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("2");
		tester.setMovingPlayer(testBoard);
		assertEquals("3",testBoard.getMovingPlayer());
	}
	
	public void testSetMovingPlayerE(){
		
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("1");
		tester.setMovingPlayer(testBoard);
		assertEquals("2",testBoard.getMovingPlayer());
	}
	
	public void testSetMovingPlayerF(){
		
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("4");
		tester.setMovingPlayer(testBoard);
		assertEquals("1",testBoard.getMovingPlayer());
	}
	
	public void testSetMovingPlayerG(){
		
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("3");
		tester.setMovingPlayer(testBoard);
		assertEquals("4",testBoard.getMovingPlayer());
	}

	public void testSetMovingPlayerH(){
		
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("2");
		tester.setMovingPlayer(testBoard);
		assertEquals("3",testBoard.getMovingPlayer());
	}

	public void testSetMovingPlayerI(){
		
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("1");
		tester.setMovingPlayer(testBoard);
		assertEquals("2",testBoard.getMovingPlayer());
	}
	
	
	
	@Test
	public void testCheckBeadConfPlayerOne(){
		testBoard.setBeadConfiguration("0010000111001011100000000000110010000110001100000");
		tester.checkbeadConf(testBoard);
		assertEquals(true,testBoard.isPlayerOne());
	}
	
	@Test
	public void testCheckBeadConfPlayerTwo(){
		testBoard.setBeadConfiguration("0020000222002022200000000000220020000220002200000");
		tester.checkbeadConf(testBoard);
		assertEquals(true,testBoard.isPlayerTwo());
	}
	
	@Test
	public void testCheckBeadConfPlayerThree(){
		testBoard.setBeadConfiguration("0030000333003033300000000000330030000330003300000");
		tester.checkbeadConf(testBoard);
		assertEquals(true,testBoard.isPlayerThree());
	}
	
	@Test
	public void testCheckBeadConfPlayerFour(){
		testBoard.setBeadConfiguration("0040000444004044400000000000440040000440004400000");
		tester.checkbeadConf(testBoard);
		assertEquals(true,testBoard.isPlayerFour());
	}

}
