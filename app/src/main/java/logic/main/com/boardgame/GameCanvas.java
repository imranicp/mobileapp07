package logic.main.com.boardgame;

/**
 * Created by Vahiiid on 12/24/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.TypedValue;
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
    Bitmap barTop_vc, scaled_top_barvc,barTop_vi, scaled_top_barvi,barTop_vo, scaled_top_barvo;
    Bitmap barBottom_vc, scaled_Bottom_barvc,barBottom_vi, scaled_Bottom_barvi,barBottom_vo,scaled_Bottom_barvo;

    public GameCanvas(Context context) {
        super(context);
        final int width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,35,getResources().getDisplayMetrics());
        final int height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,35,getResources().getDisplayMetrics());


        //Loading three textures for vertical,horizontal and holes
        h_square = BitmapFactory.decodeResource(getResources(), R.drawable.h);
        v_square = BitmapFactory.decodeResource(getResources(), R.drawable.v);
        hole = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        barLeft_ho = BitmapFactory.decodeResource(getResources(), R.drawable.hc2);
        barRight_ho = BitmapFactory.decodeResource(getResources(), R.drawable.hc1);
        barLeft_hi = BitmapFactory.decodeResource(getResources(), R.drawable.hi2);
        barRight_hi =BitmapFactory.decodeResource(getResources(), R.drawable.hi1);
        barLeft_HC = BitmapFactory.decodeResource(getResources(), R.drawable.ho2);
        barRight_HC =BitmapFactory.decodeResource(getResources(), R.drawable.ho1);
        barTop_vc = BitmapFactory.decodeResource(getResources(), R.drawable.vc1);
        barBottom_vc= BitmapFactory.decodeResource(getResources(), R.drawable.vc2);
        barTop_vi= BitmapFactory.decodeResource(getResources(), R.drawable.vi1);
        barBottom_vi= BitmapFactory.decodeResource(getResources(), R.drawable.vi2);
        barTop_vo= BitmapFactory.decodeResource(getResources(), R.drawable.vo1);
        barBottom_vo= BitmapFactory.decodeResource(getResources(), R.drawable.vo2);
        //Scaling textures to 40sp
        scaled_h_square = Bitmap.createScaledBitmap(h_square, width, height, true);
        scaled_v_square = Bitmap.createScaledBitmap(v_square, width, height, true);
        scaled_hole = Bitmap.createScaledBitmap(hole, width, height, true);
        scaled_left_barHC = Bitmap.createScaledBitmap(barLeft_HC, width, height, true);
        scaled_right_barHC = Bitmap.createScaledBitmap(barRight_HC, width, height, true);
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


        //Top Bars Of the Board
        canvas.drawBitmap(scaled_top_barvi, (x/2) -300, (y/2) -505, null);
        canvas.drawBitmap(scaled_top_barvc, (x/2) -200, (y/2) -505, null);
        canvas.drawBitmap(scaled_top_barvi, (x/2) -100, (y/2) -505, null);
        canvas.drawBitmap(scaled_top_barvo, (x/2) , (y/2) -505, null);
        canvas.drawBitmap(scaled_top_barvo, (x/2) + 100, (y/2) -505, null);
        canvas.drawBitmap(scaled_top_barvc, (x/2) + 200, (y/2) -505, null);
        canvas.drawBitmap(scaled_top_barvi, (x/2) + 300, (y/2) -505, null);


        //First row of the board
        canvas.drawBitmap(scaled_left_barHC, (x/2) - 400, (y/2) -400, null);
        canvas.drawBitmap(scaled_h_square, (x/2) -300, (y/2) -400, null);
        canvas.drawBitmap(scaled_v_square, (x/2) -200, (y/2) -400, null);
        canvas.drawBitmap(scaled_v_square, (x/2) -100, (y/2) -400, null);
        canvas.drawBitmap(scaled_h_square, (x/2) , (y/2) -400, null);
        canvas.drawBitmap(scaled_hole, (x/2) + 100, (y/2) -400, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 200, (y/2) -400, null);
        canvas.drawBitmap(scaled_hole, (x/2) + 300, (y/2) -400, null);
        canvas.drawBitmap(scaled_right_barHC, (x/2) + 400, (y/2) -400, null);

        //Second row of the board
        canvas.drawBitmap(scaled_left_barhi, (x/2)-400, (y/2) -295, null);
        canvas.drawBitmap(scaled_h_square, (x/2)-300, (y/2) -295, null);
        canvas.drawBitmap(scaled_v_square, (x/2) - 200,(y/2) -295, null);
        canvas.drawBitmap(scaled_h_square, (x/2) - 100,(y/2) -295, null);
        canvas.drawBitmap(scaled_hole, (x/2) ,(y/2) -295, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 100,(y/2) -295, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 200,(y/2) -295, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 300,(y/2) -295, null);
        canvas.drawBitmap(scaled_right_barho, (x/2) + 400,(y/2) -295, null);

        //Third row of the board
        canvas.drawBitmap(scaled_left_barHC, (x/2)-400, (y/2) -190, null);
        canvas.drawBitmap(scaled_v_square, (x/2)-300, (y/2) -190, null);
        canvas.drawBitmap(scaled_h_square, (x/2)-200, (y/2) -190, null);
        canvas.drawBitmap(scaled_hole, (x/2) -100, (y/2) -190, null);
        canvas.drawBitmap(scaled_v_square, (x/2), (y/2) -190, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 100, (y/2) -190, null);
        canvas.drawBitmap(scaled_h_square, (x/2)+ 200, (y/2) -190, null);
        canvas.drawBitmap(scaled_v_square, (x/2)+ 300, (y/2) -190, null);
        canvas.drawBitmap(scaled_right_barHC, (x/2)+ 400, (y/2) -190, null);

        //Fourth row of the board
        canvas.drawBitmap(scaled_left_barho, (x/2)-400, (y/2 )-85, null);
        canvas.drawBitmap(scaled_hole, (x/2)-300, (y/2 )-85, null);
        canvas.drawBitmap(scaled_h_square, (x/2) -200, (y/2) -85, null);
        canvas.drawBitmap(scaled_hole, (x/2) -100, (y/2 )-85, null);
        canvas.drawBitmap(scaled_v_square, (x/2) , (y/2 )-85, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 100, (y/2 )-85, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 200, (y/2 )-85, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 300, (y/2 )-85, null);
        canvas.drawBitmap(scaled_right_barhi, (x/2) + 400, (y/2 )-85, null);

        //Fifth row of the board
        canvas.drawBitmap(scaled_left_barhi, (x/2) -400, (y/2), null);
        canvas.drawBitmap(scaled_h_square, (x/2) -300, (y/2), null);
        canvas.drawBitmap(scaled_v_square, (x/2) -200, (y/2), null);
        canvas.drawBitmap(scaled_v_square, (x/2) -100, (y/2), null);
        canvas.drawBitmap(scaled_h_square, (x/2) , (y/2), null);
        canvas.drawBitmap(scaled_hole, (x/2) + 100, (y/2), null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 200, (y/2), null);
        canvas.drawBitmap(scaled_hole, (x/2) + 300, (y/2), null);
        canvas.drawBitmap(scaled_right_barho, (x/2) + 400, (y/2), null);

        //Sixth row of the board
        canvas.drawBitmap(scaled_left_barHC, (x/2)-400, (y/2) + 95, null);
        canvas.drawBitmap(scaled_hole, (x/2)-300, (y/2) + 95, null);
        canvas.drawBitmap(scaled_v_square, (x/2) - 200,(y/2) + 95, null);
        canvas.drawBitmap(scaled_h_square, (x/2) - 100,(y/2) + 95, null);
        canvas.drawBitmap(scaled_hole, (x/2) ,(y/2) + 95, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 100,(y/2) + 95, null);
        canvas.drawBitmap(scaled_v_square, (x/2) + 200,(y/2) + 95, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 300,(y/2) + 95, null);
        canvas.drawBitmap(scaled_right_barHC, (x/2) + 400,(y/2) + 95, null);

        //Seventh row of the board
        canvas.drawBitmap(scaled_left_barho, (x/2)-400, (y/2) +195, null);
        canvas.drawBitmap(scaled_v_square, (x/2)-300, (y/2) +195, null);
        canvas.drawBitmap(scaled_h_square, (x/2)-200, (y/2) +195, null);
        canvas.drawBitmap(scaled_hole, (x/2) -100, (y/2) +195, null);
        canvas.drawBitmap(scaled_v_square, (x/2), (y/2) +195, null);
        canvas.drawBitmap(scaled_h_square, (x/2) + 100, (y/2) +195, null);
        canvas.drawBitmap(scaled_h_square, (x/2)+ 200, (y/2) +195, null);
        canvas.drawBitmap(scaled_v_square, (x/2)+ 300, (y/2) +195, null);
        canvas.drawBitmap(scaled_right_barhi, (x/2)+ 400, (y/2) +195, null);

        //Bottom Bars of the Board
        canvas.drawBitmap(scaled_Bottom_barvc, (x/2) -300, (y/2) +300, null);
        canvas.drawBitmap(scaled_Bottom_barvi, (x/2) -200, (y/2) +300, null);
        canvas.drawBitmap(scaled_Bottom_barvc, (x/2) -100, (y/2) +300, null);
        canvas.drawBitmap(scaled_Bottom_barvo, (x/2) , (y/2) +300, null);
        canvas.drawBitmap(scaled_Bottom_barvo, (x/2) + 100, (y/2) +300, null);
        canvas.drawBitmap(scaled_Bottom_barvi, (x/2) + 200, (y/2) +300, null);
        canvas.drawBitmap(scaled_Bottom_barvc, (x/2) + 300, (y/2) +300, null);


    }
}
