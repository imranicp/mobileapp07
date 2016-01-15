package logic.main.com.boardgame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
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
import android.widget.Toast;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.manager.DatabaseManager;
import logic.main.com.boardgame.manager.MusicManager;

//this activity is triggered on Human-Human game select,
//which can be 2 players, 3 players, 4 players
public class PlayerSelectActivity extends Activity {

    //this value will store the number of players value which is received from the game type activity
    int value;

    //continueMusic is a boolean variable which is common across all activities to
    //allow seamless playback of game music in case continueMusic is true
    boolean continueMusic;

    //the databaseHelper object which is used to query the DB
    DatabaseManager dataBaseHelper;

    //the AutoCompleteTextView which is used to enter player 1 name
    private AutoCompleteTextView player1_name;

    //the AutoCompleteTextView which is used to enter player 2 name
    private AutoCompleteTextView player2_name;

    //the AutoCompleteTextView which is used to enter player 3 name
    private AutoCompleteTextView player3_name;

    //the AutoCompleteTextView which is used to enter player 4 name
    private AutoCompleteTextView player4_name;

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


    //the onCreate function initializes all the elements relevant to the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting content view to the name layout
        setContentView(R.layout.name_layout);

        //assigning the AutoCompleteTextView resource for player 1 name
        player1_name = (AutoCompleteTextView) findViewById(R.id.player1_name);

        //assigning the AutoCompleteTextView resource for player 2 name
        player2_name = (AutoCompleteTextView) findViewById(R.id.player2_name);

        //assigning the AutoCompleteTextView resource for player 3 name
        player3_name = (AutoCompleteTextView) findViewById(R.id.player3_name);

        //assigning the AutoCompleteTextView resource for player 4 name
        player4_name = (AutoCompleteTextView) findViewById(R.id.player4_name);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/flash.ttf");
        TextView tv=(TextView) findViewById(R.id.playButton);
        tv.setTypeface(font);
        //initializing the DB manager object for this activity
        dataBaseHelper = new DatabaseManager(this);

        //for the AutoComplete TextView we need an adaptor which will get suggestions for player names
        //based on the letters which are entered in the player name box
        //if similar matches are found then a list is shown
        ItemAutoTextAdapter adapter = this.new ItemAutoTextAdapter(dataBaseHelper);

        //getting the continueMusic value which is always passed as an extra to the intent
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");

        //getting the numberOfPlayers value which is passed as an extra to the intent
        value = getIntent().getExtras().getInt("numberOfPlayers");

        //setting the ItemAutoText adaptor to the player 1 TextBox
        player1_name.setAdapter(adapter);

        //setting the on item click listner as the adaptor to the player 1 TextBox
        player1_name.setOnItemClickListener(adapter);

        //setting the ItemAutoText adaptor to the player 2 TextBox
        player2_name.setAdapter(adapter);

        //setting the on item click listener as the adaptor to the player 2 TextBox
        player2_name.setOnItemClickListener(adapter);

        //the imeOptions are set to 6 when the player has entered name and presses next on the softkeyBoard
        //the keyboard is destroyed from the view, which means that action is completed
        player2_name.setImeOptions(6);

