package com.main.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoveControllerMine {
	MoveController testObj;
	String testStr;
	@Before
	public void setUp() throws Exception {
	testObj = new MoveController();
	testStr = "";
	}

	@After
	public void tearDown() throws Exception {
	testObj = null;
	}

	@Test
	public void testBoardConf() {
		testStr=testObj.boardConfGenerator("1001101", "2022002");
		assertEquals("hbbrbbhrhhbbbbrhhbrhhbrbrhrhhbhhhhbbbbhbrhbhbbrhb", testStr);
	}
	
	@Test
	public void testBeadConf(){
		testStr = testObj.beadConfGenerator("000010002000000000012000000000000000010000000000", "hbbrbbhrhhbbbbrhhbrhhbrbrhrhhbhhhhbbbbhbrhbhbbrhb");
		assertEquals("000010000000000000000000000000000000010000000000", testStr);
	}

}
