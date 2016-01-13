package logic.main.com.boardgame.support;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import logic.main.com.boardgame.core.Board;


public class BeadConfingSetter {


    public void setBeadConfig(Board board,Context context,Activity mainActivity) {
        String beadConfiguration="";
        ImageView boardImage;
        int i;
        int suffix = 1;
        for(i=0;i<49;i++){
            String prefix = "a";

            if (i >= 7 && i < 14) {
                prefix = "b";
            }
            if (i >= 14 && i < 21) {
                prefix = "c";
            }
            if (i >= 21 && i < 28) {
                prefix = "d";
            }
            if (i >= 28 && i < 35) {
                prefix = "e";
            }
            if (i >= 35 && i < 42) {
                prefix = "f";
            }
            if (i >= 42 && i < 49) {
                prefix = "g";
            }
            String resource = prefix + String.valueOf(suffix);
            // Log.e("resourceInaction", resource);
            int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
            boardImage = (ImageView) mainActivity.findViewById(resID);
            String imageTag=(String)boardImage.getTag();
            // Log.e("tagInaction", imageTag);

            if(imageTag.contains("1")){
                beadConfiguration=beadConfiguration+"1";
            }
            else if(imageTag.contains("2")){
                beadConfiguration=beadConfiguration+"2";
            }
            else if(imageTag.contains("3")){
                beadConfiguration=beadConfiguration+"3";
            }
            else if(imageTag.contains("4")){
                beadConfiguration=beadConfiguration+"4";
            }
            else {
                beadConfiguration=beadConfiguration+"0";
            }
            suffix++;
            if (suffix > 7) {
                suffix = 1;
            }

        }
        board.setBeadConfiguration(beadConfiguration);
    }
}
