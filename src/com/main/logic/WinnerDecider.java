package com.main.logic;

public class WinnerDecider {
	public Board checkWin(Board board) {
		BeadConf beadConf =new BeadConf();
		beadConf.checkbeadConf(board);

		if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()) {
			board.setWinner(Constants.one);
			board.setPlayerCount(1);
			board.setMovingPlayer(board.getLastMovingPlayer());

		}
		if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerFour()) {
			board.setWinner(Constants.two);
			board.setPlayerCount(1);
			board.setMovingPlayer(board.getLastMovingPlayer());
		}
		if (!board.isPlayerOne() && !board.isPlayerTwo() && !board.isPlayerFour()) {
			board.setWinner(Constants.three);
			board.setPlayerCount(1);
			board.setMovingPlayer(board.getLastMovingPlayer());
		}
		if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerTwo()) {
			board.setWinner(Constants.four);
			board.setPlayerCount(1);
			board.setMovingPlayer(board.getLastMovingPlayer());
		}
		if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()
				&& !board.isPlayerOne()) {
			board.setWinner(Integer.parseInt((board.getMoveOne().substring(3, 4))));
			board.setPlayerCount(1);

		}
		return board;
	}

}
