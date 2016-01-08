package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;


public class SelectPlayerActivity extends Activity {



    public void onClick(View v) {
        Intent newActivity = new Intent(this, MainActivity.class);
        switch(v.getId()) {
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
        setContentView(R.layout.select_palyer_layout);
    }
}
