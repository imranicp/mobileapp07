package logic.main.com.boardgame.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import logic.main.com.boardgame.R;

//this is the game history list view adaptor which is used to show the list view
//which has the list of scores from the database
public class HistoryListViewAdapter extends BaseAdapter {

    //the arraylist which will have a hashMap which contains all the unique values
    public ArrayList<HashMap<String, String>> list;

    //the activity of the ListVies
    Activity activity;

    //initializing the listview adaptor
    public HistoryListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();

        //setting the activity to the supplied activity
        this.activity = activity;

        //setting the list to the supplied arraylist
        this.list = list;
    }

    @Override
    public int getCount() {

        //if the list is not null then the list size is returned
        if (list != null) {

            //returning the size of the list
            return list.size();
        } else {

            //returning zero in case of null list
            return 0;
        }
    }

    //getting the list item at the specified position
    @Override
    public Object getItem(int position) {

        //if the list is not null then the item at the required position is returned
        if (list != null) {

            //item at the specified position
            return list.get(position);

        } else {

            //if the list is null then the a null value is returned
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    //This part of the code has helped me understand how android uses recycling
    // to increase the performance of the system in case the listviews are displayed
    //initially convertView is null for all the visible rows, because initially there
    //were no view(item) in recycler, so your getView() creates each new view for the visible items,
    //but the moment you scroll up your item 1 will be sent to the Recycler
    //with it state
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getview:" + position + " " + convertView);

        //getting the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        //viewHolder object which is the object used for list values
        ViewHolder holder;

        //if convert view is null then the list view must be populated
        if (convertView == null) {

            //inflate the layout of the listview into convertview
            convertView = inflater.inflate(R.layout.history_layout, null);

            //new holder object
            holder = new ViewHolder();

            //setting the name of the player 1 to textView name1
            holder.txtPlayer1 = (TextView) convertView.findViewById(R.id.name1);

            //setting the name of the player 2 to textView name2
            holder.txtPlayer2 = (TextView) convertView.findViewById(R.id.name2);

            //setting the name of the player 3 to textView name3
            holder.txtPlayer3 = (TextView) convertView.findViewById(R.id.name3);

            //setting the name of the player 4 to textView name4
            holder.txtPlayer4 = (TextView) convertView.findViewById(R.id.name4);


            holder.separator1 = (View) convertView.findViewById(R.id.seprt2nd);

            holder.separator2 = (View) convertView.findViewById(R.id.seprt3rd);


            //setting tag to the convertView
            convertView.setTag(holder);

        } else {
            //convertView to get the viewHolder object from its tag
            holder = (ViewHolder) convertView.getTag();

        }

        //getting the map of values from the list's specific position
        HashMap<String, String> map = list.get(position);

        //setting the text of the player 1 name textView
        holder.txtPlayer1.setText(map.get("PLAYER1"));

        //if winner if player 1 then winner image must be set
        if (map.get("PLAYER1").equals(map.get("WINNER"))) {

            //setting winner image
            holder.txtPlayer1.setBackgroundResource(R.drawable.winner2);

        }


        //setting the text of the player 2 name textView
        holder.txtPlayer2.setText(map.get("PLAYER2"));

        //if winner if player 2 then winner image must be set
        if (map.get("PLAYER2").equals(map.get("WINNER"))) {

            //setting winner image
            holder.txtPlayer2.setBackgroundResource(R.drawable.winner2);
        }

        //if there was a player 3 in the game then his name is also set in the text View, or else
        //the textview is made invisible
        if (map.get("PLAYER3") != "") {

            //setting player 3 name
            holder.txtPlayer3.setVisibility(View.VISIBLE);
            holder.txtPlayer3.setText(map.get("PLAYER3"));

            //setting separator visibility to visible
            holder.separator1.setVisibility(View.VISIBLE);

            //setting separator visibility to invisible
            holder.separator2.setVisibility(View.INVISIBLE);

            //if winner if player 3 then winner image must be set
            if (map.get("PLAYER3").equals(map.get("WINNER"))) {

                //setting winner image
                holder.txtPlayer3.setBackgroundResource(R.drawable.winner2);

            }

        } else {

            //setting player 3 textView invisible
            holder.txtPlayer3.setVisibility(View.INVISIBLE);

            //setting separator visibility to invisible
            holder.separator1.setVisibility(View.INVISIBLE);

            //setting separator visibility to invisible
            holder.separator2.setVisibility(View.INVISIBLE);


        }


        //if there was a player 3 in the game then his name is also set in the text View, or else
        //the textview is made invisible
        if (map.get("PLAYER4") != "") {
            //setting player 4 name
            holder.txtPlayer4.setVisibility(View.VISIBLE);
            holder.txtPlayer4.setText(map.get("PLAYER4"));

            //setting separator visibility to visible
            holder.separator1.setVisibility(View.VISIBLE);

            //setting separator visibility to visible
            holder.separator2.setVisibility(View.VISIBLE);

            //if winner if player 4 then winner image must be set
            if (map.get("PLAYER4").equals(map.get("WINNER"))) {

                //setting winner image
                holder.txtPlayer4.setBackgroundResource(R.drawable.winner2);
            }

        } else {

            //setting player 4 textView invisible
            holder.txtPlayer4.setVisibility(View.INVISIBLE);

            //setting separator visibility to invisible
            holder.separator2.setVisibility(View.INVISIBLE);
        }

        //returning the convertView
        return convertView;

    }

    private static class ViewHolder {

        //Player 1 Name
        TextView txtPlayer1;

        //Player 2 Name
        TextView txtPlayer2;

        //Player 3 Name
        TextView txtPlayer3;
        
        //Player 4 Name
        TextView txtPlayer4;

        //separator one which is between player two and player three
        View separator1;

        //separator two which is between player three and player four
        View separator2;

    }


}

