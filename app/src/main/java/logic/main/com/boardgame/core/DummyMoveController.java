package logic.main.com.boardgame.core;

import java.util.regex.Pattern;

public class DummyMoveController {


    /**
     * @param input The input is expected to be the configuration of the game. The
     *              input must be set into the board object
     * @return The result of the moveTest operation, the state of the game after
     * performing moves.
     * @throws Exception
     */

    public String moveTest(String input) throws Exception {
        Board board = new Board();
        board.setInput(input);

        //the rule controller object, the class where rules are checked
        RuleController ruleController = new RuleController();

        //the Bead Conf object, the class where holes are checked and beads fall
        BeadConf beadConf = new BeadConf();

        //the Board Conf object, the class where board configuration is generated
        //according the positions of horizontal and vertical bars
        BoardConf boardConf = new BoardConf();

        //the winner decider object, the class where winner is decided
        WinnerDecider winner = new WinnerDecider();

        //the elimination decider object, the class where a player elimination is determined
        EliminationDecider eliminationDecider = new EliminationDecider();

        //the game Exception class which generates exception messages according the conditions
        GameException gameException = new GameException();


        try {

            // checking the given input follows the correct pattern
            if (Pattern.matches(Constants.gamePattern, board.getInput())) {

                //from the board object input getting the number of players
                board.setNumberOfPlayers(Integer.parseInt(board.getInput().substring(0, 1)));

                //from the board object input getting the moving player
                board.setMovingPlayer(Integer.parseInt(board.getInput().substring(1, 2)));

                //from the board object input getting the positions of horizontal bars
                board.setPostionsOfHorizontalBars(board.getInput().substring(2, 9));

                //from the board object input getting the positions of vertical bars
                board.setPostionsOfVerticalBars(board.getInput().substring(9, 16));

                //from the board object input getting the bead configuration
                board.setBeadConfiguration(board.getInput().substring(16, 65));

                //from the board object input getting the sequence of moves to be performed
                board.setSequenceOfMoves(board.getInput().substring(65, board.getInput().length()));


                // Perform moves
                // The moves are moved into an array and the moves are performed
                // one at a time
                String[] movesArray = board.getSequenceOfMoves().split(Constants.movePattern);
                if (!board.getSequenceOfMoves().isEmpty()) {
                    for (String move : movesArray) {

                        // check all the rules before performing the move
                        board = ruleController.rulesCheck(move, board);

                        // update the position of the bars according to the move
                        //'h' means a horizontal bar has been moved
                        if (move.charAt(0) == Constants.charh) {

                            //setting the new position of horizontal bars as the a bar is moved
                            board.setPostionsOfHorizontalBars(makeMove(move,
                                    board.getPostionsOfHorizontalBars(), board));

                        }
                        //'v' means a horizontal bar has been moved
                        if (move.charAt(0) == Constants.charv) {

                            //setting the new position of vertical bars as the a bar is moved
                            //makeMove function sets the new position of the bar
                            //ex: outer to central
                            board.setPostionsOfVerticalBars(makeMove(move,
                                    board.getPostionsOfVerticalBars(), board));

                        }

                        // On a successful move the player who performed the
                        // move and the move are stored in history of moves
                        setMoves(move, board);

                        // generate the configuration of the board according to
                        // the new position of the bars
                        //ex: outer to central
                        board.setBoardConfiguration(boardConf.boardConfGenerator(
                                board.getPostionsOfHorizontalBars(),
                                board.getPostionsOfVerticalBars()));

                        // generate the configuration of beads after performing
                        // the moves
                        board.setBeadConfiguration(beadConf.beadConfGenerator(board.getBeadConfiguration(),
                                board.getBoardConfiguration()));

                        // check the bead configuration for the available
                        // players
                        board = beadConf.checkbeadConf(board);

                        // set the next possible moving player
                        board = setMovingPlayer(board);

                        // check if the player is eliminated, if yes set
                        // movingPlayer to the eligible player
                        board = eliminationDecider.checkElimination(board);

                        // check for win condition
                        board = winner.checkWin(board);

                        // set the output of the board
                        board.setOutput(String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer())
                                + board.getPostionsOfHorizontalBars()
                                + board.getPostionsOfVerticalBars() + board.getBeadConfiguration());

                    }
                } else {
                    // in case there are no moves to perform check for a winning
                    // condition
                    board = winner.checkWin(board);
                    // setting the input back to the output as there are no
                    // moves to perform
                    board.setOutput(String.valueOf(board.getNumberOfPlayers()) + String.valueOf(board.getMovingPlayer())
                            + board.getPostionsOfHorizontalBars()
                            + board.getPostionsOfVerticalBars() + board.getBeadConfiguration());
                }
                System.out.println(Constants.output);
                System.out.println(board.getOutput());
            } else {
                // throw exception that the input is not valid
                board.setExceptionMessage(gameException.exception(1));
                throw new GameException();

            }
        } catch (Exception e) {
            throw new Exception(board.getExceptionMessage(), e);
        }

