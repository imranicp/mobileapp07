package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity implements View.OnTouchListener {
    //GameCanvas drawView;
    GestureDetector gdt;
    // ImageView a1,a2,a3,a4,a5,a6,a7,b1,b2,b3,b4,b5,b6,b7,c1,c2,c3,c4,c5,c6,c7,d1,d2,d3,d4,d5,d6,d7,e1,e2,e3,e4,e5,e6,e7,f1,f2,f3,f4,f5,f6,f7,g1,g2,g3,g4,g5,g6,g7;
    ImageView imageView;

    int beadCount2=10;
    int beadCount3=15;
    int beadCount4=20;
    int beadCount;
    Board board = new Board();
    MoveController moveController = new MoveController();
    BoardImageSetter boardImageSetter=new BoardImageSetter();
    BarImageSetter barImageSetter=new BarImageSetter();
    BeadConfingSetter beadConfingSetter=new BeadConfingSetter();
    MoveGenerator moveGenerator=new MoveGenerator();
    BeadPlacer beadPlacer=new BeadPlacer();
    DataBaseHelper  dataBaseHelper= new DataBaseHelper(this);
    String flingType="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*drawView = new GameCanvas(this);
        drawView.setBackgroundColor(Color.WHITE);*/
        setContentView(R.layout.layout_main);

        gdt = new GestureDetector(this, new GestureListener());

        BoardConf boardConf = new BoardConf();
        board.setMovingPlayer(1);
        board.setNumberOfPlayers(2);
       // Intent myIntent = getIntent();
       // Board board = (Board)myIntent.getParcelableExtra("numberOfPlayer");
       // int a  = myIntent.getIntExtra("numberOfPlayer",4);

        //board.setNumberOfPlayers(a);
        BarConfigGenerator barConfigGenerator=new BarConfigGenerator();
        board= barConfigGenerator.generateBarConfig(board);

        // Log.e("postionsOfVerticalBars", postionsOfVerticalBars);
        // Log.e("postionsOfHorizonlBars", postionsOfHorizontalBars);

        board.setBoardConfiguration(boardConf.boardConfGenerator(
                board.getPostionsOfHorizontalBars(),
                board.getPostionsOfVerticalBars()));

        //  Log.e("boardConfig", board.getBoardConfiguration());

        boardImageSetter.setBoardImages(board, getApplicationContext(), this);
        //setBoardImages(board);
        barImageSetter.setBarImages(board, getApplicationContext(), this);


    }

    @Override
    public boolean onTouch(final View view, final MotionEvent event) {
        Resources res = view.getResources();     // get resources
        String idString = res.getResourceEntryName(view.getId());
        String tag = (String) view.getTag();
        //  Log.e("id", idString);

        //Placing beads
        if(board.getNumberOfPlayers()==2){

            beadCount2= beadPlacer.placeBeads(beadCount2,tag,view.getId(),board,this);
            beadCount=beadCount2;
        }else if(board.getNumberOfPlayers()==3){

            beadCount3= beadPlacer.placeBeads(beadCount3,tag,view.getId(),board,this);
            beadCount=beadCount3;
        }else if(board.getNumberOfPlayers()==4){

            beadCount4= beadPlacer.placeBeads(beadCount4,tag,view.getId(),board,this);
            beadCount=beadCount4;
        }




        gdt.onTouchEvent(event);

        if ((flingType.equals("topToBottom")||flingType .equals( "bottomToTop")||flingType .equals("rightToLeft")||flingType .equals( "leftToRight"))&& beadCount==0) {
            Log.e("flingid", idString);
            Log.e("flingtag", (String) view.getTag());
            beadConfingSetter.setBeadConfig(board,getApplicationContext(),this);
            String move=moveGenerator.generateMove(view,flingType);

            //Log.e("move",move);
            // Log.e("beadConfig", board.getBeadConfiguration());
            try {
                flingType = "";
                String input=String.valueOf(board.getNumberOfPlayers())+String.valueOf(board.getMovingPlayer())+board.getPostionsOfHorizontalBars()+board.getPostionsOfVerticalBars()+board.getBeadConfiguration()+move;
                Log.e("input", input);
                board.setInput(input);
                board=moveController.moveTest(board);
                boardImageSetter.setBoardImages(board, getApplicationContext(), this);
                //setBoardImages(board);
                barImageSetter.setBarImages(board, getApplicationContext(),this);

                if(String.valueOf(board.getWinner())!="0"){
                    Toast toast = Toast.makeText(getApplicationContext(), "Winner is Player: "+String.valueOf(board.getWinner()), Toast.LENGTH_SHORT);
                    toast.show();
                    dataBaseHelper.updateScore(board);

                    // Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                    // startActivity(intent);

                }
                Log.e("newConfig",board.getOutput());
            } catch (Exception e) {
                e.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }

        }

        return true;
    }


    private static final int SWIPE_MIN_DISTANCE = 30;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

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
