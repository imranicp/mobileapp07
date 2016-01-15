package logic.main.com.boardgame.manager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import logic.main.com.boardgame.activity.GameOverActivity;
import logic.main.com.boardgame.core.Board;
import logic.main.com.boardgame.core.HistoryData;
import logic.main.com.boardgame.core.PlayerScore;

//the database Manager class which connects to the Db to execute queries
public class DatabaseManager {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "BOARDGAME";

    // table names
    private static final String TABLE_SCORE = "HIGH_SCORE";
    private static final String TABLE_MUSIC = "MUSIC_VALUES";
    private static final String TABLE_HISTORY = "GAME_HISTORY";

    //player column keys
    private static final String KEY_PLAYER1 = "PLAYER1";
    private static final String KEY_PLAYER2 = "PLAYER2";
    private static final String KEY_PLAYER3 = "PLAYER3";
    private static final String KEY_PLAYER4 = "PLAYER4";
    private static final String KEY_WINNER = "WINNER";

    // Table Columns names
    private static final String KEY_ID = "_id";

    private static final String KEY_NAME = "PLAYER_NAME";
    private static final String KEY_SCORE = "SCORE";
    private static final String KEY_SETTING = "SETTING";
    private static final String KEY_VALUE = "VALUE";

    //these variables are used to initialize the database manager class
    private final Activity mActivity;

    //the object of the inner database helper class which is responsible for all the interactions with the database
    private DataBaseHelper mDbHelper;

    //the database object
    private SQLiteDatabase db;

    //constructor of the manager which sets all the required values to query on the db
    public DatabaseManager(Activity activity) {

        //setting the activity context to the activity which invokes the DB manager
        this.mActivity = activity;

        //setting the core DB Helper class for the supplied activity
        mDbHelper = this.new DataBaseHelper(activity);

        //getting the databases which are stored in the DB Helper
        db = mDbHelper.getWritableDatabase();
    }

    //CLOSING the database
    public void close() {
        mDbHelper.close();
    }

    // Updating score
    //when the winner is decided the score of the winner must be updated
    // in this function first we check whether the player is already existing in the database or not
    //if the player is not in the database then first we insert a record corresponding to that player
    //then the score of the player is updated
    public void updateScore(Board board, String[] players, Activity mainActivity, boolean continueMusic) {

        //the winner for whom the score has to be updated
        String winner = "";

        // the value of i is used to get the winning player number from the board object
        int i = 1;

        //players array will have all the players name
        for (String player : players) {

            //if the player value is not null, we check whether it is the winner or not
            if (player != null) {

                //if the value is equal to i the i-1 position in the players array will be the winner
                if (board.getWinner() == i) {

                    //setting the winner value
                    winner = players[i - 1];

                }

                //check whether the particular player is there in the db or not, if not then a record is added
                if (!checkIsDataAlreadyInDBorNot(player)) {

                    //setting the values for the player which is to added to DB
                    ContentValues values = new ContentValues();

                    //name of the player
                    values.put(KEY_NAME, player);

                    //initially the score is set to zero
                    values.put(KEY_SCORE, 0);

                    //inserting the value into the table
                    db.insert(TABLE_SCORE, null, values);
                }
                //incrementing i value to get the next player
                i++;
            }
        }

        //the history table is updated with names of the players who played the game
        // and also the name of the player who won the game
        //Update History
        ContentValues values = new ContentValues();

        //player 1 name
        values.put(KEY_PLAYER1, players[0]);

        //player 2 name
        values.put(KEY_PLAYER2, players[1]);

        //player 3 name
        values.put(KEY_PLAYER3, players[2]);

        //player 4 name
        values.put(KEY_PLAYER4, players[3]);

        //winner name
        values.put(KEY_WINNER, winner);

        //inserting the value in the database
        db.insert(TABLE_HISTORY, null, values);

        //Update scores
        //this query increases the value of the score by 1 of the winner
        String countQuery = "UPDATE " + TABLE_SCORE + " SET " + KEY_SCORE + "=SCORE + 1" + " WHERE " + KEY_NAME + " = '" + winner + "'";

        Log.e("updateQuery", countQuery);

        //for an update query a cursor has to be moved to first and then closed
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        cursor.close();

        //as the game has finished , the game over screen must be triggered with winner names
        //and also showing the scores of the players who played the round
        Intent intent = new Intent(mainActivity.getApplicationContext(), GameOverActivity.class);

        //adding the winner name
        intent.putExtra(KEY_WINNER, winner);

        //adding the player 1 name
        intent.putExtra(KEY_PLAYER1, players[0]);

        //adding the player 2 name
        intent.putExtra(KEY_PLAYER2, players[1]);

        //adding the player 3 name
        intent.putExtra(KEY_PLAYER3, players[2]);

        //adding the player 4 name
        intent.putExtra(KEY_PLAYER4, players[3]);

        //adding the continue music variable value which holds whether the music is on or off
        intent.putExtra("continueMusic", continueMusic);

        Log.e("winner", winner);

        //starting the game over activity
        mainActivity.startActivity(intent);

    }

