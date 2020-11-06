package com.oop.GameController.Player;

/**
 * Material of 1 character.
 * 
 * @author CongTrung
 *
 */
public class Player {
	int hp;
	int mana;
	int sheld;
	int element;
	
	public Player() {}
	public Player(int hp, int mana, int sheld, int element) {
		this.hp = hp;
		this.mana = mana;
		this.sheld = sheld;
		this.element = element;
	}
}
