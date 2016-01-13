package logic.main.com.boardgame;

import android.app.Activity;
import android.os.Bundle;

public class HelpActivity extends Activity {
    boolean continueMusic;

    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_MENU);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
    }
}
