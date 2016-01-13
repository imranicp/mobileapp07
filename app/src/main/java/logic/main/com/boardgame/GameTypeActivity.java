package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class GameTypeActivity extends Activity {

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

    public void onClick(View v) {
        //Intent newActivity = new Intent(this, MainActivity.class);
        Intent playerSelectActivity = new Intent(this, PlayerSelectActivity.class);

        Intent vsComputerActivity = new Intent(this, VsComputerActivity.class);
        switch(v.getId()) {
            case R.id.onePlayer:

                vsComputerActivity.putExtra("numberOfPlayers", 2);
                vsComputerActivity.putExtra("continueMusic", continueMusic);
                startActivity(vsComputerActivity);
                finish();
                break;
            case R.id.twoPlayer:

                playerSelectActivity.putExtra("numberOfPlayers", 2);
                playerSelectActivity.putExtra("continueMusic", continueMusic);
                startActivity(playerSelectActivity);
                finish();
                break;
            case R.id.threePlayer:

                playerSelectActivity.putExtra("numberOfPlayers", 3);
                playerSelectActivity.putExtra("continueMusic", continueMusic);
                startActivity(playerSelectActivity);
                finish();
                break;
            case R.id.fourPlayer:

                playerSelectActivity.putExtra("numberOfPlayers", 4);
                playerSelectActivity.putExtra("continueMusic", continueMusic);
                startActivity(playerSelectActivity);
                finish();
                break;
            default:
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type_layout);

    }
}
