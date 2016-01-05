package logic.main.com.boardgame;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;


public class BoardImageSetter {
    ImageView imageView;

    public void setBoardImages(Board board, Context context, Activity mainActivity) {

        String boardConfig = board.getBoardConfiguration();
        int i;
        int suffix = 1;
        for (i = 0; i < boardConfig.length(); i++) {

            int j = i + 1;
            String prefix = "a";

            if (j > 7 && j <= 14) {
                prefix = "b";
            }
            if (j > 14 && j <= 21) {
                prefix = "c";
            }
            if (j > 21 && j <= 28) {
                prefix = "d";
            }
            if (j > 28 && j <= 35) {
                prefix = "e";
            }
            if (j > 35 && j <= 42) {
                prefix = "f";
            }
            if (j > 42 && j <= 49) {
                prefix = "g";
            }

            String resource = prefix + String.valueOf(suffix);
            //Log.e("resourceInaction", resource);
            if (boardConfig.charAt(i) == 'h') {
                int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                imageView.setImageResource(R.drawable.hole);
                imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                imageView.setTag("hole");
            }

            if (boardConfig.charAt(i) == 'r') {
                int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                String tileTag = (String) imageView.getTag();
                // Log.e("tileTag",tileTag);

                if(tileTag!=null) {
                    if (!tileTag.matches(".*\\d+.*")) {
                        imageView.setImageResource(R.drawable.brownpiece);
                        imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                        imageView.setTag("brownpiece");

                    } else if (tileTag.matches(".*\\d+.*")) {
                        tileTag = tileTag.replaceAll("\\D+", "");
                        if (tileTag.equals("1")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.brown1);
                            imageView.setTag("brown1");
                        }
                        if (tileTag.equals("2")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.brown2);
                            imageView.setTag("brown2");
                        }
                        if (tileTag.equals("3")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.brown3);
                            imageView.setTag("brown3");
                        }
                        if (tileTag.equals("4")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.brown4);
                            imageView.setTag("brown4");
                        }
                    }
                }else{
                    imageView.setImageResource(R.drawable.brownpiece);
                    imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                    imageView.setTag("brownpiece");
                }
            }
            if (boardConfig.charAt(i) == 'b') {
                int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");
                imageView = (ImageView) mainActivity.findViewById(resID);
                String tileTag=(String)imageView.getTag();
                if(tileTag!=null){
                    if (!tileTag.matches(".*\\d+.*")) {
                        imageView.setImageResource(R.drawable.darkpiece);
                        imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                        imageView.setTag("darkpiece");

                    } else if (tileTag.matches(".*\\d+.*")) {

                        tileTag = tileTag.replaceAll("\\D+", "");
                        if (tileTag.equals("1")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.dark1);
                            imageView.setTag("dark1");
                        }
                        if (tileTag.equals("2")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.dark2);
                            imageView.setTag("dark2");
                        }
                        if (tileTag.equals("3")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.dark3);
                            imageView.setTag("dark3");
                        }
                        if (tileTag.equals("4")) {
                            imageView = (ImageView) mainActivity.findViewById(resID);
                            imageView.setImageResource(R.drawable.dark4);
                            imageView.setTag("dark4");
                        }
                    }
                }else{
                    imageView.setImageResource(R.drawable.darkpiece);
                    imageView.setOnTouchListener((View.OnTouchListener)mainActivity);
                    imageView.setTag("darkpiece");
                }
            }
            suffix++;
            if (suffix > 7) {
                suffix = 1;
            }

        }


    }
}
