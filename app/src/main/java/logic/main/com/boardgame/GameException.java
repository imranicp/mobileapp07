package logic.main.com.boardgame;

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
			System.out.println(Constants.abort);
		}
		if (code == 1) {
			System.out.println(Constants.invalidInput);
		}

	}

	public GameException(char code, String move, Board board) {

		if (code == Constants.charr) {
			System.out.println(Constants.invalidMove + ":" + move + Constants.bar);
			System.out.println(Constants.lastOutput);
			System.out.println(board.getOutput());
		}

	}

	public GameException(int code, String bar, Board board) {
		if (code == 1) {
			System.out.println(Constants.invalidMove);
			System.out.println(Constants.horBar + bar + Constants.inner);
			System.out.println(Constants.lastOutput);
			System.out.println(board.getOutput());
		}
		if (code == 2) {
			System.out.println(Constants.invalidMove);
			System.out.println(Constants.horBar + bar + Constants.outer);
			System.out.println(Constants.lastOutput);
			System.out.println(board.getOutput());
		}
		if (code == 3) {
			System.out.println(Constants.invalidMove);
			System.out.println(Constants.verBar + bar + Constants.inner);
			System.out.println(Constants.lastOutput);
			System.out.println(board.getOutput());
		}
		if (code == 4) {
			System.out.println(Constants.invalidMove);
			System.out.println(Constants.verBar + bar + Constants.outer);
			System.out.println(Constants.lastOutput);
			System.out.println(board.getOutput());
		}

	}
}
