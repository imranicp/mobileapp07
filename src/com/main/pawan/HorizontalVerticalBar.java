package com.main.pawan;

public class HorizontalVerticalBar {
	public BoardGame SelectingSlot(BoardGame value){  
	int i,j;
	
	// Selecting the slot Horizontally

			for (i = 0; i < value.postionsOfHorizontalBar.length(); i++) {
				if (Integer.parseInt(value.postionsOfHorizontalBar.substring(i,
						i + 1)) == 0) {

					// System.out.println(value.horizontalBar.substring(i*9,(i*9)+9));

					value.selectedSlotOfHorizontalBar = value.selectedSlotOfHorizontalBar
							+ value.horizontalBar.substring(i * 9, (i * 9) + 7);

				} else if (Integer.parseInt(value.postionsOfHorizontalBar
						.substring(i, i + 1)) == 1) {

					// System.out.println(value.horizontalBar.substring(i*9,(i*9)+9));
					value.selectedSlotOfHorizontalBar = value.selectedSlotOfHorizontalBar
							+ value.horizontalBar.substring((i * 9) + 1,
									(i * 9) + 8);

				} else if (Integer.parseInt(value.postionsOfHorizontalBar
						.substring(i, i + 1)) == 2) {

					// System.out.println(value.horizontalBar.substring(i*9,(i*9)+9));
					value.selectedSlotOfHorizontalBar = value.selectedSlotOfHorizontalBar
							+ value.horizontalBar.substring((i * 9) + 2,
									(i * 9) + 9);

				}
			}
			
			System.out.println();
			System.out.println();
			// Horizontally BAr representation on the table
			for (i = 0; i < value.postionsOfHorizontalBar.length(); i++) {
				for (j = 0; j < value.postionsOfHorizontalBar.length(); j++) {
					value.slotOfHorizontalBar[i][j] = value.selectedSlotOfHorizontalBar
							.substring(((i * 7) + j), ((i * 7) + j + 1));
					System.out.print(value.slotOfHorizontalBar[i][j] + "  ");
				}
				System.out.println();
			}
	
			
			// Selecting the slot Vertically
			for (i = 0; i < value.postionsOfVerticalBar.length(); i++) {
				if (Integer.parseInt(value.postionsOfVerticalBar
						.substring(i, i + 1)) == 0) {

					// System.out.println(value.VerticalBar.substring(i*9,(i*9)+9));

					value.selectedSlotOfVerticalBar = value.selectedSlotOfVerticalBar
							+ value.verticalBar.substring(i * 9, (i * 9) + 7);

				} else if (Integer.parseInt(value.postionsOfVerticalBar.substring(
						i, i + 1)) == 1) {

					// System.out.println(value.VerticalBar.substring(i*9,(i*9)+9));
					value.selectedSlotOfVerticalBar = value.selectedSlotOfVerticalBar
							+ value.verticalBar.substring((i * 9) + 1, (i * 9) + 8);

				} else if (Integer.parseInt(value.postionsOfVerticalBar.substring(
						i, i + 1)) == 2) {

					// System.out.println(value.VerticalBar.substring(i*9,(i*9)+9));
					value.selectedSlotOfVerticalBar = value.selectedSlotOfVerticalBar
							+ value.verticalBar.substring((i * 9) + 2, (i * 9) + 9);

				}
			}
//			System.out.println(value.selectedSlotOfVerticalBar);
			
			System.out.println();
			System.out.println();
			// Vertically Bar representation on the table
			for (i = 0; i < value.postionsOfVerticalBar.length(); i++) {
				for (j = 0; j < value.postionsOfVerticalBar.length(); j++) {
					value.slotOfVerticalBar[i][j] = value.selectedSlotOfVerticalBar
							.substring(((i * 7) + j), ((i * 7) + j + 1));
					System.out.print(value.slotOfVerticalBar[i][j] + "  ");
				}
				System.out.println();
			}

			return value;
			}
}

