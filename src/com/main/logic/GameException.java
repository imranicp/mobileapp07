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

	public GameException(int code, String bar) {
		if (code == 1) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.horBar + bar + Strings.inner);
		}
		if (code == 2) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.horBar + bar + Strings.outer);
		}
		if (code == 3) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.verBar + bar + Strings.inner);
		}
		if (code == 4) {
			System.out.println(Strings.invalidMove);
			System.out.println(Strings.verBar + bar + Strings.outer);
		}

	}
}
