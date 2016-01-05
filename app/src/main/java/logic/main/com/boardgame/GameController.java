package logic.main.com.boardgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameController {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Board board = new Board();
		System.out.println(Constants.pilot);
		do {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// set the given input into the board object
				board.setInput(br.readLine());
				MoveController mvControl = new MoveController();
				mvControl.moveTest(board);
				System.out.println(Constants.next);
			} catch (Exception e) {
				System.out.println(Constants.retry);
			}
			// the input can be given till there is only one player left
		} while (board.getPlayerCount() > 1);

		if (board.getWinner() != Integer.parseInt(null)) {
			System.out.println(Constants.win + board.getWinner());
		}
	}

}
