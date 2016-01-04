package logic.main.com.boardgame;

/**
 * Created by Vahiiid on 12/24/2015.
 */
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.SampleCanvas.R;

public class MainActivity extends Activity {
    GameCanvas drawView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*drawView = new GameCanvas(this);
        drawView.setBackgroundColor(Color.WHITE);*/
        setContentView(R.layout.layout_main);

    }
}
