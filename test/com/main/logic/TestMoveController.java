package com.main.logic;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMoveController {

	MoveController moveController;
	Board testBoard;

	@Before
	public void setUp() {
		moveController = new MoveController();
		testBoard = new Board();
	}

	@After
	public void tearDown() {
		moveController = null;
		testBoard = null;
	}

	@Test
	public void testBarConfGeneratorHorizontalOneInner() {
		assertEquals("xoxoxox", moveController.barConfGenerator("h", "1", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalOneCenter() {
		assertEquals("oxoxoxo", moveController.barConfGenerator("h", "1", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalOneOuter() {
		assertEquals("xoxoxox", moveController.barConfGenerator("h", "1", '2'));
	}

	@Test
	public void testBarConfGeneratorHorizontalTwoInner() {
		assertEquals("xooxoox", moveController.barConfGenerator("h", "2", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalTwoCenter() {
		assertEquals("ooxooxo", moveController.barConfGenerator("h", "2", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalTwoOuter() {
		assertEquals("oxooxox", moveController.barConfGenerator("h", "2", '2'));
	}

	@Test
	public void testBarConfGeneratorHorizontalThreeInner() {
		assertEquals("xoooxoo", moveController.barConfGenerator("h", "3", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalThreeCenter() {
		assertEquals("oooxooo", moveController.barConfGenerator("h", "3", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalThreeOuter() {
		assertEquals("ooxooox", moveController.barConfGenerator("h", "3", '2'));
	}

	@Test
	public void testBarConfGeneratorHorizontalFourInner() {
		assertEquals("xoxoxox", moveController.barConfGenerator("h", "4", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalFourCenter() {
		assertEquals("oxoxoxo", moveController.barConfGenerator("h", "4", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalFourOuter() {
		assertEquals("xoxoxox", moveController.barConfGenerator("h", "4", '2'));
	}

	@Test
	public void testBarConfGeneratorHorizontalFiveInner() {
		assertEquals("xoooooo", moveController.barConfGenerator("h", "5", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalFiveCenter() {
		assertEquals("ooooooo", moveController.barConfGenerator("h", "5", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalFiveOuter() {
		assertEquals("oooooox", moveController.barConfGenerator("h", "5", '2'));
	}

	@Test
	public void testBarConfGeneratorHorizontalSixInner() {
		assertEquals("xxoooxo", moveController.barConfGenerator("h", "6", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalSixCenter() {
		assertEquals("xoooxox", moveController.barConfGenerator("h", "6", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalSixOuter() {
		assertEquals("oooxoxx", moveController.barConfGenerator("h", "6", '2'));
	}

	@Test
	public void testBarConfGeneratorHorizontalSevenInner() {
		assertEquals("xooxoxo", moveController.barConfGenerator("h", "7", '0'));
	}

	@Test
	public void testBarConfGeneratorHorizontalsevenCenter() {
		assertEquals("ooxoxox", moveController.barConfGenerator("h", "7", '1'));
	}

	@Test
	public void testBarConfGeneratorHorizontalSevenOuter() {
		assertEquals("oxoxoxx", moveController.barConfGenerator("h", "7", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalOneInner() {
		assertEquals("xooooxo", moveController.barConfGenerator("v", "1", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalOneCenter() {
		assertEquals("ooooxox", moveController.barConfGenerator("v", "1", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalOneOuter() {
		assertEquals("oooxoxx", moveController.barConfGenerator("v", "1", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalTwoInner() {
		assertEquals("xoooxxo", moveController.barConfGenerator("v", "2", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalTwoCenter() {
		assertEquals("oooxxoo", moveController.barConfGenerator("v", "2", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalTwoOuter() {
		assertEquals("ooxxoox", moveController.barConfGenerator("v", "2", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalThreeInner() {
		assertEquals("xoxooxo", moveController.barConfGenerator("v", "3", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalThreeCenter() {
		assertEquals("oxooxox", moveController.barConfGenerator("v", "3", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalThreeOuter() {
		assertEquals("xooxoxx", moveController.barConfGenerator("v", "3", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalFourInner() {
		assertEquals("xooxxoo", moveController.barConfGenerator("v", "4", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalFourCenter() {
		assertEquals("ooxxooo", moveController.barConfGenerator("v", "4", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalFourOuter() {
		assertEquals("oxxooox", moveController.barConfGenerator("v", "4", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalFiveInner() {
		assertEquals("xxoooxo", moveController.barConfGenerator("v", "5", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalFiveCenter() {
		assertEquals("xoooxox", moveController.barConfGenerator("v", "5", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalFiveOuter() {
		assertEquals("oooxoxx", moveController.barConfGenerator("v", "5", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalSixInner() {
		assertEquals("xxooooo", moveController.barConfGenerator("v", "6", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalSixCenter() {
		assertEquals("xooooox", moveController.barConfGenerator("v", "6", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalSixOuter() {
		assertEquals("oooooxx", moveController.barConfGenerator("v", "6", '2'));
	}

	@Test
	public void testBarConfGeneratorVerticalSevenInner() {
		assertEquals("xooxoox", moveController.barConfGenerator("v", "7", '0'));
	}

	@Test
	public void testBarConfGeneratorVerticalSevenCenter() {
		assertEquals("ooxooxo", moveController.barConfGenerator("v", "7", '1'));
	}

	@Test
	public void testBarConfGeneratorVerticalSevenOuter() {
		assertEquals("oxooxox", moveController.barConfGenerator("v", "7", '2'));
	}

	@Test
	public void testSetMovingPlayerTwoPlayersToTwo() {

		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("1");
		moveController.setMovingPlayer(testBoard);
		assertEquals("2", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerTwoPlayersToOne() {

		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("2");
		moveController.setMovingPlayer(testBoard);
		assertEquals("1", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerThreePlayersToOne() {

		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("3");
		moveController.setMovingPlayer(testBoard);
		assertEquals("1", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerThreePlayersToThree() {

		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("2");
		moveController.setMovingPlayer(testBoard);
		assertEquals("3", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerThreePlayersToTwo() {

		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("1");
		moveController.setMovingPlayer(testBoard);
		assertEquals("2", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerToOne() {

		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("4");
		moveController.setMovingPlayer(testBoard);
		assertEquals("1", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerToFour() {

		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("3");
		moveController.setMovingPlayer(testBoard);
		assertEquals("4", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerToThree() {

		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("2");
		moveController.setMovingPlayer(testBoard);
		assertEquals("3", testBoard.getMovingPlayer());
	}

	@Test
	public void testSetMovingPlayerToTwo() {

		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("1");
		moveController.setMovingPlayer(testBoard);
		assertEquals("2", testBoard.getMovingPlayer());
	}

	@Test
	public void testCheckBeadConfPlayerOne() {
		testBoard.setBeadConfiguration("0010000111001011100000000000110010000110001100000");
		moveController.checkbeadConf(testBoard);
		assertEquals(true, testBoard.isPlayerOne());
	}

	@Test
	public void testCheckBeadConfPlayerTwo() {
		testBoard.setBeadConfiguration("0020000222002022200000000000220020000220002200000");
		moveController.checkbeadConf(testBoard);
		assertEquals(true, testBoard.isPlayerTwo());
	}

	@Test
	public void testCheckBeadConfPlayerThree() {
		testBoard.setBeadConfiguration("0030000333003033300000000000330030000330003300000");
		moveController.checkbeadConf(testBoard);
		assertEquals(true, testBoard.isPlayerThree());
	}

	@Test
	public void testCheckBeadConfPlayerFour() {
		testBoard.setBeadConfiguration("0040000444004044400000000000440040000440004400000");
		moveController.checkbeadConf(testBoard);
		assertEquals(true, testBoard.isPlayerFour());
	}

	@Test
	public void testRulesCheckTwoPlayersValidMove() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("2");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		moveController.rulesCheck("h3i", board);

	}

	@Test(expected = GameException.class)
	public void testRulesCheckTwoPlayersSameBarMovedAsPreviousTurn() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("2");
		board.setMovingPlayer("2");
		board.setMoveOne("h3o1");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		moveController.rulesCheck("h3i", board);
	}

	@Test(expected = GameException.class)
	public void testRulesCheckTwoPlayersSameBarMovedAsAnotherEliminatedPlayer()
			throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("2");
		board.setMoveOne("h3o3");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(false);
		board.setPlayerFour(false);
		moveController.rulesCheck("h3i", board);
	}

	@Test(expected = GameException.class)
	public void testRulesCheckTwoPlayersSameBarMovedOfTwoEliminatedPlayer() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("2");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o3");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(false);
		board.setPlayerFour(false);
		moveController.rulesCheck("h4i", board);
	}

	@Test(expected = GameException.class)
	public void testRulesCheckTwoPlayersSameBarMovedOfThreeEliminatedPlayer() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("2");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o3");
		board.setMoveThree("h5o4");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(false);
		board.setPlayerFour(false);
		moveController.rulesCheck("h5i", board);
	}

	@Test(expected = GameException.class)
	public void testRulesCheckTwoPlayersSameBarMovedOfFourEliminatedPlayer() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("2");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o4");
		board.setMoveThree("h5o2");
		board.setMoveFour("v5o1");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(false);
		board.setPlayerFour(false);
		moveController.rulesCheck("v5i", board);
	}

	@Test(expected = GameException.class)
	public void testRulesCheckTwoPlayersSameBarMovedTwoConsecutiveTurns() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("2");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o2");
		board.setMoveThree("h3o1");
		board.setMoveFour("h4o2");
		board.setBeadConfiguration("0000100002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(false);
		board.setPlayerFour(false);
		moveController.rulesCheck("h4i", board);
	}

	@Test(expected = GameException.class)
	public void testRulesCheckThreePlayersMoveEqualToLastMove() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("3");
		board.setMovingPlayer("3");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o2");
		board.setBeadConfiguration("0000133002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(true);
		moveController.rulesCheck("h3i", board);

	}

	@Test(expected = GameException.class)
	public void testRulesCheckThreePlayersMoveEqualToSecondLastMove() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("3");
		board.setMovingPlayer("3");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o2");
		board.setBeadConfiguration("0000133002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(true);
		moveController.rulesCheck("h4i", board);

	}

	@Test(expected = GameException.class)
	public void testRulesCheckFourPlayersMoveEqualToLastMove() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("3");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o2");
		board.setBeadConfiguration("0404133002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(true);
		board.setPlayerFour(true);
		moveController.rulesCheck("h3i", board);

	}

	@Test(expected = GameException.class)
	public void testRulesCheckFourPlayersMoveEqualToSecondLastMove() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("3");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o2");
		board.setBeadConfiguration("0404133002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(true);
		board.setPlayerFour(true);
		moveController.rulesCheck("h4i", board);

	}

	@Test(expected = GameException.class)
	public void testRulesCheckFourPlayersMoveEqualToThirdLastMove() throws GameException {
		Board board = new Board();
		board.setNumberOfPlayers("4");
		board.setMovingPlayer("3");
		board.setMoveOne("h3o1");
		board.setMoveTwo("h4o2");
		board.setMoveThree("h6o4");
		board.setBeadConfiguration("0404133002000000000012000000000000000010000000000");
		board.setPlayerOne(true);
		board.setPlayerTwo(true);
		board.setPlayerThree(true);
		board.setPlayerFour(true);
		moveController.rulesCheck("h6i", board);

	}

	@Test
	public void testMakeMoveHorizontalOuterToCentral() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("h3i", "1021221", board);
		assertEquals("1011221", result);
	}

	@Test
	public void testMakeMoveHorizontalInnerToCentral() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("h3o", "1001221", board);
		assertEquals("1011221", result);
	}

	@Test
	public void testMakeMoveHorizontalCentralToInner() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("h3i", "1011221", board);
		assertEquals("1001221", result);
	}

	@Test
	public void testMakeMoveHorizontalCentralToOuter() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("h3o", "1011221", board);
		assertEquals("1021221", result);
	}

	@Test
	public void testMakeMoveVerticalOuterToCentral() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("v3i", "1021221", board);
		assertEquals("1011221", result);
	}

	@Test
	public void testMakeMoveVerticalInnerToCentral() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("v3o", "1001221", board);
		assertEquals("1011221", result);
	}

	@Test
	public void testMakeMoveVerticalCentralToInner() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("v3i", "1011221", board);
		assertEquals("1001221", result);
	}

	@Test
	public void testMakeMoveVerticalCentralToOuter() throws GameException {
		Board board = new Board();
		String result = moveController.makeMove("v3o", "1011221", board);
		assertEquals("1021221", result);
	}

	@Test(expected = GameException.class)
	public void testMakeMoveVerticalInnerToInnerException() throws GameException {
		Board board = new Board();
		moveController.makeMove("v3i", "1001221", board);

	}

	@Test(expected = GameException.class)
	public void testMakeMoveVerticalOuterToOuterException() throws GameException {
		Board board = new Board();
		moveController.makeMove("v3o", "1021221", board);

	}

	@Test(expected = GameException.class)
	public void testMakeMoveHorizontalInnerToInnerException() throws GameException {
		Board board = new Board();
		moveController.makeMove("h3i", "1001221", board);

	}

	@Test(expected = GameException.class)
	public void testMakeMoveHorizontalOuterToOuterException() throws GameException {
		Board board = new Board();
		moveController.makeMove("h3o", "1021221", board);

	}

	@Test
	public void testTransposeValidInput() throws Exception {
		String input = "2101201202101112120210012";
		String result = moveController.transpose(input);
		assertEquals("2012111010021201010122122", result);
	}

	@Test
	public void testCheckWinPlayerOne() {
		Board board = new Board();
		board.setBeadConfiguration("0000100010000100000010000000000010000010010000000");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "1");
	}

	@Test
	public void testCheckWinPlayerTwo() {
		Board board = new Board();
		board.setBeadConfiguration("0000200000000000000000000000000020000020020000000");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "2");
	}

	@Test
	public void testCheckWinPlayerThree() {
		Board board = new Board();
		board.setBeadConfiguration("0000300000000000000000000000000030000030030000000");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "3");
	}

	@Test
	public void testCheckWinPlayerFour() {
		Board board = new Board();
		board.setBeadConfiguration("0000400000000000000000000000000040000040040000000");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "4");
	}

	@Test
	public void testCheckWinPlayerAllBeadZeroLastMoveByPlayerOne() {
		Board board = new Board();
		board.setBeadConfiguration("0000000000000000000000000000000000000000000000000");
		board.setMoveOne("h3i1");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "1");
	}

	@Test
	public void testCheckWinPlayerAllBeadZeroLastMoveByPlayerTwo() {
		Board board = new Board();
		board.setBeadConfiguration("0000000000000000000000000000000000000000000000000");
		board.setMoveOne("h3i2");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "2");
	}

	@Test
	public void testCheckWinPlayerAllBeadZeroLastMoveByPlayerThree() {
		Board board = new Board();
		board.setBeadConfiguration("0000000000000000000000000000000000000000000000000");
		board.setMoveOne("h3i3");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "3");
	}

	@Test
	public void testCheckWinPlayerAllBeadZeroLastMoveByPlayerFour() {
		Board board = new Board();
		board.setBeadConfiguration("0000000000000000000000000000000000000000000000000");
		board.setMoveOne("h3i4");
		moveController.checkWin(board);
		assertEquals(board.getWinner(), "4");
	}

	@Test
	public void testSetMoves() {
		Board board = new Board();

		board.setMovingPlayer("1");
		board.setMoveOne("h4o2");
		board.setMoveTwo("h6o1");
		board.setMoveThree("v6o2");
		board.setMoveFour("h4o1");
		moveController.setMoves("h3i", board);
		assertEquals(board.getMoveOne(), "h3i1");
	}

	@Test
	public void testBoardConf() {
		String result = moveController.boardConfGenerator("1001101", "2022002");
		assertEquals("hbbrbbhrhhbbbbrhhbrhhbrbrhrhhbhhhhbbbbhbrhbhbbrhb", result);
	}

	@Test
	public void testBeadConf() {
		String result = moveController.beadConfGenerator(
				"000010002000000000012000000000000000010000000000",
				"hbbrbbhrhhbbbbrhhbrhhbrbrhrhhbhhhhbbbbhbrhbhbbrhb");
		assertEquals("000010000000000000000000000000000000010000000000", result);
	}

	// Check Elimination Two Players
	@Test
	public void testCheckEliminationTwoPlayersOne() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("1");
		testBoard.setPlayerTwo(true);
		testBoard.setPlayerOne(false);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.two);

	}

	@Test
	public void testCheckEliminationTwoPlayersTwo() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("2");
		testBoard.setMovingPlayer("2");
		testBoard.setPlayerTwo(false);
		testBoard.setPlayerOne(true);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.one);

	}

	// Check Elimination Three Players

	@Test
	public void testCheckEliminationThreePlayersOne() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("1");
		testBoard.setPlayerTwo(true);
		testBoard.setPlayerOne(false);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.two);

	}

	@Test
	public void testCheckEliminationThreePlayersTwo() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("2");
		testBoard.setPlayerTwo(false);
		testBoard.setPlayerThree(true);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.three);

	}

	@Test
	public void testCheckEliminationThreePlayersThree() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("3");
		testBoard.setMovingPlayer("3");
		testBoard.setPlayerThree(false);
		testBoard.setPlayerOne(true);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.one);

	}

	// Check Elimination Four Players

	@Test
	public void testCheckEliminationFourPlayersOne() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("1");
		testBoard.setPlayerTwo(true);
		testBoard.setPlayerOne(false);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.two);

	}

	@Test
	public void testCheckEliminationFourPlayersTwo() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("2");
		testBoard.setPlayerTwo(false);
		testBoard.setPlayerThree(true);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.three);

	}

	@Test
	public void testCheckEliminationFourPlayersThree() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("3");
		testBoard.setPlayerThree(false);
		testBoard.setPlayerFour(true);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.four);

	}

	@Test
	public void testCheckEliminationFourPlayersFour() {
		Board testBoard = new Board();
		testBoard.setNumberOfPlayers("4");
		testBoard.setMovingPlayer("4");
		testBoard.setPlayerFour(false);
		testBoard.setPlayerOne(true);
		moveController.checkElimination(testBoard);
		assertEquals(testBoard.getMovingPlayer(), Strings.one);

	}

}
