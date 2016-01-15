package logic.main.com.boardgame.support;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.core.Board;


//this class sets the images of the bars (top,bottom,right,left) on the screen
// according to the positions of the bars in board configuration
public class BarImageSetter {

    //the image view object to be used to set the images
    ImageView imageView;

    //setting the bar images on the screen, the bar configuration is taken from the board
    //the context is used to get the image resources
    //the activity is used to find the image resources by their id
    public void setBarImages(Board board,Context context,Activity mainActivity) {

        //the loop variable loops on the various positions of the bar
        int i;

        //the horizontal bar positions
        String horizontalBars = board.getPostionsOfHorizontalBars();

        //the vertical bar positions
        String verticalBars = board.getPostionsOfVerticalBars();


        for (i = 0; i < horizontalBars.length(); i++) {

            //j is the bar number which is from 1 to 7
            int j = i + 1;

            //for a horizontal bar there are two sets of images the left side image and the right side image
            //for ex: if the left side image shows outer position then
            // the right side image for that bar must show inner position
            //the combinations are inner-outer, center-center, outer-inner

            //the left resource with the bar number
            String leftResource = "l" + String.valueOf(j);

            //the right resource with the bar number
            String rightResource = "r" + String.valueOf(j);


            //if the bar position is 0 then the left bar must show inner position
            // and the right bar must show outer position
            if (horizontalBars.charAt(i) == '0') {

                //getting the resource identifier of the left bar
                int resID = context.getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to left inner image
                imageView.setImageResource(R.drawable.leftinner);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(leftResource+"inner");

                //getting the resource identifier of the right bar
                resID = context.getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to right outer image
                imageView.setImageResource(R.drawable.rightouter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(rightResource+"outer");
            }

            //if the bar position is 1 then the left bar must show center position
            // and the right bar must show center position
            if (horizontalBars.charAt(i) == '1') {

                //getting the resource identifier of the left bar
                int resID = context.getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to left center image
                imageView.setImageResource(R.drawable.leftcenter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(leftResource+"center");

                //getting the resource identifier of the right bar
                resID = context.getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to right center image
                imageView.setImageResource(R.drawable.rightcenter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(rightResource+"center");
            }


            //if the bar position is 2 then the left bar must show outer position
            // and the right bar must show inner position
            if (horizontalBars.charAt(i) == '2') {

                //getting the resource identifier of the left bar
                int resID = context.getResources().getIdentifier(leftResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to left outer image
                imageView.setImageResource(R.drawable.leftouter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(leftResource+"outer");

                //getting the resource identifier of the right bar
                resID = context.getResources().getIdentifier(rightResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to left outer image
                imageView.setImageResource(R.drawable.rightinner);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(rightResource+"inner");
            }
        }

        //the loop variable loops on the various positions of the vertical bar
        for (i = 0; i < verticalBars.length(); i++) {

            //j is the bar number which is from 1 to 7
            int j = i + 1;

            //for a vertical bar there are two sets of images the top side image and the bottom side image
            //for ex: if the top side image shows outer position then
            // the bottom side image for that bar must show inner position
            //the combinations are inner-outer, center-center, outer-inner

            //the top resource with the bar number
            String topResource = "top" + String.valueOf(j);

            //the bottom resource with the bar number
            String botResource = "bot" + String.valueOf(j);

            //if the bar position is 0 then the top bar must show inner position
            // and the bottom bar must show outer position
            if (verticalBars.charAt(i) == '0') {

                //getting the resource identifier of the top bar
                int resID = context.getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to top inner image
                imageView.setImageResource(R.drawable.topinner);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(topResource + "inner");

                //getting the resource identifier of the bottom bar
                resID = context.getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to bottom outer image
                imageView.setImageResource(R.drawable.bottomouter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(botResource+"outer");
            }

            //if the bar position is 1 then the top bar must show center position
            // and the bottom bar must show center position
            if (verticalBars.charAt(i) == '1') {

                //getting the resource identifier of the top bar
                int resID = context.getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to top center image
                imageView.setImageResource(R.drawable.topcenter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(topResource + "center");

                //getting the resource identifier of the bottom bar
                resID = context.getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to bottom center image
                imageView.setImageResource(R.drawable.bottomcenter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(botResource+"center");
            }

            //if the bar position is 2 then the top bar must show outer position
            // and the bottom bar must show inner position
            if (verticalBars.charAt(i) == '2') {

                //getting the resource identifier of the top bar
                int resID = context.getResources().getIdentifier(topResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to top outer image
                imageView.setImageResource(R.drawable.topouter);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(topResource + "outer");

                //getting the resource identifier of the bottom bar
                resID = context.getResources().getIdentifier(botResource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to bottom inner image
                imageView.setImageResource(R.drawable.bottominner);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag(botResource+"inner");
            }
        }
    }

}
