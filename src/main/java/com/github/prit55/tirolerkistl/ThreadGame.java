package com.github.prit55.tirolerkistl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class allow to run a game on a different thread. It will print the date it start and the date it end.
 * 
 * @see Game
 * @see Thread
 *
 */
public class ThreadGame extends Thread {
	
	private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private final Game game;
	
	/**
	 * Build the class.
	 * @param game The game you want to be played.
	 * 
	 * @see Game
	 */
	public ThreadGame(Game game) {
		super();
		this.game = game;
	}

	/**
	 * Call the start() method in the Game class. It also print the time before and after the execution.
	 * 
	 * @see Game
	 */
	@Override
	public void run() {
		Date d = new Date();
		System.out.println(dateTimeFormat.format(d) + " Game " + game.getId() + " is running");
		game.start();
		d = new Date();
		System.out.println(dateTimeFormat.format(d) + " Game " + game.getId() + " ended");
    }
}
