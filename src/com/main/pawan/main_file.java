package com.main.pawan;

public class main_file extends BoardGame  {

	
	public BoardGame Game(BoardGame value) {
		
		HorizontalVerticalBar slot = new HorizontalVerticalBar();
		//Board prepared		
		value = slot.SelectingSlot(value);
		System.out.println();
		System.out.println();
		ColorRepresentation color = new ColorRepresentation();
		value = color.BoardReady(value);
		value.countForFirstPlayer = 0;
		value.countForSecondPlayer = 0;
		value.countForThirdPlayer = 0;
		value.countForFourthPlayer = 0;
		BeadRepresentation bead = new BeadRepresentation();
		value = bead.Count(value);
		
		HorizontalMove hmove = new HorizontalMove();
		VerticalMove vmove=new VerticalMove();



		
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// ////////////////////////////////////////////////Taking Player Input/////////////////////////////////////////////////////
		
int length = value.sequenceOfMove.length();
		
			int i=0,j,b=0;
			String a;	
			while(length > 0){
				value.moveOfBar = value.sequenceOfMove.substring(i,i+1);
				value.barNumber = value.sequenceOfMove.substring(i+1,i+ 2);
				value.move = value.sequenceOfMove.substring(i+2,i+3);	
				length=length-3;
				i=i+3;
				j= Integer.parseInt(value.barNumber)-1;
				if (value.moveOfBar.equals("h")) {
						
								
									if (value.move.equals("o")) {
										a = value.postionsOfHorizontalBar.substring(j,j+1);	
										if(!a.equals("2")){
											value = hmove.Logic(value);
											
										}
										else{
											//throw exception
											System.out.println("false move");
											return value;
										}
									}
									else if (value.move.equals("i")){
										a = value.postionsOfHorizontalBar.substring(j,j+1);	
										if(!a.equals("0")){
											value = hmove.Logic(value);
											
										}
										else{
											System.out.println("false move");
											return value;
											//throw exception
										}
									}
						
				}
				if (value.moveOfBar.equals("v")) {
					
					
					if (value.move.equals("o")) {
						a = value.postionsOfHorizontalBar.substring(j,j+1);	
						if(!a.equals("2")){
							value = vmove.Logic(value);
						
						}
						else{
							//throw exception
						}
					}
					else if (value.move.equals("i")){
						a = value.postionsOfHorizontalBar.substring(j,j+1);	
						if(!a.equals("0")){
							value = vmove.Logic(value);
						
							
						}
						else{
							//throw exception
						}
					}
		
			}
		}
		

			return value;
}
}	
	

