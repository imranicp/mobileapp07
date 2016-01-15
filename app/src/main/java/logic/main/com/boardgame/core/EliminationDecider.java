package logic.main.com.boardgame.core;

public class EliminationDecider {
	/**
	 * This function recursively checks whether the current player who has the
	 * turn is eliminated or not, if the player is eliminated then the next
     * eligible player will get the turn if no player is available then it checks for win.
     *
	 * @param board
	 */


	public Board checkElimination(Board board) {

        //winner decider object in case all players are eliminated from the game
        WinnerDecider winnerDecider = new WinnerDecider();

        //if all 4 players are no more in the game
        if (!board.isPlayerOne()&& !board.isPlayerTwo() && !board.isPlayerThree() && !board.isPlayerFour())
		{
			winnerDecider.checkWin(board);
		}

        //if the player count is 1 then we have a winner in the game
        //if the number of players is 2 and player count is not 1
        if (board.getNumberOfPlayers()== Constants.two&&board.getPlayerCount()!=1) {

            //if the supposed moving player is 1 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.one && !board.isPlayerOne()) {

                //setting moving player value to 2
                board.setMovingPlayer(Constants.two);

                //checking for other eliminations
                checkElimination(board);
			}

            //if the supposed moving player is 2 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.two && !board.isPlayerTwo()) {

                //setting moving player value to 1
                board.setMovingPlayer(Constants.one);

                //checking for other eliminations
                checkElimination(board);
			}

		}

        //if the player count is 1 then we have a winner in the game
        //if the number of players is 3 and player count is not 1
        if (board.getNumberOfPlayers()== Constants.three&&board.getPlayerCount()!=1) {

            //if the supposed moving player is 1 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.one && !board.isPlayerOne()) {

                //setting moving player value to 2
                board.setMovingPlayer(Constants.two);

                //checking for other eliminations
                checkElimination(board);
			}
            //if the supposed moving player is 2 but he is eliminated from the game
            else if (board.getMovingPlayer() == Constants.two && !board.isPlayerTwo()) {

                //setting moving player value to 3
                board.setMovingPlayer(Constants.three);

                //checking for other eliminations
                checkElimination(board);
			}
            //if the supposed moving player is 3 but he is eliminated from the game
            else if (board.getMovingPlayer() == Constants.three && !board.isPlayerThree()) {

                //setting moving player value to 1
                board.setMovingPlayer(Constants.one);

                //checking for other eliminations
                checkElimination(board);
			}

		}

        //if the player count is 1 then we have a winner in the game
        //if the number of players is 4 and player count is not 1
        if (board.getNumberOfPlayers()== Constants.four&&board.getPlayerCount()!=1) {

            //if the supposed moving player is 1 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.one && !board.isPlayerOne()) {

                //setting moving player value to 2
                board.setMovingPlayer(Constants.two);

                //checking for other eliminations
                checkElimination(board);
			}

            //if the supposed moving player is 2 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.two && !board.isPlayerTwo()) {

                //setting moving player value to 3
                board.setMovingPlayer(Constants.three);

                //checking for other eliminations
                checkElimination(board);
			}

            //if the supposed moving player is 3 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.three && !board.isPlayerThree()) {

                //setting moving player value to 4
                board.setMovingPlayer(Constants.four);

                //checking for other eliminations
                checkElimination(board);
			}
            //if the supposed moving player is 4 but he is eliminated from the game
            if (board.getMovingPlayer() == Constants.four && !board.isPlayerFour()) {

                //setting moving player value to 1
                board.setMovingPlayer(Constants.one);

                //checking for other eliminations
                checkElimination(board);
			}
		}

        //returning the updated board object
        return board;
	}

}
