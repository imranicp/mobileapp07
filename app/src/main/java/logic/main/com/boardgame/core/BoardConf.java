package logic.main.com.boardgame.core;

public class BoardConf {

	/**
	 * This function returns the board as a 49 character string where values are
	 * b,r,or h
	 * 
	 * @param horizontalBar
	 *            The horizontal bar configuration
	 * @param verticalBar
	 *            The vertical bar configuration
	 * @return The configuration of the 7x7 board where each place is either
	 *         b=Blue, r=Red, h=Hole based on the bar configurations
	 */
	public String boardConfGenerator(String horizontalBar, String verticalBar) {

        //the horizontal bar config
        String horBarConf = "";

        //the vertical bar config
        String verBarConf = "";

        //the board config which is to be generated
        String boardConf = "";

        //the loop variable is used for number of bar for which position has to be generated
        // using the corresponding position from the barConfig generator string
        int i = 1;

        //barPos will have value like 0,1,2 means inner,central,outer
        for (char barPos : horizontalBar.toCharArray()) {

            //building the horizontal bar config string from the barConfigGenerator which gives
            // back the bar config based on its position
            horBarConf = horBarConf + barConfGenerator(Constants.h, String.valueOf(i), barPos);

            //incrementing the bar number
            i++;

		}

        //setting it back to 1 to be used again for vertical bars
        i = 1;

        //barPos will have value like 0,1,2 means inner,central,outer
        for (char barPos : verticalBar.toCharArray()) {

            //building the vertical bar config string from the barConfigGenerator which gives
            // back the bar config based on its position
            verBarConf = verBarConf + barConfGenerator(Constants.v, String.valueOf(i), barPos);

            //incrementing the bar number
            i++;
		}


        // we need to transpose the vertical bar so that we can compare them to
		// the corresponding horizontal bar positions
		verBarConf = transpose(verBarConf);

        //board config generation which will have values h,b,r
        //in this loop the horizontal bar configuration is compared with the vertical bar configuration
        //to determine whether there is a hole or a red tile or a blue tile in the board configuration
        for (i = 0; i < horBarConf.length(); i++) {

            //if there is a char 'x' in vertical bar then there should be 'b' in board config
            if (verBarConf.charAt(i) == Constants.charx) {

                //adding 'b' to board config
                boardConf = boardConf + Constants.blue;


            }
            //if there is a char 'o' in vertical bar and char 'x' in horizontal bar
            // then there should be 'r' in board config
            else if (verBarConf.charAt(i) == Constants.charo
                    && horBarConf.charAt(i) == Constants.charx) {

                //adding 'r' to board config
                boardConf = boardConf + Constants.red;

            }
            //if the above conditions are not satisfied then there should be a hole 'h'
            //in the board config
            else {
                //adding 'h' to board config
                boardConf = boardConf + Constants.hole;
			}

		}
        //returning the board config
        return boardConf;
	}

	/**
	 * @param barType
	 *            The type of bar for which the configuration must be generated
	 * @param barNumber
	 *            The number of the bar
	 * @param barPos
	 *            The position of the bar 0 (inner), 1 (central), or 2 (outer)
	 * @return
	 */
	public String barConfGenerator(String barType, String barNumber, char barPos) {
        //the bar config which is to be generated
        String barConf = "";

        //the preset values for horizontal bar represented by either 'x' or 'o'
        String horBar = Constants.horizontalBar;

        //the preset values for vertical bar represented by either 'x' or 'o'
        String verBar = Constants.verticalBar;

        //to get the particular bar configuration according to the barNumber supplied

        //if the required bar is horizontal
        if (barType == Constants.h) {

            //bar number subtracted by 1 to get the right bar as bars come from 1 to 7
            barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);

            //substring the horizontal bar variable according to the bar number
            //ex: if bar number is 1 then substring will be done from 9 to 18
            barConf = horBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));
        }
        //if the required bar is vertical
        else if (barType == Constants.v) {

            //bar number subtracted by 1 to get the right bar as bars come from 1 to 7
            barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);

            //substring the vertical bar variable according to the bar number
            //ex: if bar number is 1 then substring will be done from 9 to 18
            barConf = verBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));

		}

		// select the barConf according to the current position of the bar
        //if bar position is inner '0'
        if (barPos == Constants.charzero) {

            //substring from 0 to 7 as first 7 values will be relevant to board
            barConf = barConf.substring(0, 7);

        }
        //if bar position is center '1'
        else if (barPos == Constants.charone) {

            //substring from 1 to 8 as values from 1 to 8 will be relevant to board
            barConf = barConf.substring(1, 8);

        }
        //if bar position is outer '2'
        else if (barPos == Constants.chartwo) {

            //substring from 2 to 9 as values from 2 to 9 will be relevant to board
            barConf = barConf.substring(2, 9);

		}

		return barConf;
	}

	/**
	 * @param verticalBar
	 *            the vertical bar configuration as input
	 * 
	 * @return Perform a transpose of the vertical bar configuration so that it
	 *         can be compared to the horizontal bar and thus we can generate
	 *         the board configuration
	 */
	public String transpose(String verticalBar) {

        //converting the string to character array
        char[] charArray = verticalBar.toCharArray();

        //to get the square root of the length of the bar
        //this is the number of times the loop should move
        int n = (int) Math.sqrt(verticalBar.length());

        //other than diagonal values all other values must be switched
        //ex:the character at 1st position must be switched
        // with character at 7th position and so on
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {

                //switching of characters
                char h = charArray[i * n + j];
				charArray[i * n + j] = charArray[j * n + i];
				charArray[j * n + i] = h;
			}
		}

        //returning the transposed string
        return new String(charArray);
	}

}
