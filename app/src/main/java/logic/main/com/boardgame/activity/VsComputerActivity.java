package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.core.BarConfigGenerator;
import logic.main.com.boardgame.core.Board;
import logic.main.com.boardgame.core.BoardConf;
import logic.main.com.boardgame.core.MoveController;
import logic.main.com.boardgame.core.MoveGenerator;
import logic.main.com.boardgame.core.VsComputer;
import logic.main.com.boardgame.custom.CustomFontTextView;
import logic.main.com.boardgame.custom.CustomTextView;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;
import logic.main.com.boardgame.support.BarImageSetter;
import logic.main.com.boardgame.support.BeadConfingSetter;
import logic.main.com.boardgame.support.BeadPlacer;
import logic.main.com.boardgame.support.BoardImageSetter;

public class VsComputerActivity extends Activity implements View.OnTouchListener {
    private static final int SWIPE_MIN_DISTANCE = 30;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    GestureDetector gdt;
    String flingType = "";
    int beadCount2 = 10;
    int beadCount;
    Vibrator v;
    Board board = new Board();
    BoardImageSetter boardImageSetter = new BoardImageSetter();
    BarImageSetter barImageSetter = new BarImageSetter();
    BeadPlacer beadPlacer = new BeadPlacer();
    BeadConfingSetter beadConfingSetter = new BeadConfingSetter();
    MoveController moveController = new MoveController();
    ProgressDialog dialog;
    ImageView imageView;
    ImageView player1tile, player2tile, player3tile, player4tile;
    boolean winnerDecided = false;
    String player1name, player2name;
    DatabaseManager dataBaseHelper;
    CustomFontTextView gameState;
    boolean continueMusic, soundSetting;
    MoveGenerator moveGenerator=new MoveGenerator();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_computer);

        dataBaseHelper = new DatabaseManager(this);

        gdt = new GestureDetector(this, new GestureListener());
        soundSetting = dataBaseHelper.getSoundValue();
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        BoardConf boardConf = new BoardConf();
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        int value = getIntent().getExtras().getInt("numberOfPlayers");
        board.setNumberOfPlayers(value);
        board.setMovingPlayer(1);

        CustomTextView player1_name = (CustomTextView) findViewById(R.id.player1text);

        gameState = (CustomFontTextView) findViewById(R.id.gamestate);
        player1name = getIntent().getExtras().getString("player1");

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
        final MediaPlayer movingBar = MediaPlayer.create(this, R.raw.moving_bars_sound);
        movingBar.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer placingBead = MediaPlayer.create(this, R.raw.placing_bead_sound);
        placingBead.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer invalidMove = MediaPlayer.create(this, R.raw.invalid_move_sound);
        invalidMove.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer gameOver = MediaPlayer.create(this, R.raw.game_over_sound);
        gameOver.setOnCompletionListener(new OnCompletionListener());
        final MediaPlayer wooden_fall = MediaPlayer.create(this, R.raw.wooden_fall);
        wooden_fall.setOnCompletionListener(new OnCompletionListener());
        String tag = (String) view.getTag();
        Log.e("id", idString);
        //User places beads
        if (board.getNumberOfPlayers() == 2 && !(beadCount2 == 0) && !winnerDecided) {

            int movingPlayer = board.getMovingPlayer();
            if (movingPlayer == 1) {

                beadCount2 = beadPlacer.placeBeads(beadCount2, tag, view.getId(), board, this);
                beadCount = beadCount2;
            }
            //Computer places beads
            if (movingPlayer == 2) {

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

            if (movingPlayer != board.getMovingPlayer()) {
                if (soundSetting)
                    placingBead.start();
                updateTurn(board.getMovingPlayer());
                v.vibrate(300);
            }
            if (beadCount == 0)
                gameState.setText("Perform move");
        }



       gdt.onTouchEvent(event);

        if ((flingType.equals("topToBottom") || flingType.equals("bottomToTop") || flingType.equals("rightToLeft") || flingType.equals("leftToRight")) && beadCount == 0 && !winnerDecided) {
            Log.e("flingid", idString);
            Log.e("flingtag", (String) view.getTag());
            beadConfingSetter.setBeadConfig(board, getApplicationContext(), this);
            String move = moveGenerator.generateMove(view, flingType);

            if (!move.equals("")) {

                try {
                    gameState.setText("Perform move");
                    flingType = "";
                    if (board.getMovingPlayer() == 1) {
                        board = moveFunc(board, move);
                    }


                } catch (Exception e) {

                    if (soundSetting)
                   //     invalidMove.start();
                    e.printStackTrace();
                    v.vibrate(200);
                    gameState.setText(e.getMessage() + ". Try Again!");
                    /*Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();*/

                }

            }
        }
        if( beadCount == 0 && !winnerDecided && board.getMovingPlayer() == 2) {
            try {
                gameState.setText("Perform move");
                if (board.getMovingPlayer() == 2) {
                    Log.e("test i am in 2:", String.valueOf(board.getMovingPlayer()));
                    VsComputer vsComputer = new VsComputer();
                    String move1 = vsComputer.computermove(board);
                     Log.e("test move1: ", move1);
                    board = moveFunc(board, move1);
                }
            } catch (Exception e) {

                if (soundSetting)
                    //     invalidMove.start();
                    e.printStackTrace();
                v.vibrate(200);
                gameState.setText(e.getMessage() + ". Try Again!");
                    /*Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();*/

            }
        }
        return true;
    }






