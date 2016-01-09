package logic.main.com.boardgame;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Muhammad-Imran on 1/9/2016.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/flash.ttf"));
    }
}
