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
	public static ArrayList<Player> List_Player; // <---- 
	
	// Add player object to save in list
	public PlayerManager(Player player1, Player player2) {
		List_Player =  new ArrayList<Player>(2);
		List_Player.add(player1);
		List_Player.add(player2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 2; ++i) {
			Player tmp = List_Player.get(i);
			
			if (tmp.mana < tmp.true_mana) tmp.mana += tmp.manaRe;
			
			System.out.println("hp " + tmp.hp);
			
			if (tmp.mana <= 0 || tmp.hp <= 0) tmp.dead = true;
		}
	}
}