public Board moveFunc(Board board,String move) throws Exception {
    MoveController moveController=new MoveController();
    String oldBeadConfig = board.getBeadConfiguration();
    final MediaPlayer movingBar = MediaPlayer.create(this, R.raw.moving_bars_sound);
    movingBar.setOnCompletionListener(new OnCompletionListener());
    final MediaPlayer gameOver = MediaPlayer.create(this, R.raw.game_over_sound);
    gameOver.setOnCompletionListener(new OnCompletionListener());
    final MediaPlayer wooden_fall = MediaPlayer.create(this, R.raw.wooden_fall);
    wooden_fall.setOnCompletionListener(new OnCompletionListener());
    String input = String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer()) + board.getPostionsOfHorizontalBars() + board.getPostionsOfVerticalBars() + board.getBeadConfiguration() + move;
    Log.e("input", input);
    board.setInput(input);
    board = moveController.moveTest(board);
    String newBeadConfig = board.getBeadConfiguration();
    boardImageSetter.setBoardImages(board, getApplicationContext(), this);

    barImageSetter.setBarImages(board, getApplicationContext(), this);
    if (!oldBeadConfig.equals(newBeadConfig)) {
        v.vibrate(500);
        if (soundSetting)
            wooden_fall.start();
    }
    if (soundSetting)
        movingBar.start();
    if (!String.valueOf(board.getWinner()).equals("0")) {
        v.vibrate(500);
        String[] players = new String[4];
        players[0] = player1name;
        players[1] = player2name;

        if (soundSetting)
            gameOver.start();
        dataBaseHelper.updateScore(board, players, this, continueMusic);
        winnerDecided = true;
        finish();

    }
//    updateScores(board.getBeadConfiguration());
 //   updateTurn(board.getMovingPlayer());
    Log.e("newConfig", board.getOutput());

    return board;
}
    private class OnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.stop();
            mp.release();
        }
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
    private void updateTurn(int movingPlayer) {
/*        if (movingPlayer == 1) {
            player1tile.setImageResource(R.drawable.player1actv);
            player2tile.setImageResource(R.drawable.player2inactv);

        }
        if (movingPlayer == 2) {
            player1tile.setImageResource(R.drawable.player1inactv);
            player2tile.setImageResource(R.drawable.player2actv);

        }*/
    }
}

