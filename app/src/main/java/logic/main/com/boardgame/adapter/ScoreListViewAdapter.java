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

//this is the score list view adaptor which is used to show the list view
//which has the list of scores from the database
public class ScoreListViewAdapter extends BaseAdapter {

    //the arraylist which will have a hashMap which contains all the unique values
    public ArrayList<HashMap<String, String>> list;

    //the activity of the ListVies
    Activity activity;

    //initializing the listview adaptor
    public ScoreListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
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

        }
        //if the list is null then the a null value is returned
        else {
            return null;
        }
    }

    //itemID are always 0
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

        //getting the layout inflater
        LayoutInflater inflater = activity.getLayoutInflater();

        //viewHolder object which is the object used for list values
        ViewHolder holder;

        //if convert view is null then the list view must be populated
        if (convertView == null) {

            //inflate the layout of the listview into convertview
            convertView = inflater.inflate(R.layout.score_layout, null);

            //new holder object
            holder = new ViewHolder();

            //setting the name of the player to textView
            holder.txtName = (TextView) convertView.findViewById(R.id.name);

            //setting the score of the player to textView
            holder.txtScore = (TextView) convertView.findViewById(R.id.score);

            //setting tag to the convertView
            convertView.setTag(holder);

        } else {

            //convertView to get the viewHolder object from its tag
            holder = (ViewHolder) convertView.getTag();
        }

        //getting the map of values from the list's specific position
        HashMap<String, String> map = list.get(position);

        //setting the text of the player name textView
        holder.txtName.setText(map.get("PLAYER_NAME"));

        //setting the text of the player score textView
        holder.txtScore.setText(map.get("SCORE"));

        //returning the convertView
        return convertView;

    }

    private static class ViewHolder {

        //Player Name
        TextView txtName;

        //Player Score
        TextView txtScore;
    }


}
