package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ScoreActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_listview);
        /*TextView player1 = (TextView) findViewById(R.id.player1);
        TextView player2 = (TextView) findViewById(R.id.player2);
        TextView player3 = (TextView) findViewById(R.id.player3);
        TextView player4 = (TextView) findViewById(R.id.player4);
        */
        DatabaseManager databasehelper = new DatabaseManager(this);
        PlayerScore[] data = databasehelper.retrieveScores();
        int i = 1;

/*
        for (PlayerScore playerScore : data) {
            if (playerScore != null) {
                Log.e("name", playerScore.getName());
                Log.e("score", String.valueOf(playerScore.getScore()));
                if (i == 1) {
                    player1.setText(playerScore.getName() + ": " + playerScore.getScore());
                }
                if (i == 2) {
                    player2.setText(playerScore.getName() + ": " + playerScore.getScore());
                }
                if (i == 3) {
                    player3.setText(playerScore.getName() + ": " + playerScore.getScore());
                }
                if (i == 4) {
                    player4.setText(playerScore.getName() + ": " + playerScore.getScore());
                }
                i++;

            }*/
     }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
