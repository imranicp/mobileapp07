package com.main.vahid;


public class BoardGame {

	public static void main(String[] args) {

		String H[] = { "0", "1", "0", "1", "0", "1", "2" }, V[] = { "0", "2",
				"0", "2", "2", "1", "2" }; // position indicators of bars,0 is
											// inner, 1 is central and 2 is
											// outer.
		char movement[] = { 'v', '5', 'i' };
		char RedBoard[][] = new char[7][7], BlueBoard[][] = new char[7][7], FinalBoard[][] = new char[7][7], Beads[][] = new char[7][7], movedBoard[][] = new char[7][7], tempBoard[][] = new char[7][7];

		char[][] RedBar = { { '1', '0', '1', '0', '1', '0', '1', '0', '1' },
				{ '1', '0', '0', '1', '0', '0', '1', '0', '1' },
				{ '1', '0', '0', '0', '1', '0', '0', '0', '1' },
				{ '1', '0', '1', '0', '1', '0', '1', '0', '1' },
				{ '1', '0', '0', '0', '0', '0', '0', '0', '1' },
				{ '1', '1', '0', '0', '0', '1', '0', '1', '1' },
				{ '1', '0', '0', '1', '0', '1', '0', '1', '1' } };

		char[][] BlueBar = { { '1', '0', '0', '0', '0', '1', '0', '1', '1' },
				{ '1', '0', '0', '0', '1', '1', '0', '0', '1' },
				{ '1', '0', '1', '0', '0', '1', '0', '1', '1' },
				{ '1', '0', '0', '1', '1', '0', '0', '0', '1' },
				{ '1', '1', '0', '0', '0', '1', '0', '1', '1' },
				{ '1', '1', '0', '0', '0', '0', '0', '1', '1' },
				{ '1', '0', '0', '1', '0', '0', '1', '0', '1' } };

		RedBoard = CalcRedBoard(H, RedBar);

		BlueBoard = CalcBlueBoard(V, BlueBar);

		tempBoard = CalcFinal(RedBoard, BlueBoard);

		FinalBoard = tempBoard;

		movedBoard = lastMovement(RedBar, BlueBar, FinalBoard, movement, H, V);

		for (int a = 0; a <= 6; a++) {
			for (int b = 0; b <= 6; b++) {
				System.out.print(RedBoard[a][b] + " ");
			}
			System.out.println(' ');

		}

		System.out.println();

		for (int a = 0; a <= 6; a++) {
			for (int b = 0; b <= 6; b++) {
				System.out.print(BlueBoard[a][b] + " ");
			}
			System.out.println(' ');

		}

		System.out.println();

		for (int a = 0; a <= 6; a++) {
			for (int b = 0; b <= 6; b++) {
				System.out.print(tempBoard[a][b] + " ");
			}
			System.out.println(' ');

		}

		System.out.println();

		for (int a = 0; a <= 6; a++) {
			for (int b = 0; b <= 6; b++) {
				System.out.print(movedBoard[a][b] + " ");
			}
			System.out.println(' ');

		}

	}

	private static char[][] CalcFinal(char[][] redBoard, char[][] blueBoard) {
		char tempFinal[][] = new char[7][7];

		for (int m = 0; m <= 6; m++) {
			for (int n = 0; n <= 6; n++) {
				if (blueBoard[m][n] == '1') {
					tempFinal[m][n] = 'B';
				} else if (blueBoard[m][n] == '0' & redBoard[m][n] == '1') {
					tempFinal[m][n] = 'R';
				} else {
					tempFinal[m][n] = 'O';
				}
			}
		} // end for finalboard
		return tempFinal;
	}

	private static char[][] CalcBlueBoard(String[] v, char[][] blueBar) {
		char temp[][] = new char[7][7];

		for (int j = 0; j <= 6; j++) {
			if (v[j] == "0") {

				for (int k = 0; k <= 6; k++) {

					temp[k][j] = blueBar[j][k];

				}
			}

			else if (v[j] == "1") {

				for (int k = 0; k <= 6; k++) {

					temp[k][j] = blueBar[j][k + 1];

				}
			}

			else {
				for (int k = 0; k <= 6; k++) {

					temp[k][j] = blueBar[j][k + 2];

				}
			}

		} // end for j
		return temp;
	}

