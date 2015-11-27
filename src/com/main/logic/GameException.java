package com.main.logic;

public class GameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param code
	 *            Generates Exception based on the error code received
	 */
	public GameException(int code) {
		if (code == 0) {
			System.out.println(Strings.abort);
		}
		if (code == 1) {
			System.out.println(Strings.invalidInput);
		}

	}

	public GameException(char code, String move, Board board) {

		if (code == Strings.charr) {
			System.out.println(Strings.invalidMove + ":" + move);
			System.out.println(Strings.lastOutput);
			System.out.println(board.getOutput());
		}

	}

	public GameException(int code, String bar, Board board) {
		if (code == 1) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.horBar + bar + Strings.inner);
			System.out.println(Strings.lastOutput);
			System.out.println(board.getOutput());
		}
		if (code == 2) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.horBar + bar + Strings.outer);
			System.out.println(Strings.lastOutput);
			System.out.println(board.getOutput());
		}
		if (code == 3) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.verBar + bar + Strings.inner);
			System.out.println(Strings.lastOutput);
			System.out.println(board.getOutput());
		}
		if (code == 4) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.verBar + bar + Strings.outer);
			System.out.println(Strings.lastOutput);
			System.out.println(board.getOutput());
		}

	}
}
