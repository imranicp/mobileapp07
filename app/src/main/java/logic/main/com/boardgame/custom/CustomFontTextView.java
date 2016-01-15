package logic.main.com.boardgame.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

//a custom text view class which uses the flash font file
//it extends the TextView view
public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/lucida.ttf"));
    }
}