    //this function checks whether the player name is already there in database or not
    public boolean checkIsDataAlreadyInDBorNot(String playerName) {

        //the query take the name of the player as parameter
        String Query = "Select * from " + TABLE_SCORE + " where " + KEY_NAME + " = '" + playerName + "'";

        //A cursor is created to check whether the query returns any record or not
        Cursor cursor = db.rawQuery(Query, null);

        //if the number of records is less than or equal to 0 which means that the player is not in the DB
        //return false on the situation
        if (cursor.getCount() <= 0) {

            //closing the cursor
            cursor.close();

            //returning false
            return false;
        }

        //in case the person is there in the DB then true is returned after
        //closing the cursor
        cursor.close();

        //returning true
        return true;
    }

    //this function is used to return the history of games which are played
    //history data is a pojo which holds the data of the games which were played
    //a list of values history data is returned
    public List<HistoryData> retrieveHistoryData() {

        //the list which will hold the data which is retrieved
        List<HistoryData> historyData = new ArrayList<HistoryData>();

        //query to select all the rows in the history table
        String countQuery = "SELECT * FROM " + TABLE_HISTORY + " ORDER BY " + KEY_ID + " DESC;";

        //getting the cursor which will hold the records
        Cursor cursor = db.rawQuery(countQuery, null);

        //moving to the first record
        if (cursor.moveToFirst()) {
            do {

                //the value in column 1
                String column1 = cursor.getString(1);

                //the value in column 2
                String column2 = cursor.getString(2);

                //the value in column 3
                String column3 = cursor.getString(3);

                //the value in column 4
                String column4 = cursor.getString(4);

                //the value in column 5
                String column5 = cursor.getString(5);


                //creating the corresponding histroy object form the values
                HistoryData history = new HistoryData();

                //the value in column 1
                history.setPlayer1(column1);

                //the value in column 2
                history.setPlayer2(column2);

                //the value in column 3
                history.setPlayer3(column3);

                //the value in column 4
                history.setPlayer4(column4);

                //the value in column 5(which is the name of the winner)
                history.setWinner(column5);

                //adding the history data to the list of records
                historyData.add(history);

            } while (cursor.moveToNext());
        }

        // closing the cursor
        cursor.close();

        //returning the historydata list
        return historyData;
    }

    //this function is used to retrieve the scores of the players who have played the game
    //the results are arranged in descending order with the highest score on top
    public List<PlayerScore> retrieveScores() {

        //PlayerScore is a pojo which holds the player name and corresponding score
        List<PlayerScore> playerScores = new ArrayList<PlayerScore>();

        //query which is used to retrieve the scores
        String countQuery = "SELECT * FROM " + TABLE_SCORE + " ORDER BY " + KEY_SCORE + " DESC;";


        //cursor which holds the records from query execution
        Cursor cursor = db.rawQuery(countQuery, null);

        //moving to the first record
        if (cursor.moveToFirst()) {
            do {

                //the name of the player
                String column2 = cursor.getString(1);

                //the score of the player
                String column3 = cursor.getString(2);

                Log.e("column2", column2);
                Log.e("column3", column3);

                //the values are used to make playerScore object
                PlayerScore playerScore = new PlayerScore();

                //setting the name of the player
                playerScore.setName(column2);

                //setting the score of the player
                playerScore.setScore(Integer.parseInt(column3));

                //adding the playerScore object to the list of scores
                playerScores.add(playerScore);

            } while (cursor.moveToNext());
        }

        //closing the cursor
        cursor.close();

        //returning the list of player scores
        return playerScores;
    }

