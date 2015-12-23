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

	public String moveTest(String input) throws Exception {
		Board board = new Board();
		RuleController ruleController = new RuleController();
		BeadConf beadConf= new BeadConf();
		BoardConf boardConf = new BoardConf();
		WinnerDecider winner = new WinnerDecider();
		EliminationDecider eliminationDecider = new EliminationDecider();
		board.setInput(input);


		try {
			// checking the given input follows the correct pattern
			if (Pattern.matches(Constants.gamePattern, board.getInput())) {

				board.setNumberOfPlayers(Integer.parseInt(board.getInput().substring(0, 1)));
				board.setMovingPlayer(Integer.parseInt(board.getInput().substring(1, 2)));
				board.setPostionsOfHorizontalBars(board.getInput().substring(2, 9));
				board.setPostionsOfVerticalBars(board.getInput().substring(9, 16));
				board.setBeadConfiguration(board.getInput().substring(16, 65));
				board.setSequenceOfMoves(board.getInput().substring(65, board.getInput().length()));



				// Perform moves
				// The moves are moved into an array and the moves are performed
				// one at a time
				String[] movesArray = board.getSequenceOfMoves().split(Constants.movePattern);
				if (!board.getSequenceOfMoves().isEmpty()) {
					for (String move : movesArray) {

						// check all the rules before performing the move
						board = ruleController.rulesCheck(move, board);

						// update the position of the bars according to the move
						if (move.charAt(0) == Constants.charh) {
							board.setPostionsOfHorizontalBars(makeMove(move,
									board.getPostionsOfHorizontalBars(), board));

						}
						if (move.charAt(0) == Constants.charv) {
							board.setPostionsOfVerticalBars(makeMove(move,
									board.getPostionsOfVerticalBars(), board));

						}
						// On a successful move the player who performed the
						// move and the move are stored in history of moves
						setMoves(move, board);

						// generate the configuration of the board according to
						// the new position of the bars
						board.setBoardConfiguration(boardConf.boardConfGenerator(
								board.getPostionsOfHorizontalBars(),
								board.getPostionsOfVerticalBars()));

						// generate the configuration of beads after performing
						// the moves
						board.setBeadConfiguration(beadConf.beadConfGenerator(board.getBeadConfiguration(),
								board.getBoardConfiguration()));

						// check the bead configuration for the available
						// players
						board = beadConf.checkbeadConf(board);

						// set the next possible moving player

						setMovingPlayer(board);

						// check if the player is eliminated, if yes set
						// movingPlayer to the eligible player
						board = eliminationDecider.checkElimination(board);

						// check for win condition
						board = winner.checkWin(board);

						// set the output of the board
						board.setOutput(String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer())
								+ board.getPostionsOfHorizontalBars()
								+ board.getPostionsOfVerticalBars() + board.getBeadConfiguration());

					}
				} else {
					// in case there are no moves to perform check for a winning
					// condition
					board = winner.checkWin(board);
					// setting the input back to the output as there are no
					// moves to perform
					board.setOutput(String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer())
							+ board.getPostionsOfHorizontalBars()
							+ board.getPostionsOfVerticalBars() + board.getBeadConfiguration());
				}
				System.out.println(Constants.output);
				System.out.println(board.getOutput());
			} else {
				// throw exception that the input is not valid
				throw new GameException(1);

			}
		} catch (Exception e) {
			throw e;
		}

		return board.getOutput();
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

		if (move.charAt(0) == Constants.charh && move.charAt(2) == Constants.chari) {
			// position of bar in the barConfig string will be 1 less than the
			// specified position
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);
			if (changeBarVal == Constants.charone || changeBarVal == Constants.chartwo) {
				if (changeBarVal == Constants.charone) {
					changeBarVal = Constants.charzero;
				} else {
					changeBarVal = Constants.charone;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(1, String.valueOf(move.charAt(1)), board);
			}
		} else if (move.charAt(0) == Constants.charh && move.charAt(2) == Constants.charo) {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);

			if (changeBarVal == Constants.charzero || changeBarVal == Constants.charone) {
				if (changeBarVal == Constants.charzero) {
					changeBarVal = Constants.charone;
				} else {
					changeBarVal = Constants.chartwo;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(2, String.valueOf(move.charAt(1)), board);
			}
		} else if (move.charAt(0) == Constants.charv && move.charAt(2) == Constants.chari) {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);
			if (changeBarVal == Constants.charone || changeBarVal == Constants.chartwo) {
				if (changeBarVal == Constants.charone) {
					changeBarVal = Constants.charzero;
				} else {
					changeBarVal = Constants.charone;
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();

			} else {
				throw new GameException(3, String.valueOf(move.charAt(1)), board);
			}
		} else if (move.charAt(0) == Constants.charv && move.charAt(2) == Constants.charo) {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);

			if (changeBarVal == Constants.charzero || changeBarVal == Constants.charone) {
				if (changeBarVal == Constants.charzero) {
					changeBarVal = Constants.charone;
				} else {
					changeBarVal = Constants.chartwo;
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
	 * This function determines the next player who must perform the move
	 * 
	 * @param board
	 */
	public void setMovingPlayer(Board board) {
		board.setLastMovingPlayer(board.getMovingPlayer());
		if (board.getNumberOfPlayers()==Constants.two ) {
			if (board.getMovingPlayer()== Constants.one) {
				board.setMovingPlayer(Constants.two);
			} else if (board.getMovingPlayer()== Constants.two) {
				board.setMovingPlayer(Constants.one);
			}
		} else if (board.getNumberOfPlayers()== Constants.three) {
			if (board.getMovingPlayer()== Constants.three) {
				board.setMovingPlayer(Constants.one);
			} else if (board.getMovingPlayer()== Constants.two) {
				board.setMovingPlayer(Constants.three);
			} else if (board.getMovingPlayer()== Constants.one) {
				board.setMovingPlayer(Constants.two);
			}

		} else if (board.getNumberOfPlayers()== Constants.four) {
			if (board.getMovingPlayer()== Constants.four) {
				board.setMovingPlayer(Constants.one);
			} else if (board.getMovingPlayer()== Constants.three) {
				board.setMovingPlayer(Constants.four);
			} else if (board.getMovingPlayer()== Constants.two) {
				board.setMovingPlayer(Constants.three);
			} else if (board.getMovingPlayer()== Constants.one) {
				board.setMovingPlayer(Constants.two);
			}

		}

	}
}
