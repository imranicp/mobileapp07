package com.main.pawan;

public class GameRules {
	public BoardGame Rules(BoardGame value){
int checkPoint=0;
main_file input = new main_file();
		if (value.numberOfPlayer.equals("4")){
			if (value.movingPlayer.equals("1")){
				// Check for the consecutive move
				if (value.checkForMoveFirstPlayer.equals(value.sequenceOfMove)){
					value.output="Wrong mOve";
					checkPoint=1;
				}else{
					value.movingPlayer="2";
					value.lastMovingPlayer=value.movingPlayer;
					value.checkForMoveFirstPlayer=value.sequenceOfMove;
				}
				
			}else if (value.movingPlayer.equals("2")){
				if (value.checkForMoveSecondPlayer.equals(value.sequenceOfMove)){
					value.output="Wrong mOve";
					checkPoint=1;
				}else{
					value.movingPlayer="3";
					value.lastMovingPlayer=value.movingPlayer;
					value.checkForMoveSecondPlayer=value.sequenceOfMove;
				}
				
			}else if (value.movingPlayer.equals("3")){
				if (value.checkForMoveThirdPlayer.equals(value.sequenceOfMove)){
					value.output="Wrong mOve";
					checkPoint=1;
				}else{
					value.checkForMoveThirdPlayer=value.sequenceOfMove;
					value.lastMovingPlayer=value.movingPlayer;
					value.movingPlayer="4";
				}
				
			}
			else if (value.movingPlayer.equals("4")){
				if (value.checkForMoveFourthPlayer.equals(value.sequenceOfMove)){
					value.output="Wrong mOve";
					checkPoint=1;
				}else{
					value.checkForMoveFourthPlayer=value.sequenceOfMove;
					value.lastMovingPlayer=value.movingPlayer;
					value.movingPlayer="1";
				}
			}	
		}else if (value.numberOfPlayer.equals("2")){
			if (value.movingPlayer.equals("1")){
				if (value.checkForMoveFirstPlayer.equals(value.sequenceOfMove)){
					value.output="Wrong mOve";
					checkPoint=1;
				}else{
					value.checkForMoveFirstPlayer=value.sequenceOfMove;
					value.lastMovingPlayer=value.movingPlayer;
					value.movingPlayer="2";
				}
			}else if (value.movingPlayer.equals("2")){
				if (value.checkForMoveSecondPlayer.equals(value.sequenceOfMove)){
					value.output="Wrong mOve";
					checkPoint=1;
				}else{
					value.checkForMoveSecondPlayer=value.sequenceOfMove;
					value.lastMovingPlayer=value.movingPlayer;
					value.movingPlayer="1";
				}	
			}
		}
		if (checkPoint==0){
			value = input.Game(value);	
			return value;
		}	
		
		
		
		return value;
	}
}
