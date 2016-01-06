package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Vahiiid on 1/6/2016.
 */
public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }

    public void goToPlayerMenu(View view){
        Intent intent = new Intent(this,SelectPlayerActivity.class);
        startActivity(intent);

    }
}