        return board.getOutput();
    }

    /**
     * This function sets values to the history of moves which are being
     * performed
     *
     * @param move
     * @param board
     */

    //if a successful move is a perfomed then then that move must be stored in the history of moves
    //history of upto 4 moves is saved
    //a not null condition is checked as for the first time
    // the history of moves will have null values
    public void setMoves(String move, Board board) {

        //checking if third last move is not null
        if (board.getMoveThree() != null) {

            //setting the third last move into the fourth last move
            board.setMoveFour(board.getMoveThree());

        }
        //checking if second last move is not null
        if (board.getMoveTwo() != null) {

            //setting the second last move into the third last move
            board.setMoveThree(board.getMoveTwo());


        }

        //checking if last move is not null
        if (board.getMoveOne() != null) {

            //setting the last move into the second last move
            board.setMoveTwo(board.getMoveOne());

        }

        //setting the move which was just performed into the last move position
        board.setMoveOne(move + board.getMovingPlayer());

    }


    /**
     * This function will change the configuration of the bar which is moved for
     * example: The move is h3i and the horizontal bar configuration is 1022222,
     * the output will be 1012222
     *
     * @param move The move to be performed represented by a 3 character string
     *             where 1st character is the bar type, 2nd character is the bar
     *             number, and third character is the type of move which can be
     *             i=Inward or o=Outward
     * @param bar  The bar on which the move must be performed, it is a 7
     *             character string where each character represents the position
     *             of the bar 0 (inner), 1 (central), or 2 (outer)
     * @return Updated configuration of the bar after performing the move
     * @throws GameException
     */
    //this function tries to make a move which is changing the bar position
    //it checks whether its a valid move or not, if not then it throws an exception
    public String makeMove(String move, String bar, Board board) throws GameException {

        //exception object which is to be thrown in case of invalid move
        GameException gameException = new GameException();

        //if a horizontal bar is tried to be moved inside 'i'
        if (move.charAt(0) == Constants.charh && move.charAt(2) == Constants.chari) {

            // position of bar in the barConfig string will be 1 less than the
            // specified position
            int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;


            //changeBarval signifies the new bar position on a successful move
            //it initially takes the bar's current position
            char changeBarVal = bar.charAt(position);

            //if a bar is in central or outer position then it can be moved inside
            //else an exception must be thrown
            //if bar's current position is 1 or 2 then it will be moved inside
            if (changeBarVal == Constants.charone || changeBarVal == Constants.chartwo) {

                //if the value is 1 then it must be set to 0
                if (changeBarVal == Constants.charone) {
                    changeBarVal = Constants.charzero;
                }
                //else it must be set to 1
                else {
                    changeBarVal = Constants.charone;
                }

                //a new bar config must be generated from the old bar config
                StringBuilder newBarConfig = new StringBuilder(bar);

                //changing the bar position
                newBarConfig.setCharAt(position, changeBarVal);

                //setting the new bar config
                bar = newBarConfig.toString();

            } else {

                //throwing an exception with a message that the move is invalid
                //error code 1 means a horizontal bar is tried
                // to be moved from inner to further inner position
                board.setExceptionMessage(gameException.exception(1, String.valueOf(move.charAt(1)), board));
                throw new GameException();
            }
        }
        //if a horizontal bar is tried to be moved inside 'o'
        else if (move.charAt(0) == Constants.charh && move.charAt(2) == Constants.charo) {
            // position of bar in the barConfig string will be 1 less than the
            // specified position
            int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

            //changeBarval signifies the new bar position on a successful move
            //it initially takes the bar's current position
            char changeBarVal = bar.charAt(position);

            //if a bar is in central or inner position then it can be moved outside
            //else an exception must be thrown
            //if bar's current position is 1 or 0 then it will be moved outside
            if (changeBarVal == Constants.charzero || changeBarVal == Constants.charone) {

                //if the value is 0 then it must be set to 1
                if (changeBarVal == Constants.charzero) {
                    changeBarVal = Constants.charone;
                } else {
                    //else it must be set to 2
                    changeBarVal = Constants.chartwo;
                }

                //a new bar config must be generated from the old bar config
                StringBuilder newBarConfig = new StringBuilder(bar);

                //changing the bar position
                newBarConfig.setCharAt(position, changeBarVal);

                //setting the new bar config
                bar = newBarConfig.toString();

            } else {

                //throwing an exception with a message that the move is invalid
                //error code 2 means a horizontal bar is tried
                // to be moved from outer to further outer position
                board.setExceptionMessage(gameException.exception(2, String.valueOf(move.charAt(1)), board));
                throw new GameException();
            }
        }
        //if a horizontal bar is tried to be moved inside 'i'
        else if (move.charAt(0) == Constants.charv && move.charAt(2) == Constants.chari) {
            // position of bar in the barConfig string will be 1 less than the
            // specified position
            int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

            //changeBarval signifies the new bar position on a successful move
            //it initially takes the bar's current position
            char changeBarVal = bar.charAt(position);

            //if a bar is in central or outer position then it can be moved inside
            //else an exception must be thrown
            //if bar's current position is 1 or 2 then it will be moved inside
            if (changeBarVal == Constants.charone || changeBarVal == Constants.chartwo) {

                //if the value is 1 then it must be set to 0
                if (changeBarVal == Constants.charone) {
                    changeBarVal = Constants.charzero;
                }
                //else it must be set to 1
                else {
                    changeBarVal = Constants.charone;
                }

                //a new bar config must be generated from the old bar config
                StringBuilder newBarConfig = new StringBuilder(bar);

                //changing the bar position
                newBarConfig.setCharAt(position, changeBarVal);

                //setting the new bar config
                bar = newBarConfig.toString();

            } else {
                //throwing an exception with a message that the move is invalid
                board.setExceptionMessage(gameException.exception(3, String.valueOf(move.charAt(1)), board));
                throw new GameException();
            }
        }
        //if a vertical bar is tried to be moved inside 'o'
        else if (move.charAt(0) == Constants.charv && move.charAt(2) == Constants.charo) {

            // position of bar in the barConfig string will be 1 less than the
            // specified position
            int position = Integer.valueOf(String.valueOf(move.charAt(1))) - 1;

            //changeBarval signifies the new bar position on a successful move
            //it initially takes the bar's current position
            char changeBarVal = bar.charAt(position);

            //if a bar is in central or inner position then it can be moved outside
            //else an exception must be thrown
            //if bar's current position is 1 or 0 then it will be moved outside
            if (changeBarVal == Constants.charzero || changeBarVal == Constants.charone) {

                //if the value is 0 then it must be set to 1
                if (changeBarVal == Constants.charzero) {
                    changeBarVal = Constants.charone;
                }
                //else it must be set to 2
                else {
                    changeBarVal = Constants.chartwo;
                }

                //a new bar config must be generated from the old bar config
                StringBuilder newBarConfig = new StringBuilder(bar);

                //changing the bar position
                newBarConfig.setCharAt(position, changeBarVal);

                //setting the new bar config
                bar = newBarConfig.toString();

            } else {
                //throwing an exception with a message that the move is invalid
                board.setExceptionMessage(gameException.exception(4, String.valueOf(move.charAt(1)), board));
                throw new GameException();
            }
        }

        return bar;
    }


    /**
     * This function determines the next player who must supposedly perform the move
     *
     * @param board
     */
    public Board setMovingPlayer(Board board) {

        //set the player who last performed the move
        //this player number can be used to determine the winner in case a winner is to be decided
        // when all beads have fallen
        board.setLastMovingPlayer(board.getMovingPlayer());

        //if the number of player is 2
        if (board.getNumberOfPlayers() == Constants.two) {

            //if the moving player is 1 set it to 2
            if (board.getMovingPlayer() == Constants.one) {

                //setting moving player to 2
                board.setMovingPlayer(Constants.two);

            }
            //if the moving player is 2 set it to 1
            else if (board.getMovingPlayer() == Constants.two) {

                //setting moving player to 1
                board.setMovingPlayer(Constants.one);

            }
        }

        //if the number of player is 3
        else if (board.getNumberOfPlayers() == Constants.three) {

            //if the moving player is 3 set it to 1
            if (board.getMovingPlayer() == Constants.three) {

                //setting moving player to 1
                board.setMovingPlayer(Constants.one);

            }
            //if the moving player is 2 set it to 3
            else if (board.getMovingPlayer() == Constants.two) {

                //setting moving player to 3
                board.setMovingPlayer(Constants.three);

            }
            //if the moving player is 1 set it to 2
            else if (board.getMovingPlayer() == Constants.one) {

                //setting moving player to 2
                board.setMovingPlayer(Constants.two);

            }

        }
        //if the number of player is 4
        else if (board.getNumberOfPlayers() == Constants.four) {

            //if the moving player is 4 set it to 1
            if (board.getMovingPlayer() == Constants.four) {

                //setting moving player to 1
                board.setMovingPlayer(Constants.one);

            }
            //if the moving player is 3 set it to 4
            else if (board.getMovingPlayer() == Constants.three) {

                //setting moving player to 4
                board.setMovingPlayer(Constants.four);

            }
            //if the moving player is 2 set it to 3
            else if (board.getMovingPlayer() == Constants.two) {

                //setting moving player to 3
                board.setMovingPlayer(Constants.three);
            }
            //if the moving player is 1 set it to 2
            else if (board.getMovingPlayer() == Constants.one) {

                //setting moving player to 2
                board.setMovingPlayer(Constants.two);
            }

        }

        //returning the board object
        return board;
    }
}

