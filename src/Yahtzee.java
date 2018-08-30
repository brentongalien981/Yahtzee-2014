/*
 * File: Yahtzee.java
 * ------------------
 * This is the final and main version of the Yahtzee game.
 * 
 * This main()-game-file requires:
 * 		- YahtzeeConstants.java
 * 		- YahtzeeCategories.java
 * 		- YahtzeeScoring.java
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		dice = new int[N_DICE];
		
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		scoring = new YahtzeeScoring(nPlayers); // init and make an instance for game scoring
		playGame();
	}
	
	private void playGame() {
		for (int i=0; i<ROUNDS; i++) { // 13 rounds
			for (int player=1;		player<=nPlayers;		player++) { // loops to change player to the next player
				display.waitForPlayerToClickRoll(player); // let the player initialize turn
				initDice();
				display.displayDice(dice);	
				
				for (int j=1; j<TURNS; j++) { // each player gets to roll 3 times				
					display.waitForPlayerToSelectDice(); // this waits until you click the roll button again
					generateDice();
					display.displayDice(dice);	
				}
				
				updatePlayerScore(player);
			}
		}
		
		showGameResult(); 
	}
	
	private void initDice()	{
		for (int i=0; i<dice.length; i++) { dice[i] = rgen.nextInt(1, 6); }
	}	
	
	private void showGameResult() {
		scoring.finalizeScoreSheet();
		
		for (int i=0; i<nPlayers; i++) {
			display.updateScorecard(UPPER_SCORE,	i+1,	scoring.getUpperScore(i));
			display.updateScorecard(UPPER_BONUS,	i+1,	scoring.getUpperBonus(i));
			display.updateScorecard(LOWER_SCORE,	i+1,	scoring.getLowerScore(i));
			display.updateScorecard(TOTAL,			i+1,	scoring.getFinalScore(i));
		}
	}
	
	private void updatePlayerScore(int player) {
		boolean diceMatchesCategory;
		int category;
		int categoryScore;
		
		// this repeats if the player selects category that is already taken
		do {
			category = display.waitForPlayerToSelectCategory();	// "category" will equal to where player clicks	
			if (scoring.categoryIsTaken(category, player)) continue; // loop back
			break; // if category is not yet taken, terminate loop
		} while (true);
		
		// equates to true if player's dice configuration matches the category he clicks on
		diceMatchesCategory = YahtzeeCategories.checkCategory(dice, category); 
		
		// updates ONLY the score table array--NOT THE GUI
		if (diceMatchesCategory) { categoryScore = scoring.updateAndGetScore(dice, category, player); }
		else { categoryScore = scoring.updateAndGetScore(category, player); } // score is zero		
		
		// updates the GUI
		display.updateScorecard(category,	player,		categoryScore);
		display.updateScorecard(TOTAL, 	player, 	scoring.getTentativeTotal(player)); // updates player's tentative total score
	}	
	
	private void generateDice() {
		for (int i=0; i<dice.length; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
	}		
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private int[] dice;
	private YahtzeeDisplay display;
	private YahtzeeScoring scoring;
	private RandomGenerator rgen = new RandomGenerator();
}
	