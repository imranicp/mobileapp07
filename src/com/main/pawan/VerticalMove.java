package com.main.pawan;

public class VerticalMove {

		public BoardGame Logic(BoardGame value){
			int a=0,b,i,j;
			
			a=Integer.parseInt(value.barNumber)-1;
			if (Integer.parseInt(value.postionsOfVerticalBar.substring(a,
					a + 1)) == 0) {	
				value.selectedSlotOfVerticalBar = value.verticalBar.substring(a*9,
								a*9 + 7);
				value.postionsOfVerticalBar = value.postionsOfVerticalBar.substring(0,a)+"1"+value.postionsOfVerticalBar.substring(a+1,7);
			}
			else if (Integer.parseInt(value.postionsOfVerticalBar.substring(a,
					a + 1)) == 1) {
				value.selectedSlotOfVerticalBar = value.verticalBar.substring(a*9 + 1,
						a*9 + 8);
	///////////////////////////////////////////////////change in position of Vertical bar////////////////////////////////////////////////////			
				if(value.move.equals("o")){
					value.postionsOfVerticalBar = value.postionsOfVerticalBar.substring(0,a)+"2"+value.postionsOfVerticalBar.substring(a+1,7);	
				}else if(value.move.equals("i")){
					value.postionsOfVerticalBar = value.postionsOfVerticalBar.substring(0,a)+"0"+value.postionsOfVerticalBar.substring(a+1,7);
				}
				
				
			}
			else if (Integer.parseInt(value.postionsOfVerticalBar.substring(a,
					a + 1)) == 2) {
				value.selectedSlotOfVerticalBar = value.verticalBar.substring(a*9 + 2,
						a*9 + 9);
				value.postionsOfVerticalBar = value.postionsOfVerticalBar.substring(0,a)+"1"+value.postionsOfVerticalBar.substring(a+1,7);
			}
				for (j = 0; j < 7; j++) {
					value.slotOfVerticalBar[a][j] = value.selectedSlotOfVerticalBar
							.substring(j, j + 1);
//					System.out.print(value.slotOfVerticalBar[a][j]
//							+ "  ");
				}
				System.out.println();
				for (j = 0; j < 7; j++) {

					// if both the horizontally and vertically has a hole then colorrepresenation is egual to Danger
					if ((value.slotOfVerticalBar[a][j].equals("0"))
							&& (value.slotOfVerticalBar[a][j]
									.equals("0"))) {

						value.colorRepresentation[a][j] = "H";
					}
					// if both the horizontally is filled and vertically
					// has a hole then color represenation is egual to
					// Red
					else if ((!value.slotOfVerticalBar[a][j]
							.equals("0"))
							&& (value.slotOfVerticalBar[a][j]
									.equals("0"))) {
						value.colorRepresentation[a][j] = "R";
					}
					// if both the horizontally has a hole and
					// vertically is filled then color represenation is
					// egual to Red

					else if ((value.slotOfVerticalBar[0][j]
							.equals("0"))
							&& (!value.slotOfVerticalBar[a][j]
									.equals("0"))) {
						value.colorRepresentation[a][j] = "B";
					}
					// if both the horizontally and vertically is filled
					// then color represenation is egual to Blue

					else if ((!value.slotOfVerticalBar[0][j]
							.equals("0"))
							&& (!value.slotOfVerticalBar[a][j]
									.equals("0"))) {

						value.colorRepresentation[a][j] = "B";
					}
//					System.out.print(value.colorRepresentation[a][j]
//							+ "  ");
				}

				for (j = 0; j < 7; j++) {

					value.beadsOnTheTable[a][j] = value.beadsRepresentation
							.substring(a*7+j, a*7 +j + 1);

					if (value.beadsOnTheTable[a][j].equals("1")) {
						if (value.colorRepresentation[a][j].equals("H")) {

							value.beadsOnTheTable[a][j] = "0";
							value.output = "bead for first player fall down";
							value.countForFirstPlayer--;
							value.count--;
							System.out.print(value.output);
						} else if (value.colorRepresentation[a][j]
								.equals("R")
								|| value.colorRepresentation[a][j]
										.equals("B")) {

							value.beadsOnTheTable[a][j] = "1";
							value.output = "beads successfully placed";
							b = 1;

						}

					} else if (value.beadsOnTheTable[a][j].equals("2")) {
						if (value.colorRepresentation[a][j].equals("H")) {
							value.beadsOnTheTable[a][j] = "0";
							value.output = "bead for second player fall down";
							value.countForSecondPlayer--;
							value.count--;
							System.out.print(value.output);
							b = 0;
							break;
						} else if (value.colorRepresentation[a][j]
								.equals("R")
								|| value.colorRepresentation[a][j]
										.equals("B")) {
							value.beadsOnTheTable[a][j] = "2";
							value.output = "beads successfully placed";
							b = 1;

						}

					} else if (value.beadsOnTheTable[a][j].equals("3")) {
						if (value.colorRepresentation[a][j].equals("H")) {
							value.beadsOnTheTable[a][j] = "0";
							value.output = "bead for third player fall down";
							value.countForThirdPlayer--;
							value.count--;
							System.out.print(value.output);
						} else if (value.colorRepresentation[a][j]
								.equals("R")
								|| value.colorRepresentation[a][j]
										.equals("B")) {
							value.beadsOnTheTable[a][j] = "3";
							value.output = "beads successfully placed";
							b = 1;

						}

					} else if (value.beadsOnTheTable[a][j].equals("4")) {
						if (value.colorRepresentation[a][j].equals("H")) {
							value.output = "bead for fourth player fall down";
							value.beadsOnTheTable[a][j] = "0";

							value.countForFourthPlayer--;
							value.count--;
							System.out.print(value.output);
						} else if (value.colorRepresentation[a][j]
								.equals("R")
								|| value.colorRepresentation[a][j]
										.equals("B")) {
							value.beadsOnTheTable[a][j] = "4";
							value.output = "beads successfully placed";
							b = 1;

						}

					} else {
						value.beadsOnTheTable[a][j] = "0";
					}

					
				}

				String newBeadRepresentation="";
				System.out.println();
				for(i=0;i<7;i++){
					for(j=0;j<7;j++){
						newBeadRepresentation=newBeadRepresentation+value.beadsOnTheTable[i][j];
						System.out.print(value.beadsOnTheTable[i][j]);
					}
					System.out.println();
				}
				value.beadsRepresentation=newBeadRepresentation;
	
	
	
				return value;

		}



			
		}

