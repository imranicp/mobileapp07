package logic.main.com.boardgame.core;

import android.util.Log;
import android.view.View;

//This class generates a move according to the type of fling motion event on a bar on the screen
public class MoveGenerator {

    //the generateMove function takes the view(bar) and movement(fling type) which is performed
    public String generateMove(View view, String flingType) {
        //the move which has to be generated
        String move="";

        //the tag of the view which is moved
        //we use the tag of the view to know whether its a top vertical bar, or a bottom vertical bar
        //or a left horizontal bar or a right horizontal bar
        //the view tag also contains the number of the bar
        String viewTag=(String) view.getTag();


        Log.e("moveTag", viewTag);
        Log.e("flingType",flingType);

        //if the motion event on the screen is a top to bottom
        //then a either a top vertical bar is moved inside 'i'
        //or a bottom vertical bar is moved outside
        //it means that there is an inward movement of bar
        if (flingType.equals("topToBottom")) {

            //if the bar moved is a top vertical bar
            if(viewTag.contains("top")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="v"+viewTag+"i";

            }

            //if the bar moved is a bottom vertical bar
            if(viewTag.contains("bot")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="v"+viewTag+"i";

            }
        }

        //if the motion event on the screen is a bottom to top
        //then a either a top vertical bar is moved outside 'o'
        //or a bottom vertical bar is moved inside
        //it means that there is an outward movement of bar
        if (flingType.equals("bottomToTop")) {

            //if the bar moved is a bottom vertical bar
            if(viewTag.contains("bot")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="v"+viewTag+"o";
            }

            //if the bar moved is a top vertical bar
            if(viewTag.contains("top")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="v"+viewTag+"o";

            }
        }

        //if the motion event on the screen is a right to left
        //then a either a left horizontal bar is moved outside 'o'
        //or a right horizontal bar is moved inside
        //it means that there is an outward movement of bar
        if (flingType.equals("rightToLeft")) {

            //if the bar moved is a left horizontal bar
            if(viewTag.contains("l")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="h"+viewTag+"o";
            }

            //if the bar moved is a right horizontal bar
            if(viewTag.contains("r")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="h"+viewTag+"o";
            }
        }

        //if the motion event on the screen is a left to right
        //then a either a left horizontal bar is moved inside 'i'
        //or a right horizontal bar is moved outside
        //it means that there is an inward movement of bar
        if (flingType.equals("leftToRight")) {

            //if the bar moved is a left horizontal bar
            if(viewTag.contains("l")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="h"+viewTag+"i";
            }

            //if the bar moved is a right horizontal bar
            if(viewTag.contains("r")){

                //replacing the alphabets leaving only the bar number
                viewTag = viewTag.replaceAll("\\D+","");

                //setting the move value
                move="h"+viewTag+"i";
            }
        }

        //returning the generated move value
        return move;
    }

}
