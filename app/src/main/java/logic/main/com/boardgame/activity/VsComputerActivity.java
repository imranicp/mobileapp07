package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

//this class implements the Human vs Computer game functionality
public class VsComputerActivity extends Activity implements View.OnTouchListener {

    //the minimum swipe distance which must be covered along either x-axis or y-axis
    //this variable will be used to determine the user gesture
    private static final int SWIPE_MIN_DISTANCE = 30;

    //the minimum threshold velocity with which the move must be
    // performed inorder for it to qualify as valid gesture
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    //the gesture detector class object
    GestureDetector gdt;

    //the motion gesture which is done by the user
    String flingType = "";

    //the vibrator class object
    Vibrator v;

    //the bead count variable which signifies the number of beads available for a 2 player game
    int beadCount2 = 10;

    //the count of available beads which can be placed on the board tiles
    int beadCount;

    //the board object on which will be used as a core to the game.
    //all representation will be set in the board object
    //the game literally is on the whole described by this object
    Board board = new Board();

    //the board image setter object which is used to set the images on the board accordingly
    BoardImageSetter boardImageSetter = new BoardImageSetter();

    //the bar image setter object which is used to set the images on the bar according to the bar positions
    BarImageSetter barImageSetter = new BarImageSetter();

    //bead placer is used to place a bead at the particular tile on which the user has placed the bead
    BeadPlacer beadPlacer = new BeadPlacer();

    //beadConfingSetter is used to generate the configuration of the beads on the board, according to bead placement on the board
    BeadConfingSetter beadConfingSetter = new BeadConfingSetter();

    //image view object which is used to set images to resources
    ImageView imageView;


    boolean winnerDecided = false;

    //names of the players which are entered in the previous activity
    String player1name, player2name;

    //database helper is used to query the database
    DatabaseManager dataBaseHelper;

    CustomFontTextView gameState;

    //vs computer core class
    VsComputer vsComputer;

    //the preference setting variables which are used for music and sound
    boolean continueMusic, soundSetting;

    //move generator is used to generate a move based on the motion event (gesture) of the user
    MoveGenerator moveGenerator=new MoveGenerator();

    //the text view which is used to show the player score
    CustomTextView player1txtscr, player2txtscr, player3txtscr, player4txtscr;

    //the image tile of the players, these images are toggled according to the moving player
    ImageView player1tile, player2tile, player3tile, player4tile;

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

        //vs computer core class initialization
        vsComputer = new VsComputer();

        //setting the game layout
        setContentView(R.layout.game_layout);

        //initializing the datebase helper
        dataBaseHelper = new DatabaseManager(this);

        //initializing the gesture detector object using a Gesture Listener class
        //this gesture listener class uses the onFling function to determine
        // the type of gesture/motion event which is performed
        gdt = new GestureDetector(this, new GestureListener());

        //getting the sound setting preference which is saved in the database
        soundSetting = dataBaseHelper.getSoundValue();

        //initializing the vibrator object
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //board config generator generates the config of the board using the position of the horizontal and vertical bars
        BoardConf boardConf = new BoardConf();

        //getting the continueMusic value from the intent extra
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        //getting the numberOfPlayers which are going to play from the intent extra
        int value = getIntent().getExtras().getInt("numberOfPlayers");

        //setting the number of players in the board object
        board.setNumberOfPlayers(value);

        //setting the player who starts performing the move to player 1
        board.setMovingPlayer(1);

        //initializing the game state with the resource id
        //game state shows the messages
        gameState = (CustomFontTextView) findViewById(R.id.gamestate);


        //the TextView which shows the player 1 name
        CustomTextView player1_name = (CustomTextView) findViewById(R.id.player1text);

        //the TextView which shows the player 2 name
        CustomTextView player2_name = (CustomTextView) findViewById(R.id.player2text);

        //the TextView which shows the player 3 name
        CustomTextView player3_name = (CustomTextView) findViewById(R.id.player3text);

        //the TextView which shows the player 4 name
        CustomTextView player4_name = (CustomTextView) findViewById(R.id.player4text);

        //getting the name of player 1 from the intent extra
        player1name = "HUMAN";

        //setting the name of player 1 in the textview
        player1_name.setText(player1name);

        //getting the name of player 2 from the intent extra
        player2name = "BOT";

        //setting the name of player 2 in the textview
        player2_name.setText(player2name);

        //initializing the score displayer for player 1
        player1txtscr = (CustomTextView) findViewById(R.id.player1scoredisplay);

