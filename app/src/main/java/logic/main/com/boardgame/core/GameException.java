package logic.main.com.boardgame.core;

public class GameException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param code
     *            Generates Exception based on the error code received
     */
    //fatal exceptions are logged in here
    public String exception(int code) {
        //the message which is to be sent
        String message="";

        //0 means aborting exception
        if (code == 0) {
            message = Constants.abort;
            System.out.println(Constants.abort);

        }
        //1 means the given input was invalid
        if (code == 1) {
            message=Constants.invalidInput;
            System.out.println(Constants.invalidInput);
        }
        //returning the exception message
        return message;
    }

    //moves which were performed in the previous turns cannot be performed
    //this exception message is thrown when this case is satisfied
    public String exception(char code, String move, Board board) {
        //exception for a bar which was moved in previous turns
        // error code 'r'
        if (code == Constants.charr) {
            System.out.println(move + Constants.bar);
            System.out.println(Constants.lastOutput);
            System.out.println(board.getOutput());
        }
        //returning the exception message
        return Constants.bar;
    }

    //bars cannot be moved to outer position if they are already in outer position
    //similarly bars which are in inner position cannot be moved further inner position
    //this set of exceptions are handled here and exception messages are returned accordingly
    public String exception(int code, String bar, Board board) {

        //the message which is to be sent
        String message="";

        //error code 1 means a horizontal bar is tried
        // to be moved from inner to further inner position
        if (code == 1) {

            System.out.println(Constants.horBar + bar + Constants.inner);
            //setting message
            message=Constants.horBar + bar + Constants.inner;
            System.out.println(Constants.lastOutput);
            System.out.println(board.getOutput());
        }

        //error code 2 means a horizontal bar is tried
        // to be moved from outer to further outer position
        if (code == 2) {

            System.out.println(Constants.horBar + bar + Constants.outer);
            //setting message
            message=Constants.horBar + bar + Constants.outer;
            System.out.println(Constants.lastOutput);
            System.out.println(board.getOutput());
        }

        //error code 3 means a vertical bar is tried
        // to be moved from inner to further inner position
        if (code == 3) {

            System.out.println(Constants.verBar + bar + Constants.inner);
            //setting message
            message=Constants.verBar + bar + Constants.inner;
            System.out.println(Constants.lastOutput);
            System.out.println(board.getOutput());
        }

        //error code 4 means a vertical bar is tried
        // to be moved from outer to further outer position
        if (code == 4) {

            System.out.println(Constants.verBar + bar + Constants.outer);
            //setting message
            message=Constants.verBar + bar + Constants.outer;
            System.out.println(Constants.lastOutput);
            System.out.println(board.getOutput());
        }

        //returning back the message
        return message;
    }
}
