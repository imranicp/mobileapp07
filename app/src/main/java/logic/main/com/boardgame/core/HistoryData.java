package logic.main.com.boardgame.core;

//this class is used to create objects for the HISTORY TABLE in the database
public class HistoryData {

    //the player1 name
    private String player1;

    //the player2 name
    private String player2;

    //the player3 name
    private String player3;

    //the player4 name
    private String player4;

    //the winner name
    private String winner;


    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public String getPlayer4() {
        return player4;
    }

    public void setPlayer4(String player4) {
        this.player4 = player4;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }
}
