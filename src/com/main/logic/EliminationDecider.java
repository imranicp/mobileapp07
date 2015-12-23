package com.main.logic;

public class EliminationDecider {
	/**
	 * This function recursively checks whether the current player who has the
	 * turn is eliminated or not, if the player is eliminated then the next
	 * eligible player will get the turn.
	 * 
	 * @param board
	 */


	public Board checkElimination(Board board) {
		WinnerDecider winnerDecider = new WinnerDecider();

		if (!board.isPlayerOne()&& !board.isPlayerTwo() && !board.isPlayerThree() && !board.isPlayerFour())
		{
			winnerDecider.checkWin(board);
		}
		if (board.getNumberOfPlayers()== Strings.two) {
			if (board.getMovingPlayer()== Strings.one && !board.isPlayerOne()
					) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer()== Strings.two && !board.isPlayerTwo()
					) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers()== Strings.three) {
			if (board.getMovingPlayer()== Strings.one  && !board.isPlayerOne()
					) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			else if (board.getMovingPlayer()== Strings.two && !board.isPlayerTwo()
					) {
				board.setMovingPlayer(Strings.three);
				checkElimination(board);
			}
			else if (board.getMovingPlayer()== Strings.three && !board.isPlayerThree()
					) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers()== Strings.four) {

			if (board.getMovingPlayer()== Strings.one && !board.isPlayerOne()
					) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer()== Strings.two && !board.isPlayerTwo()
					) {
				board.setMovingPlayer(Strings.three);
				checkElimination(board);
			}

			if (board.getMovingPlayer()== Strings.three && !board.isPlayerThree()
					) {
				board.setMovingPlayer(Strings.four);
				checkElimination(board);
			}
			if (board.getMovingPlayer()== Strings.four && !board.isPlayerFour()
					) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}
		}
		return board;
	}

}
