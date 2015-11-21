package com.main.rishabh;

import java.util.regex.Pattern;

/**
 * @author Rishabh
 * 
 */
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
		String output = "";
		String boardConf = "";
		String pattern = "[234][1234][012]{7}[012]{7}[01234]{49}([hv][1234567][io])*";

		try {
			if (Pattern.matches(pattern, input)) {
				int inputLength = input.length();
				int moveCounter = 0;
				String numberOfPlayers = String.valueOf(input.charAt(0));
				System.out.println(numberOfPlayers);
				String movingPlayer = String.valueOf(input.charAt(1));
				System.out.println(movingPlayer);

				String horizontalBar = input.substring(2, 9);
				System.out.println(horizontalBar);

				String verticalBar = input.substring(9, 16);
				System.out.println(verticalBar);

				String beadConf = input.substring(16, 65);
				System.out.println(beadConf);

				String moves = input.substring(65, inputLength);
				System.out.println(moves);

				boardConf = boardConfGenerator(horizontalBar, verticalBar);

				System.out.println(boardConf);
				System.out.println(beadConf);
				String[] movesArray = moves.split("(?<=\\G...)");
				if (!moves.isEmpty()) {
					for (String move : movesArray) {
						moveCounter = moveCounter + 1;
						if (move.charAt(0) == 'h') {
							horizontalBar = makeMove(move, horizontalBar);
						}
						if (move.charAt(0) == 'v') {
							verticalBar = makeMove(move, verticalBar);
						}

						System.out.println("Board Config after performing moves");
						boardConf = boardConfGenerator(horizontalBar, verticalBar);

						beadConf = beadConfGenerator(beadConf, boardConf);

						movingPlayer = getMovingPlayer(beadConf, moveCounter, movingPlayer,
								numberOfPlayers);

						String playersLeft = checkbeadConf(beadConf, numberOfPlayers);
					}
				}

				System.out.println(boardConf);
				System.out.println(beadConf);

				output = numberOfPlayers + movingPlayer + horizontalBar + verticalBar + beadConf;
				System.out.println(output);
			} else {
				throw new GameException(1);
			}
		} catch (Exception e) {
			throw e;
			// e.fillInStackTrace();
		}

		return output;
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

	public String getMovingPlayer(String beadConf, int moveCounter, String movingPlayer,
			String numberOfPlayers) {

		if (numberOfPlayers.equals("2")) {
			if (moveCounter % 2 == 0) {

				movingPlayer = "1";

			} else if (numberOfPlayers.equals("2") && moveCounter % 2 != 0) {
				movingPlayer = "2";
			}
		} else if (numberOfPlayers.equals("3")) {
			if (movingPlayer.equals("3")) {
				if (moveCounter % 3 == 0) {
					movingPlayer = "3";
				} else if (moveCounter % 3 == 1) {
					movingPlayer = "1";
				} else if (moveCounter % 3 == 2) {
					movingPlayer = "2";
				}
			} else if (movingPlayer.equals("2")) {
				if (moveCounter % 3 == 0) {
					movingPlayer = "2";
				} else if (moveCounter % 3 == 1) {
					movingPlayer = "3";
				} else if (moveCounter % 3 == 2) {
					movingPlayer = "1";
				}
			} else if (movingPlayer.equals("1")) {
				if (moveCounter % 3 == 0) {
					movingPlayer = "1";
				} else if (moveCounter % 3 == 1) {
					movingPlayer = "2";
				} else if (moveCounter % 3 == 2) {
					movingPlayer = "3";
				}
			}

		} else if (numberOfPlayers.equals("4")) {
			if (movingPlayer.equals("4")) {
				if (moveCounter % 4 == 0) {
					movingPlayer = "4";
				} else if (moveCounter % 4 == 1) {
					movingPlayer = "1";
				} else if (moveCounter % 4 == 2) {
					movingPlayer = "2";
				} else if (moveCounter % 4 == 3) {
					movingPlayer = "3";
				}
			} else if (movingPlayer.equals("2")) {
				if (moveCounter % 4 == 0) {
					movingPlayer = "2";
				} else if (moveCounter % 4 == 1) {
					movingPlayer = "3";
				} else if (moveCounter % 4 == 2) {
					movingPlayer = "4";
				} else if (moveCounter % 4 == 3) {
					movingPlayer = "1";
				}
			} else if (movingPlayer.equals("3")) {
				if (moveCounter % 4 == 0) {
					movingPlayer = "3";
				} else if (moveCounter % 4 == 1) {
					movingPlayer = "4";
				} else if (moveCounter % 4 == 2) {
					movingPlayer = "1";
				} else if (moveCounter % 4 == 3) {
					movingPlayer = "2";
				}
			} else if (movingPlayer.equals("1")) {
				if (moveCounter % 4 == 0) {
					movingPlayer = "1";
				} else if (moveCounter % 4 == 1) {
					movingPlayer = "2";
				} else if (moveCounter % 4 == 2) {
					movingPlayer = "3";
				} else if (moveCounter % 4 == 3) {
					movingPlayer = "4";
				}
			}

		}
		return movingPlayer;
	}

	public String checkbeadConf(String beadConf, String numberOfPlayers) {
		String playersLeft = "";
		if (numberOfPlayers.equals("2")) {
			if (beadConf.indexOf('2') == -1) {
				playersLeft = "1";
				System.out.println("Player 1 wins");
			}
			if (beadConf.indexOf('1') == -1) {
				playersLeft = "1";
				System.out.println("Player 2 wins");
			}
		}

		if (numberOfPlayers.equals("3")) {
			if (beadConf.indexOf('3') == -1) {
				playersLeft = "2";

			}
			if (beadConf.indexOf('2') == -1) {
				playersLeft = "2";

			}
			if (beadConf.indexOf('1') == -1) {
				playersLeft = "2";

			}
		}
		return playersLeft;
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
			if (beadConf.charAt(i) != '0' && boardConf.charAt(i) == 'h') {
				StringBuilder newBeadConfig = new StringBuilder(beadConf);
				newBeadConfig.setCharAt(i, '0');
				beadConf = newBeadConfig.toString();
			}
		}
		return beadConf;
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

			horBarConf = horBarConf + barConfGenerator("h", String.valueOf(i), barPos);
			i++;
		}
		i = 1;
		System.out.println("");
		for (char barPos : verticalBar.toCharArray()) {

			verBarConf = verBarConf + barConfGenerator("v", String.valueOf(i), barPos);
			i++;
		}
		System.out.println("");
		System.out.println(verBarConf);
		verBarConf = transpose(verBarConf);

		System.out.println("");
		System.out.println(verBarConf);
		System.out.println("");
		System.out.println(horBarConf);
		System.out.println("");
		for (i = 0; i < horBarConf.length(); i++) {
			if (verBarConf.charAt(i) == 'x') {
				boardConf = boardConf + "b";
			} else if (verBarConf.charAt(i) == 'o' && horBarConf.charAt(i) == 'x') {
				boardConf = boardConf + "r";
			} else {
				boardConf = boardConf + "h";
			}

		}
		return boardConf;
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

		System.out.println(move);
		if (move.charAt(0) == 'h' && move.charAt(2) == 'i') {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);
			if (changeBarVal == '1' || changeBarVal == '2') {
				if (changeBarVal == '1') {
					changeBarVal = '0';
				} else {
					changeBarVal = '1';
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);
				System.out.println(bar);
				bar = newBarConfig.toString();
				System.out.println(bar);
			} else {
				throw new GameException(2);
			}
		} else if (move.charAt(0) == 'h' && move.charAt(2) == 'o') {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);

			if (changeBarVal == '0' || changeBarVal == '1') {
				if (changeBarVal == '0') {
					changeBarVal = '1';
				} else {
					changeBarVal = '2';
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);

				bar = newBarConfig.toString();
				System.out.println(bar);
			} else {
				throw new GameException(2);
			}
		} else if (move.charAt(0) == 'v' && move.charAt(2) == 'i') {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);
			if (changeBarVal == '1' || changeBarVal == '2') {
				if (changeBarVal == '1') {
					changeBarVal = '0';
				} else {
					changeBarVal = '1';
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);
				System.out.println(bar);
				bar = newBarConfig.toString();
				System.out.println(bar);
			} else {
				throw new GameException(2);
			}
		} else if (move.charAt(0) == 'v' && move.charAt(2) == 'o') {
			int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

			char changeBarVal = bar.charAt(position);

			if (changeBarVal == '0' || changeBarVal == '1') {
				if (changeBarVal == '0') {
					changeBarVal = '1';
				} else {
					changeBarVal = '2';
				}

				StringBuilder newBarConfig = new StringBuilder(bar);
				newBarConfig.setCharAt(position, changeBarVal);
				System.out.println(bar);
				bar = newBarConfig.toString();
				System.out.println(bar);
			} else {
				throw new GameException(2);
			}
		}

		return bar;
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
		String horBar = "xoxoxoxoxxooxooxoxxoooxoooxxoxoxoxoxxoooooooxxxoooxoxxxooxoxoxx";
		String verBar = "xooooxoxxxoooxxooxxoxooxoxxxooxxoooxxxoooxoxxxxoooooxxxooxooxox";
		if (barType == "h") {
			barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);
			barConf = horBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));
		} else if (barType == "v") {

			barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);
			barConf = verBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));

		}

		if (barPos == '0') {
			barConf = barConf.substring(0, 7);
		} else if (barPos == '1') {
			barConf = barConf.substring(1, 8);
		} else if (barPos == '2') {
			barConf = barConf.substring(2, 9);
		}
		System.out.println(barConf);
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
}
