package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;

import static logic.main.com.boardgame.R.drawable.menu_music_off;
import static logic.main.com.boardgame.R.drawable.menu_music_on;
import static logic.main.com.boardgame.R.drawable.menu_sound_off;
import static logic.main.com.boardgame.R.drawable.menu_sound_on;

//This activity is the launcher activity which comes up when the game starts
public class MenuActivity extends Activity {

    //continueMusic is a boolean variable which is common across all activities to
    //allow seamless playback of game music in case continueMusic is true

    //soundSetting is a boolean variable which is for the main game activity
    // which is used for, setting sound on or off

    boolean continueMusic, soundSetting;

    //the databaseHelper object which is used to query the DB
    DatabaseManager dataBaseHelper;

    //onCreate instance of the launcher activity loads the main menu of the game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //the main menu layout is loaded
        setContentView(R.layout.menu_layout);

        //initializing the DB manager object for this activity
        dataBaseHelper = new DatabaseManager(this);

        //getting the resource id of the music Icon on the main menu
        //clicking this icon user can toggle game music playback
        final ImageView imgMusic = (ImageView) findViewById(R.id.musicIcon);

        //getting the setting for continueMusic which is stored in the database
        continueMusic = dataBaseHelper.getMusicValue();

        //if continue music is true menu music is turned on
        //the image of the music button shows on
        if (continueMusic) {

            //setting on image on the button
            imgMusic.setImageResource(menu_music_on);

            //starting the music
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {

            //setting off image on the button
            imgMusic.setImageResource(menu_music_off);

            //stopping the music
            MusicManager.pause();
        }
        //toggling of the music is done onthis clickListener for the image
        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if continueMusic is true then it must become false and music should stop
                if (continueMusic) {

                    //continueMusic to false
                    continueMusic = false;

                    //setting the new preference value in database
                    dataBaseHelper.setMusicValue(continueMusic);

                    //pausing the music
                    MusicManager.pause();

                    //setting off image on the button
                    imgMusic.setImageResource(menu_music_off);
                }
                //if continueMusic is false then it must become true and music should start
                else {

                    //continueMusic to true
                    continueMusic = true;

                    //setting the new preference value in database
                    dataBaseHelper.setMusicValue(continueMusic);

                    //starting the music
                    MusicManager.start(getApplication(), MusicManager.MUSIC_MENU);

                    //setting on image on the button
                    imgMusic.setImageResource(menu_music_on);
                }
            }
        });

        //getting the resource id of the sound Icon on the main menu
        //clicking this icon user can toggle game sounds
        final ImageView imgSound = (ImageView) findViewById(R.id.soundIcon);

        //getting the setting for sound which is stored in the database
        soundSetting = dataBaseHelper.getSoundValue();

        //if soundSetting is true then
        //the image of the music button shows on
        if (soundSetting) {
            imgSound.setImageResource(menu_sound_on);
        }
        //else soundSetting is false then
        //the image of the music button shows off
        else {
            imgSound.setImageResource(menu_sound_off);
        }

        //toggling of the music is done onthis clickListener for the sound image
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if soundSetting is true then it must become false and soundSetting should be updated in DB
                if (soundSetting) {
                    //soundSetting to false
                    soundSetting = false;

                    //setting the new preference value in database
                    dataBaseHelper.setSoundValue(soundSetting);

                    //setting off image on the button
                    imgSound.setImageResource(menu_sound_off);
                }
                //if soundSetting is true then it must become true and soundSetting should be updated in DB
                else {
                    //soundSetting to true
                    soundSetting = true;

                    //setting the new preference value in database
                    dataBaseHelper.setSoundValue(soundSetting);

                    //setting on image on the button
                    imgSound.setImageResource(menu_sound_on);
                }
            }
        });
    }

    //when Start button is pressed then the Game Type activity must be shown
    public void goToPlayerMenu(View view){

        //creating an intent for Game Type activity
        Intent intent = new Intent(this, GameTypeActivity.class);

        //adding continueMusic value as extra
        intent.putExtra("continueMusic", continueMusic);

        //starting the intent
        startActivity(intent);

    }

    //when Score button is pressed then the Score activity must be shown
    public void goToScore(View view){

        //creating an intent for Score activity
        Intent intent = new Intent(this,ScoreActivity.class);

        //adding continueMusic value as extra
        intent.putExtra("continueMusic", continueMusic);

        //starting the intent
        startActivity(intent);

    }

    //when History button is pressed then the History activity must be shown
    public void goToHistory(View view){

        //creating an intent for History activity
        Intent intent = new Intent(this,HistoryActivity.class);

        //adding continueMusic value as extra
        intent.putExtra("continueMusic", continueMusic);

        //starting the intent
        startActivity(intent);

    }

    //when about button is pressed then the about activity must be shown
    public void goToAbout(View view) {

        //creating an intent for about activity
        Intent intent = new Intent(this,AboutActivity.class);

        //adding continueMusic value as extra
        intent.putExtra("continueMusic", continueMusic);

        //starting the intent
        startActivity(intent);

    }

    //when help button is pressed then the help activity must be shown
    public void goToHelp(View view){

        //creating an intent for help activity
        Intent intent = new Intent(this,HelpActivity.class);

        //adding continueMusic value as extra
        intent.putExtra("continueMusic", continueMusic);

        //starting the intent
        startActivity(intent);

    }

    //this is the main activity of the game and thus the user must be alerted that the game will be exited if chooses to do so
    @Override
    public void onBackPressed() {

        //building an alert dialog to confirm that the user wants to exit
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                //setting message in the alert dialog
                .setMessage("Are you sure you want to exit?")
                        //adding the positive button function
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //if the user chooses to exit then we must save the music preference in the database
                        //and then set it to false, as the music must be stopped when the game is exited

                        //saving the music preference
                        dataBaseHelper.setMusicValue(continueMusic);

                        //setting continue music to false
                        continueMusic = false;

                        //finishing the main activity
                        finish();

                    }
                })
                        //if cancelled then nothing happens to the state of the game activity
                .setNegativeButton("No", null).show();
    }


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
}
