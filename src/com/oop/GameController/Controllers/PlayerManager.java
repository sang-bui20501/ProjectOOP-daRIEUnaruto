package com.oop.GameController.Controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.oop.GameController.Player.Player;


/*
 * Manage player task like:
 * 	add 2 player on startup  
 * 	recovery mana
 * 	check mana and hp in order to decide the winner 
 */


public class PlayerManager implements ActionListener {
	public static ArrayList<Player> List_Player;
	
	// Add player object to save in list
	public PlayerManager(Player player1, Player player2) {
		List_Player =  new ArrayList<Player>(2);
		List_Player.add(player1);
		List_Player.add(player2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; ++i) {
			if (List_Player.get(i).mana < List_Player.get(i).true_mana) 
				List_Player.get(i).mana += List_Player.get(i).manaRe;
			
			if (List_Player.get(i).mana <= 0 || List_Player.get(i).hp <= 0)
				List_Player.get(i).dead = true;
		}
	}
}
