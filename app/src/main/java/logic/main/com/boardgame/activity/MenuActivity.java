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


public class MenuActivity extends Activity {
    boolean continueMusic, soundSetting, exit = false;
    DatabaseManager dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        dataBaseHelper = new DatabaseManager(this);

        final ImageView imgMusic = (ImageView) findViewById(R.id.musicIcon);
        continueMusic = dataBaseHelper.getMusicValue();

        if (continueMusic) {

            imgMusic.setImageResource(menu_music_on);
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {

            imgMusic.setImageResource(menu_music_off);
            MusicManager.pause();
        }

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (continueMusic) {
                    continueMusic = false;
                    dataBaseHelper.setMusicValue(continueMusic);
                    MusicManager.pause();
                    imgMusic.setImageResource(menu_music_off);
                } else {
                    continueMusic = true;
                    dataBaseHelper.setMusicValue(continueMusic);
                    MusicManager.start(getApplication(), MusicManager.MUSIC_MENU);
                    imgMusic.setImageResource(menu_music_on);
                }
            }
        });

        final ImageView imgSound = (ImageView) findViewById(R.id.soundIcon);
        soundSetting = dataBaseHelper.getSoundValue();

        if (soundSetting) {
            imgSound.setImageResource(menu_sound_on);
        } else {
            imgSound.setImageResource(menu_sound_off);
        }

        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundSetting) {
                    soundSetting = false;
                    dataBaseHelper.setSoundValue(soundSetting);
                    imgSound.setImageResource(menu_sound_off);
                } else {
                    soundSetting = true;
                    dataBaseHelper.setSoundValue(soundSetting);
                    imgSound.setImageResource(menu_sound_on);
                }
            }
        });
    }


    public void goToPlayerMenu(View view){
        Intent intent = new Intent(this, GameTypeActivity.class);
        intent.putExtra("continueMusic", continueMusic);
        startActivity(intent);
        //finish();

    }

    public void goToScore(View view){
        Intent intent = new Intent(this,ScoreActivity.class);
        intent.putExtra("continueMusic", continueMusic);
        startActivity(intent);
        //finish();
    }

    public void goToHistory(View view){
        Intent intent = new Intent(this,HistoryActivity.class);
        intent.putExtra("continueMusic", continueMusic);
        startActivity(intent);
        //finish();
    }

    public void goToAbout(View view) {
        Intent intent = new Intent(this,AboutActivity.class);
        intent.putExtra("continueMusic", continueMusic);
        startActivity(intent);
        //finish();
    }

    public void goToHelp(View view){
        Intent intent = new Intent(this,HelpActivity.class);
        intent.putExtra("continueMusic", continueMusic);
        startActivity(intent);
        //finish();
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHelper.setMusicValue(continueMusic);

                        continueMusic = false;
                        exit = true;
                        finish();

                    }
                }).setNegativeButton("No", null).show();
    }

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
}
