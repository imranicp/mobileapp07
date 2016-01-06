package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Vahiiid on 1/6/2016.
 */
public class SelectPlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_palyer_layout);
    }

    public void goToGame(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
