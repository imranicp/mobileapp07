package logic.main.com.boardgame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;


public class HelpActivity extends Activity {

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] number;
    String[] desc;
    int[] image;
    PageIndicator mIndicator;
    boolean continueMusic;

    @Override
    protected void onPause() {
        super.onPause();

        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (continueMusic) {
            MusicManager.start(this, MusicManager.MUSIC_MENU);
        } else {
            MusicManager.pause();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from viewpager_main.xml
        setContentView(R.layout.viewpager_main);
        continueMusic = getIntent().getExtras().getBoolean("continueMusic");
        // Generate sample data
        number = new String[] { "1", "2", "3", "4", "5", "6", "7"};

        desc = new String[] { "when you start a game a Random Board Configuration is generated the board is empty right now,",
                "Place beads by tapping on horizontal bar tile or vertical bar tile, you can not place a bead on hole. each player places bead one bye one,",
                "After placement of beads you can play the game. you can move the vertical top bar to downwards position,",
                "You can also move the horizontal bars to outwards position or inwards,",
                "The same goes for horizontal bar on the other side, you can move it outwards or inwards",
                "Bottom bar can be moved upwards or downwards",
                "when the beads of all the other players are fallen and one players beads are remaining on the board he wins"};


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
        indicator.setFillColor(0x8874290c);
        indicator.setStrokeColor(0xFF000000);
        indicator.setStrokeWidth(2 * density);

    }

    @Override
    public void onBackPressed() {

        finish();
    }
}