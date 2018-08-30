/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class YahtzeePrototype extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
		showPlayersScoreSheet();
	}
	
	// generate dice configuration
	private int numGen() {
		return rgen.nextInt(1, 6);
	}

	private void playGame() {
		for (int round=0; round<3; round++) {
			// init dice
			for (int i=0; i<dice.length; i++) {
				dice[i] = numGen();
			}
			display.printMessage("Welcome! Roll the dice.");
			display.waitForPlayerToClickRoll(1);
			display.displayDice(dice);
			
			// reroll selected dices for max of 2 tries
			for (int turnsLeft=2; turnsLeft>0; turnsLeft--) {
				display.waitForPlayerToSelectDice();
				// check for dice selected to update
				for (int i=0; i<dice.length; i++) {
					if (display.isDieSelected(i)) {
						dice[i] = numGen();
					}
				}
				display.displayDice(dice);
			}
			
			
			// *** THIS METHOD() UPDATES PLAYER'S SCORE SHEET ***
			// click for category for points
			int category = display.waitForPlayerToSelectCategory();		
			//boolean p = YahtzeeMagicStub.checkCategory(dice, FULL_HOUSE);
			
			// *** IMPLEMENT THIS METHOD ON YOUR OWN ***
			boolean p = YahtzeeMagicStub.checkCategory(dice, category);
			if(p) {
				int receivedScore = giveScore(dice, category);
				display.updateScorecard(category, 1, receivedScore);
				// updates the total score row
				display.updateScorecard(TOTAL, 1, totalScore+=receivedScore);
			}
			else {
				display.updateScorecard(category, 1, 0);
			}
		}
	}
		
	private int giveScore(int diceConfig[], int categ) {
		int points = 0;
		switch(categ) {
		case ONES:
			for (int i : diceConfig) {
				if (i == 1) {
					upperScoresIndexes[0] = ++points;
				}
			}
			break;				
		case TWOS:
			for (int i : diceConfig) { if (i == 2) { upperScoresIndexes[1] = points+=2; } }
			break;
		case THREES:
			for (int i : diceConfig) { if (i == 3) { upperScoresIndexes[2] = points+=3; } }
			break;
		case FOURS:
			for (int i : diceConfig) { if (i == 4) { upperScoresIndexes[3] = points+=4; } }
			break;
		case FIVES:
			for (int i : diceConfig) { if (i == 5) { upperScoresIndexes[4] = points+=5; } }
			break;
		case SIXES:
			for (int i : diceConfig) { if (i == 6) { upperScoresIndexes[5] = points+=6; } }
			break;
		case THREE_OF_A_KIND:
			for (int i : diceConfig) { lowerScoresIndexes[0] = points+=i; } 
			break;
		case FOUR_OF_A_KIND:
			for (int i : diceConfig) { lowerScoresIndexes[1] = points+=i; }
			break;
		case FULL_HOUSE:
			lowerScoresIndexes[2] = points = 25;
			break;
		case SMALL_STRAIGHT:
			lowerScoresIndexes[3] = points = 30;
			break;
		case LARGE_STRAIGHT:
			lowerScoresIndexes[4] = points = 40;
			break;
		case YAHTZEE:
			lowerScoresIndexes[5] = points = 50;
			break;
		case CHANCE:
			for (int i : diceConfig) { lowerScoresIndexes[6] = points+=i; }
			break;
		}
		return points;
	}
	
	// *** this method() shows the player's final score sheet ***
	private void showPlayersScoreSheet() {
		// total the upperScores
		int upperScore = 0;
		for (int i : upperScoresIndexes) {
			upperScore += i;
		}
		display.updateScorecard(UPPER_SCORE, 1, upperScore);
		
		// give Bonus score if upperScore is equal or greater than 63
		int upperBonus = upperScore>=63 ? 35 : 0;
		display.updateScorecard(UPPER_BONUS, 1, upperBonus);
		
		// total of lowerScores
		int lowerScore = 0;
		for (int i : lowerScoresIndexes) {
			lowerScore += i;
		}
		display.updateScorecard(LOWER_SCORE, 1, lowerScore);
		
		totalScore  = upperScore+upperBonus+lowerScore;		
		display.updateScorecard(TOTAL, 1, totalScore);
	}	
		
	/* Private instance variables */
	private int[] upperScoresIndexes = new int[6];
	private int[] lowerScoresIndexes = new int[7];
	int totalScore  = 0;
	private int[] dice = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
