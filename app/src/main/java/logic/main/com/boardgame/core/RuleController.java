package logic.main.com.boardgame.core;


public class RuleController {
	/**
	 * @param move
	 *            the move which is supposed to be performed
	 * @param board
	 *            the function will deal with the history of 4 moves and they
	 *            are stored like a chain of moves where MoveOne will the latest
	 *            performed move and so on. We will also store the player who
	 *            performed the move by adding the player number to move
	 *            performed using the makeMove function
	 * @throws GameException
	 */

	public Board rulesCheck(String move, Board board) throws GameException {
        //game exception object, the object which will hold the generated exception's message
        GameException gameException=new GameException();

        //the bead config object to check the configuration of the bead
        BeadConf beadConf =new BeadConf();

        /*This function checks the configuration of the beads and determines
        * whether a player is currently playing or not, For ex: if the bead
        * configuration doesn't have 4 then PlayerFour will be set to false*/
        board = beadConf.checkbeadConf(board);


        // counter is to check the number of players currently eligible to
        // perform moves
        int counter = 0;

        //if player is set to true means eligible to perform moves
        if (board.isPlayerOne()) {

            //increase the counter value
            counter++;
		}

        //if player is set to true means eligible to perform moves
        if (board.isPlayerTwo()) {

            //increase the counter value
            counter++;
		}

        //if player is set to true means eligible to perform moves
        if (board.isPlayerThree()) {

            //increase the counter value
            counter++;
		}

        //if player is set to true means eligible to perform moves
        if (board.isPlayerFour()) {

            //increase the counter value
            counter++;
		}


		// here we check whether the move is valid or not, the bar which is
		// supposed to be moved must not be moved in the last turns by other
		// players

        //if the number of players playing the game are 4 and the total players left are greater than 2
        if (board.getNumberOfPlayers()== Constants.four && counter > 2) {

            //if the last move (moveOne) is not null
            //and the moving player is not equal to the player who  performed that last move
            //but the bar was moved in the last turn (moveOne) then an exception will be thrown
            if (board.getMoveOne() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveOne().substring(3, 4)))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {

                //exception with message that the bar was moved in previous move
                board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
            }

            //if the second last move (moveTwo) is not null
            //and the moving player is not equal to the player who performed that second last move
            //but the bar was moved in the second last turn (moveTwo) then an exception will be thrown
            else if (board.getMoveTwo() != null
                    && board.getMovingPlayer()!=Integer.parseInt((board.getMoveTwo().substring(3, 4)))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {

                //exception with message that the bar was moved in previous move
                board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
            }

            //if the third last move (MoveThree) is not null
            //and the moving player is not equal to the player who performed that third last move
            //but the bar was moved in the third last turn (MoveThree) then an exception will be thrown
            else if (board.getMoveThree() != null
                    && board.getMovingPlayer()!=Integer.parseInt((board.getMoveThree().substring(3, 4)))
					&& move.substring(0, 2).equals(board.getMoveThree().substring(0, 2))) {
				board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
			}
		}


        // here we check whether the move is valid or not, the bar which is
		// supposed to be moved must not be moved in the last turns by other
		// players

        //if the number of players playing the game are 3 and the total players left are greater than 2
        if (board.getNumberOfPlayers()== Constants.three && counter > 2) {

            //if the last move (moveOne) is not null
            //and the moving player is not equal to the player who  performed that last move
            //but the bar was moved in the last turn (moveOne) then an exception will be thrown
            if (board.getMoveOne() != null
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveOne().substring(3, 4)))
					&& (move.substring(0, 2).equals(board.getMoveOne().substring(0, 2)))) {

                //exception with message that the bar was moved in previous move
                board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
            }
            //if the second last move (moveTwo) is not null
            //and the moving player is not equal to the player who performed that second last move
            //but the bar was moved in the second last turn (moveTwo) then an exception will be thrown
            else if (board.getMoveTwo() != null
                    && board.getMovingPlayer()!=Integer.parseInt((board.getMoveTwo().substring(3, 4)))
					&& move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2))) {

                //exception with message that the bar was moved in previous move
                board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
			}
		}

		// when the number of players are just two then we have to check that a
		// bar must not be moved more then two times by the same player in
		// consecutive turns. We make use of the stored player number in the
		// history of moves ie the 4th character in the move history.

		if (counter == 2) {

			// this check must be done again because even if the current number
			// of players are two, but still they should not be able to move the
			// bars which were moved by players who got eliminated in the recent
			// moves
            //if the last move (moveOne) is not null
            //and the moving player is not equal to the player who  performed that last move
            //but the bar was moved in the last turn (moveOne) then an exception will be thrown
            if (board.getMoveOne() != null
                    && (move.substring(0,2).equals(board.getMoveOne().substring(0,2)))
					&& board.getMovingPlayer()!=Integer.parseInt((board.getMoveOne().substring(3, 4)))){

                //exception with message that the bar was moved in previous move
                board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
			}

			// this is the check for two consecutive turns on the same bar
            //if the second last move (MoveTwo) is not null
            //if the fourth last move (MoveFour) is not null
            //and the moving player is equal to the player who  performed those moves
            //then an exception will be thrown
            if (board.getMoveTwo() != null
                    && board.getMoveFour() != null
					&& (board.getMovingPlayer()==Integer.parseInt((board.getMoveTwo().substring(3, 4))))
					&& (board.getMovingPlayer()==Integer.parseInt((board.getMoveFour().substring(3, 4))))
					&& (move.substring(0, 2).equals(board.getMoveTwo().substring(0, 2)) && move
							.substring(0, 2).equals(board.getMoveFour().substring(0, 2)))) {

                //exception with message that the bar was moved in previous move
                board.setExceptionMessage(gameException.exception(Constants.charr, move, board));
				throw new GameException();
			}



		}

        //setting the player count in the board object which means the player left in the game
        board.setPlayerCount(counter);

        //returning the board object
        return board;

	}
}
