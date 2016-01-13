package logic.main.com.boardgame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;


/**
 * Created by Vahiiid on 1/11/2016.
 */
public class HelpActivity extends Activity {

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] number;
    String[] desc;
    int[] image;
    PageIndicator mIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from viewpager_main.xml
        setContentView(R.layout.viewpager_main);

        // Generate sample data
        number = new String[] { "1", "2", "3", "4", "5", "6", "7"};

        desc = new String[] { "Description About First Page,Description About First Page,Description About First Page,",
                "Description About First Page,Description About First Page,Description About First Page,",
                "Description About First Page,Description About First Page,Description About First Page,",
                "Description About First Page,Description About First Page,Description About First Page,",
                "Description About First Page,Description About First Page,Description About First Page,",
                "Description About First Page,Description About First Page,Description About First Page,",
                "Description About First Page,Description About First Page,Description About First Page,"};


        image = new int[] { R.drawable.hlp1, R.drawable.hlp2,
                R.drawable.hlp3d, R.drawable.hlp3l,
                R.drawable.hlp3r, R.drawable.hlp3u, R.drawable.hlp4,};

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(HelpActivity.this, number, desc, image);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(viewPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0x00000000);
        indicator.setRadius(10 * density);
        indicator.setPageColor(0xFFf0f8ff);
        indicator.setFillColor(0x88bc8861);
        indicator.setStrokeColor(0xFF000000);
        indicator.setStrokeWidth(2 * density);

    }
}