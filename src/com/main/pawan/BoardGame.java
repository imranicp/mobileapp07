package com.main.pawan;

 public class BoardGame {
	//userInput of 68 character
	
		String userInput;
	//String for Assigning Filled and hole in Vertical Bar and Assigning Filled and hole in Vertical Bar
		String verticalBar ="X0000X0XXX000XX00XX0X00X0XXX00XX000XXX000X0XXXX00000XXX00X00X0X";

	//Assigning Filled and hole in Horizontal Bar and Assigning Filled and hole in Horizontal Bar
		String horizontalBar="X0X0X0X0XX00X00X0XX000X000XX0X0X0X0XX0000000XXX000X0XXX00X0X0XX";
	//Output of 65 Character String with Resulting state or Error
		String output = null;
	//userInput for bar movement from player	
		String userInputForBarMovement;
	//Number of Player playing	
		
		String numberOfPlayer;
	//Moving Player
		
		String movingPlayer;
	//Positions of horizontal bar at the start of the game	

		String postionsOfHorizontalBar;
	//Positions of vertical bar at the start of the Game	
		
		String postionsOfVerticalBar;
	//Beads representation	
		
		String beadsRepresentation;
		String beadsPlaced;
	//Sequence of move by Player
		
		String sequenceOfMove;
	//Selected slot of vertical bar
		
		String selectedSlotOfVerticalBar="";
	//selected slot of horizontal bar
		String selectedSlotOfHorizontalBar="";

	// Formation of table
		
		String[][] slotOfHorizontalBar= new String[7][7];
		String[][] slotOfVerticalBar= new String[7][7];
		String[][] colorRepresentation= new String[7][7];
		String[][] beadsOnTheTable = new String[7][7];
		
		int countForFirstPlayer = 0;
		int countForSecondPlayer = 0;
		int countForThirdPlayer = 0;
		int countForFourthPlayer = 0;
		int count = 0;
		String moveOfBar = "";
		String barNumber = "";
		String move = "";
		String checkForMoveFirstPlayer="";
		String checkForMoveSecondPlayer="";
		String checkForMoveThirdPlayer="";
		String checkForMoveFourthPlayer="";
		String winner;
		String lastMovingPlayer;
		 BoardGame(){}
		
}
