package com.main.logic;

import java.util.regex.Pattern;

public class MoveController {
	/**
	 * @param input
	 *            The input is expected to be the configuration of the game. The
	 *            input must be set into the board object
	 * @return The result of the moveTest operation, the state of the game after
	 *         performing moves.
	 * @throws Exception
	 * 
	 */

	public Board moveTest(Board board) throws Exception {

		try {
			// checking the given input follows the correct pattern
			if (Pattern.matches(Strings.gamePattern, board.getInput())) {

				board.setNumberOfPlayers(board.getInput().substring(0, 1));
				board.setMovingPlayer(board.getInput().substring(1, 2));
				board.setPostionsOfHorizontalBars(board.getInput().substring(2, 9));
				board.setPostionsOfVerticalBars(board.getInput().substring(9, 16));
				board.setBeadConfiguration(board.getInput().substring(16, 65));
				board.setSequenceOfMoves(board.getInput().substring(65, board.getInput().length()));

				// Perform moves
				// The moves are moved into an array and the moves are performed
				// one at a time
				String[] movesArray = board.getSequenceOfMoves().split(Strings.movePattern);
				if (!board.getSequenceOfMoves().isEmpty()) {
					for (String move : movesArray) {

						// check all the rules before performing the move
						rulesCheck(move, board);

						// update the position of the bars according to the move
						if (move.charAt(0) == Strings.charh) {
							board.setPostionsOfHorizontalBars(makeMove(move,
									board.getPostionsOfHorizontalBars(), board));

						}
						if (move.charAt(0) == Strings.charv) {
							board.setPostionsOfVerticalBars(makeMove(move,
									board.getPostionsOfVerticalBars(), board));

						}
						// On a successful move the player who performed the
						// move and the move are stored in history of moves
						setMoves(move, board);

						// generate the configuration of the board according to
						// the new position of the bars
						board.setBoardConfiguration(boardConfGenerator(
								board.getPostionsOfHorizontalBars(),
								board.getPostionsOfVerticalBars()));

						// generate the configuration of beads after performing
						// the moves
						board.setBeadConfiguration(beadConfGenerator(board.getBeadConfiguration(),
								board.getBoardConfiguration()));

						// check the bead configuration for the available
						// players
						checkbeadConf(board);

						// set the next possible moving player
						setMovingPlayer(board);

						// check if the player is eliminated, if yes set
						// movingPlayer to the eligible player
						checkElimination(board);

						// check for win condition
						checkWin(board);

						// set the output of the board
						board.setOutput(board.getNumberOfPlayers() + board.getMovingPlayer()
								+ board.getPostionsOfHorizontalBars()
								+ board.getPostionsOfVerticalBars() + board.getBeadConfiguration());

					}
				} else {
					// in case there are no moves to perform check for a winning
					// condition
					checkWin(board);
					// setting the input back to the output as there are no
					// moves to perform
					board.setOutput(board.getNumberOfPlayers() + board.getMovingPlayer()
							+ board.getPostionsOfHorizontalBars()
							+ board.getPostionsOfVerticalBars() + board.getBeadConfiguration());
				}
				System.out.println(Strings.output);
				System.out.println(board.getOutput());
			} else {
				// throw exception that the input is not valid
				throw new GameException(1);

			}
		} catch (Exception e) {
			throw e;
		}

		return board;
	}

