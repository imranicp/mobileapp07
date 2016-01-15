package logic.main.com.boardgame.core;

//this class is used to make objects for the database SCORE table which holds the name and scores of the players
public class PlayerScore {

    //player name
    private String name;

    //score of the player
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

