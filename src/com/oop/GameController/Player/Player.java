package com.oop.GameController.Player;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import com.oop.GameController.Skill.SkillRender;

/*
 * Save the information of player
 */

public class Player implements Serializable{
	public boolean useSkill = false;
	public boolean changeState = false;
	public int id;
	public int true_hp;
	public int hp;
	public int true_mana;
	public int mana;
	public int manaRe;
	public int true_shield;
	public int shield;
	public String element;
	public String name;
	public int posX;
	public int posY;
	public int width;
	public int height;
	
	public boolean dead = false;
	
	//public SkillManager List_Skill;
	
	public Player(String name, int id) {
		try {
			// Open file and implement information of player into Object
			
			String path = "src/resource/characters/" + name + "_info.txt";
			File obj = new File(path);
			Scanner sc = new Scanner(obj);
			
			this.name = name;
			this.id = id;
			
			this.true_hp = sc.nextInt();
			this.hp = true_hp;
			
			this.true_mana = sc.nextInt();
			this.mana = true_mana;
			
			this.manaRe = sc.nextInt();
			
			this.true_shield = sc.nextInt();
			this.shield = this.true_shield;
			
			sc.nextLine();
			this.element = sc.nextLine();
			
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Nope");
		}
	}
	
	public SkillRender generateSkill(String inputKey) {
		// Check that if pressed more than 5 key
		if (inputKey.length() == 0 || inputKey.length() > 1 && inputKey.length() < 5) return null;
		
		System.out.println(inputKey);
		
		// Paint the skill
		SkillRender re = new SkillRender(this, inputKey);
		return re;
	}
	
	// bound for intersects
	public Rectangle getBound() {
		return new Rectangle(this.posX, this.posY, this.width, this.height);
	}
}
