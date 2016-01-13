package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.adapter.HistoryListViewAdapter;
import logic.main.com.boardgame.core.HistoryData;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;


public class HistoryActivity extends Activity {
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
        setContentView(R.layout.history_listview);
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        ListView listView = (ListView) findViewById(R.id.history_listview);
        dataBaseHelper = new DatabaseManager(this);
        list = new ArrayList<HashMap<String, String>>();
        List<HistoryData> historyData = dataBaseHelper.retrieveHistoryData();

        for (HistoryData history : historyData) {
            if (history != null) {
                Log.e("winner", history.getWinner());

                HashMap<String, String> temp = new HashMap<String, String>();

                temp.put("PLAYER1", history.getPlayer1());
                temp.put("PLAYER2", history.getPlayer2());
                if (history.getPlayer3() != null) {
                    temp.put("PLAYER3", history.getPlayer3());
                } else {
                    temp.put("PLAYER3", "");
                }
                if (history.getPlayer4() != null) {
                    temp.put("PLAYER4", history.getPlayer4());
                } else {
                    temp.put("PLAYER4", "");
                }

                temp.put("WINNER", history.getWinner());
                list.add(temp);
            }
        }
        HistoryListViewAdapter adapter = new HistoryListViewAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
