package com.main.pawan;

public class GameResult {
	public BoardGame Winner(BoardGame value){
		if(value.countForFirstPlayer==0 && value.countForSecondPlayer==0 && value.countForThirdPlayer==0 && value.countForFourthPlayer==0){
			
				value.winner=value.lastMovingPlayer;
		}
		else{
				if(value.countForFirstPlayer>0){
					value.winner = "1"; 
				}else if(value.countForSecondPlayer>0){
					value.winner="2";
				}else if (value.countForThirdPlayer>0){
					value.winner="3";
				}
				else if(value.countForFourthPlayer>0){
					value.winner="4";
				}
		}
		return value;
	}
}
