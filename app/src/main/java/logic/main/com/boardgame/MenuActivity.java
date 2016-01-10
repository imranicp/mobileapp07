package logic.main.com.boardgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

    }

    public void goToPlayerMenu(View view){
        Intent intent = new Intent(this, GameTypeActivity.class);
        startActivity(intent);
        finish();

    }

    public void goToScore(View view){
        Intent intent = new Intent(this,ScoreActivity.class);
        startActivity(intent);
        finish();
    }

   public void goToAbout(View view){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
       finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }
}
