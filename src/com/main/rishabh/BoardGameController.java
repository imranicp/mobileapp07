/**
 * 
 */
package com.main.rishabh;

/**
 * @author Rishabh
 * 
 */
public class BoardGameController {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		MoveController mvControl = new MoveController();

		// String result=mvControl
		// .moveTest("11110002211000220000100002000000000012000000000000000010000000000h3ov2o");
		String result = mvControl
				.moveTest("44012012021011020000100002000000000012000000000000000010000000000h3iv2oh2o");
		System.out.println(result);
	}

}
