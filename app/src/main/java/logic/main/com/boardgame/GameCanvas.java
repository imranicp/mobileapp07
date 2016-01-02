package logic.main.com.boardgame;

/**
 * Created by Vahiiid on 12/24/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.SampleCanvas.R;

import java.lang.reflect.Type;


public class GameCanvas extends View {

    Bitmap h_square;
    Bitmap v_square;
    Bitmap hole;
    Bitmap scaled_h_square;
    Bitmap scaled_v_square;
    Bitmap scaled_hole, barLeft_hc, scaled_left_barhc, barRight_hc, scaled_right_barhc;
    Bitmap barLeft_hi, scaled_left_barhi,barRight_hi, scaled_right_barhi;
    Bitmap barLeft_ho, scaled_left_barho,barRight_ho, scaled_right_barho;
    Bitmap barTop_vc, scaled_top_barvc,barTop_vi, scaled_top_barvi,barTop_vo, scaled_top_barvo;
    Bitmap barBottom_vc, scaled_Bottom_barvc,barBottom_vi, scaled_Bottom_barvi,barBottom_vo,scaled_Bottom_barvo;
    String output[] = {"B","B","H","R","B","H","H","H","B","H","R","B","R","H","R","B","H","R","B","H","R","R","B","H","R","B","H","R","R","B","H","R","B","H","R","R","B","H","R","B","H","R","R","B","H","R","B","H","R"};
    String barPos[] = {"0","1","2","0","1","2","1","0","1","2","0","1","2","1"};
    int c = 400;
    int p = 300;
    int row=0;
    int c1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,35,getResources().getDisplayMetrics());
    int r1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics());
    int c2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,133,getResources().getDisplayMetrics());
    int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,134,getResources().getDisplayMetrics());
    int v = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,168,getResources().getDisplayMetrics());


    public GameCanvas(Context context) {
        super(context);
        final int width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,35,getResources().getDisplayMetrics());
        final int height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,35,getResources().getDisplayMetrics());


        //Loading three textures for squares and bars
        h_square = BitmapFactory.decodeResource(getResources(), R.drawable.h);
        v_square = BitmapFactory.decodeResource(getResources(), R.drawable.v);
        hole = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        barLeft_ho = BitmapFactory.decodeResource(getResources(), R.drawable.ho2);
        barRight_ho = BitmapFactory.decodeResource(getResources(), R.drawable.ho1);
        barLeft_hi = BitmapFactory.decodeResource(getResources(), R.drawable.hi2);
        barRight_hi =BitmapFactory.decodeResource(getResources(), R.drawable.hi1);
        barLeft_hc = BitmapFactory.decodeResource(getResources(), R.drawable.hc2);
        barRight_hc =BitmapFactory.decodeResource(getResources(), R.drawable.hc1);
        barTop_vc = BitmapFactory.decodeResource(getResources(), R.drawable.vc1);
        barBottom_vc= BitmapFactory.decodeResource(getResources(), R.drawable.vc2);
        barTop_vi= BitmapFactory.decodeResource(getResources(), R.drawable.vi1);
        barBottom_vi= BitmapFactory.decodeResource(getResources(), R.drawable.vi2);
        barTop_vo= BitmapFactory.decodeResource(getResources(), R.drawable.vo1);
        barBottom_vo= BitmapFactory.decodeResource(getResources(), R.drawable.vo2);

        //Scaling textures
        scaled_h_square = Bitmap.createScaledBitmap(h_square, width, height, true);
        scaled_v_square = Bitmap.createScaledBitmap(v_square, width, height, true);
        scaled_hole = Bitmap.createScaledBitmap(hole, width, height, true);
        scaled_left_barhc = Bitmap.createScaledBitmap(barLeft_hc, width, height, true);
        scaled_right_barhc = Bitmap.createScaledBitmap(barRight_hc, width, height, true);
        scaled_left_barhi =  Bitmap.createScaledBitmap(barLeft_hi, width, height, true);
        scaled_right_barhi = Bitmap.createScaledBitmap(barRight_hi, width, height, true);
        scaled_left_barho =  Bitmap.createScaledBitmap(barLeft_ho, width, height, true);
        scaled_right_barho = Bitmap.createScaledBitmap(barRight_ho, width, height, true);
        scaled_top_barvc= Bitmap.createScaledBitmap(barTop_vc, width, height, true);
        scaled_Bottom_barvc=  Bitmap.createScaledBitmap(barBottom_vc, width, height, true);
        scaled_top_barvi= Bitmap.createScaledBitmap(barTop_vi, width, height, true);
        scaled_Bottom_barvi=  Bitmap.createScaledBitmap(barBottom_vi, width, height, true);
        scaled_top_barvo= Bitmap.createScaledBitmap(barTop_vo, width, height, true);
        scaled_Bottom_barvo=  Bitmap.createScaledBitmap(barBottom_vo, width, height, true);

    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //board squares positions are defined according to x and y
        int x = getWidth();
        int y = getHeight();

        //Drawing the board
        for(int r=0; r<49; r++){

            if (output[r] == "R")
                canvas.drawBitmap(scaled_h_square, (x / 2) -r1 + (row * c1), (y / 2) - c2, null);
            else if (output[r] == "B")
                canvas.drawBitmap(scaled_v_square, (x / 2) - r1 + (row * c1), (y / 2) - c2, null);
            else if (output[r] == "H")
                canvas.drawBitmap(scaled_hole, (x / 2) - r1 + (row * c1), (y / 2) - c2, null);
            row++;
            if((r==6)||(r==13)||(r==20)||(r==27)||(r==34)||(r==41)||(r==48)){
            c2=c2-c1;
            row=0;
            }

        }

        //Drawing horizontal bars
        for (int r=0; r<7; r++){
            if (barPos[r] == "0"){
                canvas.drawBitmap(scaled_left_barhi, (x/2) - h , (y/2) - h + (r * c1), null);
                canvas.drawBitmap(scaled_right_barho, (x/2) + h, (y/2) - h + (r * c1), null);
            }
            else if (barPos[r] == "1") {
                canvas.drawBitmap(scaled_left_barhc, (x/2) - h , (y/2) - h + (r * c1), null);
                canvas.drawBitmap(scaled_right_barhc, (x/2) + h, (y/2) - h + (r * c1), null);
            }
            else if (barPos[r] == "2"){
                canvas.drawBitmap(scaled_left_barho, (x/2) - h , (y/2) - h + (r * c1), null);
                canvas.drawBitmap(scaled_right_barhi, (x/2) + h, (y/2) - h + (r * c1), null);
            }
        }

        //Drawing vertical bars
        for (int c=0; c<7; c++){
            if (barPos[c+7] == "0"){
                canvas.drawBitmap(scaled_top_barvi, (x/2) - r1 + (c * c1), (y/2) - v, null);
                canvas.drawBitmap(scaled_Bottom_barvo, (x/2) - r1 + (c * c1), (y/2) + r1, null);
            }
            else if (barPos[c+7] == "1"){
                canvas.drawBitmap(scaled_top_barvc, (x/2) - r1 + (c * c1), (y/2) - v, null);
                canvas.drawBitmap(scaled_Bottom_barvc, (x/2) - r1 + (c * c1), (y/2) + r1, null);
            }
            else if (barPos[c+7] == "2"){
                canvas.drawBitmap(scaled_top_barvo, (x/2) - r1 + (c * c1), (y/2) -v, null);
                canvas.drawBitmap(scaled_Bottom_barvi, (x/2) - r1 + (c * c1), (y/2) +r1, null);
            }
        }

    }
}