        //linearLayout which holds the player 3 textBox
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.player3_lay);

        //visibility is initially set to invisible, in case its just a 2 player game
        linearLayout3.setVisibility(View.INVISIBLE);

        //linearLayout which holds the player 4 textBox
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.player4_lay);

        //visibility is initially set to invisible, in case its a 3 player game
        linearLayout4.setVisibility(View.INVISIBLE);

        //value 3 signifies that its a 3 player game
        //the visibiliy of linear layout of player 3 must be set to visible and imeOptions must be set for player 2 and 3
        if (value == 3) {
            //the imeOptions are set to 5 when the player has entered name and presses next on the softkeyBoard
            //the keyboard is set for the next textbox which is available for taking input
            player2_name.setImeOptions(5);

            //setting the ItemAutoText adaptor to the player 3 TextBox
            player3_name.setAdapter(adapter);

            //setting the on item click listener as the adaptor to the player 3 TextBox
            player3_name.setOnItemClickListener(adapter);

            //visibility is set back to visible as its a 3 player game
            linearLayout3.setVisibility(View.VISIBLE);

            //the imeOptions are set to 6 when the player has entered name and presses next on the softkeyBoard
            //the keyboard is destroyed from the view, which means that action is completed
            player3_name.setImeOptions(6);

            //visibility of linear layout of player 4 is set to invisible, as its a 3 player game
            linearLayout4.setVisibility(View.INVISIBLE);
        }

        //value 4 signifies that its a 4 player game
        //the visibiliy of linear layout of player 4 and player 4
        // must be set to visible and imeOptions must be set for player 2, 3 and 4
        if (value == 4) {

            //the imeOptions are set to 5 when the player has entered name and presses next on the softkeyBoard
            //the keyboard is set for the next textbox which is available for taking input
            player2_name.setImeOptions(5);

            //the imeOptions are set to 5 when the player has entered name and presses next on the softkeyBoard
            //the keyboard is set for the next textbox which is available for taking input
            player3_name.setImeOptions(5);

            //the imeOptions are set to 6 when the player has entered name and presses next on the softkeyBoard
            //the keyboard is destroyed from the view, which means that action is completed
            player4_name.setImeOptions(6);

            //setting the ItemAutoText adaptor to the player 3 TextBox
            player3_name.setAdapter(adapter);

            //setting the on item click listener as the adaptor to the player 3 TextBox
            player3_name.setOnItemClickListener(adapter);

            //setting the ItemAutoText adaptor to the player 3 TextBox
            player4_name.setAdapter(adapter);

            //setting the on item click listener as the adaptor to the player 4 TextBox
            player4_name.setOnItemClickListener(adapter);

            //visibility of linear layout of player 3 set back to visible as its a 4 player game
            linearLayout3.setVisibility(View.VISIBLE);

            //visibility of linear layout of player 4 set back to visible as its a 4 player game
            linearLayout4.setVisibility(View.VISIBLE);
        }


    }

    //for the backButton press functionality,
    //this activity finishes and triggers back the game type selection activity
    @Override
    public void onBackPressed() {

        //setting intent to game type activity
        Intent intent = new Intent(this, GameTypeActivity.class);

        //putting continueMusic value in intent extras
        intent.putExtra("continueMusic", continueMusic);

        //starting the intent
        startActivity(intent);

        //finishing the activity
        finish();
    }
    //on click of the play button the game activity must be triggered if all the eligible player names are entered
    public void onClick(View v) {

        //new intent for the game activity class
        Intent newActivity = new Intent(this, GameActivity.class);

        //taking the value of player 1 name which is entered
        String p1Name = player1_name.getText().toString();

        //taking the value of player 2 name which is entered
        String p2Name = player2_name.getText().toString();

        //taking the value of player 3 name which is entered
        String p3Name = player3_name.getText().toString();

        //taking the value of player 4 name which is entered
        String p4Name = player4_name.getText().toString();

        //switching on id of the button pressed
        switch (v.getId()) {

            //in case of play button
            case R.id.playButton:

                //if the number of players is 2
                if (value == 2) {


                    //replacing all the empty spaces in the entered name
                    p1Name = p1Name.replaceAll("\\s+", "");

                    //replacing all the empty spaces in the entered name
                    p2Name = p2Name.replaceAll("\\s+", "");

                    //we check whether player names have been entered in both text Boxes or not
                    //if either of the name is not entered then display a toast message
                    if (p1Name.equals("") || p2Name.equals("")) {

                        //setting the toast message
                        Toast toast = Toast.makeText(getApplicationContext(), "Player cannot be empty", Toast.LENGTH_SHORT);

                        //showing the toast message
                        toast.show();
                    }

                    //if both the player names are not empty then the game activity is triggered
                    if (!p1Name.equals("") && !p2Name.equals("")) {

                        //putting the number of players as intent extra
                        newActivity.putExtra("numberOfPlayers", 2);

                        //putting the player 1 name as intent extra
                        newActivity.putExtra("player1", p1Name);

                        //putting the player 2 name as intent extra
                        newActivity.putExtra("player2", p2Name);

                        //putting the continueMusic as intent extra
                        newActivity.putExtra("continueMusic", continueMusic);

                        //starting the game activity
                        startActivity(newActivity);

                        //finishing this activity
                        finish();
                    }
                }

                //if the number of players is 3
                if (value == 3) {

                    //replacing all the empty spaces in the entered name
                    p1Name = p1Name.replaceAll("\\s+", "");

                    //replacing all the empty spaces in the entered name
                    p2Name = p2Name.replaceAll("\\s+", "");

                    //replacing all the empty spaces in the entered name
                    p3Name = p3Name.replaceAll("\\s+", "");


                    //we check whether player names have been entered in both text Boxes or not
                    //if any of the name is not entered then display a toast message
                    if (p1Name.equals("") || p2Name.equals("") || p3Name.equals("")) {

                        //setting the toast message
                        Toast toast = Toast.makeText(getApplicationContext(), "Player cannot be empty", Toast.LENGTH_SHORT);

                        //showing the toast message
                        toast.show();
                    }

                    //if the player names are not empty then the game activity is triggered
                    if (!p1Name.equals("") && !p2Name.equals("") && !p3Name.equals("")) {

                        //putting the number of players as intent extra
                        newActivity.putExtra("numberOfPlayers", 3);

                        //putting the player 1 name as intent extra
                        newActivity.putExtra("player1", p1Name);

                        //putting the player 2 name as intent extra
                        newActivity.putExtra("player2", p2Name);

                        //putting the player 3 name as intent extra
                        newActivity.putExtra("player3", p3Name);

                        //putting the continueMusic as intent extra
                        newActivity.putExtra("continueMusic", continueMusic);

                        //starting the game activity
                        startActivity(newActivity);

                        //finishing this activity
                        finish();
                    }
                }

                //if the number of players is 4
                if (value == 4) {

                    //replacing all the empty spaces in the entered name
                    p1Name = p1Name.replaceAll("\\s+", "");

                    //replacing all the empty spaces in the entered name
                    p2Name = p2Name.replaceAll("\\s+", "");

                    //replacing all the empty spaces in the entered name
                    p3Name = p3Name.replaceAll("\\s+", "");

                    //replacing all the empty spaces in the entered name
                    p4Name = p4Name.replaceAll("\\s+", "");

                    //we check whether player names have been entered in both text Boxes or not
                    //if any of the name is not entered then display a toast message
                    if (p1Name.equals("") || p2Name.equals("") || p3Name.equals("") || p4Name.equals("")) {

                        //setting the toast message
                        Toast toast = Toast.makeText(getApplicationContext(), "Player cannot be empty", Toast.LENGTH_SHORT);

                        //showing the toast message
                        toast.show();
                    }

                    //if the player names are not empty then the game activity is triggered
                    if (!p1Name.equals("") && !p2Name.equals("") && !p3Name.equals("") && !p4Name.equals("")) {

                        //putting the number of players as intent extra
                        newActivity.putExtra("numberOfPlayers", 4);

                        //putting the player 1 name as intent extra
                        newActivity.putExtra("player1", p1Name);

                        //putting the player 2 name as intent extra
                        newActivity.putExtra("player2", p2Name);

                        //putting the player 3 name as intent extra
                        newActivity.putExtra("player3", p3Name);

                        //putting the player 4 name as intent extra
                        newActivity.putExtra("player4", p4Name);

                        //putting the continueMusic as intent extra
                        newActivity.putExtra("continueMusic", continueMusic);

                        //starting the game activity
                        startActivity(newActivity);

                        //finishing this activity
                        finish();
                    }
                }
                break;

            default:
        }

    }

    //the onPause function for this activity must destroy this activity as the is an active DB connection when this activity is running
    //which may lead to exceptions during runtime
    @Override
    public void onPause() {
        super.onPause();

        //setting continueMusic to false
        continueMusic = false;

        //calling music manager to pause the music
        MusicManager.pause();

        //finishing this activity
        this.finish();
    }

    //this class acts as an adaptor to the AutoComplete text views
    //this class finds the matches to the names entered on the screen to the name in DB
    //thus the suggestions are displayed during player selection
    class ItemAutoTextAdapter extends CursorAdapter
            implements android.widget.AdapterView.OnItemClickListener {

        //db object for this class
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

            //if the filter query provider is available then it can give the query according to the constraint to the DB

            if (getFilterQueryProvider() != null) {

                //the return value is a cursor which hold the matching records
                return getFilterQueryProvider().runQuery(constraint);

            }

            //here the dbHelper is called to retrieve the set of matching names to the given input
            Cursor cursor = mDbHelper.getMatchingNames(
                    (constraint != null ? constraint.toString() : null));

            //the return value is a cursor which hold the matching records
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
            //when a suggestion is selected from the list the corresponding text is taken from the cursor results

            //columnIndex holds the column index of the player name column
            final int columnIndex = cursor.getColumnIndexOrThrow("PLAYER_NAME");

            //str is the string of the selected name
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

            //this binds the text which is selected to the autocomplete text view
            final String text = convertToString(cursor);
            //setting the text in the internal textView
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

            //displaying the particular text in the dropdown list which appears after the query
            final LayoutInflater inflater = LayoutInflater.from(context);

            //view which has to be shown
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

            //when a value is selected from the drop down list then the keyboard input must be hidden
            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            //hiding the keyboard from the view
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

        }
    }
}
