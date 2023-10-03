package com.github.prit55.tirolerkistl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The runner class used in the simulation.
 *
 */
public class Runner {
	
	/**
	 * The number of simultaneous games that will be play.
	 */
	private static final int SIMULTANEOUS_GAME = 5;
	
	/**
	 * The number of game each game are going to play.
	 */
	private static final long GAME_NUMBER = 2_000_000_000L;
	
	/**
	 * The player starting sticks based on the rule of the game.
	 */
	private static final int START_STICK = 6;

	/**
	 * Run the simulation.
	 * 
	 * @throws InterruptedException
	 * 
	 * @see ThreadGame
	 * @see Game
	 */
	public static void main(String[] args) throws InterruptedException {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Game[] games = new Game[SIMULTANEOUS_GAME];
		ThreadGame[] threads = new ThreadGame[SIMULTANEOUS_GAME];
		
		Date d = new Date();
		System.out.println(dateTimeFormat.format(d) + " START");
		
		for(int i = 0; i < SIMULTANEOUS_GAME; ++i) {
			int playerNumber = i + 2;
			games[i] = new Game(i, GAME_NUMBER, START_STICK, playerNumber);
			threads[i] = new ThreadGame(games[i]);
			threads[i].start();
		}
		
		for(int i = 0; i < SIMULTANEOUS_GAME; ++i) {
			threads[i].join();
		}
		
		System.out.println("\nGAME_NUMBER = " + GAME_NUMBER + ", START_STICK = " + START_STICK);
		
		for(int i = 0; i < SIMULTANEOUS_GAME; ++i) {
			System.out.println(games[i].toString());
		}
		
		d = new Date();
		System.out.println("\n" + dateTimeFormat.format(d) + " END");
		
	}

}
