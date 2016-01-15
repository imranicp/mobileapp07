package logic.main.com.boardgame.core;

// this class holds the function which generates the bead configuration
public class BeadConf {

	/**
	 * Checks whether a hole has appeared under the bead, if so the bead falls
	 * off the board and the bead is removed from bead configuration
	 * 
	 * @param beadConf
	 *            The current configuration of the beads on the board
	 * @param boardConf
	 *            The current configuration of the board
	 * @return The configuration of the beads in the board
	 */
	public String beadConfGenerator(String beadConf, String boardConf) {

        //loop variable goes till the length of the board to generate the new bead config
        int i;

		for (i = 0; i < beadConf.length(); i++) {

            /* check whether the character a hole has appeared under a bead
               if there is a bead at i'th position and in the boardConfig there is a hole 'h'
               then that position is set to 0 which means a bead has fallen*/
            if (beadConf.charAt(i) != Constants.charzero && boardConf.charAt(i) == Constants.charh) {

                //StringBuilder object to modify the string beadConf
                StringBuilder newBeadConfig = new StringBuilder(beadConf);

                //setting char 0 at the specified i'th position
                newBeadConfig.setCharAt(i, Constants.charzero);

                //Converting the beadConfig back to String
                beadConf = newBeadConfig.toString();
			}

		}

        //returning the new beadConfig
        return beadConf;
	}

	/**
	 * This function checks the configuration of the beads and determines
	 * whether a player is currently playing or not, For ex: if the bead
	 * configuration doesn't have 4 then PlayerFour will be set to false
	 * 
	 * @param board
	 */
	public Board checkbeadConf(Board board) {

        /* check whether the beadConfiguration has the char '4' or not,
           if it is not there then player four is no more playing the game*/
        if (!board.getBeadConfiguration().contains(Character.toString(Constants.charfour))) {
            //setting player four to false
            board.setPlayerFour(false);
		}

        /* check whether the beadConfiguration has the char '3' or not,
           if it is not there then player three is no more playing the game*/
        if (!board.getBeadConfiguration().contains(Character.toString(Constants.charthree))) {
            //setting player three to false
            board.setPlayerThree(false);
		}

        /* check whether the beadConfiguration has the char '2' or not,
           if it is not there then player two is no more playing the game*/
        if (!board.getBeadConfiguration().contains(Character.toString(Constants.chartwo))) {
            //setting player two to false
            board.setPlayerTwo(false);

		}

        /* check whether the beadConfiguration has the char '1' or not,
           if it is not there then player one is no more playing the game*/
        if (!board.getBeadConfiguration().contains(Character.toString(Constants.charone))) {
            //setting player two to false
            board.setPlayerOne(false);
		}

        //returning the updated board object
        return board;
	}
}
