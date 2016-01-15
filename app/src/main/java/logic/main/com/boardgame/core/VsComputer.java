package logic.main.com.boardgame.core;

import android.util.Log;

import java.util.Random;


public class VsComputer {
    public String computermove(Board board)  {


        String sideofbar="";

        String move="";
        int i= randInt(0,1);


        String b = "";
        if ( i==0 ) {
            sideofbar = "h";
            move=move+sideofbar;
            b = beadplaced(board,move);

        }else{
            sideofbar="v";
            move=move+sideofbar;
            b = beadplaced(board,move);


        }
        RuleController testRuleController = new RuleController();
        MoveController testMoveController=new MoveController();
        Board testBoard=new Board();
        testBoard=board;
        String input = String.valueOf(testBoard.getNumberOfPlayers()) + String.valueOf(testBoard.getMovingPlayer()) + testBoard.getPostionsOfHorizontalBars() + testBoard.getPostionsOfVerticalBars() + testBoard.getBeadConfiguration() + b;

        Log.e("testmove:", b);
        Log.e("test input", input);
        testBoard.setInput(input);
        try {
            testBoard = testMoveController.moveTest(testBoard);
            // testBoard = testRuleController.rulesCheck(b,testBoard);
            // Log.e("test ","clear");
        } catch (Exception e) {
            computermove(board);
        }


        return b;

    }
    public String beadplaced(Board board,String x)  {
        int j= randInt(0,6);
        String s="";
        String move="";
        String c = "";

        s = board.getBeadConfiguration().substring((j*7),((j*7)+6));
        if(!s.contains("1")||!s.contains("2")){
            beadplaced(board,x);


        }else{


            c=checkstatusofbar(board, j, x);


        }


        return c;
    }
    public String checkstatusofbar(Board board,int j,String x){

        String s;
        String move="";
        int k=randInt(0,1);
        if(k==0){
            s = "i";
            if(!x.equals("h")){
                if(board.getPostionsOfVerticalBars().substring(j,j+1).equals("0")){
                    checkstatusofbar(board,j,x);
                }else{
                    move=move+x+String.valueOf((j+1))+"i";


                }

            }else{
                if(board.getPostionsOfHorizontalBars().substring(j,j+1).equals("0")){
                    checkstatusofbar(board,j,x);
                }else{
                    move=move+x+String.valueOf((j+1))+"i";


                }
            }

        }else{

            s = "o";
            if(!x.equals("h")){
                if(board.getPostionsOfVerticalBars().substring(j,j+1).equals("2")){
                    checkstatusofbar(board,j,x);
                }else{
                    move=move+x+String.valueOf((j+1))+"o";

                }

            }else{
                if(board.getPostionsOfHorizontalBars().substring(j,j+1).equals("2")){
                    checkstatusofbar(board,j,x);
                }else{
                    move=move+x+String.valueOf((j+1))+"o";

                }
            }

        }

        return  move;
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }




}
