package com.main.logic;

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
		int i;
		for (i = 0; i < beadConf.length(); i++) {
			if (beadConf.charAt(i) != Strings.charzero && boardConf.charAt(i) == Strings.charh) {
				StringBuilder newBeadConfig = new StringBuilder(beadConf);
				newBeadConfig.setCharAt(i, Strings.charzero);
				beadConf = newBeadConfig.toString();
			}
		}
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

		if (!board.getBeadConfiguration().contains(Character.toString(Strings.charfour))) {
			board.setPlayerFour(false);
		}

		if (!board.getBeadConfiguration().contains(Character.toString(Strings.charthree))) {
			board.setPlayerThree(false);
		}
		if (!board.getBeadConfiguration().contains(Character.toString(Strings.chartwo))) {
			board.setPlayerTwo(false);

		}
		if (!board.getBeadConfiguration().contains(Character.toString(Strings.charone))) {
			board.setPlayerOne(false);

		}
		return board;
	}
}
