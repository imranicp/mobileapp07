package logic.main.com.boardgame;

/**
 * Created by Vahiiid on 12/24/2015.
 */
import java.io.InputStream;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.SampleCanvas.R;


public class GameCanvas extends View {

    Bitmap h_square;
    Bitmap v_square;
    Bitmap hole;
    Bitmap scaled_h_square;
    Bitmap scaled_v_square;
    Bitmap scaled_hole;
    //board squares positions are defined according to x and y
    int x = 350;
    int y = 650;


    public GameCanvas(Context context) {
        super(context);
        //Loading three textures for vertical,horizontal and holes
        h_square = BitmapFactory.decodeResource(getResources(), R.drawable.h);
        v_square = BitmapFactory.decodeResource(getResources(), R.drawable.v);
        hole = BitmapFactory.decodeResource(getResources(), R.drawable.b);

        //Scaling textures to 40sp
        scaled_h_square = Bitmap.createScaledBitmap(h_square, 40, 40, true);
        scaled_v_square = Bitmap.createScaledBitmap(v_square, 40, 40, true);
        scaled_hole = Bitmap.createScaledBitmap(hole, 40, 40, true);


    }


    @Override
    public void onDraw(Canvas canvas) {

        //First row of the board
        canvas.drawBitmap(scaled_h_square, x, y, null);
        canvas.drawBitmap(scaled_v_square, x + 40, y, null);
        canvas.drawBitmap(scaled_v_square, x + 80, y, null);
        canvas.drawBitmap(scaled_h_square, x + 120, y, null);
        canvas.drawBitmap(scaled_hole, x + 160, y, null);
        canvas.drawBitmap(scaled_h_square, x + 200, y, null);
        canvas.drawBitmap(scaled_hole, x + 240, y, null);

        //Second row of the board
        canvas.drawBitmap(scaled_hole, x, y + 40, null);
        canvas.drawBitmap(scaled_v_square, x + 40, y + 40, null);
        canvas.drawBitmap(scaled_h_square, x + 80, y + 40, null);
        canvas.drawBitmap(scaled_hole, x + 120, y + 40, null);
        canvas.drawBitmap(scaled_v_square, x + 160, y + 40, null);
        canvas.drawBitmap(scaled_v_square, x + 200, y + 40, null);
        canvas.drawBitmap(scaled_h_square, x + 240, y + 40, null);

        //Third row of the board
        canvas.drawBitmap(scaled_hole, x, y + 80, null);
        canvas.drawBitmap(scaled_h_square, x + 40, y + 80, null);
        canvas.drawBitmap(scaled_hole, x + 80, y + 80, null);
        canvas.drawBitmap(scaled_v_square, x + 120, y + 80, null);
        canvas.drawBitmap(scaled_h_square, x + 160, y + 80, null);
        canvas.drawBitmap(scaled_h_square, x + 200, y + 80, null);
        canvas.drawBitmap(scaled_v_square, x + 240, y + 80, null);


    }
}
