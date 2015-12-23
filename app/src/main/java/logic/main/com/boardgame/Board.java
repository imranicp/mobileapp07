package logic.main.com.boardgame;

public class Board {

	private String input;
	private String output;
	private int numberOfPlayers;
	private int movingPlayer;
	private String postionsOfHorizontalBars;
	private String postionsOfVerticalBars;
	private String sequenceOfMoves;
	private String boardConfiguration;
	private String beadConfiguration;
	private boolean playerOne;
	private boolean playerTwo;
	private boolean playerThree;
	private boolean playerFour;
	private String moveOne;
	private String moveTwo;
	private String moveThree;
	private String moveFour;
	private int winner;
	private int playerCount;
	private int lastMovingPlayer;
	/**
	 * @param playerOne
	 * @param playerTwo
	 * @param playerThree
	 * @param playerFour
	 */
	public Board() {
		super();
		this.playerCount = 2;
		this.playerOne = true;
		this.playerTwo = true;
		this.playerThree = true;
		this.playerFour = true;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @return the numberOfPlayers
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @param numberOfPlayers
	 *            the numberOfPlayers to set
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * @return the movingPlayer
	 */
	public int getMovingPlayer() {
		return movingPlayer;
	}

	/**
	 * @param movingPlayer
	 *            the movingPlayer to set
	 */
	public void setMovingPlayer(int movingPlayer) {
		this.movingPlayer = movingPlayer;
	}

	/**
	 * @return the postionsOfHorizontalBars
	 */
	public String getPostionsOfHorizontalBars() {
		return postionsOfHorizontalBars;
	}

	/**
	 * @param postionsOfHorizontalBars
	 *            the postionsOfHorizontalBars to set
	 */
	public void setPostionsOfHorizontalBars(String postionsOfHorizontalBars) {
		this.postionsOfHorizontalBars = postionsOfHorizontalBars;
	}

	/**
	 * @return the postionsOfVerticalBars
	 */
	public String getPostionsOfVerticalBars() {
		return postionsOfVerticalBars;
	}

	/**
	 * @param postionsOfVerticalBars
	 *            the postionsOfVerticalBars to set
	 */
	public void setPostionsOfVerticalBars(String postionsOfVerticalBars) {
		this.postionsOfVerticalBars = postionsOfVerticalBars;
	}

	/**
	 * @return the sequenceOfMoves
	 */
	public String getSequenceOfMoves() {
		return sequenceOfMoves;
	}

	/**
	 * @param sequenceOfMoves
	 *            the sequenceOfMoves to set
	 */
	public void setSequenceOfMoves(String sequenceOfMoves) {
		this.sequenceOfMoves = sequenceOfMoves;
	}

	/**
	 * @return the boardConfiguration
	 */
	public String getBoardConfiguration() {
		return boardConfiguration;
	}

	/**
	 * @param boardConfiguration
	 *            the boardConfiguration to set
	 */
	public void setBoardConfiguration(String boardConfiguration) {
		this.boardConfiguration = boardConfiguration;
	}

	/**
	 * @return the beadConfiguration
	 */
	public String getBeadConfiguration() {
		return beadConfiguration;
	}

	/**
	 * @param beadConfiguration
	 *            the beadConfiguration to set
	 */
	public void setBeadConfiguration(String beadConfiguration) {
		this.beadConfiguration = beadConfiguration;
	}

	/**
	 * @return the playerOne
	 */
	public boolean isPlayerOne() {
		return playerOne;
	}

	/**
	 * @param playerOne
	 *            the playerOne to set
	 */
	public void setPlayerOne(boolean playerOne) {
		this.playerOne = playerOne;
	}

	/**
	 * @return the playerTwo
	 */
	public boolean isPlayerTwo() {
		return playerTwo;
	}

	/**
	 * @param playerTwo
	 *            the playerTwo to set
	 */
	public void setPlayerTwo(boolean playerTwo) {
		this.playerTwo = playerTwo;
	}

	/**
	 * @return the playerThree
	 */
	public boolean isPlayerThree() {
		return playerThree;
	}

	/**
	 * @param playerThree
	 *            the playerThree to set
	 */
	public void setPlayerThree(boolean playerThree) {
		this.playerThree = playerThree;
	}

	/**
	 * @return the playerFour
	 */
	public boolean isPlayerFour() {
		return playerFour;
	}

	/**
	 * @param playerFour
	 *            the playerFour to set
	 */
	public void setPlayerFour(boolean playerFour) {
		this.playerFour = playerFour;
	}

	/**
	 * @return the moveOne
	 */
	public String getMoveOne() {
		return moveOne;
	}

	/**
	 * @param moveOne
	 *            the moveOne to set
	 */
	public void setMoveOne(String moveOne) {
		this.moveOne = moveOne;
	}

	/**
	 * @return the moveTwo
	 */
	public String getMoveTwo() {
		return moveTwo;
	}

	/**
	 * @param moveTwo
	 *            the moveTwo to set
	 */
	public void setMoveTwo(String moveTwo) {
		this.moveTwo = moveTwo;
	}

	/**
	 * @return the moveThree
	 */
	public String getMoveThree() {
		return moveThree;
	}

	/**
	 * @param moveThree
	 *            the moveThree to set
	 */
	public void setMoveThree(String moveThree) {
		this.moveThree = moveThree;
	}

	/**
	 * @return the moveFour
	 */
	public String getMoveFour() {
		return moveFour;
	}

	/**
	 * @param moveFour
	 *            the moveFour to set
	 */
	public void setMoveFour(String moveFour) {
		this.moveFour = moveFour;
	}

	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @param winner
	 *            the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * @return the count
	 */
	public int getPlayerCount() {
		return playerCount;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public int getLastMovingPlayer() {
		return lastMovingPlayer;
	}

	public void setLastMovingPlayer(int lastMovingPlayer) {
		this.lastMovingPlayer = lastMovingPlayer;
	}
}
