package com.oop.GameController;

//Singleton
public class GameManager extends GameState{
	private static GameManager game_instance = null;
	
	public GameManager() {}
	
	public static GameManager getInstance() {
		if (game_instance == null)
			game_instance = new GameManager();
		
		return game_instance;
	}

	@Override
	void printout() {
		System.out.println("Testing...");
	}	
}