    //This functionality is used to give the suggestions to the autocomplete text view which is used at player name entry
    //when the player enters atleast 2 letters then
    // the suggestions for names are taken from the databases and provided to the user
    // Getting Matching names
    public Cursor getMatchingNames(String constraint) throws SQLException {

        //query string string to find matching names
        String queryString =
                "SELECT _id, PLAYER_NAME FROM " + TABLE_SCORE;

        if (constraint != null) {
            // Query for any rows where the player name begins with the
            // string specified in constraint.

            constraint = constraint.trim() + "%";
            queryString += " WHERE PLAYER_NAME LIKE ?";
        }
        //parameters for the query
        String params[] = {constraint};

        if (constraint == null) {
            // If no parameters are used in the query,
            // the params arg must be null.
            params = null;
        }
        try {

            //cursor which holds the results of the query
            Cursor cursor = db.rawQuery(queryString, params);
            if (cursor != null) {

                //if the cursor holds some values then the activity is called to start managing the cursor,
                //as soon as the values are retrieved from the database those will be shown as suggestion to the user
                //using this function call subsequent calls are made
                this.mActivity.startManagingCursor(cursor);

                //moving the cursor to first record
                cursor.moveToFirst();

                //returning the cursor object
                return cursor;
            }

        } catch (SQLException e) {
            Log.e("DbAdapter", e.toString());
            //throwing any exceptions which occur
            throw e;
        }

        //a null cursor is returned if there are no parameters set
        return null;
    }

    //retireve latest score is called when the game is over and
    // the latest updated scores of the played must be displayed
    //this function uses the PlayerScore object which holds the player name and score
    public List<PlayerScore> retrieveLatestScores(List<String> playerList) {

        //the list of players with their scores
        List<PlayerScore> playerScores = new ArrayList<PlayerScore>();

        //the query to find the score of the players
        String countQuery = "SELECT * FROM " + TABLE_SCORE + " where ";

        //this loop is used to build the query string where every playerName is addded as a where clause seperated by ORs
        for (String playerName : playerList) {
            if (playerName != null) {
                countQuery = countQuery + " " + KEY_NAME + " = '" + playerName + "' OR";
            }

        }

        //this is done to the query string as the OR after the last player is not required
        countQuery = countQuery.substring(0, countQuery.length() - 2);

        //the order of the query is set to the score in descending order
        countQuery = countQuery + " ORDER BY " + KEY_SCORE + " DESC;";

        Log.e("countQuery", countQuery);

        //the cursor which holds the records, result from the query
        Cursor cursor = db.rawQuery(countQuery, null);

        //moving to the first record
        if (cursor.moveToFirst()) {
            do {

                //column 2 which holds the name
                String column2 = cursor.getString(1);

                //column 3 which holds the score
                String column3 = cursor.getString(2);

                Log.e("column2", column2);
                Log.e("column3", column3);

                //the player Score object is created which will hold the player and score
                PlayerScore playerScore = new PlayerScore();

                //setting the player name
                playerScore.setName(column2);

                //setting the player score
                playerScore.setScore(Integer.parseInt(column3));

                //adding the player pojo to the list of playerScores
                playerScores.add(playerScore);

            } while (cursor.moveToNext());
        }

        //closing the cursor
        cursor.close();

        //returning the player scores list
        return playerScores;
    }

    //this function is used to get music volume setting value which is stored in DB
    //it is either 1=ON or 0=OFF
    public boolean getMusicValue() {

        //query to get the setting value of the MUSIC
        String countQuery = "SELECT VALUE FROM " + TABLE_MUSIC + " where " + KEY_SETTING + "='MUSIC';";

        //result is by default false
        boolean result = false;

        //cursor which gets the record
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.moveToFirst()) {
            do {

                //the value to of the setting
                String column1 = cursor.getString(0);

                //if the value is 1 means that the setting is turned on
                //result should be true
                if (column1.equals("1")) {

                    //setting result as true
                    result = true;

                }
            } while (cursor.moveToNext());
        }

        //closing the cursor
        cursor.close();

