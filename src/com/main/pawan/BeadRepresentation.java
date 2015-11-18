package com.main.pawan;

public class BeadRepresentation {

	public BoardGame Count(BoardGame value){
		
int i,j;
		
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Logic of putting beads on the board but some improvement needed in
		// the code.(need to be coded well)
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int a = 0, b = 1;
		

		for (i = 0; i < 7; i++) {

			for (j = 0; j < 7; j++) {

				value.beadsOnTheTable[i][j] = value.beadsRepresentation
						.substring(a, a + 1);

				if (value.beadsOnTheTable[i][j].equals("1")) {
					if (value.colorRepresentation[i][j].equals("H")) {

						value.beadsOnTheTable[i][j] = "0";
						value.output = "bead for first player fall down";

						System.out.print(value.output);

					} else if (value.colorRepresentation[i][j].equals("R")
							|| value.colorRepresentation[i][j].equals("B")) {

						value.beadsOnTheTable[i][j] = "1";
						value.output = "beads successfully placed";
						b = 1;
						value.countForFirstPlayer++;
						value.count++;
					}

				} else if (value.beadsOnTheTable[i][j].equals("2")) {
					if (value.colorRepresentation[i][j].equals("H")) {
						value.beadsOnTheTable[i][j] = "0";
						value.output = "bead for second player fall down";

						System.out.print(value.output);

						b = 0;
						break;
					} else if (value.colorRepresentation[i][j].equals("R")
							|| value.colorRepresentation[i][j].equals("B")) {
						value.beadsOnTheTable[i][j] = "2";
						value.output = "beads successfully placed";
						b = 1;
						value.countForSecondPlayer++;
						value.count++;
					}

				} else if (value.beadsOnTheTable[i][j].equals("3")) {
					if (value.colorRepresentation[i][j].equals("H")) {
						value.beadsOnTheTable[i][j] = "0";
						value.output = "bead for third player fall down";

						System.out.print(value.output);

					} else if (value.colorRepresentation[i][j].equals("R")
							|| value.colorRepresentation[i][j].equals("B")) {
						value.beadsOnTheTable[i][j] = "3";
						value.output = "beads successfully placed";
						b = 1;
						value.countForThirdPlayer++;
						value.count++;
					}

				} else if (value.beadsOnTheTable[i][j].equals("4")) {
					if (value.colorRepresentation[i][j].equals("D")) {
						value.output = "bead for fourth player fall down";
						value.beadsOnTheTable[i][j] = "0";
						System.out.print(value.output);

					} else if (value.colorRepresentation[i][j].equals("R")
							|| value.colorRepresentation[i][j].equals("B")) {
						value.beadsOnTheTable[i][j] = "4";
						value.output = "beads successfully placed";
						b = 1;
						value.countForFourthPlayer++;
						value.count++;
					}

				} else {
					value.beadsOnTheTable[i][j] = "0";
				}

				a++;
			}

		}

		if (b != 0) {
			System.out.println("\n" + value.output);
			System.out.println("\n" + value.countForFirstPlayer);
			System.out.println("\n" + value.countForSecondPlayer);
			System.out.println("\n" + value.countForThirdPlayer);
			System.out.println("\n" + value.countForFourthPlayer);
		}
	/*	for (i = 0; i < 7; i++) {
			for (j = 0; j < 7; j++) {
				System.out.print(value.beadsOnTheTable[i][j]);
			}
			System.out.println();
		}*/
		return value;
	}
}
