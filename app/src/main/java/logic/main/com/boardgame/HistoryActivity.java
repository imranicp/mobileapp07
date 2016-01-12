package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HistoryActivity extends Activity {
    private ArrayList<HashMap<String, String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_listview);
        ListView listView = (ListView) findViewById(R.id.history_listview);
        DatabaseManager databaseHelper = new DatabaseManager(this);
        list = new ArrayList<HashMap<String, String>>();
        List<HistoryData> historyData = databaseHelper.retrieveHistoryData();

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
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
