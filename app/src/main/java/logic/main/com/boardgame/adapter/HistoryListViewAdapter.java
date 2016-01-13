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


public class HistoryListViewAdapter extends BaseAdapter {
    public ArrayList<HashMap<String, String>> list;
    Activity activity;

    public HistoryListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        } else {
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = activity.getLayoutInflater();
        ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.history_layout, null);

            holder = new ViewHolder();
            holder.txtPlayer1 = (TextView) convertView.findViewById(R.id.name1);
            holder.txtPlayer2 = (TextView) convertView.findViewById(R.id.name2);
            holder.txtPlayer3 = (TextView) convertView.findViewById(R.id.name3);
            holder.txtPlayer4 = (TextView) convertView.findViewById(R.id.name4);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = list.get(position);

        holder.txtPlayer1.setText(map.get("PLAYER1"));
        holder.txtPlayer2.setText(map.get("PLAYER2"));

        if (map.get("PLAYER3") != "") {
            holder.txtPlayer3.setText(map.get("PLAYER3"));
        } else {
            holder.txtPlayer3.setVisibility(View.INVISIBLE);
        }

        if (map.get("PLAYER4") != "") {
            holder.txtPlayer4.setText(map.get("PLAYER4"));
        } else {
            holder.txtPlayer4.setVisibility(View.INVISIBLE);
        }

        return convertView;

    }

    private static class ViewHolder {
        TextView txtPlayer1;
        TextView txtPlayer2;
        TextView txtPlayer3;
        TextView txtPlayer4;
    }


}