	private static char[][] CalcRedBoard(String[] h, char[][] redBar) {

		char temp[][] = new char[7][7];

		for (int i = 0; i <= 6; i++) {
			if (h[i] == "0") {

				for (int k = 0; k <= 6; k++) {

					temp[i][k] = redBar[i][k];

				}

			} else if (h[i] == "1") {

				for (int k = 0; k <= 6; k++) {

					temp[i][k] = redBar[i][k + 1];

				}

			} else {
				for (int k = 0; k <= 6; k++) {

					temp[i][k] = redBar[i][k + 2];

				}
			}

		} // end for i

		return temp;

	}

	private static char[][] beadEater(char board[][], char beads[][],
			char move[]) {
		char newPosition[][] = null;
		int i = move[2];

		if (move[1] == 'h') {
			for (int j = 0; j <= 6; j++) {
				if (board[i][j] == 'O' & beads[i][j] != ' ') {
					beads[i][j] = ' ';

				}
			}
		} else if (move[1] == 'v') {
			for (int j = 0; j <= 6; j++) {
				if (board[j][i] == 'O' & beads[j][i] != ' ') {
					beads[j][i] = ' ';
				}
			}
		} else {
			System.out.println("Error");
		}

		return beads;
	}

	private static char[][] lastMovement(char redBar[][], char blueBar[][],
			char board[][], char move[], String hPos[], String vPos[]) {
		// char[] abc = new char[3];
		// abc = move;
		char d = move[0]; // vertical or horizontal - h or v
		int b = Character.getNumericValue(move[1]); // number of moved bar -
													// from 1 to 7
		char m = move[2]; // direction of movement - i or o

		if (d == 'h' & hPos[b] == "0" & m == 'i') {
			System.out.println("Inbound move is not allowed for horezontal bar"
					+ hPos[b]);
		} else if (d == 'h' & hPos[b] == "0" & m == 'o') {
			for (int j = 0; j <= 6; j++) {
				board[b][j] = redBar[b][j + 1];
			}
		} else if (d == 'h' & hPos[b] == "1" & m == 'i') {
			for (int j = 0; j <= 6; j++) {
				board[b][j] = redBar[b][j];
			}
		} else if (d == 'h' & hPos[b] == "1" & m == 'o') {
			for (int j = 0; j <= 6; j++) {
				board[b][j] = redBar[b][j + 2];
			}
		} else if (d == 'h' & hPos[b] == "2" & m == 'i') {
			for (int j = 0; j <= 6; j++) {
				board[b][j] = redBar[b][j + 1];
			}
		} else if (d == 'h' & hPos[b] == "2" & m == 'o') {
			System.out
					.println("Outbound move is not allowed for horezontal bar"
							+ hPos[b]);
		} else if (d == 'v' & vPos[b] == "0" & m == 'i') {
			System.out.println("Inbound move is not allowed for vertical bar"
					+ vPos[b]);
		} else if (d == 'v' & vPos[b] == "0" & m == 'o') {
			for (int j = 0; j <= 6; j++) {
				board[j][b] = blueBar[b][j + 1];
			}
		} else if (d == 'v' & vPos[b] == "1" & m == 'i') {
			for (int j = 0; j <= 6; j++) {
				board[j][b] = blueBar[b][j];
			}
		} else if (d == 'v' & vPos[b] == "1" & m == 'o') {
			for (int j = 0; j <= 6; j++) {
				board[j][b] = blueBar[b][j + 2];
			}
		} else if (d == 'v' & vPos[b] == "2" & m == 'i') {
			for (int j = 0; j <= 6; j++) {
				board[j][b] = blueBar[b][j + 1];
			}
		} else if (d == 'v' & vPos[b] == "2" & m == 'o') {
			System.out.println("Outbound move is not allowed for vertical bar"
					+ vPos[b]);
		}

		// calculation of the new board
		for (int j = 0; j <= 6; j++) {
			if (d == 'h' & blueBar[b][j] == '1') {
				board[b][j] = 'B';
			} else if (d == 'h' & blueBar[b][j] == '0' & redBar[b][j] == '1') {
				board[b][j] = 'R';
			} else if (d == 'h' & blueBar[b][j] == '0' & redBar[b][j] == '0') {
				board[b][j] = 'O';
			} else if (d == 'v' & blueBar[j][b] == '1') {
				board[j][b] = 'B';
			} else if (d == 'v' & blueBar[j][b] == '0' & redBar[j][b] == '1') {
				board[j][b] = 'R';
			} else {
				board[j][b] = 'O';
			}
		}

		return board;

	}
}
