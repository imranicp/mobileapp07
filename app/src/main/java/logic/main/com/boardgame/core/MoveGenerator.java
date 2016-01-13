package logic.main.com.boardgame.core;

import android.util.Log;
import android.view.View;

public class MoveGenerator {

    public String generateMove(View view, String flingType) {
        String move="";
        String viewTag=(String) view.getTag();
        Log.e("moveTag", viewTag);
        Log.e("flingType",flingType);

        if(flingType == "topToBottom"){

            if(viewTag.contains("top")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"i";
            }

            if(viewTag.contains("bot")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"i";
            }
        }

        if(flingType == "bottomToTop"){

            if(viewTag.contains("bot")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"o";
            }

            if(viewTag.contains("top")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="v"+viewTag+"o";
            }
        }

        if(flingType == "rightToLeft"){

            if(viewTag.contains("l")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"o";
            }

            if(viewTag.contains("r")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"o";
            }
        }

        if(flingType == "leftToRight"){

            if(viewTag.contains("l")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"i";
            }

            if(viewTag.contains("r")){
                viewTag = viewTag.replaceAll("\\D+","");
                move="h"+viewTag+"i";
            }
        }

        return move;
    }

}
