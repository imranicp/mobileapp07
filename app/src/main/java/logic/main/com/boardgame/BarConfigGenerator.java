package logic.main.com.boardgame;

import java.util.Random;

public class BarConfigGenerator {


    public Board generateBarConfig(Board board){

        String postionsOfHorizontalBars = "";
        String postionsOfVerticalBars = "";
        int i;
        for (i = 0; i < 7; i++) {

            postionsOfHorizontalBars = postionsOfHorizontalBars + String.valueOf(randInt(0, 2));

        }
        board.setPostionsOfHorizontalBars(postionsOfHorizontalBars);
        System.out.println(board.getPostionsOfHorizontalBars());
        for (i = 0; i < 7; i++) {

            postionsOfVerticalBars = postionsOfVerticalBars + String.valueOf(randInt(0, 2));
        }
        board.setPostionsOfVerticalBars(postionsOfVerticalBars);
        return  board;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
