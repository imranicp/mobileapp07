package logic.main.com.boardgame;

import android.app.Activity;
import android.os.Bundle;


public class AboutActivity extends Activity {
    boolean continueMusic;

    @Override
    protected void onPause() {
        super.onPause();

        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
