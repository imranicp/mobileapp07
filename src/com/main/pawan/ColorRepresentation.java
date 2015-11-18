package com.main.pawan;

public class ColorRepresentation {
	public BoardGame BoardReady(BoardGame value){
	int i,j;
	
	// Color representation and Putting Beads
			for (i = 0; i < 7; i++) {

				for (j = 0; j < 7; j++) {

					// if both the horizontally and vertically has a hole then colorrepresenation is egual to Danger

					if ((value.slotOfHorizontalBar[i][j].equals("0"))
							&& (value.slotOfVerticalBar[i][j].equals("0"))) {

						value.colorRepresentation[i][j] = "H";
					}
					// if both the horizontally is filled and vertically has a hole then colorrepresenation is egual to Red
					else if ((!value.slotOfHorizontalBar[i][j].equals("0"))
							&& (value.slotOfVerticalBar[i][j].equals("0"))) {
						value.colorRepresentation[i][j] = "R";
					}
					// if both the horizontally has a hole and vertically is filled then colorrepresenation is egual to Red

					else if ((value.slotOfHorizontalBar[i][j].equals("0"))
							&& (!value.slotOfVerticalBar[i][j].equals("0"))) {
						value.colorRepresentation[i][j] = "B";
					}
					// if both the horizontally and vertically is filled then colorrepresenation is egual to Blue

					else if ((!value.slotOfHorizontalBar[i][j].equals("0"))
							&& (!value.slotOfVerticalBar[i][j].equals("0"))) {

						value.colorRepresentation[i][j] = "B";
					}
					System.out.print(value.colorRepresentation[i][j] + "  ");
				}
				System.out.println();

			}
			return value;
	}
}
