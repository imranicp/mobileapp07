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

	public Board moveTest(Board board) throws Exception {

		try {
			if (Pattern.matches(Strings.gamePattern, board.getInput())) {

				board.setNumberOfPlayers(board.getInput().substring(0, 1));
				board.setMovingPlayer(board.getInput().substring(1, 2));
				board.setPostionsOfHorizontalBars(board.getInput().substring(2, 9));
				board.setPostionsOfVerticalBars(board.getInput().substring(9, 16));
				board.setBeadConfiguration(board.getInput().substring(16, 65));
				board.setSequenceOfMoves(board.getInput().substring(65, board.getInput().length()));

				// Perform moves
				String[] movesArray = board.getSequenceOfMoves().split(Strings.movePattern);
				if (!board.getSequenceOfMoves().isEmpty()) {
					for (String move : movesArray) {
						rulesCheck(move, board);
						if (move.charAt(0) == Strings.charh) {
							board.setPostionsOfHorizontalBars(makeMove(move,
									board.getPostionsOfHorizontalBars()));

						}
						if (move.charAt(0) == Strings.charv) {
							board.setPostionsOfVerticalBars(makeMove(move,
									board.getPostionsOfVerticalBars()));

						}

						board.setBoardConfiguration(boardConfGenerator(
								board.getPostionsOfHorizontalBars(),
								board.getPostionsOfVerticalBars()));

						board.setBeadConfiguration(beadConfGenerator(board.getBeadConfiguration(),
								board.getBoardConfiguration()));

						checkbeadConf(board);
						setMovingPlayer(board);
						checkElimination(board);
						checkWin(board);
						board.setOutput(board.getNumberOfPlayers() + board.getMovingPlayer()
								+ board.getPostionsOfHorizontalBars()
								+ board.getPostionsOfVerticalBars() + board.getBeadConfiguration());

						// System.out.println(board.getOutput());
					}
				}

			} else {

				throw new GameException(1);

			}
		} catch (Exception e) {
			throw e;
		}

		return board;
	}

	public void checkWin(Board board) {
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
		if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()) {
			board.setWinner(Strings.one);
		}
		if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerFour()) {
			board.setWinner(Strings.two);
		}
		if (!board.isPlayerOne() && !board.isPlayerTwo() && !board.isPlayerFour()) {
			board.setWinner(Strings.three);
		}
		if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerTwo()) {
			board.setWinner(Strings.four);
		}
		if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()
				&& !board.isPlayerOne()) {
			board.setWinner(board.getMoveOne().substring(3, 4));
		}

	}

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

	public void rulesCheck(String move, Board board) throws GameException {
		checkbeadConf(board);
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

		if (board.getNumberOfPlayers().equals(Strings.four) && counter > 2) {
			System.out.println("move: " + move.substring(0, 3));
			System.out.println("move one: " + board.getMoveOne());
			System.out.println("move two: " + board.getMoveTwo());
			System.out.println("move three: " + board.getMoveThree());
			if (board.getMoveOne() != null
					&& !board.getMovingPlayer().equals(board.getMoveOne().substring(3, 4))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(2);
			} else if (board.getMoveTwo() != null
					&& !board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(2);
			} else if (board.getMoveThree() != null
					&& !board.getMovingPlayer().equals(board.getMoveThree().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveThree().substring(0, 2))) {
				throw new GameException(2);
			} else {
				setMoves(move, board);

			}

		}

		if (board.getNumberOfPlayers().equals(Strings.three) && counter > 2) {
			System.out.println("move: " + move.substring(0, 3));
			System.out.println("move one: " + board.getMoveOne());
			System.out.println("move two: " + board.getMoveTwo());
			System.out.println("move three: " + board.getMoveThree());
			System.out.println("move four: " + board.getMoveFour());

			if (board.getMoveOne() != null
					&& !board.getMovingPlayer().equals(board.getMoveOne().substring(3, 4))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(2);
			} else if (board.getMoveTwo() != null
					&& !board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(2);
			} else {
				setMoves(move, board);

			}

		}

		if (counter == 2) {
			System.out.println("move: " + move.substring(0, 3));
			System.out.println("move one: " + board.getMoveOne());
			System.out.println("move two: " + board.getMoveTwo());
			System.out.println("move three: " + board.getMoveThree());
			System.out.println("move four: " + board.getMoveFour());

			if (board.getMoveOne() != null
					&& !board.getMovingPlayer().equals(board.getMoveOne().substring(3, 4))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {
				throw new GameException(2);
			} else if (board.getMoveTwo() != null
					&& !board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {
				throw new GameException(2);
			} else if (board.getMoveThree() != null
					&& !board.getMovingPlayer().equals(board.getMoveThree().substring(3, 4))
					&& move.substring(0, 2).equals(board.getMoveThree().substring(0, 2))) {
				throw new GameException(2);
			}

			if (board.getMoveOne() != null
					&& move.substring(0, 2).equals(board.getMoveOne().substring(0, 2))) {
				throw new GameException(2);
			} else if (board.getMoveTwo() != null
					&& board.getMoveFour() != null
					&& (board.getMovingPlayer().equals(board.getMoveTwo().substring(3, 4)))
					&& (board.getMovingPlayer().equals(board.getMoveFour().substring(3, 4)))
					&& (move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2)) && move
							.substring(0, 2).equals(board.getMoveFour().substring(0, 2)))) {
				throw new GameException(2);
			} else {
				setMoves(move, board);
			}

		}

		board.setCount(counter);

	}

	/**
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
	public String makeMove(String move, String bar) throws GameException {

		if (move.charAt(0) == Strings.charh && move.charAt(2) == Strings.chari) {
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
				throw new GameException(2);
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
				throw new GameException(2);
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
				throw new GameException(2);
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
				throw new GameException(2);
			}
		}

		return bar;
	}

	/**
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
	 * The overall input will have the player number who will start performing
	 * moves after the moves are performed the moving player value in the output
	 * must be the player who last performed the move
	 * 
	 * @param beadConf
	 *            Configuration of the beads
	 * @param moveCounter
	 *            the move Counter Value which is the number of moves performed
	 * @param movingPlayer
	 *            the player which started the sequence of moves
	 * @param numberOfPlayers
	 *            the total number of player playing the game
	 * @return
	 */

	public void setMovingPlayer(Board board) {

		if (board.getNumberOfPlayers().equals(Strings.two)) {
			if (board.getMovingPlayer().equals(Strings.one)) {
				board.setMovingPlayer(Strings.two);
			}
			if (board.getMovingPlayer().equals(Strings.two)) {
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

	public void checkElimination(Board board) {

		if (board.getNumberOfPlayers().equals(Strings.two)) {
			if (board.getMovingPlayer().equals(Strings.one) & !board.isPlayerOne()) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.two) & !board.isPlayerTwo()) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers().equals(Strings.three)) {
			if (board.getMovingPlayer().equals(Strings.one) & !board.isPlayerOne()) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.two) & !board.isPlayerTwo()) {
				board.setMovingPlayer(Strings.three);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.three) & !board.isPlayerThree()) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}

		}
		if (board.getNumberOfPlayers().equals(Strings.four)) {
			if (board.getMovingPlayer().equals(Strings.one) & !board.isPlayerOne()) {
				board.setMovingPlayer(Strings.two);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.two) & !board.isPlayerTwo()) {
				board.setMovingPlayer(Strings.three);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.three) & !board.isPlayerThree()) {
				board.setMovingPlayer(Strings.four);
				checkElimination(board);
			}
			if (board.getMovingPlayer().equals(Strings.four) & !board.isPlayerFour()) {
				board.setMovingPlayer(Strings.one);
				checkElimination(board);
			}
		}
	}

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
