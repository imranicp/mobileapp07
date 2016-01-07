package logic.main.com.boardgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Vahiiid on 1/6/2016.
 */
public class ScoreActivity extends Activity {
        DataBaseHelper databasehelper = new DataBaseHelper(this);
        Database database = new Database();
        StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        final TextView textViewToChange = (TextView) findViewById(R.id.textview);
        Database [] data= databasehelper.retriveScores();
        int i;

        for (i=0;i<4;i++) {

            textViewToChange.append(data[i].getName() +"                 "+ String.valueOf(data[i].getScore())+ "\n");
        }


    }
}