	/**
	 * This function checks the condition for win when there are beads from only
	 * one player in the bead configuration, also in conditions when there are
	 * no beads the player who last performed the moves will be the winner
	 * 
	 * @param board
	 */
	public void checkWin(Board board) {
		checkbeadConf(board);

		if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()) {
			board.setWinner(Strings.one);
			board.setPlayerCount(1);
		}
		if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerFour()) {
			board.setWinner(Strings.two);
			board.setPlayerCount(1);
		}
		if (!board.isPlayerOne() && !board.isPlayerTwo() && !board.isPlayerFour()) {
			board.setWinner(Strings.three);
			board.setPlayerCount(1);
		}
		if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerTwo()) {
			board.setWinner(Strings.four);
			board.setPlayerCount(1);
		}
		if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()
				&& !board.isPlayerOne()) {
			board.setWinner(board.getMoveOne().substring(3, 4));
			board.setPlayerCount(1);
		}

	}

	/**
	 * This function sets values to the history of moves which are being
	 * performed
	 * 
	 * @param move
	 * @param board
	 */
	public void setMoves(String move, Board board) {
		if (board.getMoveThree() != null) {
			board.setMoveFour(board.getMoveThree());
		}
		if (board.getMoveTwo() != null) {
			board.setMoveThree(board.getMoveTwo());
		}

		if (board.getMoveOne() != null) {
			board.setMoveTwo(board.getMoveOne());
		}

		board.setMoveOne(move + board.getMovingPlayer());
	}

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
	public void rulesCheck(String move, Board board) throws GameException {
		checkbeadConf(board);
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
		if (board.getNumberOfPlayers().equals(Strings.four) && counter > 2) {
			if (board.getMoveOne() != null
					&& !board.getMovingPlayer().equals(board.getMoveOne().substring(3, 4))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveTwo() != null
					&& !board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveThree() != null
					&& !board.getMovingPlayer().equals(board.getMoveThree().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveThree().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			}
		}
		// here we check whether the move is valid or not, the bar which is
		// supposed to be moved must not be moved in the last turns by other
		// players
		if (board.getNumberOfPlayers().equals(Strings.three) && counter > 2) {

			if (board.getMoveOne() != null
					&& !board.getMovingPlayer().equals(board.getMoveOne().substring(3, 4))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveTwo() != null
					&& !board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4))
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
			if (board.getMoveOne() != null
					&& !board.getMovingPlayer().equals(board.getMoveOne().substring(3, 4))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveTwo() != null
					&& !board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveThree() != null
					&& !board.getMovingPlayer().equals(board.getMoveThree().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveThree().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			} else if (board.getMoveFour() != null
					&& !board.getMovingPlayer().equals(board.getMoveFour().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveFour().substring(0, 2))) {
				throw new GameException(Strings.charr, move, board);
			}

			// this is the check for two consecutive turns on the same bar
			if (board.getMoveTwo() != null
					&& board.getMoveFour() != null
					&& (board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4)))
					&& (board.getMovingPlayer().equals(board.getMoveFour().substring(3, 4)))
					&& (move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2)) && move
							.substring(0, 2).equals(board.getMoveFour().substring(0, 2)))) {
				throw new GameException(Strings.charr, move, board);
			}
		}

		board.setPlayerCount(counter);

	}

	/**
	 * This function will change the configuration of the bar which is moved for
	 * example: The move is h3i and the horizontal bar configuration is 1022222,
	 * the output will be 1012222
	 * 
	 * @param move
	 *            The move to be performed represented by a 3 character string
	 *            where 1st character is the bar type, 2nd character is the bar
	 *            number, and third character is the type of move which can be
	 *            i=Inward or o=Outward
	 * @param bar
	 *            The bar on which the move must be performed, it is a 7
	 *            character string where each character represents the position
	 *            of the bar 0 (inner), 1 (central), or 2 (outer)
	 * @return Updated configuration of the bar after performing the move
	 * @throws GameException
	 */
	public String makeMove(String move, String bar, Board board) throws GameException {

		if (move.charAt(0) == Strings.charh && move.charAt(2) == Strings.chari) {
			// position of bar in the barConfig string will be 1 less than the
			// specified position
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);
			if (changeBarVal == Strings.charone || changeBarVal == Strings.chartwo) {
				if (changeBarVal == Strings.charone) {
					changeBarVal = Strings.charzero;
				} else {
					changeBarVal = Strings.charone;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(1, String.valueOf(move.charAt(1)), board);
			}
		} else if (move.charAt(0) == Strings.charh && move.charAt(2) == Strings.charo) {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);

			if (changeBarVal == Strings.charzero || changeBarVal == Strings.charone) {
				if (changeBarVal == Strings.charzero) {
					changeBarVal = Strings.charone;
				} else {
					changeBarVal = Strings.chartwo;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(2, String.valueOf(move.charAt(1)), board);
			}
		} else if (move.charAt(0) == Strings.charv && move.charAt(2) == Strings.chari) {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);
			if (changeBarVal == Strings.charone || changeBarVal == Strings.chartwo) {
				if (changeBarVal == Strings.charone) {
					changeBarVal = Strings.charzero;
				} else {
					changeBarVal = Strings.charone;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(3, String.valueOf(move.charAt(1)), board);
			}
		} else if (move.charAt(0) == Strings.charv && move.charAt(2) == Strings.charo) {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);

			if (changeBarVal == Strings.charzero || changeBarVal == Strings.charone) {
				if (changeBarVal == Strings.charzero) {
					changeBarVal = Strings.charone;
				} else {
					changeBarVal = Strings.chartwo;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(4, String.valueOf(move.charAt(1)), board);
			}
		}

		return bar;
	}

	/**
	 * This function returns the board as a 49 character string where values are
	 * b,r,or h
	 * 
	 * @param horizontalBar
	 *            The horizontal bar configuration
	 * @param verticalBar
	 *            The vertical bar configuration
	 * @return The configuration of the 7x7 board where each place is either
	 *         b=Blue, r=Red, h=Hole based on the bar configurations
	 */
	public String boardConfGenerator(String horizontalBar, String verticalBar) {
		String horBarConf = "";
		String verBarConf = "";
		String boardConf = "";
		int i = 1;
		for (char barPos : horizontalBar.toCharArray()) {

			horBarConf = horBarConf + barConfGenerator(Strings.h, String.valueOf(i), barPos);
			i++;
		}
		i = 1;

		for (char barPos : verticalBar.toCharArray()) {

			verBarConf = verBarConf + barConfGenerator(Strings.v, String.valueOf(i), barPos);
			i++;
		}
		// we need to transpose the vertical bar so that we can compare them to
		// the corresponding horizontal bar positions
		verBarConf = transpose(verBarConf);

		for (i = 0; i < horBarConf.length(); i++) {
			if (verBarConf.charAt(i) == Strings.charx) {
				boardConf = boardConf + Strings.blue;
			} else if (verBarConf.charAt(i) == Strings.charo
					&& horBarConf.charAt(i) == Strings.charx) {
				boardConf = boardConf + Strings.red;
			} else {
				boardConf = boardConf + Strings.hole;
			}

		}
		return boardConf;
	}

	/**
	 * @param barType
	 *            The type of bar for which the configuration must be generated
	 * @param barNumber
	 *            The number of the bar
	 * @param barPos
	 *            The position of the bar 0 (inner), 1 (central), or 2 (outer)
	 * @return
	 */
	public String barConfGenerator(String barType, String barNumber, char barPos) {
		String barConf = "";
		String horBar = Strings.horizontalBar;
		String verBar = Strings.verticalBar;
		if (barType == Strings.h) {
			barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);
			barConf = horBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));
		} else if (barType == Strings.v) {

			barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);
			barConf = verBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));

		}
		// select the barConf according to the current position of the bar
		if (barPos == Strings.charzero) {
			barConf = barConf.substring(0, 7);
		} else if (barPos == Strings.charone) {
			barConf = barConf.substring(1, 8);
		} else if (barPos == Strings.chartwo) {
			barConf = barConf.substring(2, 9);
		}

		return barConf;
	}

	/**
	 * @param verticalBar
	 *            the vertical bar configuration as input
	 * 
	 * @return Perform a transpose of the vertical bar configuration so that it
	 *         can be compared to the horizontal bar and thus we can generate
	 *         the board configuration
	 */
	public String transpose(String verticalBar) {

		char[] charArray = verticalBar.toCharArray();
		int n = (int) Math.sqrt(verticalBar.length());
		for (int i = 0; i < n; ++i) {
			for (int j = i; j < n; ++j) {
				char h = charArray[i * n + j];
				charArray[i * n + j] = charArray[j * n + i];
				charArray[j * n + i] = h;
			}
		}
		return new String(charArray);
	}

	/**
	 * Checks whether a hole has appeared under the bead, if so the bead falls
	 * off the board and the bead is removed from bead configuration
	 * 
	 * @param beadConf
	 *            The current configuration of the beads on the board
	 * @param boardConf
	 *            The current configuration of the board
	 * @return The configuration of the beads in the board
	 */
	public String beadConfGenerator(String beadConf, String boardConf) {
		int i;
		for (i = 0; i < beadConf.length(); i++) {
			if (beadConf.charAt(i) != Strings.charzero && boardConf.charAt(i) == Strings.charh) {
				StringBuilder newBeadConfig = new StringBuilder(beadConf);
				newBeadConfig.setCharAt(i, Strings.charzero);
				beadConf = newBeadConfig.toString();
			}
		}
		return beadConf;
	}

	/**
	 * This function determines the next player who must perform the move
	 * 
	 * @param board
	 */
	public void setMovingPlayer(Board board) {

		if (board.getNumberOfPlayers().equals(Strings.two)) {
			if (board.getMovingPlayer().equals(Strings.one)) {
				board.setMovingPlayer(Strings.two);
			} else if (board.getMovingPlayer().equals(Strings.two)) {
				board.setMovingPlayer(Strings.one);
			}
		} else if (board.getNumberOfPlayers().equals(Strings.three)) {
			if (board.getMovingPlayer().equals(Strings.three)) {
				board.setMovingPlayer(Strings.one);
			} else if (board.getMovingPlayer().equals(Strings.two)) {
				board.setMovingPlayer(Strings.three);
			} else if (board.getMovingPlayer().equals(Strings.one)) {
				board.setMovingPlayer(Strings.two);
			}

		} else if (board.getNumberOfPlayers().equals(Strings.four)) {
			if (board.getMovingPlayer().equals(Strings.four)) {
				board.setMovingPlayer(Strings.one);
			} else if (board.getMovingPlayer().equals(Strings.three)) {
				board.setMovingPlayer(Strings.four);
			} else if (board.getMovingPlayer().equals(Strings.two)) {
				board.setMovingPlayer(Strings.three);
			} else if (board.getMovingPlayer().equals(Strings.one)) {
				board.setMovingPlayer(Strings.two);
			}

		}

	}

	/**
	 * This function recursively checks whether the current player who has the
	 * turn is eliminated or not, if the player is eliminated then the next
	 * eligible player will get the turn.
	 * 
	 * @param board
	 */
	public void checkElimination(Board board) {

		if (board.getNumberOfPlayers().equals(Strings.two)) {
			if (board.getMovingPlayer().equals(Strings.one) && !board.isPlayerOne()
					&& board.isPlayerTwo()) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.two) && !board.isPlayerTwo()
					&& board.isPlayerOne()) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers().equals(Strings.three)) {
			if (board.getMovingPlayer().equals(Strings.one) && !board.isPlayerOne()
					&& board.isPlayerTwo()) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.two) && !board.isPlayerTwo()
					&& board.isPlayerThree()) {
				board.setMovingPlayer(Strings.three);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.three) && !board.isPlayerThree()
					&& board.isPlayerOne()) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers().equals(Strings.four)) {
			if (board.getMovingPlayer().equals(Strings.one) && !board.isPlayerOne()
					&& board.isPlayerTwo()) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.two) && !board.isPlayerTwo()
					&& board.isPlayerThree()) {
				board.setMovingPlayer(Strings.three);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.three) && !board.isPlayerThree()
					&& board.isPlayerFour()) {
				board.setMovingPlayer(Strings.four);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.four) && !board.isPlayerFour()
					&& board.isPlayerOne()) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}
		}
	}

	/**
	 * This function checks the configuration of the beads and determines
	 * whether a player is currently playing or not, For ex: if the bead
	 * configuration doesnt have 4 then PlayerFour will be set to false
	 * 
	 * @param board
	 */
	public void checkbeadConf(Board board) {

		if (!board.getBeadConfiguration().contains(Character.toString(Strings.charfour))) {
			board.setPlayerFour(false);
		}

		if (!board.getBeadConfiguration().contains(Character.toString(Strings.charthree))) {
			board.setPlayerThree(false);
		}
		if (!board.getBeadConfiguration().contains(Character.toString(Strings.chartwo))) {
			board.setPlayerTwo(false);

		}
		if (!board.getBeadConfiguration().contains(Character.toString(Strings.charone))) {
			board.setPlayerOne(false);

		}

	}

}
