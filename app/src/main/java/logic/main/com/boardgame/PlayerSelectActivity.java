package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayerSelectActivity extends Activity {
    int value;
    boolean continueMusic;
    private AutoCompleteTextView player1_name;
    private AutoCompleteTextView player2_name;
    private AutoCompleteTextView player3_name;
    private AutoCompleteTextView player4_name;

    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_MENU);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_layout);

        player1_name = (AutoCompleteTextView) findViewById(R.id.player1_name);
        player2_name = (AutoCompleteTextView) findViewById(R.id.player2_name);
        player3_name = (AutoCompleteTextView) findViewById(R.id.player3_name);
        player4_name = (AutoCompleteTextView) findViewById(R.id.player4_name);

        DatabaseManager dataBaseHelper = new DatabaseManager(this);
        ItemAutoTextAdapter adapter = this.new ItemAutoTextAdapter(dataBaseHelper);
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        value = getIntent().getExtras().getInt("numberOfPlayers");

        player1_name.setAdapter(adapter);
        player1_name.setOnItemClickListener(adapter);

        player2_name.setAdapter(adapter);
        player2_name.setOnItemClickListener(adapter);

        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.player3_lay);
        linearLayout3.setVisibility(View.INVISIBLE);

        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.player4_lay);
        linearLayout4.setVisibility(View.INVISIBLE);

        if (value == 3) {
            player3_name.setAdapter(adapter);
            player3_name.setOnItemClickListener(adapter);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.INVISIBLE);
        }

        if (value == 4) {
            player3_name.setAdapter(adapter);
            player3_name.setOnItemClickListener(adapter);
            player4_name.setAdapter(adapter);
            player4_name.setOnItemClickListener(adapter);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GameTypeActivity.class);
        intent.putExtra("continueMusic", continueMusic);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        Intent newActivity = new Intent(this, MainActivity.class);

        String p1Name = player1_name.getText().toString();
        String p2Name = player2_name.getText().toString();
        String p3Name = player3_name.getText().toString();
        String p4Name = player4_name.getText().toString();

        switch (v.getId()) {
            case R.id.playButton:
                if (value == 2) {
                    p1Name = p1Name.replaceAll("\\s+", "");
                    p2Name = p2Name.replaceAll("\\s+", "");
                    if (!p1Name.equals("") && !p2Name.equals("")) {
                        newActivity.putExtra("numberOfPlayers", 2);
                        newActivity.putExtra("player1", p1Name);
                        newActivity.putExtra("player2", p2Name);
                        newActivity.putExtra("continueMusic", continueMusic);
                        startActivity(newActivity);
                        finish();
                    }
                }
                if (value == 3) {
                    p1Name = p1Name.replaceAll("\\s+", "");
                    p2Name = p2Name.replaceAll("\\s+", "");
                    p3Name = p3Name.replaceAll("\\s+", "");
                    if (!p1Name.equals("") && !p2Name.equals("") && !p3Name.equals("")) {
                        newActivity.putExtra("numberOfPlayers", 3);
                        newActivity.putExtra("player1", p1Name);
                        newActivity.putExtra("player2", p2Name);
                        newActivity.putExtra("player3", p3Name);
                        newActivity.putExtra("continueMusic", continueMusic);
                        startActivity(newActivity);
                        finish();
                    }
                }
                if (value == 4) {
                    p1Name = p1Name.replaceAll("\\s+", "");
                    p2Name = p2Name.replaceAll("\\s+", "");
                    p3Name = p3Name.replaceAll("\\s+", "");
                    p4Name = p4Name.replaceAll("\\s+", "");

                    if (!p1Name.equals("") && !p2Name.equals("") && !p3Name.equals("") && !p4Name.equals("")) {
                        newActivity.putExtra("numberOfPlayers", 4);
                        newActivity.putExtra("player1", p1Name);
                        newActivity.putExtra("player2", p2Name);
                        newActivity.putExtra("player3", p3Name);
                        newActivity.putExtra("player4", p4Name);
                        newActivity.putExtra("continueMusic", continueMusic);
                        startActivity(newActivity);
                        finish();
                    }
                }
                break;

            default:
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
        }
        this.finish();
    }

    class ItemAutoTextAdapter extends CursorAdapter
            implements android.widget.AdapterView.OnItemClickListener {

        private DatabaseManager mDbHelper;

        /**
         * Constructor. Note that no cursor is needed when we create the
         * adapter. Instead, cursors are created on demand when completions are
         * needed for the field. (see
         * {@link ItemAutoTextAdapter#runQueryOnBackgroundThread(CharSequence)}.)
         *
         * @param dbHelper The AutoCompleteDbAdapter in use by the outer class
         *                 object.
         */
        public ItemAutoTextAdapter(DatabaseManager dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(PlayerSelectActivity.this, null);
            mDbHelper = dbHelper;
        }

        /**
         * Invoked by the AutoCompleteTextView field to get completions for the
         * current input.
         * <p/>
         * NOTE: If this method either throws an exception or returns null, the
         * Filter class that invokes it will log an error with the traceback,
         * but otherwise ignore the problem. No choice list will be displayed.
         * Watch those error logs!
         *
         * @param constraint The input entered thus far. The resulting query will
         *                   search for states whose name begins with this string.
         * @return A Cursor that is positioned to the first row (if one exists)
         * and managed by the activity.
         */
        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }

            Cursor cursor = mDbHelper.getMatchingNames(
                    (constraint != null ? constraint.toString() : null));

            return cursor;
        }

        /**
         * Called by the AutoCompleteTextView field to get the text that will be
         * entered in the field after a choice has been made.
         *
         * @param cursor The cursor, positioned to a particular row in the list.
         * @return A String representing the row's text value. (Note that this
         * specializes the base class return value for this method,
         * which is {@link CharSequence}.)
         */
        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("PLAYER_NAME");
            final String str = cursor.getString(columnIndex);
            return str;
        }

        /**
         * Called by the ListView for the AutoCompleteTextView field to display
         * the text for a particular choice in the list.
         *
         * @param view    The TextView used by the ListView to display a particular
         *                choice.
         * @param context The context (Activity) to which this form belongs;
         *                equivalent to {@code SelectState.this}.
         * @param cursor  The cursor for the list of choices, positioned to a
         *                particular row.
         */
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final String text = convertToString(cursor);
            ((TextView) view).setText(text);
        }

        /**
         * Called by the AutoCompleteTextView field to display the text for a
         * particular choice in the list.
         *
         * @param context The context (Activity) to which this form belongs;
         *                equivalent to {@code SelectState.this}.
         * @param cursor  The cursor for the list of choices, positioned to a
         *                particular row.
         * @param parent  The ListView that contains the list of choices.
         * @return A new View (really, a TextView) to hold a particular choice.
         */
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view =
                    inflater.inflate(android.R.layout.simple_dropdown_item_1line,
                            parent, false);

            return view;
        }

        /**
         * Called by the AutoCompleteTextView field when a choice has been made
         * by the user.
         *
         * @param listView The ListView containing the choices that were displayed to
         *                 the user.
         * @param view     The field representing the selected choice
         * @param position The position of the choice within the list (0-based)
         * @param id       The id of the row that was chosen (as provided by the _id
         *                 column in the cursor.)
         */
        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            // Get the state's capital from this row in the database.
            //String capital = cursor.getString(cursor.getColumnIndexOrThrow("capital"));

            // Update the parent class's TextView
            //mStateCapitalView.setText(capital);
        }
    }
}