        //returning the result
        return result;
    }

    //this function sets the music value in the DB
    //if the continueMusic value is set false then the sound setting must be set to 0
    //if the continueMusic value is set true then the sound setting must be set to 1
    public void setMusicValue(boolean continueMusic) {

        //if continue music is true
        if (continueMusic) {

            //update query to set the value to 1
            String countQuery = "UPDATE " + TABLE_MUSIC + " SET " + KEY_VALUE + "= '1'" + " where " + KEY_SETTING + "='MUSIC';";
            Log.e("updateQuery", countQuery);

            //cursor to execute the query
            Cursor cursor = db.rawQuery(countQuery, null);

            //moving to first for successful execution
            cursor.moveToFirst();

            //closing the cursor
            cursor.close();
        }

        //if continue music is flase
        else {

            //update query to set the value to 0
            String countQuery = "UPDATE " + TABLE_MUSIC + " SET " + KEY_VALUE + "= '0'" + " where " + KEY_SETTING + "='MUSIC';";

            Log.e("updateQuery", countQuery);

            //cursor to execute the query
            Cursor cursor = db.rawQuery(countQuery, null);

            //moving to first for successful execution
            cursor.moveToFirst();

            //closing the cursor
            cursor.close();
        }
    }

    //this function is used to get sound volume setting value which is stored in DB
    //it is either 1=ON or 0=OFF
    public boolean getSoundValue() {

        //query to get the setting value of the MUSIC
        String countQuery = "SELECT VALUE FROM " + TABLE_MUSIC + " where " + KEY_SETTING + "='SOUND';";

        //result is by default false
        boolean result = false;

        //cursor which gets the record
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.moveToFirst()) {
            do {

                //the value to of the setting
                String column1 = cursor.getString(0);

                //if the value is 1 means that the setting is turned on
                //result should be true
                if (column1.equals("1")) {

                    //setting result as true
                    result = true;
                }
            } while (cursor.moveToNext());
        }

        //closing the cursor
        cursor.close();

        //returning the result
        return result;
    }

    //this function sets the sound value in the DB
    //if the soundSetting value is set false then the sound setting must be set to 0
    //if the soundSetting value is set true then the sound setting must be set to 1
    public void setSoundValue(boolean soundSetting) {

        //if soundSetting is true
        if (soundSetting) {

            //update query to set the value to 1
            String countQuery = "UPDATE " + TABLE_MUSIC + " SET " + KEY_VALUE + "= '1'" + " where " + KEY_SETTING + "='SOUND';";

            Log.e("updateQuery", countQuery);

            //cursor to execute the query
            Cursor cursor = db.rawQuery(countQuery, null);

            //moving to first for successful execution
            cursor.moveToFirst();

            //closing the cursor
            cursor.close();
        } else {
            //update query to set the value to 1
            String countQuery = "UPDATE " + TABLE_MUSIC + " SET " + KEY_VALUE + "= '0'" + " where " + KEY_SETTING + "='SOUND';";

            Log.e("updateQuery", countQuery);

            //cursor to execute the query
            Cursor cursor = db.rawQuery(countQuery, null);

            //moving to first for successful execution
            cursor.moveToFirst();

            //closing the cursor
            cursor.close();
        }
    }

    //this is the core inner class of the database manager which holds the on create and on upgrade funnctions
    public class DataBaseHelper extends SQLiteOpenHelper {

        //DB Helper is initialized for the supplied context
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        // Creating Tables on first install of the game
        @Override
        public void onCreate(SQLiteDatabase db) {

            //database query execution for the scores table
            String CREATE_TABLE_SCORE = "CREATE TABLE " + TABLE_SCORE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_SCORE + " INTEGER" + ")";
            db.execSQL(CREATE_TABLE_SCORE);

            //database query execution for the history of games table
            String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PLAYER1 + " TEXT," + KEY_PLAYER2 +
                    " TEXT," + KEY_PLAYER3 + " TEXT," + KEY_PLAYER4 + " TEXT,"
                    + KEY_WINNER + " TEXT" + ")";

            db.execSQL(CREATE_TABLE_HISTORY);

            //database query execution for the sound and music preferences table
            String CREATE_TABLE_MUSIC = "CREATE TABLE IF NOT EXISTS " + TABLE_MUSIC + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SETTING + " TEXT," + KEY_VALUE + " TEXT" + ")";
            db.execSQL(CREATE_TABLE_MUSIC);

            //adding the default and the only values which are present in the music preferences table
            //value 1 for a row means that the music or sound is on correspondingly
            //these values are updated accordingly when a setting is changed in the preferences
            ContentValues values = new ContentValues();
            values.put(KEY_SETTING, "MUSIC");
            values.put(KEY_VALUE, "1");
            db.insert(TABLE_MUSIC, null, values);

            ContentValues values1 = new ContentValues();
            values1.put(KEY_SETTING, "SOUND");
            values1.put(KEY_VALUE, "1");
            db.insert(TABLE_MUSIC, null, values1);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //On Upgrade of database in a game in which the version of the database
            // is lower than the current version.
            //all the tables are created

            //database query execution for the scores table
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SCORE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_SCORE + " INTEGER" + ")");

            //database query execution for the history of games table
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PLAYER1 + " TEXT," + KEY_PLAYER2 +
                    " TEXT," + KEY_PLAYER3 + " TEXT," + KEY_PLAYER4 + " TEXT,"
                    + KEY_WINNER + " TEXT" + ")");

            //database query execution for the sound and music preferences table
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_MUSIC + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SETTING + " TEXT," + KEY_VALUE + " TEXT" + ")");



        }
    }


}
