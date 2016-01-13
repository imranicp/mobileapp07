package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.os.Bundle;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;


public class AboutActivity extends Activity {
    boolean continueMusic;
    DatabaseManager dataBaseHelper;

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
        setContentView(R.layout.about_layout);
        dataBaseHelper = new DatabaseManager(this);
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
