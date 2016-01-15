package logic.main.com.boardgame.support;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.core.Board;

//This class is used to set the board images
//if a bead is there then the bead's image must be shown on the board
//also when a move is performed the bead configuration might change as there are beads which fall
// the board image changes from a brown to dark or hole or vice versa
//this class sets the board images by finding the bead images from the image tag on the board
//if there is no bead the corresponding image is according to the board configuration
//the tiles have ids as a1,a2,a3,a4,a5,a6,a7,b1,b2,b2,b3,b4,b5,b6,b7.. so on....till g7
public class BoardImageSetter {

    //the image view object to be used to set the images
    ImageView imageView;

    //the board is used to set the bead configuration value
    //the context is used to get the image resources
    //the activity is used to find the image resources by their id
    public void setBoardImages(Board board, Context context, Activity mainActivity) {

        //the string which holds the board configuration
        //board configuration is 'h'=hole,'r'=brown piece, 'b'=dark piece
        String boardConfig = board.getBoardConfiguration();

        //the loop variable loops on the various images on the board
        int i;

        //the tiles have ids as a1,a2,a3,a4,a5,a6,a7,b1,b2,b2,b3,b4,b5,b6,b7.. so on....till g7
        //the suffix variable to form the resource name to get its identifier from the context
        int suffix = 1;

        // loop for 49 tiles on the board
        for (i = 0; i < boardConfig.length(); i++) {

            //the j value is used to determine the prefix which means the value goes from 1 to 49
            int j = i + 1;

            //the prefix value changes for every row on the board
            //the rows are represented by 7 prefixes a,b,c,d,e,f,g

            //initially prefix value 'a': bar a1 to a7
            String prefix = "a";

            //for tiles in second row: tiles b1 to b7
            if (j > 7 && j <= 14) {

                //prefix value 'b'
                prefix = "b";

            }

            //for tiles in third row: tiles c1 to c7
            if (j > 14 && j <= 21) {

                //prefix value 'c'
                prefix = "c";

            }

            //for tiles in fourth row: tiles d1 to d7
            if (j > 21 && j <= 28) {

                //prefix value 'd'
                prefix = "d";

            }

            //for tiles in fifth row: tiles e1 to e7
            if (j > 28 && j <= 35) {

                //prefix value 'e'
                prefix = "e";

            }

            //for tiles in sixth row: tiles f1 to f7
            if (j > 35 && j <= 42) {

                //prefix value 'f'
                prefix = "f";

            }

            //for tiles in seventh row : tiles g1 to g7
            if (j > 42 && j <= 49) {

                //prefix value 'g'
                prefix = "g";

            }

            //the resource variable is formed adding prefix to suffix
            String resource = prefix + String.valueOf(suffix);

            //if there is 'h' in the bead configuration means a hole must be set to the imageview
            if (boardConfig.charAt(i) == 'h') {

                //getting the resource identifier of the tile
                int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting the imageview image resource to hole image
                imageView.setImageResource(R.drawable.hole);

                //setting on touch listener to the imageview
                imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                //setting a unique tag to the image
                //using this tag the image can be identified on the tile
                imageView.setTag("hole");
            }

            //if there is 'r' in the bead configuration means a brown image must be set to the imageview
            if (boardConfig.charAt(i) == 'r') {

                //getting the resource identifier of the tile
                int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting a unique tag to the image
                //using this tag the image can be identified on the tile
                String tileTag = (String) imageView.getTag();

                //the tile image must be changed to a brown piece which means a brown image must be set
                //but if there is a player's bead on it then the image with a bead must be set
                //tile tag must be not null after the initial setting
                if(tileTag!=null) {

                    //if there is no player number in the tile tag then we can just put the image in which there is no bead
                    if (!tileTag.matches(".*\\d+.*")) {

                        //setting the imageview image resource to brownpiece image
                        imageView.setImageResource(R.drawable.brownpiece);

                        //setting on touch listener to the imageview
                        imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                        //setting a unique tag to the image
                        //using this tag the tile can be identified
                        imageView.setTag("brownpiece");

                    }
                    //in case there is a bead on the tile means the tag contains a number
                    else if (tileTag.matches(".*\\d+.*")) {

                        //removing all the alphabets in the tag to get the player number who is having bead on the tile
                        tileTag = tileTag.replaceAll("\\D+", "");

                        //player 1 has a bead on the tile
                        if (tileTag.equals("1")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to brownpiece image with player 1 bead
                            imageView.setImageResource(R.drawable.brown1);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("brown1");
                        }

                        //player 2 has a bead on the tile
                        if (tileTag.equals("2")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to brownpiece image with player 2 bead
                            imageView.setImageResource(R.drawable.brown2);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("brown2");
                        }

                        //player 3 has a bead on the tile
                        if (tileTag.equals("3")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to brownpiece image with player 3 bead
                            imageView.setImageResource(R.drawable.brown3);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("brown3");
                        }

                        //player 4 has a bead on the tile
                        if (tileTag.equals("4")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to brownpiece image with player 4 bead
                            imageView.setImageResource(R.drawable.brown4);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("brown4");
                        }
                    }
                }
                //when first time the images are set on the tile that time the tag is null
                //if there is 'r' in the bead configuration means a brown image must be set to the imageview
                else {

                    //setting the imageview image resource to brownpiece image
                    imageView.setImageResource(R.drawable.brownpiece);

                    //setting on touch listener to the imageview
                    imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                    //setting a unique tag to the image
                    //using this tag the tile can be identified
                    imageView.setTag("brownpiece");
                }
            }

            //if there is 'b' in the bead configuration means a dark piece image must be set to the imageview
            if (boardConfig.charAt(i) == 'b') {

                //getting the resource identifier of the tile
                int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");

                //setting the imageview value according to the determined resourceID
                imageView = (ImageView) mainActivity.findViewById(resID);

                //setting a unique tag to the image
                //using this tag the image can be identified on the tile
                String tileTag=(String)imageView.getTag();


                //the tile image must be changed to a dark piece which means a dark piece image must be set
                //but if there is a player's bead on it then the image with a bead must be set
                //tile tag must be not null after the initial setting
                if(tileTag!=null){

                    //if there is no player number in the tile tag then we can just put the image in which there is no bead
                    if (!tileTag.matches(".*\\d+.*")) {

                        //setting the imageview image resource to darkpiece image
                        imageView.setImageResource(R.drawable.darkpiece);

                        //setting on touch listener to the imageview
                        imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                        //setting a unique tag to the image
                        //using this tag the image can be identified on the tile
                        imageView.setTag("darkpiece");

                    }
                    //in case there is a bead on the tile means the tag contains a number
                    else if (tileTag.matches(".*\\d+.*")) {

                        //removing all the alphabets in the tag to get the player number who is having bead on the tile
                        tileTag = tileTag.replaceAll("\\D+", "");

                        //player 1 has a bead on the tile
                        if (tileTag.equals("1")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to darkpiece image with player 1 bead
                            imageView.setImageResource(R.drawable.dark1);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("dark1");
                        }

                        //player 2 has a bead on the tile
                        if (tileTag.equals("2")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to darkpiece image with player 2 bead
                            imageView.setImageResource(R.drawable.dark2);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("dark2");
                        }

                        //player 3 has a bead on the tile
                        if (tileTag.equals("3")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to darkpiece image with player 3 bead
                            imageView.setImageResource(R.drawable.dark3);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("dark3");
                        }

                        //player 4 has a bead on the tile
                        if (tileTag.equals("4")) {

                            //setting the imageview value according to the determined resourceID
                            imageView = (ImageView) mainActivity.findViewById(resID);

                            //setting the imageview image resource to darkpiece image with player 4 bead
                            imageView.setImageResource(R.drawable.dark4);

                            //setting a unique tag to the image
                            //using this tag the tile can be identified
                            imageView.setTag("dark4");
                        }
                    }
                }
                //when first time the images are set on the tile that time the tag is null
                //if there is 'b' in the bead configuration means a brown image must be set to the imageview
                else {

                    //setting the imageview image resource to darkpiece image
                    imageView.setImageResource(R.drawable.darkpiece);

                    //setting on touch listener to the imageview
                    imageView.setOnTouchListener((View.OnTouchListener) mainActivity);

                    //setting a unique tag to the image
                    //using this tag the tile can be identified
                    imageView.setTag("darkpiece");
                }
            }

            //increasing the suffix value by 1 to move from one tile to the next
            //it goes from 1 till 7 as there are 7 tiles on every board
            suffix++;

            //if the suffix value is greater 7 means that we have go to the next row in the board
            if (suffix > 7) {

                //suffix value reset to 1
                suffix = 1;

            }

        }
    }
}
