package logic.main.com.boardgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnTouchListener {

    private static final int SWIPE_MIN_DISTANCE = 30;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    GestureDetector gdt;
    int beadCount2=10;
    int beadCount3=15;
    int beadCount4=20;
    int beadCount;
    boolean winnerDecided = false;
    Board board = new Board();
    MoveController moveController = new MoveController();
    BoardImageSetter boardImageSetter=new BoardImageSetter();
    BarImageSetter barImageSetter=new BarImageSetter();
    BeadConfingSetter beadConfingSetter=new BeadConfingSetter();
    MoveGenerator moveGenerator=new MoveGenerator();
    BeadPlacer beadPlacer=new BeadPlacer();
    DatabaseManager dataBaseHelper;
    String player1name, player2name, player3name, player4name;
    String flingType="";
    boolean continueMusic;

    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_MENU);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*drawView = new GameCanvas(this);
        drawView.setBackgroundColor(Color.WHITE);*/
        setContentView(R.layout.layout_main);
        dataBaseHelper = new DatabaseManager(this);
        gdt = new GestureDetector(this, new GestureListener());

        BoardConf boardConf = new BoardConf();
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        int value = getIntent().getExtras().getInt("numberOfPlayers");
        board.setNumberOfPlayers(value);
        board.setMovingPlayer(1);
        CustomTextView player1_name = (CustomTextView) findViewById(R.id.player1text);
        CustomTextView player2_name = (CustomTextView) findViewById(R.id.player2text);
        player1name = getIntent().getExtras().getString("player1");
        player2name = getIntent().getExtras().getString("player2");
        player1_name.setText(player1name);
        player2_name.setText(player2name);

        ImageView player3tile = (ImageView) findViewById(R.id.player3tile);
        CustomTextView player3_name = (CustomTextView) findViewById(R.id.player3text);
        ImageView player3score = (ImageView) findViewById(R.id.player3score);
        CustomTextView player3txtscr = (CustomTextView) findViewById(R.id.player3txtscr);
        player3tile.setVisibility(View.INVISIBLE);
        player3_name.setVisibility(View.INVISIBLE);
        player3score.setVisibility(View.INVISIBLE);
        player3txtscr.setVisibility(View.INVISIBLE);
        ImageView player4tile = (ImageView) findViewById(R.id.player4tile);
        CustomTextView player4_name = (CustomTextView) findViewById(R.id.player4text);
        ImageView player4score = (ImageView) findViewById(R.id.player4score);
        CustomTextView player4txtscr = (CustomTextView) findViewById(R.id.player4txtscr);
        player4tile.setVisibility(View.INVISIBLE);
        player4_name.setVisibility(View.INVISIBLE);
        player4score.setVisibility(View.INVISIBLE);
        player4txtscr.setVisibility(View.INVISIBLE);

        if (value == 3) {
            player3name = getIntent().getExtras().getString("player3");

            player3_name.setText(player3name);
            player3tile.setVisibility(View.VISIBLE);
            player3_name.setVisibility(View.VISIBLE);
            player3score.setVisibility(View.VISIBLE);
            player3txtscr.setVisibility(View.VISIBLE);


        }
        if (value == 4) {
            player3tile.setVisibility(View.VISIBLE);
            player3_name.setVisibility(View.VISIBLE);
            player3score.setVisibility(View.VISIBLE);
            player3txtscr.setVisibility(View.VISIBLE);
            player4tile.setVisibility(View.VISIBLE);
            player4_name.setVisibility(View.VISIBLE);
            player4score.setVisibility(View.VISIBLE);
            player4txtscr.setVisibility(View.VISIBLE);
            player3name = getIntent().getExtras().getString("player3");
            player3_name.setText(player3name);
            player4name = getIntent().getExtras().getString("player4");
            player4_name.setText(player4name);
        }
        BarConfigGenerator barConfigGenerator = new BarConfigGenerator();
        board = barConfigGenerator.generateBarConfig(board);

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
    public void onBackPressed() {
        final Intent intent = new Intent(this, MenuActivity.class);
        new AlertDialog.Builder(this)
                .setTitle("Quit Game?")
                .setMessage("Are you sure you want to Quit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent event) {

        final MediaPlayer movingBar = MediaPlayer.create(this, R.raw.moving_bars_sound);
        movingBar.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer placingBead = MediaPlayer.create(this, R.raw.placing_bead_sound);
        placingBead.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer invalidMove = MediaPlayer.create(this, R.raw.invalid_move_sound);
        invalidMove.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer gameOver = MediaPlayer.create(this, R.raw.game_over_sound);
        gameOver.setOnCompletionListener(new OnCompletionListener());

        Resources res = view.getResources();     // get resources
        String idString = res.getResourceEntryName(view.getId());
        String tag = (String) view.getTag();
        //  Log.e("id", idString);

        //Placing beads
        if (board.getNumberOfPlayers() == 2 && !(beadCount2 == 0) && !winnerDecided) {
            placingBead.start();
            beadCount2= beadPlacer.placeBeads(beadCount2,tag,view.getId(),board,this);
            beadCount=beadCount2;
        } else if (board.getNumberOfPlayers() == 3 && !(beadCount3 == 0) && !winnerDecided) {
            placingBead.start();
            beadCount3= beadPlacer.placeBeads(beadCount3,tag,view.getId(),board,this);
            beadCount=beadCount3;
        } else if (board.getNumberOfPlayers() == 4 && !(beadCount4 == 0) && !winnerDecided) {
            placingBead.start();
            beadCount4= beadPlacer.placeBeads(beadCount4,tag,view.getId(),board,this);
            beadCount=beadCount4;
        }




        gdt.onTouchEvent(event);

        if ((flingType.equals("topToBottom") || flingType.equals("bottomToTop") || flingType.equals("rightToLeft") || flingType.equals("leftToRight")) && beadCount == 0 && !winnerDecided) {
            Log.e("flingid", idString);
            Log.e("flingtag", (String) view.getTag());
            beadConfingSetter.setBeadConfig(board,getApplicationContext(),this);
            String move=moveGenerator.generateMove(view,flingType);

            if (move != "") {
                //Log.e("move",move);
                // Log.e("beadConfig", board.getBeadConfiguration());
                try {
                    flingType = "";
                    String input = String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer()) + board.getPostionsOfHorizontalBars() + board.getPostionsOfVerticalBars() + board.getBeadConfiguration() + move;
                    Log.e("input", input);
                    board.setInput(input);
                    board = moveController.moveTest(board);
                    boardImageSetter.setBoardImages(board, getApplicationContext(), this);
                    //setBoardImages(board);
                    barImageSetter.setBarImages(board, getApplicationContext(), this);
                    movingBar.start();
                    if (String.valueOf(board.getWinner()) != "0") {
                        String[] players = new String[4];
                        players[0] = player1name;
                        players[1] = player2name;
                        if (board.getNumberOfPlayers() == 3) {
                            players[2] = player3name;
                        }
                        if (board.getNumberOfPlayers() == 4) {
                            players[2] = player3name;
                            players[3] = player4name;
                        }
                        gameOver.start();
                        dataBaseHelper.updateScore(board, players, this, continueMusic);
                        winnerDecided = true;
                        finish();

                    }
                    Log.e("newConfig", board.getOutput());
                } catch (Exception e) {
                    invalidMove.start();
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }

        return true;
    }

    private class OnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.stop();
            mp.release();
        }
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
