package logic.main.com.boardgame.core;

//this class is used to decide whether there is a winner is decided
public class WinnerDecider {

    //the check win function takes the bead config an finds the number of players
    //sets the player count to 1 if there is a winner
    public Board checkWin(Board board) {

        //the bead config object to check the configuration of the bead
        BeadConf beadConf =new BeadConf();

        /*This function checks the configuration of the beads and determines
        * whether a player is currently playing or not, For ex: if the bead
        * configuration doesn't have 4 then PlayerFour will be set to false*/
        beadConf.checkbeadConf(board);


        //if player 4, player 3, player 2 are eliminated from the game then player 1 is the winner
        if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()) {

            //setting winner as 1
            board.setWinner(Constants.one);

            //setting player count to 1 which means that a winner is decided
            board.setPlayerCount(1);

            //the moving player is set to one who last performed the move
            board.setMovingPlayer(board.getLastMovingPlayer());

		}

        //if player 1, player 3, player 4 are eliminated from the game then player 2 is the winner
        if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerFour()) {

            //setting winner as 2
            board.setWinner(Constants.two);

            //setting player count to 1 which means that a winner is decided
            board.setPlayerCount(1);

            //the moving player is set to one who last performed the move
            board.setMovingPlayer(board.getLastMovingPlayer());
		}

        //if player 1, player 2, player 4 are eliminated from the game then player 3 is the winner
        if (!board.isPlayerOne() && !board.isPlayerTwo() && !board.isPlayerFour()) {

            //setting winner as 3
            board.setWinner(Constants.three);

            //setting player count to 1 which means that a winner is decided
            board.setPlayerCount(1);

            //the moving player is set to one who last performed the move
            board.setMovingPlayer(board.getLastMovingPlayer());
		}

        //if player 1, player 2, player 3 are eliminated from the game then player 4 is the winner
        if (!board.isPlayerOne() && !board.isPlayerThree() && !board.isPlayerTwo()) {

            //setting winner as 4
            board.setWinner(Constants.four);

            //setting player count to 1 which means that a winner is decided
            board.setPlayerCount(1);

            //the moving player is set to one who last performed the move
            board.setMovingPlayer(board.getLastMovingPlayer());
		}

        //if player 1, player 2, player 3, player 4  all are eliminated from the game
        // then player who last performed the move is set as the winner
        if (!board.isPlayerFour() && !board.isPlayerThree() && !board.isPlayerTwo()
				&& !board.isPlayerOne()) {

            //setting the winner to the player who last performed the move
            //in the move history the player number is saved with the move which is successfully performed
            //character at position 3 is the player number
            board.setWinner(Integer.parseInt((board.getMoveOne().substring(3, 4))));

            //setting player count to 1 which means that a winner is decided
            board.setPlayerCount(1);

		}

        //returning the updated board object with the winner set
        return board;
	}

}
