package com.github.prit55.tirolerkistl;

import java.util.Arrays;
import java.util.Random;

/**
 * Simulate an entire game of Tiroler Kirst
 *
 */
public class Game {
	
	/**
	 * This is an upper bound limit for the game duration counter.
	 */
	private static final int MAX_GAME_ROUND = 200;

	/**
	 * Identifies the game
	 */
	private final int id;
	
	/**
	 * The number of different game this class will simulate
	 */
	private final long gameNumber;
	
	/**
	 * The number of sticks with each player begin the game
	 */
	private final int startingStick;
	
	/**
	 * The number of player in the game
	 */
	private final int playerNumber;
	
	/**
	 * Keeps track of each player's sticks
	 */
	private final long[] playerStick;
	
	/**
	 * Counts the number of games each player wins
	 */
	private final long[] playerWin;
	
	/**
	 * Counts the number of games each player lost and have the most number of sticks
	 */
	private final long[] playerWorst;
	
	/**
	 * Keeps track of the sticks in the board
	 */
	private final int[] board;
	
	/**
	 * Counts the turns of each game
	 */
	private final long[] gameRound;
	
	/**
	 * Simulates the d6 dice
	 */
	private final Random random;
	
	/**
	 * Build the class
	 * @param id Identifies the game
	 * @param gameNumber The number of different game this class will simulate
	 * @param startingStick The number of sticks with each player begin the game
	 * @param playerNumber The number of player in the game
	 */
	public Game(int id, long gameNumber, int startingStick, int playerNumber) {
		this.id = id;
		this.gameNumber = gameNumber;
		this.startingStick = startingStick;
		this.playerNumber = playerNumber;
		this.playerStick = new long[this.playerNumber];
		this.playerWin = new long[this.playerNumber];
		this.playerWorst = new long[this.playerNumber];
		this.board = new int[5];
		this.gameRound = new long[MAX_GAME_ROUND];
		this.random = new Random();
	}

	public int getId() {
		return id;
	}

	public long getGameNumber() {
		return gameNumber;
	}
	
	public int getStartingStick() {
		return startingStick;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public long[] getPlayerStick() {
		return playerStick;
	}

	public long[] getPlayerWin() {
		return playerWin;
	}

	public long[] getPlayerWorst() {
		return playerWorst;
	}
	
	public int[] getBoard() {
		return board;
	}
	
	public long[] getGameRound() {
		return gameRound;
	}
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", gameNumber=" + gameNumber + ", startingStick=" + startingStick + ", playerNumber="
				+ playerNumber + ", playerWin=" + Arrays.toString(playerWin) + ", playerWorst="
				+ Arrays.toString(playerWorst) + ", gameRound=" + Arrays.toString(gameRound) + "]";
	}

	/**
	 * Start the game. It will play a number of games equals the gameNumber variable.
	 * @see reset
	 * @see firstRound
	 * @see allRound
	 * @see evaluateGame
	 */
	public void start() {
		for(int i = 0; i < gameNumber; ++i) {
			reset();
			firstRound();
			int gameRoundCount = allRound();
			evaluateGame(gameRoundCount);
		}
	}
	
	/** 
	 * Simulate the first round of the game, because the rule in this case are different. In the first round every player makes a move.
	 * @see evaluateTurn
	 */
	private void firstRound() {
		for(int i = 0; i < playerNumber; ++i) {
			int value = random.nextInt(6);
			evaluateTurn(i, value);
		}
	}
	
	/**
	 * Simulate the game starting from the second round, until the game end.<br>
	 * If the current player is the first player, increase the round counter.<br>
	 * The current player play until it finish all his sticks or draw a stick.
	 * @return The variable <b>gameRoundCount</b>, the number of round needed to end the game.
	 * 
	 * @see evaluateTurn
	 */
	private int allRound() {
		int gameRoundCount = 1;
		int currPlayer = 0;
		boolean gameEnded = false;
		while(!gameEnded) {
			if(currPlayer == 0)
				++gameRoundCount;
			boolean getStick = false;
			while(!getStick) {
				int value = random.nextInt(6);
				getStick = evaluateTurn(currPlayer, value);
				if(playerStick[currPlayer] == 0) {
					gameEnded = true;
					break;
				}
			}
			currPlayer = (currPlayer + 1) % playerNumber;
		}
		return gameRoundCount;
	}
	
	/**
	 * Check if the player remove his stick or get a new one.<br>
	 * The location number 5 of the board is an hole, so it always remove a stick.<br>
	 * If a location already has a stick the player pickup and get that stick, otherwise a put one of his stick in it.
	 * @param player The player id.
	 * @param value the value of the dice.
	 * @return TRUE if the player get a stick, FALSE otherwise
	 */
	private boolean evaluateTurn(int player, int value) {
		boolean getStick = false;
		if(value == 5)
			--playerStick[player];
		else {
			if(board[value] == 1) {
				board[value] = 0;
				++playerStick[player];
				getStick = true;
			}
			else {
				board[value] = 1;
				--playerStick[player];
			}
		}
		return getStick;
	}
	
	/**
	 * Increase the statistics related to winner, worst loser and number of game round.<br>
	 * In the first loop find the winning player and the number of sticks the worst player has.<br>
	 * In the second loop find all the worst player.<br>
	 * In the end increase the gameRound counter based on the rounds of the game.
	 * @param gameRoundCount The number of round played before the game ended
	 */
	private void evaluateGame(int gameRoundCount) {
		long maxStick = 0;
		
		for(int i = 0; i < playerNumber; ++i) {
			if(playerStick[i] > maxStick)
				maxStick = playerStick[i];
			if(playerStick[i] == 0)
				++playerWin[i];
		}
		
		for(int i = 0; i < playerNumber; ++i) {
			if(playerStick[i] == maxStick)
				++playerWorst[i];
		}
		
		if(gameRoundCount >= MAX_GAME_ROUND)
			++gameRound[0];
		
		else
			++gameRound[gameRoundCount];
		
	}
	
	/**
	 * Reset the player and the board, so a new game can be play.
	 */
	private void reset() {
		Arrays.fill(playerStick, startingStick);
		Arrays.fill(board, 0);
	}
}
