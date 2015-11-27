package com.main.logic;

public class Strings {
	public static final String gamePattern = "[234][1234][012]{7}[012]{7}[01234]{49}([hv][1234567][io])*";
	public static final String movePattern = "(?<=\\G...)";
	public static final String horizontalBar = "xoxoxoxoxxooxooxoxxoooxoooxxoxoxoxoxxoooooooxxxoooxoxxxooxoxoxx";
	public static final String verticalBar = "xooooxoxxxoooxxooxxoxooxoxxxooxxoooxxxoooxoxxxxoooooxxxooxooxox";
	public static final String abort = "Aborting Execution";
	public static final String invalidInput = "Input is invalid";
	public static final String invalidMove = "Invalid Move";
	public static final String one = "1";
	public static final String two = "2";
	public static final String three = "3";
	public static final String four = "4";
	public static final String h = "h";
	public static final String v = "v";
	public static final String red = "r";
	public static final String blue = "b";
	public static final String hole = "h";
	public static final char charh = 'h';
	public static final char charr = 'r';
	public static final char charv = 'v';
	public static final char charo = 'o';
	public static final char charx = 'x';
	public static final char chari = 'i';
	public static final char charzero = '0';
	public static final char charone = '1';
	public static final char chartwo = '2';
	public static final char charthree = '3';
	public static final char charfour = '4';
	public static final String retry = "Please try a different move";
	public static final String win = "The winner is: Player ";
	public static final String horBar = "Horizontal Bar ";
	public static final String inner = " is already in inner position";
	public static final String outer = " is already in outer position";
	public static final String verBar = "Vertical Bar ";
	public static final String output = "Output:";
	public static final String lastOutput = "Last Output:";
}
