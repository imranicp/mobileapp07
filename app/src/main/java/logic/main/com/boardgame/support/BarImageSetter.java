package logic.main.com.boardgame.support;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.core.Board;


public class BarImageSetter {
    ImageView imageView;
    public void setBarImages(Board board,Context context,Activity mainActivity) {
        int i;
        String horizontalBars = board.getPostionsOfHorizontalBars();
        String verticalBars = board.getPostionsOfVerticalBars();

        for (i = 0; i < horizontalBars.length(); i++) {
            int j = i + 1;
            String leftResource = "l" + String.valueOf(j);
            String rightResource = "r" + String.valueOf(j);

            if (horizontalBars.charAt(i) == '0') {
                int resID = context.getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.leftinner);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(leftResource+"inner");

                resID = context.getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.rightouter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(rightResource+"outer");
            }

            if (horizontalBars.charAt(i) == '1') {
                int resID = context.getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.leftcenter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(leftResource+"center");

                resID = context.getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.rightcenter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(rightResource+"center");
            }

            if (horizontalBars.charAt(i) == '2') {
                int resID = context.getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.leftouter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(leftResource+"outer");

                resID = context.getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.rightinner);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(rightResource+"inner");
            }
        }

        for (i = 0; i < verticalBars.length(); i++) {
            int j = i + 1;
            String topResource = "top" + String.valueOf(j);
            String botResource = "bot" + String.valueOf(j);

            if (verticalBars.charAt(i) == '0') {
                int resID = context.getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.topinner);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(topResource+"inner");

                resID = context.getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.bottomouter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(botResource+"outer");
            }

            if (verticalBars.charAt(i) == '1') {
                int resID = context.getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.topcenter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(topResource+"center");
                resID = context.getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.bottomcenter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(botResource+"center");
            }

            if (verticalBars.charAt(i) == '2') {
                int resID = context.getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.topouter);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(topResource+"outer");

                resID = context.getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.bottominner);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag(botResource+"inner");
            }
        }
    }

}
