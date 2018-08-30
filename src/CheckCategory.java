import acm.program.*;

public class CheckCategory extends ConsoleProgram {	
	public void run() {
		while (true) {
			boolean test = false;
			int dice[] = new int[5];
			for (int i=0; i<dice.length; i++) {
				dice[i] = readInt(":");
			}	
		}
	}
	
	/*
	// for large straight
				testblock:
				for (int i=0; i<dice.length; i++) {		// loops through the dice	
					for (int j=i+1; j<dice.length; j++) {		// loops through the dice starting from [i+1]
						if (dice[i] == dice[j]) {	
							test = false;
							break testblock;
						}
						// 1
						
					}
				}*/
	
	
	/*	// 3 and 4 of a kind
	 * testblock:
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
		
		
		
		// YAHTZEE!	
		for(int i=0; i<dice.length; i++) {
				if (dice[0] != dice[i]) {
					test = false;
					break;
				}
			}
	 */
}
