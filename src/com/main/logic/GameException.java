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

		if (code == 2) {
			System.out.println(Strings.invalidMove);
		}

	}

}
