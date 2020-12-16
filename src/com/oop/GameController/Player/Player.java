package com.oop.GameController.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.oop.GameController.Skill.SkillRender;

public class Player {
	public int id;
	public int true_hp;
	public int hp;
	public int true_mana;
	public int mana;
	public int sheld;
	public String element;
	public String name;
	public int posX;
	public int posY;
	
	public Player(String name, int id) {
		try {
			// Open file and implement information of player into Object
			
			String path = "src/resource/characters/" + name + "_info.txt";
			File obj = new File(path);
			Scanner sc = new Scanner(obj);
			
			this.id = id;
			this.true_hp = sc.nextInt();
			this.hp = true_hp;
			this.true_mana = sc.nextInt();
			this.mana = true_mana;
			this.sheld= sc.nextInt();
			sc.nextLine();
			this.element = sc.nextLine();
			this.name = name;
			
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
}
