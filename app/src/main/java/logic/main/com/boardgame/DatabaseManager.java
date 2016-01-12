package logic.main.com.boardgame;

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

public class DatabaseManager {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "BOARDGAME";

    // table name
    private static final String TABLE_SCORE = "HIGH_SCORE";

    private static final String TABLE_HISTORY = "GAME_HISTORY";

    private static final String KEY_PLAYER1 = "PLAYER1";
    private static final String KEY_PLAYER2 = "PLAYER2";
    private static final String KEY_PLAYER3 = "PLAYER3";
    private static final String KEY_PLAYER4 = "PLAYER4";
    private static final String KEY_WINNER = "WINNER";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "PLAYER_NAME";
    private static final String KEY_SCORE = "SCORE";
    private final Activity mActivity;
    private DataBaseHelper mDbHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Activity activity) {
        this.mActivity = activity;
        mDbHelper = this.new DataBaseHelper(activity);
        db = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    // Updating score
    public void updateScore(Board board, String[] players, Activity mainActivity) {
        String winner = "";
        int i = 1;
        for (String player : players) {
            if (player != null) {
                if (board.getWinner() == i) {
                    winner = players[i - 1];
                }

                if (!checkIsDataAlreadyInDBorNot(player)) {
                    ContentValues values = new ContentValues();
                    values.put(KEY_NAME, player);
                    values.put(KEY_SCORE, 0);
                    db.insert(TABLE_SCORE, null, values);
                }
                i++;
            }
        }
        //Update History
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER1, players[0]);
        values.put(KEY_PLAYER2, players[1]);
        values.put(KEY_PLAYER3, players[2]);
        values.put(KEY_PLAYER4, players[3]);
        values.put(KEY_WINNER, winner);
        db.insert(TABLE_HISTORY, null, values);

        //Update scores
        String countQuery = "UPDATE " + TABLE_SCORE + " SET " + KEY_SCORE + "=SCORE + 1" + " WHERE " + KEY_NAME + " = '" + winner + "'";
        Log.e("updateQuery", countQuery);
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        cursor.close();
        Intent intent = new Intent(mainActivity.getApplicationContext(), GameOverActivity.class);
        intent.putExtra(KEY_WINNER, winner);
        intent.putExtra(KEY_PLAYER1, players[0]);
        intent.putExtra(KEY_PLAYER2, players[1]);
        intent.putExtra(KEY_PLAYER3, players[2]);
        intent.putExtra(KEY_PLAYER4, players[3]);
        Log.e("winner", winner);
        mainActivity.startActivity(intent);

    }


    public boolean checkIsDataAlreadyInDBorNot(String playerName) {

        String Query = "Select * from " + TABLE_SCORE + " where " + KEY_NAME + " = '" + playerName + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<HistoryData> retrieveHistoryData() {
        List<HistoryData> historyData = new ArrayList<HistoryData>();
        String countQuery = "SELECT * FROM " + TABLE_HISTORY + " ORDER BY " + KEY_ID + " DESC;";
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String column1 = cursor.getString(1);
                String column2 = cursor.getString(2);
                String column3 = cursor.getString(3);
                String column4 = cursor.getString(4);
                String column5 = cursor.getString(5);


                HistoryData history = new HistoryData();
                history.setPlayer1(column1);
                history.setPlayer2(column2);
                history.setPlayer3(column3);
                history.setPlayer4(column4);
                history.setWinner(column5);
                historyData.add(history);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return historyData;
    }


    public List<PlayerScore> retrieveScores() {

        List<PlayerScore> playerScores = new ArrayList<PlayerScore>();

        String countQuery = "SELECT * FROM " + TABLE_SCORE + " ORDER BY " + KEY_SCORE + " DESC;";


        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                Log.e("column2", column2);
                Log.e("column3", column3);
                //Do something Here with values
                PlayerScore playerScore = new PlayerScore();
                playerScore.setName(column2);
                playerScore.setScore(Integer.parseInt(column3));
                playerScores.add(playerScore);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return playerScores;
    }

    // Getting Scores
    public Cursor getMatchingNames(String constraint) throws SQLException {

        String queryString =
                "SELECT _id, PLAYER_NAME FROM " + TABLE_SCORE;

        if (constraint != null) {
            // Query for any rows where the state name begins with the
            // string specified in constraint.
            //
            // NOTE:
            // If wildcards are to be used in a rawQuery, they must appear
            // in the query parameters, and not in the query string proper.
            // See http://code.google.com/p/android/issues/detail?id=3153
            constraint = constraint.trim() + "%";
            queryString += " WHERE PLAYER_NAME LIKE ?";
        }
        String params[] = {constraint};

        if (constraint == null) {
            // If no parameters are used in the query,
            // the params arg must be null.
            params = null;
        }
        try {
            Cursor cursor = db.rawQuery(queryString, params);
            if (cursor != null) {
                this.mActivity.startManagingCursor(cursor);
                cursor.moveToFirst();

                return cursor;
            }
        } catch (SQLException e) {
            Log.e("DbAdapter", e.toString());
            throw e;
        }

        return null;
    }

    public List<PlayerScore> retrieveLatestScores(List<String> playerList) {
        List<PlayerScore> playerScores = new ArrayList<PlayerScore>();
        String countQuery = "SELECT * FROM " + TABLE_SCORE + " where ";


        for (String playerName : playerList) {
            if (playerName != null) {
                countQuery = countQuery + " " + KEY_NAME + " = '" + playerName + "' OR";
            }

        }
        countQuery = countQuery.substring(0, countQuery.length() - 2);
        countQuery = countQuery + " ORDER BY " + KEY_SCORE + " DESC;";

        Log.e("countQuery", countQuery);

        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                Log.e("column2", column2);
                Log.e("column3", column3);
                //Do something Here with values
                PlayerScore playerScore = new PlayerScore();
                playerScore.setName(column2);
                playerScore.setScore(Integer.parseInt(column3));
                playerScores.add(playerScore);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return playerScores;
    }

    public class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE_SCORE = "CREATE TABLE " + TABLE_SCORE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_SCORE + " INTEGER" + ")";
            db.execSQL(CREATE_TABLE_SCORE);

            String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PLAYER1 + " TEXT," + KEY_PLAYER2 +
                    " TEXT," + KEY_PLAYER3 + " TEXT," + KEY_PLAYER4 + " TEXT,"
                    + KEY_WINNER + " TEXT" + ")";
            db.execSQL(CREATE_TABLE_HISTORY);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SCORE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_SCORE + " INTEGER" + ")");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PLAYER1 + " TEXT," + KEY_PLAYER2 +
                    " TEXT," + KEY_PLAYER3 + " TEXT," + KEY_PLAYER4 + " TEXT,"
                    + KEY_WINNER + " TEXT" + ")");

        }
    }


}
