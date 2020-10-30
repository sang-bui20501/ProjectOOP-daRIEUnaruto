package com.oop.GameController.Controllers;

import java.util.ArrayList;

public abstract class GameState {
	abstract void printState(ArrayList<?> state);
	
	abstract ArrayList<?> pushState(ArrayList<?> state);// Current state -> new State

	abstract void resetState(ArrayList<?> state);
}
