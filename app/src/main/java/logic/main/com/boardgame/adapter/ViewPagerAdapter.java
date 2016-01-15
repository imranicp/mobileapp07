package logic.main.com.boardgame.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import logic.main.com.boardgame.R;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] number;
    String[] desc;
    int[] image;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, String[] number, String[] desc,
                             int[] image) {
        this.context = context;
        this.number = number;
        this.desc = desc;
        this.image = image;
    }

    @Override
    public int getCount() {
        return number.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtdesc;
        ImageView image2;

        //getting the service from the context
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the view
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtdesc = (TextView) itemView.findViewById(R.id.desc);

        // Capture position and set to the TextViews
        txtdesc.setText(desc[position]);

        // Locate the ImageView in viewpager_item.xml
        image2 = (ImageView) itemView.findViewById(R.id.image1);

        // Capture position and set to the ImageView
        image2.setImageResource(image[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}
