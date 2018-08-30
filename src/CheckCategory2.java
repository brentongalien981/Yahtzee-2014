// this is for small-straight category

import acm.program.*;

public class CheckCategory2 extends ConsoleProgram {
	int diceSequence[] = new int[7];
	boolean test = false;
	
	public void run() {		
		while (true) {
			
			int dice[] = new int[5];
			for (int i=0; i<dice.length; i++) {
				dice[i] = readInt(":");
			}

			
			switch (dice[0]) {
			case 1:
				diceOne(dice);
				break;
			case 2:
				diceTwo(dice);
				break;
			case 3:
				diceThree(dice);
				break;
			case 4:
				diceFour(dice);
				break;
			case 5:
				diceFive(dice);
				break;
			case 6:
				diceSix(dice);
				break;
			}
			
			for (int i=0; i<diceSequence.length; i++) {
				println("diceSeq[] = " + diceSequence[i]);
			}
			
			checkDiceSequence();
			
			println(test);
			test = false;
		}
	}
	
	private void checkDiceSequence() {
		int count = 0;
		for (int i=0; i<diceSequence.length; i++) {
			if (diceSequence[i] != 0) { //---------------------------> // is the box filled by a number?
				for (int j = i+1; 	j <= i+3; 	j++) {
					if (diceSequence[j] == 0) { break; } // there should be four straight non-zero value
					else { ++count; }
				}
				
				if (count == 3) { // small-straight!!!
					test = true;
					break;
				}
				else { count = 0; }
			}
		}
	}
	
	private void diceSix(int dice[]) {
		diceSequence[3] = 1;
		
		for (int i=1; i<dice.length; i++) {
			switch(dice[i]) {
			case 3:
				diceSequence[0] = 1;
				diceSequence[6] = 1;
				break;
			case 4:
				diceSequence[1] = 1;
				break;
			case 5:
				diceSequence[2] = 1;
				break;
			case 1:
				diceSequence[4] = 1;
				break;
			case 2:
				diceSequence[5] = 1;
				break;
			}
		}		
	}
	
	private void diceFive(int dice[]) {
		diceSequence[3] = 1;
		
		for (int i=1; i<dice.length; i++) {
			switch(dice[i]) {
			case 2:
				diceSequence[0] = 1;
				diceSequence[6] = 1;
				break;
			case 3:
				diceSequence[1] = 1;
				break;
			case 4:
				diceSequence[2] = 1;
				break;
			case 6:
				diceSequence[4] = 1;
				break;
			case 1:
				diceSequence[5] = 1;
				break;
			}
		}		
	}
	
	private void diceFour(int dice[]) {
		diceSequence[3] = 1;
		
		for (int i=1; i<dice.length; i++) {
			switch(dice[i]) {
			case 1:
				diceSequence[0] = 1;
				diceSequence[6] = 1;
				break;
			case 2:
				diceSequence[1] = 1;
				break;
			case 3:
				diceSequence[2] = 1;
				break;
			case 5:
				diceSequence[4] = 1;
				break;
			case 6:
				diceSequence[5] = 1;
				break;
			}
		}		
	}
	
	private void diceThree(int dice[]) {
		diceSequence[3] = 1;
		
		for (int i=1; i<dice.length; i++) {
			switch(dice[i]) {
			case 6:
				diceSequence[0] = 1;
				diceSequence[6] = 1;
				break;
			case 1:
				diceSequence[1] = 1;
				break;
			case 2:
				diceSequence[2] = 1;
				break;
			case 4:
				diceSequence[4] = 1;
				break;
			case 5:
				diceSequence[5] = 1;
				break;
			}
		}		
	}
	
	private void diceTwo(int dice[]) {
		diceSequence[3] = 1;
		
		for (int i=1; i<dice.length; i++) {
			switch(dice[i]) {
			case 5:
				diceSequence[0] = 1;
				diceSequence[6] = 1;
				break;
			case 6:
				diceSequence[1] = 1;
				break;
			case 1:
				diceSequence[2] = 1;
				break;
			case 3:
				diceSequence[4] = 1;
				break;
			case 4:
				diceSequence[5] = 1;
				break;
			}
		}		
	}
	
	private void diceOne(int dice[]) {
		diceSequence[3] = 1;	// if an index is assigned a value of 1, that value is in the dice configuration--the opposite to zero

		for (int i=1; i<dice.length; i++) {
			switch(dice[i]) {
			case 4:
				diceSequence[0] = 4;
				diceSequence[6] = 4;
				break;
			case 5:
				diceSequence[1] = 5;
				break;
			case 6:
				diceSequence[2] = 6;
				break;
			case 2:
				diceSequence[4] = 2;
				break;
			case 3:
				diceSequence[5] = 3;
				break;
			}
		}		
	}
}