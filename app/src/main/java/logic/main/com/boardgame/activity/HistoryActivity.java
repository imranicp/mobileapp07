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

//It is the activity which shows the history of games played between the players
public class HistoryActivity extends Activity {

    //continueMusic is a boolean variable which is common across all activities to
    //allow seamless playback of game music in case continueMusic is true
    boolean continueMusic;

    //the databaseHelper object which is used to query the DB
    DatabaseManager dataBaseHelper;

    //the list of values which is in the form of hashMap
    // must be displayed in the listview on the screen
    private ArrayList<HashMap<String, String>> list;

    //the onPause functionality of every activity implements that
    //when the home button is pressed or when the activity goes in background then
    //the music playback must be paused.
    @Override
    protected void onPause() {
        super.onPause();

        //setting continueMusic to false
        continueMusic = false;

        //calling music manager to pause the music
        MusicManager.pause();


    }

    //the onResume functionality of every activity implements that
    //when the activity resumes or when the activity comes in foreground then
    //the music playback must be resumed if the value for continueMusic is true,
    // or else it should remain false.
    @Override
    protected void onResume() {
        super.onResume();

        //the value for continueMusic is retrieved from the database
        //as preferences are saved in the database
        continueMusic = dataBaseHelper.getMusicValue();

        //checking if continue music is true or not
        //if continue music is true then the music must continue playing
        if (continueMusic) {

            //calling music manager to start the music
            MusicManager.start(this, MusicManager.MUSIC_MENU);

        } else {

            //calling music manager to pause the music
            MusicManager.pause();

        }
    }

    //the onCreate function loads the layout
    // and initializes the objects critical to the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting the content view to the history view layout
        setContentView(R.layout.history_listview);

        //getting the continueMusic value which is always passed as an extra to the intent
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        //getting the historyListview from the resources
        ListView listView = (ListView) findViewById(R.id.history_listview);

        //initializing the DB manager object for this activity
        dataBaseHelper = new DatabaseManager(this);

        //initializing the arrayList
        list = new ArrayList<HashMap<String, String>>();

        //retrieving the game history from the database
        //notice the list takes values in the form of HistoryData pojo objects
        List<HistoryData> historyData = dataBaseHelper.retrieveHistoryData();

        //iterating on the list of HistoryData to set the value in the hashMap
        //which is then added to the list
        for (HistoryData history : historyData) {

            //when the HistoryData object is not null
            if (history != null) {
                Log.e("winner", history.getWinner());

                //creating a temporary hashMap
                HashMap<String, String> temp = new HashMap<String, String>();

                //adding player 1 name to the HashMap
                temp.put("PLAYER1", history.getPlayer1());

                //adding player 2 name to the HashMap
                temp.put("PLAYER2", history.getPlayer2());

                //adding player 3 name to the HashMap
                //if player 3 name is not null
                //means the game was played by 3 or more players
                if (history.getPlayer3() != null) {

                    //adding player 3 name to the HashMap
                    temp.put("PLAYER3", history.getPlayer3());

                }
                //if player 3 name is null
                //means the game was played by 2 players
                else {
                    //adding empty player 3 name to the HashMap
                    //this helps in hiding the player 3 name tile in the listview
                    temp.put("PLAYER3", "");
                }

                //adding player 4 name to the HashMap
                //if player 4 name is not null
                //means the game was played by 4  players
                if (history.getPlayer4() != null) {

                    //adding player 4 name to the HashMap
                    temp.put("PLAYER4", history.getPlayer4());

                }
                //if player 4 name is null
                //means the game was played by 3 players or less
                else {
                    //adding empty player 4 name to the HashMap
                    //this helps in hiding the player 4 name tile in the listview
                    temp.put("PLAYER4", "");
                }

                //adding the winner name to the HashMap
                temp.put("WINNER", history.getWinner());

                //adding the hashMap to the list
                list.add(temp);
            }
        }

        //initializing the history view adaptor class which will handle
        // the display of the history data in the list view
        //listViews will have a set adaptor which will
        // set the view values while the list is displayed on the screen
        HistoryListViewAdapter adapter = new HistoryListViewAdapter(this, list);

        //setting the created adaptor to the listView
        listView.setAdapter(adapter);
    }

    //for the backButton press functionality in the activities,
    //non-gaming related activities must directly finish when the back button is pressed
    @Override
    public void onBackPressed() {

        //finishing the activity
        finish();

    }
}
