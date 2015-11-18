package com.main.pawan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController {
	
	public static void main(String[] arg) throws IOException{
		BoardGame value = new BoardGame();
		GameRules start = new GameRules();
		GameResult result = new GameResult();
		
do{		
//Input from user		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		value.userInput = br.readLine();
		value.numberOfPlayer = value.userInput.substring(0, 1);
		value.movingPlayer = value.userInput.substring(1, 2);
		value.postionsOfHorizontalBar = value.userInput.substring(2, 9);
		value.postionsOfVerticalBar = value.userInput.substring(9, 16);
		value.beadsRepresentation = value.userInput.substring(16, 65);
		value.sequenceOfMove = value.userInput.substring(65,
				value.userInput.length());
//		
		System.out.println("Hello to Board Game");
//		 System.out.println(value.verticalBar);
//		 System.out.println(value.horizontalBar);
		System.out.println("Number OfPlayer :" + value.numberOfPlayer);
		System.out.println("Moving Player :" + value.movingPlayer);
		System.out.println("Postions Of value.horizontalBar:"
				+ value.postionsOfHorizontalBar);
		System.out.println("Postions Of value.VerticalBar :"
				+ value.postionsOfVerticalBar);
		System.out.println("Beads Representation:" + value.beadsRepresentation);
		System.out.println("Sequence Of Move :" + value.sequenceOfMove);

//Game Logic		
		
		 
		start.Rules(value);
//Output		
		value.output =value.numberOfPlayer+value.movingPlayer+value.postionsOfHorizontalBar+value.postionsOfVerticalBar+value.beadsRepresentation;
		System.out.println();
		System.out.println(value.output);



		
}while(value.count>1);	
		result.Winner(value);	
		System.out.println("Winner of the Game is : "+value.winner);
}
	}


