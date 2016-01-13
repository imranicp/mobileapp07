package logic.main.com.boardgame.core;

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
		if (board.getNumberOfPlayers()== Constants.two&&board.getPlayerCount()!=1) {
			if (board.getMovingPlayer()== Constants.one && !board.isPlayerOne()
					) {
				board.setMovingPlayer(Constants.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer()== Constants.two && !board.isPlayerTwo()
					) {
				board.setMovingPlayer(Constants.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers()== Constants.three&&board.getPlayerCount()!=1) {
			if (board.getMovingPlayer()== Constants.one  && !board.isPlayerOne()
					) {
				board.setMovingPlayer(Constants.two);
				checkElimination(board);
			}
			else if (board.getMovingPlayer()== Constants.two && !board.isPlayerTwo()
					) {
				board.setMovingPlayer(Constants.three);
				checkElimination(board);
			}
			else if (board.getMovingPlayer()== Constants.three && !board.isPlayerThree()
					) {
				board.setMovingPlayer(Constants.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers()== Constants.four&&board.getPlayerCount()!=1) {

			if (board.getMovingPlayer()== Constants.one && !board.isPlayerOne()
					) {
				board.setMovingPlayer(Constants.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer()== Constants.two && !board.isPlayerTwo()
					) {
				board.setMovingPlayer(Constants.three);
				checkElimination(board);
			}

			if (board.getMovingPlayer()== Constants.three && !board.isPlayerThree()
					) {
				board.setMovingPlayer(Constants.four);
				checkElimination(board);
			}
			if (board.getMovingPlayer()== Constants.four && !board.isPlayerFour()
					) {
				board.setMovingPlayer(Constants.one);
				checkElimination(board);
			}
		}
		return board;
	}

}
