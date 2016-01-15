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


//when the game is finished then this activity shows the player name who has won the game
// and also the scores of the players involved in the finished game
public class GameOverActivity extends Activity {

    //key for player 1 used for getting value from the intent extra
    private static final String KEY_PLAYER1 = "PLAYER1";

    //key for player 2 used for getting value from the intent extra
    private static final String KEY_PLAYER2 = "PLAYER2";

    //key for player 3 used for getting value from the intent extra
    private static final String KEY_PLAYER3 = "PLAYER3";

    //key for player 4 used for getting value from the intent extra
    private static final String KEY_PLAYER4 = "PLAYER4";

    //key for winner of the game used for getting value from the intent extra
    private static final String KEY_WINNER = "WINNER";

    //the String to which the values from the intent extra will be set
    String player1, player2, player3, player4;

    //the aiGame boolean variable
    boolean aiGame;

    //the number of players in the game, value comes from the intent extra
    int numberOfPlayers;

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

    //onCreate function initializes the variables which are relevant to this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loading the layout for game over scenario
        setContentView(R.layout.game_over_layout);

        //initializing the DB manager object for this activity
        dataBaseHelper = new DatabaseManager(this);

        //getting the winner value which is passed as an extra to the intent
        String winner = getIntent().getExtras().getString(KEY_WINNER);

        //determine whether the game which has ended was an AI Game or not
        aiGame = getIntent().getExtras().getBoolean("aiGame");

        //getting the player 1 name value which is passed as an extra to the intent
        player1 = getIntent().getExtras().getString(KEY_PLAYER1);

        //getting the player 2 name value which is passed as an extra to the intent
        player2 = getIntent().getExtras().getString(KEY_PLAYER2);

        //getting the player 3 name value which is passed as an extra to the intent
        player3 = getIntent().getExtras().getString(KEY_PLAYER3);

        //getting the player 4 name value which is passed as an extra to the intent
        player4 = getIntent().getExtras().getString(KEY_PLAYER4);

        //getting the continueMusic value which is always passed as an extra to the intent
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        //getting the resource which is used to show the winner name
        TextView winnerText = (TextView) findViewById(R.id.winner);

        //setting the winner name in the TextView
        winnerText.setText(winner + " WINS!");

        //getting the ListView resource which shows the scores of the players
        ListView listView = (ListView) findViewById(R.id.score_list);

        //initializing the list as an arraylist of hashMaps
        list = new ArrayList<HashMap<String, String>>();

        //creating a list of names of players who played the game
        List<String> playerList = new ArrayList<String>();

        //adding player 1 name
        playerList.add(player1);

        //adding player 2 name
        playerList.add(player2);

        //adding player 3 name
        playerList.add(player3);

        //adding player 4 name
        playerList.add(player4);

        //Counting of players
        //iterating on the list
        for (String playerScore : playerList) {
            if (playerScore != null) {
                //incrementing the number of players
                numberOfPlayers++;
            }
        }

        //retrieving the scores from the database
        //notice the list takes values in the form of PlayerScore pojo objects
        List<PlayerScore> playerScores = dataBaseHelper.retrieveLatestScores(playerList);

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

    //the goToMain button just finishes the activity
    public void goToMain(View view) {
        //calling activity finish
        finish();
    }

    //when the button play again is pressed then the
    // Game activity is called back with the same parameters as before
    public void playAgain(View view) {
        //the intent to be sent
        Intent intent;

        //if it is aiGame send VsComputer Activity
        if (aiGame) {

            //intent set to VsComputer Activity
            intent = new Intent(this, VsComputerActivity.class);
        } else {

            //creating  game activity intent
            intent = new Intent(this, GameActivity.class);
        }
        //adding number of player as intent extra
        intent.putExtra("numberOfPlayers", numberOfPlayers);

        //adding number of player 1 name as intent extra
        intent.putExtra("player1", player1);

        //adding number of player 2 name as intent extra
        intent.putExtra("player2", player2);

        //adding continueMusic intent extra
        intent.putExtra("continueMusic", continueMusic);

        //if player three value is not null means there were 3 or 4 players in the game
        //their names must also be sent
        //player 3 name not null
        if (player3 != null) {

            //adding number of player 3 name as intent extra
            intent.putExtra("player3", player3);

        }

        //if player three value is not null means there were 4 players in the game
        //their names must also be sent
        //player 4 name not null
        if (player4 != null) {

            //adding number of player 4 name as intent extra
            intent.putExtra("player4", player4);
        }

        //starting the activity
        startActivity(intent);

        //finishing this activity
        finish();
    }


    //for the backButton press functionality in the activities,
    //non-gaming related activities must directly finish when the back button is pressed
    @Override
    public void onBackPressed() {
        //finishing this activity
        finish();

    }
}
