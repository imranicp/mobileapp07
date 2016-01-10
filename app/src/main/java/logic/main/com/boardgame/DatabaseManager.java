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

public class DatabaseManager {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BOARDGAME";

    // Contacts table name
    private static final String TABLE_SCORE = "HIGH_SCORE";

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


        String countQuery = "UPDATE " + TABLE_SCORE + " SET " + KEY_SCORE + "=SCORE + 1" + " WHERE " + KEY_NAME + " = '" + winner + "'";
        Log.e("updateQuery", countQuery);
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        cursor.close();
        Intent intent = new Intent(mainActivity.getApplicationContext(), GameOverActivity.class);
        intent.putExtra("winner", winner);
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



    public PlayerScore[] retrieveScores() {

        PlayerScore[] playerScore = new PlayerScore[50];
        int i = 0;

        String countQuery = "SELECT * FROM " + TABLE_SCORE + " ORDER BY " + KEY_SCORE + " DESC;";


        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                Log.e("column2", column2);
                Log.e("column3", column3);
                //Do something Here with values
                playerScore[i] = new PlayerScore();
                playerScore[i].setName(column2);
                playerScore[i].setScore(Integer.parseInt(column3));
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return playerScore;
    }

    // Getting Scores

    public Cursor getMatchingStates(String constraint) throws SQLException {

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


            ContentValues values = new ContentValues();
            values.put(KEY_NAME, "PLAYER1"); // NAME
            values.put(KEY_SCORE, 1); // SCORE

            // Inserting Row
            db.insert(TABLE_SCORE, null, values);

            values.put(KEY_NAME, "PLAYER2"); // NAME
            values.put(KEY_SCORE, 0); // SCORE

            // Inserting Row
            db.insert(TABLE_SCORE, null, values);

            values.put(KEY_NAME, "PLAYER3"); // NAME
            values.put(KEY_SCORE, 0); // SCORE

            // Inserting Row
            db.insert(TABLE_SCORE, null, values);

            values.put(KEY_NAME, "PLAYER4"); // NAME
            values.put(KEY_SCORE, 0); // SCORE

            // Inserting Row
            db.insert(TABLE_SCORE, null, values);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SCORE);

            // Create tables again
            onCreate(db);
        }
    }


}
