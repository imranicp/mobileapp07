package logic.main.com.boardgame;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class VsComputerActivity extends Activity implements View.OnTouchListener {
    private static final int SWIPE_MIN_DISTANCE = 30;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    GestureDetector gdt;
    String flingType = "";
    int beadCount2 = 10;
    int beadCount;
    Board board = new Board();
    BoardImageSetter boardImageSetter = new BoardImageSetter();
    BarImageSetter barImageSetter = new BarImageSetter();
    BeadPlacer beadPlacer = new BeadPlacer();
    BeadConfingSetter beadConfingSetter = new BeadConfingSetter();
    ProgressDialog dialog;
    ImageView imageView;

    public static int randInt(int min, int max, String[] availablepositions) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (availablepositions[randomNum] == null) {
            randomNum = randInt(0, 49, availablepositions);
        } else {
            return randomNum;
        }
        return randomNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_computer);
        gdt = new GestureDetector(this, new GestureListener());

        BoardConf boardConf = new BoardConf();

        int value = getIntent().getExtras().getInt("numberOfPlayers");
        board.setNumberOfPlayers(value);
        board.setMovingPlayer(1);

        BarConfigGenerator barConfigGenerator = new BarConfigGenerator();
        board = barConfigGenerator.generateBarConfig(board);

        board.setBoardConfiguration(boardConf.boardConfGenerator(
                board.getPostionsOfHorizontalBars(),
                board.getPostionsOfVerticalBars()));

        boardImageSetter.setBoardImages(board, getApplicationContext(), this);
        barImageSetter.setBarImages(board, getApplicationContext(), this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Resources res = view.getResources();     // get resources
        String idString = res.getResourceEntryName(view.getId());
        String tag = (String) view.getTag();
        Log.e("id", idString);
        //User places beads
        if (board.getMovingPlayer() == 1) {

            beadCount2 = beadPlacer.placeBeads(beadCount2, tag, view.getId(), board, this);
            beadCount = beadCount2;
        }
        //Computer places beads
        if (board.getMovingPlayer() == 2) {

            beadConfingSetter.setBeadConfig(board, this.getApplicationContext(), this);

            String botId = botPlacesBead(board);

            int resID = getResources().getIdentifier(botId, "id", "logic.main.com.boardgame");
            imageView = (ImageView) findViewById(resID);
            String tileTag = (String) imageView.getTag();

            beadCount2 = beadPlacer.placeBeads(beadCount2, tileTag, resID, board, this);
            beadCount = beadCount2;

            /*dialog = ProgressDialog.show(this, "", " Bot performing move",
                    true);
            dialog.show();*/

            /*Log.e("after:",board.getBeadConfiguration());
            boardImageSetter.setBoardImages(board, getApplicationContext(), this);
            //setBoardImages(board);
            barImageSetter.setBarImages(board, getApplicationContext(), this);*/
            /*Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            }, 2000);*/


        }


        return true;
    }

    private String botPlacesBead(Board board) {

        String[] availablepositions = findAvailablePositions(board);

        int beadPos = randInt(0, 49, availablepositions);
         
        return availablepositions[beadPos];
    }

    private String[] findAvailablePositions(Board board) {
        String[] pos = new String[50];
        int i, k = 0;
        String boardConfig = board.getBoardConfiguration();
        String beadConfig = board.getBeadConfiguration();

        int suffix = 1;
        for (i = 0; i < 49; i++) {

            int j = i + 1;
            String prefix = "a";

            if (j > 7 && j <= 14) {
                prefix = "b";
            }
            if (j > 14 && j <= 21) {
                prefix = "c";
            }
            if (j > 21 && j <= 28) {
                prefix = "d";
            }
            if (j > 28 && j <= 35) {
                prefix = "e";
            }
            if (j > 35 && j <= 42) {
                prefix = "f";
            }
            if (j > 42 && j <= 49) {
                prefix = "g";
            }

            String resource = prefix + String.valueOf(suffix);


            if (!(boardConfig.charAt(i) == 'h') && !(beadConfig.charAt(i) == '1') && !(beadConfig.charAt(i) == '2')) {
                pos[k] = resource;
                k++;
            }
            suffix++;
            if (suffix > 7) {
                suffix = 1;
            }
        }
        return pos;
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // imageView.setBackgroundResource(R.drawable.ho2);
                flingType = "rightToLeft";
                /*Toast toast = Toast.makeText(getApplicationContext(), "right to left", Toast.LENGTH_SHORT);
                toast.show();*/
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // imageView.setBackgroundResource(R.drawable.hi2);
                flingType = "leftToRight";
                /*Toast toast = Toast.makeText(getApplicationContext(), "left to right", Toast.LENGTH_SHORT);
                toast.show();*/
            }

            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                //flingView.setImageResource(R.drawable.topcenter);
                flingType = "bottomToTop";
                /*Toast toast = Toast.makeText(getApplicationContext(), "bottom to top", Toast.LENGTH_SHORT);
                toast.show();*/
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                // flingView.setImageResource(R.drawable.topinner);
                flingType = "topToBottom";
                /*Toast toast = Toast.makeText(getApplicationContext(), "top to bottom", Toast.LENGTH_SHORT);
                toast.show();*/
            }
            return false;
        }
    }
}
