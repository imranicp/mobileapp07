package logic.main.com.boardgame.core;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

//this class implements the functionality of AI for the game
public class VsComputer {


    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    // this function is called for the computer to generate a possible move
    public String computermove(Board board) {

        // first the AI finds the possible human positions from the board

        //listOfHumanPositions hold the list of positions of human's bead
        //positions are represented by tile number can be anything from 0 to 49
        List<Integer> listOfHumanPositions = new ArrayList<Integer>();

        //i,j are looping variables
        int i, j;

        //the current bead configuration from the board object
        String beadConf = board.getBeadConfiguration();

        //looping on the bead configuration
        for (i = 0; i < board.getBeadConfiguration().length(); i++) {

            //if there is a human's bead which is represented by 1
            //then add that position to the list
            if (beadConf.charAt(i) == '1') {

                //adding to the list
                listOfHumanPositions.add(i + 1);
            }
        }


        //next we find the resource names of those tiles which have human's bead

        //the tiles have ids as a1,a2,a3,a4,a5,a6,a7,b1,b2,b2,b3,b4,b5,b6,b7.. so on....till g7
        //the suffix variable to form the resource name to get its identifier from the context
        int suffix = 1;

        //List which has the resource names
        List<String> resourceList = new ArrayList<String>();

        // loop for 49 tiles on the board

        for (i = 0; i < 49; i++) {
            //j increments i by 1 as tiles are from 1 to 49
            j = i + 1;

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

            //if in the list of humanPositions the value of j (tile value)
            //is found then we add that resource name to the resource list
            for (Integer val : listOfHumanPositions) {

                //when the list has the tile value
                if (val == j)
                    //adding resource to the list
                    resourceList.add(resource);

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

        //now we find the possible bars which can be moved to make human's bead to fall
        //for example the resource value is b5 then the possible bars which can be moved are
        //horizontal bar number 2 , and vertical bar number 5
        //as rows are represented by a,b,c,d,e,f,g and verticals are represented by 1,2,3,4,5,6,7
        //We use a set for this operation as we don't want duplicate bar values
        Set<String> suggestedBarSet = new HashSet<String>();

        //looping on the list of resources determined
        for (String resource : resourceList) {

            Log.e("resource", resource);

            //for horizontal bars row 'a'
            if (resource.charAt(0) == 'a') {

                //suggested bar is added to set
                suggestedBarSet.add("h1");
            }

            //for horizontal bars row 'b'
            if (resource.charAt(0) == 'b') {

                //suggested bar is added to set
                suggestedBarSet.add("h2");
            }

            //for horizontal bars row 'c'
            if (resource.charAt(0) == 'c') {

                //suggested bar is added to set
                suggestedBarSet.add("h3");
            }

            //for horizontal bars row 'd'
            if (resource.charAt(0) == 'd') {

                //suggested bar is added to set
                suggestedBarSet.add("h4");
            }

            //for horizontal bars row 'e'
            if (resource.charAt(0) == 'e') {

                //suggested bar is added to set
                suggestedBarSet.add("h5");
            }

            //for horizontal bars row 'f'
            if (resource.charAt(0) == 'f') {

                //suggested bar is added to set
                suggestedBarSet.add("h6");
            }

            //for horizontal bars row 'g'
            if (resource.charAt(0) == 'g') {

                //suggested bar is added to set
                suggestedBarSet.add("h7");
            }

            //for vertical bar 1
            if (resource.charAt(1) == '1') {

                //suggested bar is added to set
                suggestedBarSet.add("v1");

            }

            //for vertical bar 2
            if (resource.charAt(1) == '2') {

                //suggested bar is added to set
                suggestedBarSet.add("v2");

            }

            //for vertical bar 3
            if (resource.charAt(1) == '3') {

                //suggested bar is added to set
                suggestedBarSet.add("v3");

            }

            //for vertical bar 4
            if (resource.charAt(1) == '4') {

                //suggested bar is added to set
                suggestedBarSet.add("v4");
            }

            //for vertical bar 5
            if (resource.charAt(1) == '5') {

                //suggested bar is added to set
                suggestedBarSet.add("v5");
            }

            //for vertical bar 6
            if (resource.charAt(1) == '6') {

                //suggested bar is added to set
                suggestedBarSet.add("v6");
            }

            //for vertical bar 7
            if (resource.charAt(1) == '7') {

                //suggested bar is added to set
                suggestedBarSet.add("v7");
            }
        }

        //Now based on the suggested bar set we determine whether those bars can be moved inwards 'i' or outwards 'o'
        //for bars in central position both type of moves can be performed
        //we check the configuration of horizontal,vertical bars for this

        //thus the suggested moves are added to the suggested move list
        List<String> suggestedMoveList = new ArrayList<String>();

        //looping on the suggestedBarSet
        for (String suggestedBar : suggestedBarSet) {

            //getting the number of the bar for which we need to determine the move type
            int pos = Character.getNumericValue(suggestedBar.charAt(1));

            //subtracting it by one as bar numbers are from 1 to 7
            pos = pos - 1;

            //if the suggested bar is horizontal
            if (suggestedBar.charAt(0) == 'h') {

                //get the position of the bar from the horizontal bar configuration
                char barPosition = board.getPostionsOfHorizontalBars().charAt(pos);

                //if it is 1 means central position then both inner and outer moves can be performed
                if (barPosition == '1') {

                    //adding outward move to move list
                    suggestedMoveList.add(suggestedBar + 'o');

                    //adding inward move to move list
                    suggestedMoveList.add(suggestedBar + 'i');
                }

                //if it is 0 than outward move can be performed
                if (barPosition == '0') {

                    //adding outward move to move list
                    suggestedMoveList.add(suggestedBar + 'o');

                }

                //if it is 2 than inward move can be performed
                if (barPosition == '2') {

                    //adding inward move to move list
                    suggestedMoveList.add(suggestedBar + 'i');

                }
            }

            //if the suggested bar is vertical
            if (suggestedBar.charAt(0) == 'v') {

                //get the position of the bar from the vertical bar configuration
                char barPosition = board.getPostionsOfVerticalBars().charAt(pos);

                //if it is 1 means central position then both inner and outer moves can be performed
                if (barPosition == '1') {

                    //adding outward move to move list
                    suggestedMoveList.add(suggestedBar + 'o');

                    //adding inward move to move list
                    suggestedMoveList.add(suggestedBar + 'i');
                }

                //if it is 0 than outward move can be performed
                if (barPosition == '0') {

                    //adding outward move to move list
                    suggestedMoveList.add(suggestedBar + 'o');

                }

                //if it is 2 than inward move can be performed
                if (barPosition == '2') {

                    //adding inward move to move list
                    suggestedMoveList.add(suggestedBar + 'i');

                }
            }
        }

        //now we use a dummy class of the moveController to test whether these moves are fruitful or not
        //the probable moves which are valid are added to probableMoveList
        List<String> probableMoveList = new ArrayList<String>();

        //bestMove
        //best move is calculated by performing all the moves and finding which move is the most effective one
        //which has the maximum posibility of making maximum human bead fall
        String bestMove = "";

        //this variable is used to find the change in count of human's bead which might happen after the suggested move is performed
        int bestChangeInCount = 0;

        for (String suggestedMove : suggestedMoveList) {
            Log.e("checking", "------------------------------------------------------------------");

            Log.e("suggestedMove", suggestedMove);

            //DummyBoard object is created
            DummyBoard testBoard = new DummyBoard(board);

            //the original count of human's bead is taken into account
            int humanBeadCount = 0;

            //the string oldBeadConfiguration holds the original configuration of beads
            String oldBeadConfiguration = board.getBeadConfiguration();

            Log.e("oldBeadConfiguration", oldBeadConfiguration);

            //we set the value of the humanBeadCount which by counting the bead named 1 on the bead configuration
            humanBeadCount = oldBeadConfiguration.length() - oldBeadConfiguration.replace("1", "").length();

            Log.e("humanBeadCount", String.valueOf(humanBeadCount));

            //we create a testInput string which is used to find whether the suggested move will be successful or not
            //this is similar to a standard game input string
            String testInput = String.valueOf(testBoard.getNumberOfPlayers()) +
                    String.valueOf(testBoard.getMovingPlayer()) + testBoard.getPostionsOfHorizontalBars()
                    + testBoard.getPostionsOfVerticalBars() + testBoard.getBeadConfiguration() + suggestedMove;
            testBoard.setInput(testInput);

            //we create variables to determine whether there was a change on the count of human's bead after a dummy move is performed
            //based on the suggested move
            int changeInHumanBeadCount = 0, newHumanBeadCount = 0;

            //dummy controller object
            DummyMoveController testMoveController = new DummyMoveController();
            try {

                //we set the output of the move into the dummy object's output
                testBoard.setOutput(testMoveController.moveTest(testBoard.getInput()));

                //we find the new bead configuration after the dummy move is performed
                String newBeadConfiguration = testBoard.getBeadConfiguration();

                Log.e("newBeadConfiguration", newBeadConfiguration);

                //the new human bead count is taken in to consideration which means that
                // we can find the change is count if a bead has fallen on performing the dummy move
                newHumanBeadCount = newBeadConfiguration.length() - newBeadConfiguration.replace("1", "").length();

                Log.e("newHumanBeadCount", String.valueOf(newHumanBeadCount));

                //if there is a change in human bead count then we can determine the best move
                if (newHumanBeadCount != humanBeadCount) {

                    Log.e("humanBeadCount", String.valueOf(humanBeadCount));

                    //change in human bead count is the old human bead count - the new human bead count after performing the dummy move
                    changeInHumanBeadCount = humanBeadCount - newHumanBeadCount;

                    Log.e("changeInHumanBeadCount", String.valueOf(changeInHumanBeadCount));

                    //if the change in bead count is greater than last best change in count
                    // we can make current move as the best move
                    if (changeInHumanBeadCount > bestChangeInCount) {

                        //bestchange in count to the new change in count
                        bestChangeInCount = changeInHumanBeadCount;

                        Log.e("bestChangeInCount", String.valueOf(bestChangeInCount));

                        //setting best move to the suggested move
                        bestMove = suggestedMove;

                        Log.e("bestMoveNowIs", String.valueOf(bestMove));
                    }

                }
                //if we are able to find any change in human's bead count then the suggested move is added to probable moves
                else {
                    Log.e("notHelpfulMove", suggestedMove);

                    //adding the suggested move to probable move list
                    probableMoveList.add(suggestedMove);
                }


            } catch (Exception e) {
                Log.e("notAgoodMove", suggestedMove);

            }
            Log.e("checking", "------------------------------------------------------------------");

        }

        for (String probableMove : probableMoveList) {
            Log.e("probableMove", probableMove);
        }

        // if we are able to find the best move which has to be performed
        // then we return the value of the best move
        if (!bestMove.equals("")) {
            Log.e("bestMove", bestMove);
            //return the best move value
            return bestMove;
        }

        //move made randomly as we were not able to find a best moves were found
        String anyProbableMove = probableMoveList.get(randInt(0, probableMoveList.size() - 1));

        Log.e("anyProbableMove", anyProbableMove);
        Log.e("majboori", anyProbableMove);

        //the anyProbableMove is returned in case no best move is found
        return anyProbableMove;
    }


}
