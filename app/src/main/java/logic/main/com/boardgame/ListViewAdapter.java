package logic.main.com.boardgame;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class ListViewAdapter extends BaseAdapter {
    public ArrayList<HashMap<String, String>> list;
    Activity activity;

    public ListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        LayoutInflater inflater = activity.getLayoutInflater();
        ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.score_layout, null);
            /*LinearLayout view = (LinearLayout) LinearLayout.inflate(this.activity,
                    R.layout.score_layout, null);*/

            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtScore = (TextView) convertView.findViewById(R.id.score);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = list.get(position);
        holder.txtName.setText(map.get("PLAYER_NAME"));
        holder.txtScore.setText(map.get("SCORE"));

        return convertView;

    }

    private static class ViewHolder {
        TextView txtName;
        TextView txtScore;
    }


}
