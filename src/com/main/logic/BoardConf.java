package com.main.logic;

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
		String horBarConf = "";
		String verBarConf = "";
		String boardConf = "";
		int i = 1;
		for (char barPos : horizontalBar.toCharArray()) {

			horBarConf = horBarConf + barConfGenerator(Strings.h, String.valueOf(i), barPos);
			i++;
		}
		i = 1;

		for (char barPos : verticalBar.toCharArray()) {

			verBarConf = verBarConf + barConfGenerator(Strings.v, String.valueOf(i), barPos);
			i++;
		}
		// we need to transpose the vertical bar so that we can compare them to
		// the corresponding horizontal bar positions
		verBarConf = transpose(verBarConf);

		for (i = 0; i < horBarConf.length(); i++) {
			if (verBarConf.charAt(i) == Strings.charx) {
				boardConf = boardConf + Strings.blue;
			} else if (verBarConf.charAt(i) == Strings.charo
					&& horBarConf.charAt(i) == Strings.charx) {
				boardConf = boardConf + Strings.red;
			} else {
				boardConf = boardConf + Strings.hole;
			}

		}
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
		String barConf = "";
		String horBar = Strings.horizontalBar;
		String verBar = Strings.verticalBar;
		if (barType == Strings.h) {
			barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);
			barConf = horBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));
		} else if (barType == Strings.v) {

			barNumber = String.valueOf(Integer.parseInt(barNumber) - 1);
			barConf = verBar.substring((((Integer.valueOf(barNumber)) * 9)),
					(((Integer.valueOf(barNumber)) * 9) + 9));

		}
		// select the barConf according to the current position of the bar
		if (barPos == Strings.charzero) {
			barConf = barConf.substring(0, 7);
		} else if (barPos == Strings.charone) {
			barConf = barConf.substring(1, 8);
		} else if (barPos == Strings.chartwo) {
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

		char[] charArray = verticalBar.toCharArray();
		int n = (int) Math.sqrt(verticalBar.length());
		for (int i = 0; i < n; ++i) {
			for (int j = i; j < n; ++j) {
				char h = charArray[i * n + j];
				charArray[i * n + j] = charArray[j * n + i];
				charArray[j * n + i] = h;
			}
		}
		return new String(charArray);
	}

}
