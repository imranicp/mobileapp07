package logic.main.com.boardgame;

/**
 * Created by Vahiiid on 12/24/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.SampleCanvas.R;


public class GameCanvas extends View {

    Bitmap h_square;
    Bitmap v_square;
    Bitmap hole;
    Bitmap scaled_h_square;
    Bitmap scaled_v_square;
    Bitmap scaled_hole, barLeft_HC, scaled_left_barHC, barRight_HC, scaled_right_barHC;
    Bitmap barLeft_hi, scaled_left_barhi,barRight_hi, scaled_right_barhi;
    Bitmap barLeft_ho, scaled_left_barho,barRight_ho, scaled_right_barho;
    public GameCanvas(Context context) {
        super(context);
        //Loading three textures for vertical,horizontal and holes
        h_square = BitmapFactory.decodeResource(getResources(), R.drawable.h);
        v_square = BitmapFactory.decodeResource(getResources(), R.drawable.v);
        hole = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        barLeft_HC = BitmapFactory.decodeResource(getResources(), R.drawable.hc11);
        barRight_HC = BitmapFactory.decodeResource(getResources(), R.drawable.hc2);
        barLeft_hi = BitmapFactory.decodeResource(getResources(), R.drawable.hi1);
        barRight_hi =BitmapFactory.decodeResource(getResources(), R.drawable.hi2);
        barLeft_ho = BitmapFactory.decodeResource(getResources(), R.drawable.ho1);
        barRight_ho =BitmapFactory.decodeResource(getResources(), R.drawable.ho2);

        //Scaling textures to 40sp
        scaled_h_square = Bitmap.createScaledBitmap(h_square, 100, 100, true);
        scaled_v_square = Bitmap.createScaledBitmap(v_square, 100, 100, true);
        scaled_hole = Bitmap.createScaledBitmap(hole, 100, 100, true);
        scaled_left_barHC = Bitmap.createScaledBitmap(barLeft_HC, 100, 100, true);
        scaled_right_barHC = Bitmap.createScaledBitmap(barRight_HC, 100, 100, true);
        scaled_left_barhi =  Bitmap.createScaledBitmap(barLeft_hi, 100, 100, true);
        scaled_right_barhi = Bitmap.createScaledBitmap(barRight_hi, 100, 100, true);
        scaled_left_barho =  Bitmap.createScaledBitmap(barLeft_ho, 100, 100, true);
        scaled_right_barho = Bitmap.createScaledBitmap(barRight_ho, 100, 100, true);

    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //board squares positions are defined according to x and y
        int x = getWidth();
        int y = getHeight();


        //First row of the board
        canvas.drawBitmap(scaled_left_barHC, (x/2) -400, (y/2) -400, null);
        canvas.drawBitmap(scaled_h_square, (x/2) -300, (y/2) -400, null);
        canvas.drawBitmap(scaled_v_square, (x/2) -200, (y/2) -400, null);
        canvas.drawBitmap(scaled_v_square, (x/2) -100, (y/2) -400, null);
        canvas.drawBitmap(scaled_h_square, (x/2) , (y/2) -400, null);
        canvas.drawBitmap(scaled_hole, (x/2) + 100, (y/2) -400, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 200, (y/2) -400, null);
        canvas.drawBitmap(scaled_hole, (x/2) + 300, (y/2) -400, null);
        canvas.drawBitmap(scaled_right_barHC, (x/2) + 400, (y/2) -400, null);

        //Second row of the board
        canvas.drawBitmap(scaled_left_barhi, (x/2)-400, (y/2) -300, null);
        canvas.drawBitmap(scaled_h_square, (x/2)-300, (y/2) -300, null);
        canvas.drawBitmap(scaled_v_square, (x/2) - 200,(y/2) -300, null);
        canvas.drawBitmap(scaled_h_square, (x/2) - 100,(y/2) -300, null);
        canvas.drawBitmap(scaled_hole, (x/2) ,(y/2) -300, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 100,(y/2) -300, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 200,(y/2) -300, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 300,(y/2) -300, null);
        canvas.drawBitmap(scaled_right_barho, (x/2) + 400,(y/2) -300, null);

        //Third row of the board
        canvas.drawBitmap(scaled_v_square, (x/2)-300, (y/2) -200, null);
        canvas.drawBitmap(scaled_h_square, (x/2)-200, (y/2) -200, null);
        canvas.drawBitmap(scaled_hole, (x/2) -100, (y/2) -200, null);
        canvas.drawBitmap(scaled_v_square, (x/2), (y/2) -200, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 100, (y/2) -200, null);
        canvas.drawBitmap(scaled_h_square, (x/2)+ 200, (y/2) -200, null);
        canvas.drawBitmap(scaled_v_square, (x/2)+ 300, (y/2) -200, null);

        //Fourth row of the board
        canvas.drawBitmap(scaled_hole, (x/2)-300, (y/2 )-100, null);
        canvas.drawBitmap(scaled_h_square, (x/2) -200, (y/2) -100, null);
        canvas.drawBitmap(scaled_hole, (x/2) -100, (y/2 )-100, null);
        canvas.drawBitmap(scaled_v_square, (x/2) , (y/2 )-100, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 100, (y/2 )-100, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 200, (y/2 )-100, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 300, (y/2 )-100, null);

        //Fifth row of the board
        canvas.drawBitmap(scaled_h_square, (x/2) -300, (y/2), null);
        canvas.drawBitmap(scaled_v_square, (x/2) -200, (y/2), null);
        canvas.drawBitmap(scaled_v_square, (x/2) -100, (y/2), null);
        canvas.drawBitmap(scaled_h_square, (x/2) , (y/2), null);
        canvas.drawBitmap(scaled_hole, (x/2) + 100, (y/2), null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 200, (y/2), null);
        canvas.drawBitmap(scaled_hole, (x/2) + 300, (y/2), null);

        //Sixth row of the board
        canvas.drawBitmap(scaled_hole, (x/2)-300, (y/2) + 100, null);
        canvas.drawBitmap(scaled_v_square, (x/2) - 200,(y/2) + 100, null);
        canvas.drawBitmap(scaled_h_square, (x/2) - 100,(y/2) + 100, null);
        canvas.drawBitmap(scaled_hole, (x/2) ,(y/2) + 100, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 100,(y/2) + 100, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 200,(y/2) + 100, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 300,(y/2) + 100, null);

        //Seventh row of the board
        canvas.drawBitmap(scaled_v_square, (x/2)-300, (y/2) +200, null);
        canvas.drawBitmap(scaled_h_square, (x/2)-200, (y/2) +200, null);
        canvas.drawBitmap(scaled_hole, (x/2) -100, (y/2) +200, null);
        canvas.drawBitmap(scaled_v_square, (x/2), (y/2) +200, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 100, (y/2) +200, null);
        canvas.drawBitmap(scaled_h_square, (x/2)+ 200, (y/2) +200, null);
        canvas.drawBitmap(scaled_v_square, (x/2)+ 300, (y/2) +200, null);


    }
}
