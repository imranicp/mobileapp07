package logic.main.com.boardgame.core;

import java.util.Random;

public class BarConfigGenerator {

    /* Function generating random number which is in the given range of numbers
    * for BarConfig it generates numbers 0,1,2 randomly*/
    public static int randInt(int min, int max) {
        //random object creation
        Random rand = new Random();

        /*random number generation using nextInt function
        value between 0 (inclusive) and the specified value
        (exclusive) , drawn from this random number generator's sequence
         added 1 as the number should be exclusive */
        int randomNum = rand.nextInt((max - min) + 1) + min;

        /*returning randomNum*/
        return randomNum;
    }

    public Board generateBarConfig(Board board){
        /*String for horizontal bar positions*/
        String postionsOfHorizontalBars = "";

        /*String for vertical bar positions*/
        String postionsOfVerticalBars = "";

        /*looping variable*/
        int i;

        /*loop goes on for 7 times as there are 7 horizontal bars*/
        for (i = 0; i < 7; i++) {
            /*adding generated random values to the String for horizontal bar positions*/
            postionsOfHorizontalBars = postionsOfHorizontalBars + String.valueOf(randInt(0, 2));

        }
        /*setting the String for horizontal bar positions in the board object*/
        board.setPostionsOfHorizontalBars(postionsOfHorizontalBars);

        /*loop goes on for 7 times as there are 7 horizontal bars*/
        for (i = 0; i < 7; i++) {
            /*adding generated random values to the String for vertical bar positions*/
            postionsOfVerticalBars = postionsOfVerticalBars + String.valueOf(randInt(0, 2));
        }
        /*setting the String for vertical bar positions in the board object*/
        board.setPostionsOfVerticalBars(postionsOfVerticalBars);
        /*returning the board object*/
        return  board;
    }
}
