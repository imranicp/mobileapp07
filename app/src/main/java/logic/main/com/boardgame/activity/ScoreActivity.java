package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.adapter.ScoreListViewAdapter;
import logic.main.com.boardgame.core.PlayerScore;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;

//It is the activity which shows the scores of the players of the game
public class ScoreActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting the content view to the score list layout
        setContentView(R.layout.score_listview);

        //getting the scoreListView from the resources
        ListView listView = (ListView) findViewById(R.id.score_list);

        //initializing the arrayList
        list = new ArrayList<HashMap<String, String>>();

        //getting the continueMusic value which is always passed as an extra to the intent
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        //initializing the DB manager object for this activity
        dataBaseHelper = new DatabaseManager(this);

        //retrieving the scores from the database
        //notice the list takes values in the form of PlayerScore pojo objects
        List<PlayerScore> playerScores = dataBaseHelper.retrieveScores();

        //iterating on the list of playerScores to set the value in the hashMap
        //which is then added to the list
        for (PlayerScore playerScore : playerScores) {

            //when the playerScore object is not null
            if (playerScore != null) {

                Log.e("name", playerScore.getName());
                Log.e("score", String.valueOf(playerScore.getScore()));

                //creating a temporary hashMap
                HashMap<String, String> temp = new HashMap<String, String>();

                //adding player name to the HashMap
                temp.put("PLAYER_NAME", playerScore.getName());

                //adding player score to the hashmap
                temp.put("SCORE", String.valueOf(playerScore.getScore()));

                //adding the hashMap to the list
                list.add(temp);
            }
        }

        //initializing the score view adaptor class which will handle
        // the display of the scores in the list view
        //listViews will have a set adaptor which will
        // set the view values while the list is displayed on the screen
        ScoreListViewAdapter adapter = new ScoreListViewAdapter(this, list);

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