        //initializing the score displayer for player 2
        player2txtscr = (CustomTextView) findViewById(R.id.player2scoredisplay);

        //initializing the score displayer for player 3
        player3txtscr = (CustomTextView) findViewById(R.id.player3scoredisplay);

        //initializing the score displayer for player 4
        player4txtscr = (CustomTextView) findViewById(R.id.player4scoredisplay);

        //the image of the player 1 tile
        player1tile = (ImageView) findViewById(R.id.player1tile);

        //the image of the player 2 tile
        player2tile = (ImageView) findViewById(R.id.player2tile);

        player2tile.setImageResource(R.drawable.aiinactive);

        //the image of the player 3 tile
        player3tile = (ImageView) findViewById(R.id.player3tile);

        //the image of the player 4 tile
        player4tile = (ImageView) findViewById(R.id.player4tile);


        //images of the player 1 and 2 scores are by default visible
        //as there will be minimum 2 players

        //the image of the player 3 score display
        ImageView player3score = (ImageView) findViewById(R.id.player3score);

        //setting all player 3 related views to invisible
        player3tile.setVisibility(View.INVISIBLE);
        player3_name.setVisibility(View.INVISIBLE);
        player3score.setVisibility(View.INVISIBLE);
        player3txtscr.setVisibility(View.INVISIBLE);

        //the image of the player 4 score display
        ImageView player4score = (ImageView) findViewById(R.id.player4score);

        //setting all player 4 related views to invisible
        player4tile.setVisibility(View.INVISIBLE);
        player4_name.setVisibility(View.INVISIBLE);
        player4score.setVisibility(View.INVISIBLE);
        player4txtscr.setVisibility(View.INVISIBLE);
        //setting score of player 1
        player1txtscr.setText("5");

        //setting score of player 2
        player2txtscr.setText("5");

        gameState = (CustomFontTextView) findViewById(R.id.gamestate);

        BarConfigGenerator barConfigGenerator = new BarConfigGenerator();
        board = barConfigGenerator.generateBarConfig(board);

        board.setBoardConfiguration(boardConf.boardConfGenerator(
                board.getPostionsOfHorizontalBars(),
                board.getPostionsOfVerticalBars()));

