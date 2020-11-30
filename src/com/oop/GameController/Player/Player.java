package com.oop.GameController.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Player {
	public int hp;
	public int mana;
	public int sheld;
	public String element;
	public String name;
	public int posX;
	public int posY;
	
	public Player(String name) {
		try {
			// Open file and imple information of player into Object
			
			String path = "src/resource/characters/" + name + "_info.txt";
			File obj = new File(path);
			Scanner sc = new Scanner(obj);
			
			this.hp = sc.nextInt();
			this.mana = sc.nextInt();
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
	
	public void generateSkill(String inputKey) {
		// Check that if press more than 5 key
		if (inputKey.length() < 5)
			return;
		
		System.out.println(inputKey);
	}
}
