package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

/**
 * Created by Vahiiid on 1/6/2016.
 */
public class SelectPlayerActivity extends Activity {
    Board board = new Board();
    Bundle bundle = new Bundle();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_palyer_layout);



        Button next = (Button) findViewById(R.id.twoPlayer);
        Button next2=(Button) findViewById(R.id.threePlayer);
        Button next3= (Button) findViewById(R.id.fourPlayer);
        next.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {


                                     int a = 2;

                                        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                                //        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                                   //     myIntent.putExtra("numberOfPlayer", (Parcelable) board);
                                        myIntent.putExtra("numberOfPlayer",a);


                                        startActivityForResult(myIntent,0);

                                    }
                                });
        next2.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view) {
                                         board.setNumberOfPlayers(3);
                                   //    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                                         Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                                         myIntent.putExtra("numberOfPlayer", (Parcelable) board);
                                         startActivityForResult(myIntent, 0);

                                     }
                                 });
        next3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                board.setNumberOfPlayers(4);
               // Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                myIntent.putExtra("numberOfPlayer", (Parcelable) board);
                startActivityForResult(myIntent,0);
            }
    });

    }
}
