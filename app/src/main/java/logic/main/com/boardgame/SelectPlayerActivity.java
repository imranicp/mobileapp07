package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class SelectPlayerActivity extends Activity {



    public void onClick(View v) {
        Intent newActivity = new Intent(this, MainActivity.class);
        Intent vsComputerActivity = new Intent(this, VsComputerActivity.class);
        switch(v.getId()) {
            case R.id.onePlayer:

                vsComputerActivity.putExtra("numberOfPlayers", 2);
                startActivity(vsComputerActivity);

                break;
            case R.id.twoPlayer:

                newActivity.putExtra("numberOfPlayers", 2);
                startActivity(newActivity);

                break;
            case R.id.threePlayer:

                newActivity.putExtra("numberOfPlayers", 3);
                startActivity(newActivity);
                break;
            case R.id.fourPlayer:

                newActivity.putExtra("numberOfPlayers", 4);
                startActivity(newActivity);
                break;
            default:
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_player_layout);
    }
}
