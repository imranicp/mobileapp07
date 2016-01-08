package logic.main.com.boardgame;
;import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pawan on 06-01-2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BOARDGAME";

    // Contacts table name
    private static final String TABLE_SCORE = "HIGH_SCORE";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "PLAYER_NAME";
    private static final String KEY_SCORE = "SCORE";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,"PLAYER1"); // NAME
        values.put(KEY_SCORE,1 ); // SCORE

        // Inserting Row
        db.insert(TABLE_SCORE, null, values);

        values.put(KEY_NAME,"PLAYER2"); // NAME
        values.put(KEY_SCORE,0 ); // SCORE

        // Inserting Row
        db.insert(TABLE_SCORE, null, values);

        values.put(KEY_NAME,"PLAYER3"); // NAME
        values.put(KEY_SCORE,0 ); // SCORE

        // Inserting Row
        db.insert(TABLE_SCORE, null, values);

        values.put(KEY_NAME,"PLAYER4"); // NAME
        values.put(KEY_SCORE,0 ); // SCORE

        // Inserting Row
        db.insert(TABLE_SCORE, null, values);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SCORE);

        // Create tables again
        onCreate(db);
    }

    // Updating single contact
    public int updateScore(Board board) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT " + KEY_SCORE + " FROM " + TABLE_SCORE + " WHERE " + KEY_NAME + " = PLAYER" + board.getWinner();
        Cursor cursor = db.rawQuery(countQuery, null);
        String column1="";
        if(cursor.moveToFirst()) {
            do {
                //assing values


                 column1 = cursor.getString(1);
                //Do something Here with values

            }while(cursor.moveToNext());
        }
        cursor.close();

        int updateScore = Integer.parseInt(column1) + 1;

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, updateScore);
        // updating row
        return db.update(TABLE_SCORE, values, KEY_NAME + " = " + board.getWinner(),null);
    }

    // Getting Scores

    public Database[] retriveScores() {

       Database[] database = new Database[4];
        int i=0;




        String countQuery = "SELECT * FROM " + TABLE_SCORE + " ORDER BY " + KEY_SCORE +" DESC;"  ;
    //    String countQuery = "SELECT"+ KEY_NAME +","+KEY_SCORE +" FROM " + TABLE_SCORE + " ORDER BY " + KEY_SCORE +" DESC;"  ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()){
            do{
                //assing values

                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                //Do something Here with values
                database[i].setName(column2);
                database[i].setScore(Integer.parseInt(column3));
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return database;
    }



}
