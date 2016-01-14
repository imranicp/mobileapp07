package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.adapter.ScoreListViewAdapter;
import logic.main.com.boardgame.core.PlayerScore;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;

public class GameOverActivity extends Activity {
    private static final String KEY_PLAYER1 = "PLAYER1";
    private static final String KEY_PLAYER2 = "PLAYER2";
    private static final String KEY_PLAYER3 = "PLAYER3";
    private static final String KEY_PLAYER4 = "PLAYER4";
    private static final String KEY_WINNER = "WINNER";
    String player1, player2, player3, player4;
    int numberOfPlayers;
    boolean continueMusic;
    DatabaseManager dataBaseHelper;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onPause() {
        super.onPause();

        continueMusic = false;
        MusicManager.pause();


    }

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = dataBaseHelper.getMusicValue();

        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_layout);
        dataBaseHelper = new DatabaseManager(this);
        String winner = getIntent().getExtras().getString(KEY_WINNER);
        player1 = getIntent().getExtras().getString(KEY_PLAYER1);
        player2 = getIntent().getExtras().getString(KEY_PLAYER2);
        player3 = getIntent().getExtras().getString(KEY_PLAYER3);
        player4 = getIntent().getExtras().getString(KEY_PLAYER4);
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        TextView winnerText = (TextView) findViewById(R.id.winner);
        winnerText.setText(winner + " WINS!");

        ListView listView = (ListView) findViewById(R.id.score_list);
        list = new ArrayList<HashMap<String, String>>();

        DatabaseManager databaseHelper = new DatabaseManager(this);
        List<String> playerList = new ArrayList<String>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        //Counting of players

        for (String playerScore : playerList) {
            if (playerScore != null) {
                numberOfPlayers++;
            }
        }
        
        List<PlayerScore> playerScores = databaseHelper.retrieveLatestScores(playerList);

        for (PlayerScore playerScore : playerScores) {

            if (playerScore != null) {

                Log.e("name", playerScore.getName());
                Log.e("score", String.valueOf(playerScore.getScore()));
                HashMap<String, String> temp = new HashMap<String, String>();

                temp.put("PLAYER_NAME", playerScore.getName());
                temp.put("SCORE", String.valueOf(playerScore.getScore()));

                list.add(temp);
            }
        }
        ScoreListViewAdapter adapter = new ScoreListViewAdapter(this, list);
        listView.setAdapter(adapter);

    }

    public void goToMain(View view) {
        /*Intent intent = new Intent(this, MenuActivity.class);

        startActivity(intent);*/
        finish();
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("numberOfPlayers", numberOfPlayers);
        intent.putExtra("player1", player1);
        intent.putExtra("player2", player2);
        intent.putExtra("continueMusic", continueMusic);
        if (player3 != null) {
            intent.putExtra("player3", player3);
        }

        if (player4 != null) {
            intent.putExtra("player4", player4);
        }

        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {

        finish();

    }
}