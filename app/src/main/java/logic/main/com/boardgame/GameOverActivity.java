package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        String winner = getIntent().getExtras().getString("winner");
        TextView winnerText = (TextView) findViewById(R.id.winner);
        winnerText.setText(winner + " WINS!");
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();

    }
}
