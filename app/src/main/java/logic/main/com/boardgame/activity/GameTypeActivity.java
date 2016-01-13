package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;


public class GameTypeActivity extends Activity {

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

        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        super.onCreate(savedInstanceState);
        dataBaseHelper = new DatabaseManager(this);
        setContentView(R.layout.game_type_layout);

    }
}
