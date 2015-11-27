package com.main.logic;

import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class MoveControllerTest {


	

		MoveController moveController;
		Board board ;

		@Before
		public void setUp() throws Exception {
			moveController = new MoveController();
			board =new Board();
		}

	
	@After
	public void tearDown() throws Exception {
		moveController = null;
	}

	@Test
	public void testSetMoves() {
		
		board.setMovingPlayer("2");
		board.setMoveOne("v4o2");
		board.setMoveTwo("h2o1");
		board.setMoveThree("h6o2");
		board.setMoveFour("v4o1");
		moveController.setMoves("v3i", board);
		assertEquals(board.getMoveOne(), "v3i2");
		
		
	}

	@Test
	public void testTranspose() {
		
		
		
		 
		 String result =moveController.transpose("ooooxoxoooxxooxoxooxoxooxxoooxxoooxoxoooooxxoxooxox");
		assertEquals("ooxxooxooooxxxooxoxoooxoxooxxxoxoooooxooooxoooxoxox",result);
		
		
	}

}
