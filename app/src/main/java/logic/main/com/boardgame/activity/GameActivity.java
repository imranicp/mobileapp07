package logic.main.com.boardgame.activity;

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

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.core.BarConfigGenerator;
import logic.main.com.boardgame.core.Board;
import logic.main.com.boardgame.core.BoardConf;
import logic.main.com.boardgame.core.MoveController;
import logic.main.com.boardgame.core.MoveGenerator;
import logic.main.com.boardgame.custom.CustomFontTextView;
import logic.main.com.boardgame.custom.CustomTextView;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;
import logic.main.com.boardgame.support.BarImageSetter;
import logic.main.com.boardgame.support.BeadConfingSetter;
import logic.main.com.boardgame.support.BeadPlacer;
import logic.main.com.boardgame.support.BoardImageSetter;


public class GameActivity extends Activity implements View.OnTouchListener {

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
    boolean continueMusic, soundSetting;
    CustomTextView player1txtscr, player2txtscr, player3txtscr, player4txtscr;
    CustomFontTextView gameState;
    ImageView player1tile, player2tile, player3tile, player4tile;
    @Override
    protected void onPause() {
        super.onPause();

        continueMusic = false;
        MusicManager.pause();


    }

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = dataBaseHelper.getMusicValue();

        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_layout);
        dataBaseHelper = new DatabaseManager(this);


        gdt = new GestureDetector(this, new GestureListener());
        soundSetting = dataBaseHelper.getSoundValue();
        BoardConf boardConf = new BoardConf();
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        int value = getIntent().getExtras().getInt("numberOfPlayers");
        board.setNumberOfPlayers(value);
        board.setMovingPlayer(1);

        CustomTextView player1_name = (CustomTextView) findViewById(R.id.player1text);
        CustomTextView player2_name = (CustomTextView) findViewById(R.id.player2text);
        gameState = (CustomFontTextView) findViewById(R.id.gamestate);
        player1name = getIntent().getExtras().getString("player1");
        player2name = getIntent().getExtras().getString("player2");
        player1_name.setText(player1name);
        player2_name.setText(player2name);

        player1txtscr = (CustomTextView) findViewById(R.id.player1scoredisplay);
        player2txtscr = (CustomTextView) findViewById(R.id.player2scoredisplay);
        player3txtscr = (CustomTextView) findViewById(R.id.player3scoredisplay);
        player4txtscr = (CustomTextView) findViewById(R.id.player4scoredisplay);
        player1tile = (ImageView) findViewById(R.id.player1tile);
        player2tile = (ImageView) findViewById(R.id.player2tile);
        player3tile = (ImageView) findViewById(R.id.player3tile);
        CustomTextView player3_name = (CustomTextView) findViewById(R.id.player3text);
        ImageView player3score = (ImageView) findViewById(R.id.player3score);

        player3tile.setVisibility(View.INVISIBLE);
        player3_name.setVisibility(View.INVISIBLE);
        player3score.setVisibility(View.INVISIBLE);
        player3txtscr.setVisibility(View.INVISIBLE);
        player4tile = (ImageView) findViewById(R.id.player4tile);
        CustomTextView player4_name = (CustomTextView) findViewById(R.id.player4text);
        ImageView player4score = (ImageView) findViewById(R.id.player4score);

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
        player1txtscr.setText("5");
        player2txtscr.setText("5");
        player3txtscr.setText("5");
        player4txtscr.setText("5");

        BarConfigGenerator barConfigGenerator = new BarConfigGenerator();
        board = barConfigGenerator.generateBarConfig(board);

        board.setBoardConfiguration(boardConf.boardConfGenerator(
                board.getPostionsOfHorizontalBars(),
                board.getPostionsOfVerticalBars()));


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

                        //startActivity(intent);
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

            int movingPlayer = board.getMovingPlayer();
            beadCount2= beadPlacer.placeBeads(beadCount2,tag,view.getId(),board,this);
            beadCount=beadCount2;
            if (movingPlayer != board.getMovingPlayer()) {
                if (soundSetting)
                    placingBead.start();
                updateTurn(board.getMovingPlayer());
            }
            if (beadCount == 0)
                gameState.setText("Perform move");
        } else if (board.getNumberOfPlayers() == 3 && !(beadCount3 == 0) && !winnerDecided) {

            int movingPlayer = board.getMovingPlayer();
            beadCount3= beadPlacer.placeBeads(beadCount3,tag,view.getId(),board,this);
            beadCount=beadCount3;
            if (movingPlayer != board.getMovingPlayer()) {
                if (soundSetting)
                    placingBead.start();
                updateTurn(board.getMovingPlayer());
            }
            if (beadCount == 0)
                gameState.setText("Perform move");
        } else if (board.getNumberOfPlayers() == 4 && !(beadCount4 == 0) && !winnerDecided) {

            int movingPlayer = board.getMovingPlayer();
            beadCount4= beadPlacer.placeBeads(beadCount4,tag,view.getId(),board,this);
            beadCount=beadCount4;
            if (movingPlayer != board.getMovingPlayer()) {
                if (soundSetting)
                    placingBead.start();
                updateTurn(board.getMovingPlayer());
            }
            if (beadCount == 0)
                gameState.setText("Perform move");
        }


        gdt.onTouchEvent(event);

        if ((flingType.equals("topToBottom") || flingType.equals("bottomToTop") || flingType.equals("rightToLeft") || flingType.equals("leftToRight")) && beadCount == 0 && !winnerDecided) {
            Log.e("flingid", idString);
            Log.e("flingtag", (String) view.getTag());
            beadConfingSetter.setBeadConfig(board,getApplicationContext(),this);
            String move=moveGenerator.generateMove(view,flingType);

            if (!move.equals("")) {

                try {
                    gameState.setText("Perform move");
                    flingType = "";
                    String input = String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer()) + board.getPostionsOfHorizontalBars() + board.getPostionsOfVerticalBars() + board.getBeadConfiguration() + move;
                    Log.e("input", input);
                    board.setInput(input);
                    board = moveController.moveTest(board);
                    boardImageSetter.setBoardImages(board, getApplicationContext(), this);
                    //setBoardImages(board);
                    barImageSetter.setBarImages(board, getApplicationContext(), this);
                    if (soundSetting)
                        movingBar.start();
                    if (!String.valueOf(board.getWinner()).equals("0")) {
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
                        if (soundSetting)
                            gameOver.start();
                        dataBaseHelper.updateScore(board, players, this, continueMusic);
                        winnerDecided = true;
                        finish();

                    }
                    updateScores(board.getBeadConfiguration());
                    updateTurn(board.getMovingPlayer());
                    Log.e("newConfig", board.getOutput());
                } catch (Exception e) {
                    if (soundSetting)
                        invalidMove.start();
                    e.printStackTrace();
                    gameState.setText(e.getMessage() + ". Try Again!");
                    /*Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();*/
                }
            }
        }

        return true;
    }

    private void updateTurn(int movingPlayer) {
        if (movingPlayer == 1) {
            player1tile.setImageResource(R.drawable.player1actv);
            player2tile.setImageResource(R.drawable.player2inactv);
            player3tile.setImageResource(R.drawable.player3inactv);
            player4tile.setImageResource(R.drawable.player4inactv);
        }
        if (movingPlayer == 2) {
            player1tile.setImageResource(R.drawable.player1inactv);
            player2tile.setImageResource(R.drawable.player2actv);
            player3tile.setImageResource(R.drawable.player3inactv);
            player4tile.setImageResource(R.drawable.player4inactv);
        }
        if (movingPlayer == 3) {
            player1tile.setImageResource(R.drawable.player1inactv);
            player2tile.setImageResource(R.drawable.player2inactv);
            player3tile.setImageResource(R.drawable.player3actv);
            player4tile.setImageResource(R.drawable.player4inactv);
        }
        if (movingPlayer == 4) {
            player1tile.setImageResource(R.drawable.player1inactv);
            player2tile.setImageResource(R.drawable.player2inactv);
            player3tile.setImageResource(R.drawable.player3inactv);
            player4tile.setImageResource(R.drawable.player4actv);
        }
    }

    private void updateScores(String beadConfiguration) {

        player1txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("1", "").length()));
        player2txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("2", "").length()));
        player3txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("3", "").length()));
        player4txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("4", "").length()));

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
