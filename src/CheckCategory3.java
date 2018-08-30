// this is for full-house configuration

import acm.program.*;

public class CheckCategory3 extends ConsoleProgram {
	boolean test = false;
	int dice[] = new int[5];
	int firstNum, secNum; // the 2 #s that constitute the full-house
	
	public void run() {
		while (true) {			
			for (int i=0; i<dice.length; i++) {
				dice[i] = readInt(":");
			}
			
			firstNum = dice[0]; // dice[0] is one of the 2 repeating #s
			
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
			
			boolean test = false;
			// this tests if you have 3 #s of firstNum or secNum and
			// 2 #s of firstNum or secNum /OR/ vice versa
			if ( (firstNumCount == 3) && (secNumCount == 2) ) { test = true; }
			else if ( (firstNumCount == 2) && (secNumCount == 3) ) { test = true; }
			
			println(test);
			
		}
	}
}
