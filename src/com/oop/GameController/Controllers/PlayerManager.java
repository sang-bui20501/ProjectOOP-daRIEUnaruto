package com.oop.GameController.Controllers;

import java.util.ArrayList;

import com.oop.GameController.Player.Player;

public class PlayerManager {
	public ArrayList<Player> List_Player = new ArrayList<Player>(2);
	
	// Add player object to save in list
	public PlayerManager(Player player1, Player player2) {
		this.List_Player.add(player1);
		this.List_Player.add(player2);
	}
}
