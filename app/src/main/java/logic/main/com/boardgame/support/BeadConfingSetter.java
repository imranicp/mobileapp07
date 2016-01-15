package logic.main.com.boardgame.support;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import logic.main.com.boardgame.core.Board;


//This class is used to set the Bead Configuration inside the board object.
//when all the beads are placed the bead configuration should be generated for the board to perform moves
//also when a move is performed the bead configuration might change as there are beads which fall
// the board image changes from a brown to dark or hole or vice versa
//this class sets the bead configuration by finding the bead images from the image tag on the board
//the tiles have ids as a1,a2,a3,a4,a5,a6,a7,b1,b2,b2,b3,b4,b5,b6,b7.. so on....till g7
public class BeadConfingSetter {

    //the board is used to set the bead configuration value
    //the context is used to get the image resources
    //the activity is used to find the image resources by their id
    public void setBeadConfig(Board board,Context context,Activity mainActivity) {

        //the bead configuration to set
        String beadConfiguration="";

        //the image view object to be used to set the images
        ImageView boardImage;

        //the loop variable loops on the various images on the board
        int i;

        //the tiles have ids as a1,a2,a3,a4,a5,a6,a7,b1,b2,b2,b3,b4,b5,b6,b7.. so on....till g7
        //the suffix variable to form the resource name to get its identifier from the context
        int suffix = 1;

        // loop for 49 tiles on the board
        for(i=0;i<49;i++){

            //the prefix value changes for every row on the board
            //the rows are represented by 7 prefixes a,b,c,d,e,f,g

            //initially prefix value 'a': bar a1 to a7
            String prefix = "a";

            //for tiles in second row: tiles b1 to b7
            if (i >= 7 && i < 14) {

                //prefix value 'b'
                prefix = "b";

            }

            //for tiles in third row: tiles c1 to c7
            if (i >= 14 && i < 21) {

                //prefix value 'c'
                prefix = "c";

            }

            //for tiles in fourth row: tiles d1 to d7
            if (i >= 21 && i < 28) {

                //prefix value 'd'
                prefix = "d";

            }

            //for tiles in fifth row: tiles e1 to e7
            if (i >= 28 && i < 35) {

                //prefix value 'e'
                prefix = "e";

            }

            //for tiles in sixth row: tiles f1 to f7
            if (i >= 35 && i < 42) {

                //prefix value 'f'
                prefix = "f";

            }

            //for tiles in seventh row : tiles g1 to g7
            if (i >= 42 && i < 49) {

                //prefix value 'g'
                prefix = "g";

            }

            //the resource variable is formed adding prefix to suffix
            String resource = prefix + String.valueOf(suffix);


            //getting the resource identifier of the tile
            int resID = context.getResources().getIdentifier(resource, "id", "logic.main.com.boardgame");

            //setting the imageview value according to the determined resourceID
            boardImage = (ImageView) mainActivity.findViewById(resID);

            //getting the unique tag to the image
            //using this tag the tile can be identified to have the bead number on it
            //1,2,3,4 represent a player's bead, 0 means there is no bead on that tile
            String imageTag=(String)boardImage.getTag();

            //player 1's bead is on the tile
            if(imageTag.contains("1")){

                //adding 1 to the bead configuration
                beadConfiguration=beadConfiguration+"1";

            }
            //player 2's bead is on the tile
            else if(imageTag.contains("2")){

                //adding 2 to the bead configuration
                beadConfiguration=beadConfiguration+"2";

            }
            //player 3's bead is on the tile
            else if(imageTag.contains("3")){

                //adding 3 to the bead configuration
                beadConfiguration=beadConfiguration+"3";

            }
            //player 4's bead is on the tile
            else if(imageTag.contains("4")){

                //adding 4 to the bead configuration
                beadConfiguration=beadConfiguration+"4";

            }
            //no bead is on the tile
            else {

                //adding 0 to the configuration
                beadConfiguration=beadConfiguration+"0";

            }

            //increasing the suffix value by 1 to move from one tile to the next
            //it goes from 1 till 7 as there are 7 tiles on every row
            suffix++;

            //if the suffix value is greater 7 means that we have go to the next row in the board
            if (suffix > 7) {

                //suffix value reset to 1
                suffix = 1;
            }

        }

        //setting the generated board configuration to the bead configuration object
        board.setBeadConfiguration(beadConfiguration);
    }
}
