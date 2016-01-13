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


public class MenuActivity extends Activity {
    boolean continueMusic, exit = false;
    DatabaseManager dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        dataBaseHelper = new DatabaseManager(this);

        final ImageView imgFavorite = (ImageView) findViewById(R.id.musicIcon);
        continueMusic = dataBaseHelper.getMusicValue();

        if (continueMusic) {

            imgFavorite.setImageResource(menu_music_on);
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {

            imgFavorite.setImageResource(menu_music_off);
            MusicManager.pause();
        }

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (continueMusic) {
                    continueMusic = false;
                    dataBaseHelper.setMusicValue(continueMusic);
                    MusicManager.pause();
                    imgFavorite.setImageResource(menu_music_off);
                } else {
                    continueMusic = true;
                    dataBaseHelper.setMusicValue(continueMusic);
                    MusicManager.start(getApplication(), MusicManager.MUSIC_MENU);
                    imgFavorite.setImageResource(menu_music_on);
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

   public void goToAbout(View view){
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
