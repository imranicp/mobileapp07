package logic.main.com.boardgame.support;

import android.app.Activity;
import android.widget.ImageView;

import logic.main.com.boardgame.R;
import logic.main.com.boardgame.core.Board;
import logic.main.com.boardgame.core.MoveController;


//the bead placer sets the image of the tile on which a bead is placed
//also it places the bead according to the player number
//if the bead is of player 1 then player1's corresponding image will go on that tile position
public class BeadPlacer {

    //the image view object to be used to set the images
    ImageView imageView;

    //placing the beads on the screen, the board is used to set the moving player accordingly
    //which means a bead is placed successfully
    //the bead count is the available number of beads which can be placed on the tiles on the board
    //the id is the id of the tile on which the bead must be placed
    //the activity is used to find the image resources by their id
    public int placeBeads(int beadCount,String tag, int id,Board board,Activity mainActivity) {

        //move controller object is used to call the set moving player function on the board object
        MoveController moveController = new MoveController();

        //if the tile on which the bead is placed is a browntile which corresponds to a horizontal bar color
        //number of beads available must be greater than 0
        if (tag.equals("brownpiece") && beadCount != 0) {

            //if the moving player is 1 set the player1's bead image on the tile
            if (board.getMovingPlayer() == 1) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 1 bead
                imageView.setImageResource(R.drawable.brown1);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("brown1");
            }

            //if the moving player is 2 set the player2's bead image on the tile
            if (board.getMovingPlayer() == 2) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 2 bead
                imageView.setImageResource(R.drawable.brown2);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("brown2");
            }

            //if the moving player is 3 set the player3's bead image on the tile
            if (board.getMovingPlayer() == 3) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 3 bead
                imageView.setImageResource(R.drawable.brown3);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("brown3");
            }

            //if the moving player is 4 set the player4's bead image on the tile
            if (board.getMovingPlayer() == 4) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 4 bead
                imageView.setImageResource(R.drawable.brown4);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("brown4");
            }

            //setting the player who must perform the next move
            board = moveController.setMovingPlayer(board);

            //decrease the count of bead available by 1
            beadCount--;

        }
        //if the tile on which the bead is placed is a darkpiece tile which corresponds to a vertical bar color
        //number of beads available must be greater than 0
        else if (tag.equals("darkpiece") && beadCount != 0) {

            //if the moving player is 1 set the player1's bead image on the tile
            if (board.getMovingPlayer() == 1) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 1 bead
                imageView.setImageResource(R.drawable.dark1);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("dark1");
            }

            //if the moving player is 2 set the player2's bead image on the tile
            if (board.getMovingPlayer() == 2) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 2 bead
                imageView.setImageResource(R.drawable.dark2);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("dark2");
            }

            //if the moving player is 3 set the player3's bead image on the tile
            if (board.getMovingPlayer() == 3) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 3 bead
                imageView.setImageResource(R.drawable.dark3);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("dark3");
            }

            //if the moving player is 3 set the player3's bead image on the tile
            if (board.getMovingPlayer() == 4) {

                //setting the imageview value according to the resourceID
                imageView = (ImageView) mainActivity.findViewById(id);

                //setting the imageview image resource to player 4 bead
                imageView.setImageResource(R.drawable.dark4);

                //setting a unique tag to the image
                //using this tag the bar can be identified on touch of any motion event
                imageView.setTag("dark4");
            }

            //setting the player who must perform the next move
            board = moveController.setMovingPlayer(board);

            //decrease the count of bead available by 1
            beadCount--;
        }

        //return the updated count of available beads after a bead is placed on the board
        return beadCount;

    }
}
