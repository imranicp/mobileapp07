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
		System.out.println(Strings.pilot);
		do {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// set the given input into the board object
				board.setInput(br.readLine());
				MoveController mvControl = new MoveController();
				board.setOutput(mvControl.moveTest(board.getInput()));
				System.out.println(Strings.next);
			} catch (Exception e) {
				System.out.println(Strings.retry);
			}
			// the input can be given till there is only one player left
		} while (board.getPlayerCount() > 1);

		if (board.getWinner() != null) {
			System.out.println(Strings.win + board.getWinner());
		}
	}

}
