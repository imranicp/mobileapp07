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
				System.out.println(board.getOutput());

			} catch (Exception e) {
				System.out.println(Strings.retry);
			}
		} while (board.getCount() > 1);
		System.out.println("The winner is: Player " + board.getWinner());
	}

}
