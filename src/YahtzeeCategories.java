// this is the final version for checking categories

public class YahtzeeCategories implements YahtzeeConstants {
	private static boolean test;
	
	public static boolean checkCategory(int dice[], int category) {
		test = false; // default value of test...
					  // ...also resets the value of test to false everytime this method is called
		
		if (category <= SIXES || category == CHANCE) { test = true; } // always true for categories ONES to SIXES and CHANCE
		else {
			switch(category) {
			case THREE_OF_A_KIND:
				threeOfAKind(dice);
				break;
			case FOUR_OF_A_KIND:
				fourOfAKind(dice);
				break;
			case FULL_HOUSE:
				fullHouse(dice);
				break;
			case SMALL_STRAIGHT:
				smallStraight(dice);
				break;
			case LARGE_STRAIGHT:
				largeStraight(dice);
				break;
			case YAHTZEE:
				yahtzee(dice);
				break;
			}			
		}
		
		return test;
	}
	
	private static void largeStraight(int dice[]) {
		testblock: {
			for (int i=0; i<dice.length; i++) {		// loops through the dice	
				for (int j=i+1; j<dice.length; j++) {		// loops through the dice starting from [i+1]
					if (dice[i] == dice[j]) {	
						break testblock;
					}					
				}
			}
			test = true;
		}
	}
	
	private static void yahtzee(int dice[]) {
		block: {
			for(int i=0; i<dice.length; i++) {
				if (dice[0] != dice[i]) {
					break block;
				}
			}
			test = true;
		}
	}
	
	private static void fullHouse(int dice[]) {		
		int firstNum = dice[0]; // dice[0] is one of the 2 repeating #s
		int secNum = -1;
		
		// the first # that doesn't equal to the 1st # means
		// that is the secNum for full-house configuration
		for (int i=1; i<dice.length; i++) {
			if (dice[i] != firstNum) {
				secNum = dice[i];
				break;
			}
		}
		
		int firstNumCount = 1; // count for 1st # is 1 because of dice[0] 
		int secNumCount = 0;
		for (int i=1; i<dice.length; i++) {
			if (dice[i] == firstNum) { ++firstNumCount; }
			else if (dice[i] == secNum) { ++secNumCount; }
		}
		
		// this tests if you have 3 #s of firstNum or secNum and
		// 2 #s of firstNum or secNum /OR/ vice versa
		if ( (firstNumCount == 3) && (secNumCount == 2) ) { test = true; }
		else if ( (firstNumCount == 2) && (secNumCount == 3) ) { test = true; }			
	}
	
	
	private static void threeOfAKind(int dice[]) {
		testblock: {
			for (int i=1, count = 0; 	i<=6; 	i++) {
				for (int j: dice) {
					if (i == j) {
						++count;
						if(count == 3) {
							test = true; 
							break testblock;					
						}
					}
				}
				count = 0;
			}
		}		
	}
	  
	private static void fourOfAKind(int dice[]) {
		testblock: {
			for (int i=1, count = 0; 	i<=6; 	i++) {
				for (int j: dice) {
					if (i == j) {
						++count;
						if(count == 4) {
							test = true; 
							break testblock;					
						}
					}
				}
				count = 0;
			}
		}		
	}
	
	private static void smallStraight(int dice[]) {
		int diceSequence[] = new int[7];
		diceSequence[3] = 1;	// if an index is assigned a value of 1, that value is in the dice configuration--the opposite to zero

		switch (dice[0]) {
		case 1:
			diceOne(dice, diceSequence);
			break;
		case 2:
			diceTwo(dice, diceSequence);
			break;
		case 3:
			diceThree(dice, diceSequence);
			break;
		case 4:
			diceFour(dice, diceSequence);
			break;
		case 5:
			diceFive(dice, diceSequence);
			break;
		case 6:
			diceSix(dice, diceSequence);
			break;
		}
		
		checkDiceSequence(diceSequence);
	}	
	
	private static void checkDiceSequence(int diceSequence[]) {
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
	
	private static void diceOne(int dice[], int diceSequence[]) {
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
	
	private static void diceTwo(int dice[], int diceSequence[]) {		
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
	
	private static void diceThree(int dice[], int diceSequence[]) {		
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
	
	private static void diceFour(int dice[], int diceSequence[]) {		
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
	
	private static void diceFive(int dice[], int diceSequence[]) {		
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
	
	private static void diceSix(int dice[], int diceSequence[]) {		
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
}
