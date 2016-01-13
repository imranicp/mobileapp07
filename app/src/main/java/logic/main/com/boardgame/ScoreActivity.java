package logic.main.com.boardgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreActivity extends Activity {

    boolean continueMusic;
    DatabaseManager databaseHelper;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onPause() {
        super.onPause();

        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_listview);
        ListView listView = (ListView) findViewById(R.id.score_list);
        list = new ArrayList<HashMap<String, String>>();
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        Log.e("continueMusic", String.valueOf(continueMusic));
        databaseHelper = new DatabaseManager(this);
        List<PlayerScore> playerScores = databaseHelper.retrieveScores();


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



    @Override
    public void onBackPressed() {
        finish();

    }
}
