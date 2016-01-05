package logic.main.com.boardgame;

/**
 * Created by Vahiiid on 12/24/2015.
 */

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
    ImageView flingView;
    int beadCount = 20;
    Board board = new Board();
    MoveController moveController = new MoveController();
    String flingType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*drawView = new GameCanvas(this);
        drawView.setBackgroundColor(Color.WHITE);*/
        setContentView(R.layout.layout_main);

        gdt = new GestureDetector(this, new GestureListener());


        int i;
        String postionsOfHorizontalBars = "";
        String postionsOfVerticalBars = "";
        BoardConf boardConf = new BoardConf();
        board.setMovingPlayer(1);
        board.setNumberOfPlayers(4);
        for (i = 0; i < 7; i++) {

            postionsOfHorizontalBars = postionsOfHorizontalBars + String.valueOf(randInt(0, 2));

        }
        board.setPostionsOfHorizontalBars(postionsOfHorizontalBars);
        System.out.println(board.getPostionsOfHorizontalBars());
        for (i = 0; i < 7; i++) {

            postionsOfVerticalBars = postionsOfVerticalBars + String.valueOf(randInt(0, 2));
        }
        board.setPostionsOfVerticalBars(postionsOfVerticalBars);

        // Log.e("postionsOfVerticalBars", postionsOfVerticalBars);
        // Log.e("postionsOfHorizonlBars", postionsOfHorizontalBars);

        board.setBoardConfiguration(boardConf.boardConfGenerator(
                board.getPostionsOfHorizontalBars(),
                board.getPostionsOfVerticalBars()));

        //  Log.e("boardConfig", board.getBoardConfiguration());
        setBoardImages(board);
        setBarImages(board);


    }

    @Override
    public boolean onTouch(final View view, final MotionEvent event) {
        Resources res = view.getResources();     // get resources
        String idString = res.getResourceEntryName(view.getId());
        String tag = (String) view.getTag();
        //  Log.e("id", idString);

        //Placing beads
        if (tag.equals("brownpiece") && beadCount != 0) {
            if (board.getMovingPlayer() == 1) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.brown1);
                imageView.setTag("brown1");
            }
            if (board.getMovingPlayer() == 2) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.brown2);
                imageView.setTag("brown2");
            }
            if (board.getMovingPlayer() == 3) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.brown3);
                imageView.setTag("brown3");
            }
            if (board.getMovingPlayer() == 4) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.brown4);
                imageView.setTag("brown4");
            }
            board = moveController.setMovingPlayer(board);
            beadCount--;
        }

        if (tag.equals("darkpiece") && beadCount != 0) {
            if (board.getMovingPlayer() == 1) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.dark1);
                imageView.setTag("dark1");
            }
            if (board.getMovingPlayer() == 2) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.dark2);
                imageView.setTag("dark2");
            }
            if (board.getMovingPlayer() == 3) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.dark3);
                imageView.setTag("dark3");
            }
            if (board.getMovingPlayer() == 4) {
                imageView = (ImageView) findViewById(view.getId());
                imageView.setImageResource(R.drawable.dark4);
                imageView.setTag("dark4");
            }
            board = moveController.setMovingPlayer(board);
            beadCount--;
        }


        gdt.onTouchEvent(event);

        if ((flingType == "topToBottom"||flingType == "bottomToTop"||flingType == "rightToLeft"||flingType == "leftToRight")&&beadCount==0) {
           Log.e("flingid", idString);
            Log.e("flingtag", (String) view.getTag());
            setBeadConfig(board);
            String move=moveGenerator(view,flingType);
          //  Log.e("move",move);
           // Log.e("beadConfig", board.getBeadConfiguration());
            try {
                flingType = "";
                String input=String.valueOf(board.getNumberOfPlayers())+String.valueOf(board.getMovingPlayer())+board.getPostionsOfHorizontalBars()+board.getPostionsOfVerticalBars()+board.getBeadConfiguration()+move;
                Log.e("input",input);
                board.setInput(input);
                board=moveController.moveTest(board);
                setBoardImages(board);
                setBarImages(board);

               if(String.valueOf(board.getWinner())!="0"){
                   Toast toast = Toast.makeText(getApplicationContext(), "Winner is Player: "+String.valueOf(board.getWinner()), Toast.LENGTH_SHORT);
                   toast.show();
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

    private String moveGenerator(View view, String flingType) {
        String move="";
        String viewTag=(String) view.getTag();
        Log.e("moveTag",viewTag);
        Log.e("flingType",flingType);

        if(flingType == "topToBottom"){

            if(viewTag.contains("top")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"i";
            }

            if(viewTag.contains("bot")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"i";
            }
        }

        if(flingType == "bottomToTop"){

            if(viewTag.contains("bot")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"o";
            }

            if(viewTag.contains("top")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"o";
            }
        }

        if(flingType == "rightToLeft"){

            if(viewTag.contains("l")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"o";
            }

            if(viewTag.contains("r")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"o";
            }
        }

        if(flingType == "leftToRight"){

            if(viewTag.contains("l")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"i";
            }

            if(viewTag.contains("r")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"i";
            }
        }

        return move;
    }

    private void setBeadConfig(Board board) {
        String beadConfiguration="";
        ImageView boardImage;
        int i;
        int suffix = 1;
        for(i=0;i<49;i++){
            String prefix = "a";

            if (i >= 7 && i < 14) {
                prefix = "b";
            }
            if (i >= 14 && i < 21) {
                prefix = "c";
            }
            if (i >= 21 && i < 28) {
                prefix = "d";
            }
            if (i >= 28 && i < 35) {
                prefix = "e";
            }
            if (i >= 35 && i < 42) {
                prefix = "f";
            }
            if (i >= 42 && i < 49) {
                prefix = "g";
            }
            String resource = prefix + String.valueOf(suffix);
            // Log.e("resourceInaction", resource);
            int resID = getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
            boardImage = (ImageView) findViewById(resID);
            String imageTag=(String)boardImage.getTag();
            // Log.e("tagInaction", imageTag);

            if(imageTag.contains("1")){
                beadConfiguration=beadConfiguration+"1";
            }
            else if(imageTag.contains("2")){
                beadConfiguration=beadConfiguration+"2";
            }
            else if(imageTag.contains("3")){
                beadConfiguration=beadConfiguration+"3";
            }
            else if(imageTag.contains("4")){
                beadConfiguration=beadConfiguration+"4";
            }
            else {
                beadConfiguration=beadConfiguration+"0";
            }
            suffix++;
            if (suffix > 7) {
                suffix = 1;
            }

        }
        board.setBeadConfiguration(beadConfiguration);
    }


    private void setBarImages(Board board) {
        int i;
        String horizontalBars = board.getPostionsOfHorizontalBars();
        String verticalBars = board.getPostionsOfVerticalBars();

        for (i = 0; i < horizontalBars.length(); i++) {
            int j = i + 1;
            String leftResource = "l" + String.valueOf(j);
            String rightResource = "r" + String.valueOf(j);

            if (horizontalBars.charAt(i) == '0') {
                int resID = getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.leftinner);
                imageView.setOnTouchListener(this);
                imageView.setTag(leftResource+"inner");

                resID = getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.rightouter);
                imageView.setOnTouchListener(this);
                imageView.setTag(rightResource+"outer");
            }

            if (horizontalBars.charAt(i) == '1') {
                int resID = getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.leftcenter);
                imageView.setOnTouchListener(this);
                imageView.setTag(leftResource+"center");

                resID = getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.rightcenter);
                imageView.setOnTouchListener(this);
                imageView.setTag(rightResource+"center");
            }

            if (horizontalBars.charAt(i) == '2') {
                int resID = getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.leftouter);
                imageView.setOnTouchListener(this);
                imageView.setTag(leftResource+"outer");

                resID = getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.rightinner);
                imageView.setOnTouchListener(this);
                imageView.setTag(rightResource+"inner");
            }
        }

        for (i = 0; i < verticalBars.length(); i++) {
            int j = i + 1;
            String topResource = "top" + String.valueOf(j);
            String botResource = "bot" + String.valueOf(j);

            if (verticalBars.charAt(i) == '0') {
                int resID = getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.topinner);
                imageView.setOnTouchListener(this);
                imageView.setTag(topResource+"inner");

                resID = getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.bottomouter);
                imageView.setOnTouchListener(this);
                imageView.setTag(botResource+"outer");
            }

            if (verticalBars.charAt(i) == '1') {
                int resID = getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.topcenter);
                imageView.setOnTouchListener(this);
                imageView.setTag(topResource+"center");
                resID = getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.bottomcenter);
                imageView.setOnTouchListener(this);
                imageView.setTag(botResource+"center");
            }

            if (verticalBars.charAt(i) == '2') {
                int resID = getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.topouter);
                imageView.setOnTouchListener(this);
                imageView.setTag(topResource+"outer");
                resID = getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.bottominner);
                imageView.setOnTouchListener(this);
                imageView.setTag(botResource+"inner");
            }
        }
    }






    private void setBoardImages(Board board) {
        String boardConfig = board.getBoardConfiguration();
        int i;
        int suffix = 1;
        for (i = 0; i < boardConfig.length(); i++) {

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
            //Log.e("resourceInaction", resource);
            if (boardConfig.charAt(i) == 'h') {
                int resID = getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                imageView.setImageResource(R.drawable.hole);
                imageView.setOnTouchListener(this);
                imageView.setTag("hole");
            }

            if (boardConfig.charAt(i) == 'r') {
                int resID = getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                String tileTag = (String) imageView.getTag();
               // Log.e("tileTag",tileTag);

                if(tileTag!=null) {
                    if (!tileTag.matches(".*\\d+.*")) {
                        imageView.setImageResource(R.drawable.brownpiece);
                        imageView.setOnTouchListener(this);
                        imageView.setTag("brownpiece");

                    } else if (tileTag.matches(".*\\d+.*")) {
                        tileTag = tileTag.replaceAll("\\D+", "");
                        if (tileTag.equals("1")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.brown1);
                            imageView.setTag("brown1");
                        }
                        if (tileTag.equals("2")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.brown2);
                            imageView.setTag("brown2");
                        }
                        if (tileTag.equals("3")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.brown3);
                            imageView.setTag("brown3");
                        }
                        if (tileTag.equals("4")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.brown4);
                            imageView.setTag("brown4");
                        }
                    }
                }else{
                    imageView.setImageResource(R.drawable.brownpiece);
                    imageView.setOnTouchListener(this);
                    imageView.setTag("brownpiece");
                }
            }
            if (boardConfig.charAt(i) == 'b') {
                int resID = getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) findViewById(resID);
                String tileTag=(String)imageView.getTag();
                if(tileTag!=null){
                    if (!tileTag.matches(".*\\d+.*")) {
                        imageView.setImageResource(R.drawable.darkpiece);
                        imageView.setOnTouchListener(this);
                        imageView.setTag("darkpiece");

                    } else if (tileTag.matches(".*\\d+.*")) {

                        tileTag = tileTag.replaceAll("\\D+", "");
                        if (tileTag.equals("1")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.dark1);
                            imageView.setTag("dark1");
                        }
                        if (tileTag.equals("2")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.dark2);
                            imageView.setTag("dark2");
                        }
                        if (tileTag.equals("3")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.dark3);
                            imageView.setTag("dark3");
                        }
                        if (tileTag.equals("4")) {
                            imageView = (ImageView) findViewById(resID);
                            imageView.setImageResource(R.drawable.dark4);
                            imageView.setTag("dark4");
                        }
                    }
                }else{
                    imageView.setImageResource(R.drawable.darkpiece);
                    imageView.setOnTouchListener(this);
                    imageView.setTag("darkpiece");
                }
            }
            suffix++;
            if (suffix > 7) {
                suffix = 1;
            }

        }


    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
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
