
public class YahtzeeScoring implements YahtzeeConstants
{	
	private int nPlayers;
	private int scoreTable[][];
	
	// CONSTRUCTOR
	YahtzeeScoring(int nPlayers)
	{
		this.nPlayers = nPlayers;
		scoreTable = new int[nPlayers] [N_CATEGORIES];
		
		// init 2D array with -1 values
		for (int i=0; i<scoreTable.length; i++)
		{
			for (int j=0; j<scoreTable[i].length-2; j++)
			{
				scoreTable[i][j] = -1;
			}
		}
	}
	
	// METHOD
	public boolean categoryIsTaken(int category, int player)
	{
		if (scoreTable[player-1][category-1] > -1) { return true; }
		else
		{
			scoreTable[player-1][category-1] = 0;
			return false;
		}
	}
	
	
	// METHOD 
	public void finalizeScoreSheet()
	{
		for (int i=0; i<nPlayers; i++)
		{
			// calculates upperScore row of player[i]
			int upperScore = 0;
			for(int j=0; j<UPPER_SCORE-1; j++)
			{
				upperScore += scoreTable[i][j];
			}
			scoreTable[i][UPPER_SCORE-1] = upperScore;
			
			
			// calculate upperBonus if applicable
			scoreTable[i][UPPER_BONUS-1] = (upperScore>=63 ? 35 : 0);
			
			
			// calculates lowerScore row of player[i]
			int lowerScore = 0;
			for(int j=THREE_OF_A_KIND-1;	j<LOWER_SCORE-1;	j++)
			{
				lowerScore += scoreTable[i][j];
			}
			scoreTable[i][LOWER_SCORE-1] = lowerScore;
			
			
			// calculates final score of player[i]
			scoreTable[i][TOTAL-1] = upperScore + scoreTable[i][UPPER_BONUS-1] + lowerScore;
		}
	}

	// METHOD
	public int getUpperScore(int player) { return scoreTable[player][UPPER_SCORE-1]; }
	
	// METHOD
	public int getUpperBonus(int player) { return scoreTable[player][UPPER_BONUS-1]; }
	
	// METHOD
	public int getLowerScore(int player) { return scoreTable[player][LOWER_SCORE-1]; }
	
	// METHOD
	public int getFinalScore(int player) { return scoreTable[player][TOTAL-1]; }	
	
	// METHOD
	public int getTentativeTotal(int player) { return scoreTable[player-1][TOTAL-1]; } 	
	
	
	// METHOD
	// this is an over loaded method without the dice parameter
	// just returns 0 because player chose the wrong category
	public int updateAndGetScore(int category, int player)
	{
		scoreTable[player-1][category-1] = 0;
		return 0;
	}
	
	// METHOD
	public int updateAndGetScore(int dice[], int category, int player)
	{
		int points = 0;

		switch(category)
		{
		case ONES:
			for (int i : dice)
			{
				if (i == 1)
				{
					points+=1;
				}
			}
			scoreTable[player-1][category-1] = points;
			break;
			
		case TWOS:
			for (int i : dice) { if (i == 2) { points+=2; } }
			scoreTable[player-1][category-1] = points;
			break;
		case THREES:
			for (int i : dice) { if (i == 3) { points+=3; } }
			scoreTable[player-1][category-1] = points;
			break;
		case FOURS:
			for (int i : dice) { if (i == 4) { points+=4; } }
			scoreTable[player-1][category-1] = points;
			break;
		case FIVES:
			for (int i : dice) { if (i == 5) { points+=5; } }
			scoreTable[player-1][category-1] = points;
			break;
		case SIXES:
			for (int i : dice) { if (i == 6) { points+=6; } }
			scoreTable[player-1][category-1] = points;
			break;
			
		case THREE_OF_A_KIND:
			for (int i : dice) { points+=i; } 
			scoreTable[player-1][category-1] = points;
			break;
		case FOUR_OF_A_KIND:
			for (int i : dice) { points+=i; } 
			scoreTable[player-1][category-1] = points;
			break;
		case FULL_HOUSE:
			points = 25;
			scoreTable[player-1][category-1] = points;
			break;
		case SMALL_STRAIGHT:
			points = 30;
			scoreTable[player-1][category-1] = points;
			break;
		case LARGE_STRAIGHT:
			points = 40;
			scoreTable[player-1][category-1] = points;
			break;
		case YAHTZEE:
			points = 50;
			scoreTable[player-1][category-1] = points;
			break;
		case CHANCE:
			for (int i : dice) { points+=i; } 
			scoreTable[player-1][category-1] = points;
			break;
		}
			
		scoreTable[player-1][TOTAL-1] += points; // updates tentative total score after calculating points
		return points;
	}
}
