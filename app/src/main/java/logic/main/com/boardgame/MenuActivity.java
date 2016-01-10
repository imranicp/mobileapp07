package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        Button next= (Button) findViewById(R.id.aboutBtn);

    }

    public void goToPlayerMenu(View view){
        Intent intent = new Intent(this, GameTypeActivity.class);
        startActivity(intent);

    }

    public void goToScore(View view){
        Intent intent = new Intent(this,ScoreActivity.class);
        startActivity(intent);
    }

   public void goToAbout(View view){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
}