        boardImageSetter.setBoardImages(board, getApplicationContext(), this);
        barImageSetter.setBarImages(board, getApplicationContext(), this);

    }


    //onTouch function is the function which determines which move is performed on the board
    //it understands the motionEvent and the view which is touched on the screen
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //when a touch operation is performed the view which is touched
        // it's id will be coming as a parameter to the onTouch event

        //setting the values for the various sounds which are produced due to motion events

        //setting moving bar sound
        final MediaPlayer movingBar = MediaPlayer.create(this, R.raw.moving_bars_sound);

        //setting the on Completion Listener for the media player
        movingBar.setOnCompletionListener(new OnCompletionListener());

        //setting placing bead sound
        final MediaPlayer placingBead = MediaPlayer.create(this, R.raw.placing_bead_sound);

        //setting the on Completion Listener for the media player
        placingBead.setOnCompletionListener(new OnCompletionListener());

        //setting invalid move sound
        final MediaPlayer invalidMove = MediaPlayer.create(this, R.raw.invalid_move_sound);

        //setting the on Completion Listener for the media player
        invalidMove.setOnCompletionListener(new OnCompletionListener());

        //setting gameOver sound
        final MediaPlayer gameOver = MediaPlayer.create(this, R.raw.game_over_sound);

        //setting the on Completion Listener for the media player
        gameOver.setOnCompletionListener(new OnCompletionListener());

        //setting bead fall sound
        final MediaPlayer wooden_fall = MediaPlayer.create(this, R.raw.wooden_fall);

        //setting the on Completion Listener for the media player
        wooden_fall.setOnCompletionListener(new OnCompletionListener());

        // getting the resources which are attached to the view
        Resources res = view.getResources();

        //this is a reverse call which is used to get the resource entry name of the using its id
        String idString = res.getResourceEntryName(view.getId());

        //get the tag which is associated with the view
        String tag = (String) view.getTag();


        //the following occurs only when there are beads left to be placed on the board and the winner is not decided


        //User places beads
        //if the number of players is 2 and the bead count for a 2 player game is not 0 which means a bead can be placed
        if (board.getNumberOfPlayers() == 2 && !(beadCount2 == 0) && !winnerDecided) {

            //get the moving player number
            int movingPlayer = board.getMovingPlayer();


            if (movingPlayer == 1) {

                //calling the place bead function which takes the id of the view on which the touch even happens
                //this function also decrements the count of bead left on a successful placement
                beadCount2 = beadPlacer.placeBeads(beadCount2, tag, view.getId(), board, this);

                //updating the bead count
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

            //if the move has been performed means the turn has to be updated and
            // moving player should be the next eligible player
            if (movingPlayer != board.getMovingPlayer()) {
                //if the soundSetting is true: play the sound
                if (soundSetting)
                    placingBead.start();

                //update the player who must play next
                updateTurn(board.getMovingPlayer());

                //vibrate
                v.vibrate(300);
            }

            //when all beads are placed then the board is ready for the moves to be performed
            if (beadCount == 0)
                //setting game state
                gameState.setText("Perform move");

        }

        //trigger the gesture detector for the onFling gesture detection
        gdt.onTouchEvent(event);

        //kinds of flings
        //topToBottom,bottomToTop : these two set of gestures will performed on vertical bars
        // rightToLeft,leftToRight: these two set of gestures will performed on horizontal bars

        //if there is any kind of valid fling performed after all bead are placed and the winner is not decided yet
        //then it can possibly be a  move on the board
        if ((flingType.equals("topToBottom") || flingType.equals("bottomToTop") ||
                flingType.equals("rightToLeft") || flingType.equals("leftToRight")) && beadCount == 0 && !winnerDecided) {

            Log.e("flingid", idString);

            Log.e("flingtag", (String) view.getTag());

            //first time when a fling is performed we need to predetermine the positions of the bead
            //the bead config must be set taking into consideration all
            // the beads which were placed on the board by the players
            beadConfingSetter.setBeadConfig(board, getApplicationContext(), this);

            //the move generator generates a move according to the fling type which is detected on a valid bar
            //it basically compare the type of bar and the type of fling which is performed, thus generating a possible move
            String move = moveGenerator.generateMove(view, flingType);

            //if the generated move is avalid move
            if (!move.equals("")) {

                try {

                    //setting the game text
                    gameState.setText("Perform move");

                    //resetting the fling type value
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

                    String move = vsComputer.computermove(board);

                    gameState.setText(move + " performed by BOT. Your turn");

                    board = moveFunc(board, move);
                    /*Log.e("test i am in 2:", String.valueOf(board.getMovingPlayer()));


                     Log.e("test move1: ", move1);
                   */


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


    final MediaPlayer movingBar = MediaPlayer.create(this, R.raw.moving_bars_sound);
    movingBar.setOnCompletionListener(new OnCompletionListener());
    final MediaPlayer gameOver = MediaPlayer.create(this, R.raw.game_over_sound);
    gameOver.setOnCompletionListener(new OnCompletionListener());
    final MediaPlayer wooden_fall = MediaPlayer.create(this, R.raw.wooden_fall);
    wooden_fall.setOnCompletionListener(new OnCompletionListener());

    MoveController moveController = new MoveController();
    //getting the old bead configuration before a move is performed
    //this is required to check whether on a successful move performed a bead falls or not
    String oldBeadConfig = board.getBeadConfiguration();

    //the input is constucrted taking into consideration all the parts of the board required to perform a move
    String input = String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer()) +
            board.getPostionsOfHorizontalBars() + board.getPostionsOfVerticalBars() + board.getBeadConfiguration()
            + move;

    Log.e("input", input);

    //setting the input to the board object
    board.setInput(input);

    //performing the move using the moveTest function
    //this function can throw the invalid moves as excepiton which are collected as messages for users
    board = moveController.moveTest(board);

    //after a successful move the new bead configuration is taken into consideration
    String newBeadConfig = board.getBeadConfiguration();

    //as the move is successful the images of the tiles on the screen are redrawn according to the condition after the
    // move is performed
    boardImageSetter.setBoardImages(board, getApplicationContext(), this);

    //as the move is successful the images of the bar on the screen are redrawn according to the condition after the
    // move is performed
    barImageSetter.setBarImages(board, getApplicationContext(), this);

    //the old bead config and new bead configs are matched to check whether a bead has fallen or not
    //if a bead falls
    if (!oldBeadConfig.equals(newBeadConfig)) {
        //vibrate
        v.vibrate(500);
        if (soundSetting)
            //if the soundSetting is true: play the sound
            wooden_fall.start();
    }

    if (soundSetting)
        //if the soundSetting is true: play the moving bar sound as the move is successful
        movingBar.start();

    //when there is a winner in the game the winner variable in the board object will be either 1,2,3,4
    //means the value will not be 0 anymore
    //this means the game has ended
    if (!String.valueOf(board.getWinner()).equals("0")) {

        //vibrate
        v.vibrate(500);

        //take the names of all the players who were playing the game
        //these names are taken to add in the history of game
        String[] players = new String[4];

        //add player 1 name
        players[0] = player1name;

        //add player 2 name
        players[1] = player2name;

        if (soundSetting)
            //if the soundSetting is true: play game over sound
            gameOver.start();

        //update the score in database and save the history of game
        //upon updation the gameOver activity is triggered
        dataBaseHelper.updateScore(board, players, this, continueMusic);

        //setting the winner decided value as true
        winnerDecided = true;

        //finishing the activity
        finish();

    }

    //upon successful moves some bead might fall which means the scores in the score panel must be updated
    updateScores(board.getBeadConfiguration());

    //the turn must be passed on to the next eligible player
    updateTurn(board.getMovingPlayer());

    Log.e("newConfig", board.getOutput());

    return board;
}

    //this function updates the score value on the screen by counting the bead for a particular player in the bead configuration
    private void updateScores(String beadConfiguration) {

        //in this function we just check what is difference is the length
        // of the strings if the bead number of a particular player is subtracted
        //this will give us the player's score

        //setting player 1 score
        player1txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("1", "").length()));

        //setting player 2 score
        player2txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("2", "").length()));

        //setting player 3 score
        player3txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("3", "").length()));

        //setting player 4 score
        player4txtscr.setText(String.valueOf(beadConfiguration.length() - beadConfiguration.replace("4", "").length()));

    }

    //the bot randomly places a bead at the available positions on the board
    //the bot first determines whether the tile has a possibility of placing a bead or not
    //once he has determined all the available positions then it chooses a random position from the list of available position
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

    private void updateTurn(int movingPlayer) {
        if (movingPlayer == 1) {
            player1tile.setImageResource(R.drawable.player1actv);
            player2tile.setImageResource(R.drawable.aiinactive);

        }
        if (movingPlayer == 2) {
            player1tile.setImageResource(R.drawable.player1inactv);
            player2tile.setImageResource(R.drawable.aiactive);

        }
    }

    //the onPause functionality of every activity implements that
    //when the home button is pressed or when the activity goes in background then
    //the music playback must be paused.
    @Override
    protected void onPause() {
        super.onPause();

        //setting continueMusic to false
        continueMusic = false;

        //calling music manager to pause the music
        MusicManager.pause();


    }

    //the onResume functionality of every activity implements that
    //when the activity resumes or when the activity comes in foreground then
    //the music playback must be resumed if the value for continueMusic is true,
    // or else it should remain false.
    @Override
    protected void onResume() {
        super.onResume();

        //the value for continueMusic is retrieved from the database
        //as preferences are saved in the database
        continueMusic = dataBaseHelper.getMusicValue();

        //checking if continue music is true or not
        //if continue music is true then the music must continue playing
        if (continueMusic) {

            //calling music manager to start the music
            MusicManager.start(this, MusicManager.MUSIC_MENU);

        } else {

            //calling music manager to pause the music
            MusicManager.pause();

        }
    }

    //on back press event of the game if triggered when the back button is pressed
    //in this case we would like to ask the user whether quitting option should be triggered or not
    @Override
    public void onBackPressed() {
        //create menu activity intent in case of game quit
        final Intent intent = new Intent(this, MenuActivity.class);
        new AlertDialog.Builder(this)

                //title to be quit game
                .setTitle("Quit Game?")

                        //question whether to quit or not
                .setMessage("Are you sure you want to Quit?")

                        //on no press do nothing
                .setNegativeButton(android.R.string.no, null)

                        //on yes press finish the current activity
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {


                        finish();
                        //show the dialog
                    }
                }).create().show();
    }

    private class OnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.stop();
            mp.release();
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        //onFling function which determines the flingtype
        //a fling type is decided on the basis that when the finger moves on the screen
        // how much it have moved in the x direction and the y direction
        //the difference in the x or y valuesmust be greater than the minimum swiping distance
        //also the velocity with which the swipe is performed should be greater than the threshold velocity

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                //set flingtype to right to left as the movement is in negative x direction
                flingType = "rightToLeft";

            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                //set flingtype to left to right as the movement is in positive x direction
                flingType = "leftToRight";

            }

            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                //set flingtype to bottom to top as the movement is in positive y direction
                flingType = "bottomToTop";

            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                //set flingtype to top to bottom as the movement is in negative y direction
                flingType = "topToBottom";

            }

            return false;
        }
    }
}

