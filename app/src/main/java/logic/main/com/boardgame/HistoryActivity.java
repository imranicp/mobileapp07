package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Vahiiid on 1/6/2016.
 */
public class HistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_listview);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
