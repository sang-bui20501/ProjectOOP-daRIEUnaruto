package com.oop.GameController.Skill;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.oop.GameController.Player.Player;

public class SkillRender extends JPanel {
	private static final long serialVersionUID = 3L;
	
	// info in skill_info.txt
	public String name;			// name of skill
	public int sl;				// number of skill need to create
	public int scale; 			// scale the skill
	public int xS; 				// position
	public int yS;
	public int speed;			// speed of skill
	public int damage; 			// damage
	
	public int wS; 				// width of skill
	public int hS;				// height of skill
	
	public String key; 			// combo
	public Player player; 		// player need to render
	
	public boolean status = false;		// save the status of skill
	public int firsttime = 1;
	public int id;
	
	// path of png
	String path = new String();
	
	BufferedImage i = null;
	
	public SkillRender() {};
	
	public SkillRender(Player player, String key) {
		this.player = player;
		this.key = key;
	}

	public void paint(Graphics g) 
	{
		if (this.key == null) return;
			
		if (this.key.length() > 4) {
				
			// if skill is created for the first time
			if (this.firsttime == 1) {
				path = "src/resource/skills/" + player.name + "/" + key + player.id + ".png";
				try {
					// get the skill
					i = ImageIO.read(new File(path));
				}
				catch (IOException e) {
					// if "Skill" is wrong, set status in order to delete it from SkillMamager.Skill_List
					this.status = false;
						
					System.out.println("-----------------> Wrong skill <-----------------");
					return;
				}
				
				
				// Create skill for the first time
				String info = "src/resource/skills/" + player.name + "/" + key + "_info.txt";
				Scanner sc = null;

				try {
					sc = new Scanner(new File(info));
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				this.name = sc.nextLine();
				this.sl = sc.nextInt();
				this.scale = sc.nextInt();
				this.xS = sc.nextInt();
				this.yS = sc.nextInt();
				this.speed = sc.nextInt();
				this.damage = sc.nextInt();
				
				sc.close();
				
				this.id = player.id;
				
				this.xS = (this.id == 1 ? xS : -1 * xS - 70) + player.posX;
				this.yS += player.posY;
				this.wS = i.getWidth() / scale; 
				this.hS = i.getHeight() / scale;
				
				g.drawImage(i, xS, yS, wS, hS, null);
					
				this.firsttime = 0;
				this.status = true;
					
				System.out.println(name);
			}
				
			else
				
			{
				update();
				g.drawImage(i, xS, yS, wS, hS, null);
			}
		}
			
		else
				
		{	
			// if "an chu", draw beside of character
			path = "src/resource/skills/anchu/" + this.key + ".png";
			try {
				if (this.key != "Space") 
					i = ImageIO.read(new File(path));	
			}
			catch (IOException e) {						
					System.out.println("-----------------> Wrong skill <-----------------");
					return;
			}
							
			wS = i.getWidth() / 3;
			hS = i.getHeight() / 3;
				
			g.drawImage(i, player.posX - (id == 1 ? wS : -1 * wS), player.posY, wS, hS, null);
		}
	}
	
	public void update() {
		xS += (this.id == 1 ? speed : -speed);
	}
	
	public void conflict() {
		
	}
	
	public void destroy() {
		this.status = false;
	}
}
