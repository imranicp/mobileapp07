package com.main.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.main.rishabh.MoveController;

public class GameController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board();
		do {
			try {
				// Input from user
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				board.setInput(br.readLine());
				MoveController mvControl = new MoveController();
				String result = mvControl.moveTest(board.getInput());
			} catch (Exception e) {

			}
		} while (board.getCount() > 1);

	}
}
