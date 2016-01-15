package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;

//this activity is used to choose the type of game which the user wants to play
//it can be against the AI,or 2 players, 3 players,4 players
public class GameTypeActivity extends Activity {

    //continueMusic is a boolean variable which is common across all activities to
    //allow seamless playback of game music in case continueMusic is true
    boolean continueMusic;

    //the databaseHelper object which is used to query the DB
    DatabaseManager dataBaseHelper;

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

    //the onCreate method loads the layout file to the contentView
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting the continueMusic value which is always passed as an extra to the intent
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        //initializing the DB manager object for this activity
        dataBaseHelper = new DatabaseManager(this);

        //setting the content view to the game type layout
        setContentView(R.layout.game_type_layout);

    }


    //when a button is selected on the screen the onClick function is triggered
    // to start the activity which is relevant to the type of game selected
    public void onClick(View v) {

        //the player select activity is triggered where the players can enter their names
        Intent playerSelectActivity = new Intent(this, PlayerSelectActivity.class);

        //the AI vs Human Game activity is triggered
        Intent vsComputerActivity = new Intent(this, VsComputerActivity.class);

        //switch case on the id of the button pressed
        switch(v.getId()) {

            //the AI vs Human Game
            case R.id.onePlayer:
                //set number of player to 2
                //activity to be triggered is  VsComputerActivity
                vsComputerActivity.putExtra("numberOfPlayers", 2);

                //putting the value of continueMusic in intent extra
                vsComputerActivity.putExtra("continueMusic", continueMusic);

                //starting the VsComputerActivity
                startActivity(vsComputerActivity);

                //finishing the current activity
                finish();
                break;

            //2 player Human vs Human game
            case R.id.twoPlayer:

                //set number of player to 2
                //activity to be triggered is  playerSelectActivity
                playerSelectActivity.putExtra("numberOfPlayers", 2);

                //putting the value of continueMusic in intent extra
                playerSelectActivity.putExtra("continueMusic", continueMusic);

                //starting the playerSelectActivity
                startActivity(playerSelectActivity);

                //finishing the current activity
                finish();
                break;

            //3 player Human vs Human vs Human game
            case R.id.threePlayer:

                //set number of player to 3
                //activity to be triggered is  playerSelectActivity
                playerSelectActivity.putExtra("numberOfPlayers", 3);

                //putting the value of continueMusic in intent extra
                playerSelectActivity.putExtra("continueMusic", continueMusic);

                //starting the playerSelectActivity
                startActivity(playerSelectActivity);

                //finishing the current activity
                finish();
                break;

            //4 player Human vs Human vs Human vs Human game
            case R.id.fourPlayer:

                //set number of player to 4
                //activity to be triggered is  playerSelectActivity
                playerSelectActivity.putExtra("numberOfPlayers", 4);

                //putting the value of continueMusic in intent extra
                playerSelectActivity.putExtra("continueMusic", continueMusic);

                //starting the playerSelectActivity
                startActivity(playerSelectActivity);

                //finishing the current activity
                finish();

                break;
            default:
        }

    }

    //for the backButton press functionality in the activities,
    //non-gaming related activities must directly finish when the back button is pressed
    @Override
    public void onBackPressed() {

        //finishing the activity
        finish();

    }


}
