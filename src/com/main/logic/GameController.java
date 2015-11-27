package com.main.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameController {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Board board = new Board();
		do {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				board.setInput(br.readLine());
				MoveController mvControl = new MoveController();
				board = mvControl.moveTest(board);

			} catch (Exception e) {
				System.out.println(Strings.retry);
			}
		} while (board.getPlayerCount() > 1);
		if (board.getWinner() != null) {
			System.out.println(Strings.win + board.getWinner());
		}
	}

}
