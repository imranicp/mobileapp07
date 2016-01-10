package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class GameTypeActivity extends Activity {



    public void onClick(View v) {
        //Intent newActivity = new Intent(this, MainActivity.class);
        Intent playerSelectActivity = new Intent(this, PlayerSelectActivity.class);

        Intent vsComputerActivity = new Intent(this, VsComputerActivity.class);
        switch(v.getId()) {
            case R.id.onePlayer:

                vsComputerActivity.putExtra("numberOfPlayers", 2);
                startActivity(vsComputerActivity);

                break;
            case R.id.twoPlayer:

                playerSelectActivity.putExtra("numberOfPlayers", 2);
                startActivity(playerSelectActivity);

                break;
            case R.id.threePlayer:

                playerSelectActivity.putExtra("numberOfPlayers", 3);
                startActivity(playerSelectActivity);
                break;
            case R.id.fourPlayer:

                playerSelectActivity.putExtra("numberOfPlayers", 4);
                startActivity(playerSelectActivity);
                break;
            default:
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type_layout);
    }
}
