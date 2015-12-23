package com.main.logic;


public class RuleController {
	/**
	 * @param move
	 *            the move which is supposed to be performed
	 * @param board
	 *            the function will deal with the history of 4 moves and they
	 *            are stored like a chain of moves where MoveOne will the latest
	 *            performed move and so on. We will also store the player who
	 *            performed the move by adding the player number to move
	 *            performed using the makeMove function
	 * @throws GameException
	 */

	public Board rulesCheck(String move, Board board) throws GameException {

		BeadConf beadConf =new BeadConf();
		board = beadConf.checkbeadConf(board);
		// counter is to check the number of players currently available to
		// perform moves

		int counter = 0;
		if (board.isPlayerOne()) {
			counter++;
		}
		if (board.isPlayerTwo()) {
			counter++;
		}
		if (board.isPlayerThree()) {
			counter++;
		}
		if (board.isPlayerFour()) {
			counter++;
		}


		// here we check whether the move is valid or not, the bar which is
		// supposed to be moved must not be moved in the last turns by other
		// players
		if (board.getNumberOfPlayers()== Strings.four && counter > 2) {
			if (board.getMoveOne() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveOne().substring(3, 4)))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveTwo() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveTwo().substring(3, 4)))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveThree() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveThree().substring(3, 4)))
					&& move.substring(0, 2).equals(board.getMoveThree().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			}
		}
		// here we check whether the move is valid or not, the bar which is
		// supposed to be moved must not be moved in the last turns by other
		// players
		if (board.getNumberOfPlayers()== Strings.three && counter > 2) {

			if (board.getMoveOne() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveOne().substring(3, 4)))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveTwo() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveTwo().substring(3, 4)))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			}
		}

		// when the number of players are just two then we have to check that a
		// bar must not be moved more then two times by the same player in
		// consecutive turns. We make use of the stored player number in the
		// history of moves ie the 4th character in the move history.

		if (counter == 2) {

			// this check must be done again because even if the current number
			// of players are two, but still they should not be able to move the
			// bars which were moved by players who got eliminated in the recent
			// moves



			if (board.getMoveOne()!= null 
					&& (move.substring(0,2).equals(board.getMoveOne().substring(0,2)))
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveOne().substring(3, 4)))){
				throw new GameException(Strings.charr, move, board);
			}

			// this is the check for two consecutive turns on the same bar
			if (board.getMoveTwo() != null
					&& board.getMoveFour() != null
					&& (board.getMovingPlayer()==Integer.parseInt((board.getMoveTwo().substring(3, 4))))
					&& (board.getMovingPlayer()==Integer.parseInt((board.getMoveFour().substring(3, 4))))
					&& (move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2)) && move
							.substring(0, 2).equals(board.getMoveFour().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			}



		}



		board.setPlayerCount(counter);
		return board;

	}
}
