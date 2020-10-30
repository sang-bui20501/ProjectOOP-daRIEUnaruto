package com.oop.GameController.Controllers;

import java.util.ArrayList;

import com.oop.GameController.State.PlayerState;

//Singleton
public class GameManager extends GameState {
	private static GameManager game_instance = null;

	private GameManager() {
	}

	public static GameManager getInstance() {
		if (game_instance == null)
			game_instance = new GameManager();

		return game_instance;
	}


	
	@Override
	ArrayList<?> pushState(ArrayList<?> state) {
		
		return new ArrayList<PlayerState>();
	}

	@Override
	void resetState(ArrayList<?> state) {

	}

	@Override
	void printState(ArrayList<?> state) {
		for( Object childComponent : state){
			System.out.println(childComponent.toString());
		}
	}
}
