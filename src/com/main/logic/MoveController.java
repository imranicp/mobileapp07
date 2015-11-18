package com.main.logic;

import java.util.regex.Pattern;

public class MoveController {
	/**
	 * @param input
	 *            The input string is expected to be the configuration of the
	 *            game.
	 * @return The result of the moveTest operation, the state of the game after
	 *         performing moves.
	 * @throws Exception
	 * 
	 */

	public String moveTest(String input) throws Exception {
		Board board = new Board();
		board.setInput(input);
		try {
			if (Pattern.matches(Strings.pattern, board.getInput())) {
				board.setNumberOfPlayers(board.getInput().substring(0, 1));
				board.setMovingPlayer(board.getInput().substring(1, 2));
				board.setPostionsOfHorizontalBars(board.getInput().substring(2, 9));
				board.setPostionsOfVerticalBars(board.getInput().substring(9, 16));
				board.setBeadRepresentation(board.getInput().substring(16, 65));
				board.setSequenceOfMoves(board.getInput().substring(65, board.getInput().length()));

			} else {

				throw new GameException(1);

			}
		} catch (Exception e) {
			throw e;
		}

		return board.getOutput();
	}
}
