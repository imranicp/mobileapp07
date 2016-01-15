package logic.main.com.boardgame.core;

/**
 * Created by Rishabh on 15-01-2016.
 */
public class DummyBoard {

    //the input for the game has numberOfPlayers,MovingPlayer,board configuration,bead configuration,and the move to be performed
    private String input;

    //the output for the game has numberOfPlayers,MovingPlayer,board configuration,bead configuration,
    private String output;

    //the numberOfPlayers
    private int numberOfPlayers;

    //the player who is making the move
    private int movingPlayer;

    //the positions of horizontal bars
    private String postionsOfHorizontalBars;

    //the positions of vertical bars
    private String postionsOfVerticalBars;

    //the sequence of moves to be performed
    private String sequenceOfMoves;

    //the board configuration
    private String boardConfiguration;

    //the bead configuration
    private String beadConfiguration;

    //this boolean value signifies whether the player one is playing the game or not true(yes) false(no)
    private boolean playerOne;

    //this boolean value signifies whether the player two is playing the game or not true(yes) false(no)
    private boolean playerTwo;

    //this boolean value signifies whether the player three is playing the game or not true(yes) false(no)
    private boolean playerThree;

    //this boolean value signifies whether the player four is playing the game or not true(yes) false(no)
    private boolean playerFour;


    //the history of moves which were previously performed move +player number

    //the last move
    private String moveOne;

    //the second last move
    private String moveTwo;

    //the third last move
    private String moveThree;

    //the fourth last move
    private String moveFour;

    //player number of the winner of the game
    private int winner;

    //the total number of players currently playing the game
    private int playerCount;

    //the player number who last performed the move
    private int lastMovingPlayer;

    //the exception message which has to be displayed
    private String exceptionMessage;

    public DummyBoard(Board another) {

        this.input = another.getInput();
        this.output = another.getOutput();


        this.numberOfPlayers = another.getNumberOfPlayers();

        this.movingPlayer = another.getMovingPlayer();


        this.postionsOfHorizontalBars = another.getPostionsOfHorizontalBars();


        this.postionsOfVerticalBars = another.getPostionsOfVerticalBars();


        this.sequenceOfMoves = another.getSequenceOfMoves();


        this.boardConfiguration = another.getBoardConfiguration();

        this.beadConfiguration = another.getBeadConfiguration();

        this.playerOne = another.isPlayerOne();


        this.playerTwo = another.isPlayerTwo();

        this.playerThree = another.isPlayerThree();

        this.playerFour = another.isPlayerFour();

        this.moveOne = another.getMoveOne();


        this.moveTwo = another.getMoveTwo();


        this.moveThree = another.getMoveThree();


        this.moveFour = another.getMoveFour();


        this.winner = another.getWinner();


        this.playerCount = another.getPlayerCount();


        this.lastMovingPlayer = another.getLastMovingPlayer();

        //the exception message which has to be displayed
        this.exceptionMessage = another.getExceptionMessage();

    }

    public boolean isPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        this.playerOne = playerOne;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getMovingPlayer() {
        return movingPlayer;
    }

    public void setMovingPlayer(int movingPlayer) {
        this.movingPlayer = movingPlayer;
    }

    public String getPostionsOfHorizontalBars() {
        return postionsOfHorizontalBars;
    }

    public void setPostionsOfHorizontalBars(String postionsOfHorizontalBars) {
        this.postionsOfHorizontalBars = postionsOfHorizontalBars;
    }

    public String getPostionsOfVerticalBars() {
        return postionsOfVerticalBars;
    }

    public void setPostionsOfVerticalBars(String postionsOfVerticalBars) {
        this.postionsOfVerticalBars = postionsOfVerticalBars;
    }

    public String getSequenceOfMoves() {
        return sequenceOfMoves;
    }

    public void setSequenceOfMoves(String sequenceOfMoves) {
        this.sequenceOfMoves = sequenceOfMoves;
    }

    public String getBoardConfiguration() {
        return boardConfiguration;
    }

    public void setBoardConfiguration(String boardConfiguration) {
        this.boardConfiguration = boardConfiguration;
    }

    public String getBeadConfiguration() {
        return beadConfiguration;
    }

    public void setBeadConfiguration(String beadConfiguration) {
        this.beadConfiguration = beadConfiguration;
    }

    public boolean isPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(boolean playerTwo) {
        this.playerTwo = playerTwo;
    }

    public boolean isPlayerThree() {
        return playerThree;
    }

    public void setPlayerThree(boolean playerThree) {
        this.playerThree = playerThree;
    }

    public boolean isPlayerFour() {
        return playerFour;
    }

    public void setPlayerFour(boolean playerFour) {
        this.playerFour = playerFour;
    }

    public String getMoveOne() {
        return moveOne;
    }

    public void setMoveOne(String moveOne) {
        this.moveOne = moveOne;
    }

    public String getMoveTwo() {
        return moveTwo;
    }

    public void setMoveTwo(String moveTwo) {
        this.moveTwo = moveTwo;
    }

    public String getMoveThree() {
        return moveThree;
    }

    public void setMoveThree(String moveThree) {
        this.moveThree = moveThree;
    }

    public String getMoveFour() {
        return moveFour;
    }

    public void setMoveFour(String moveFour) {
        this.moveFour = moveFour;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getLastMovingPlayer() {
        return lastMovingPlayer;
    }

    public void setLastMovingPlayer(int lastMovingPlayer) {
        this.lastMovingPlayer = lastMovingPlayer;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
