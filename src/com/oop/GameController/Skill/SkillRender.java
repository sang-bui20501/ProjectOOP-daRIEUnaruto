package com.oop.GameController.Skill;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.oop.Main;
import com.oop.GameController.Controllers.SkillManager;
import com.oop.GameController.Player.Player;

public class SkillRender extends JPanel implements Serializable {
	private static final long serialVersionUID = 3L;
	
	// info in skill_info.txt
	public String name;			// name of skill
	public int sl;				// number of skill need to create
	public int scale; 			// scale the skill
	public int xS; 				// position
	public int yS;
	public int speed;			// speed of skill
	public int damage; 			// damage
	public int mana;			// mana burn
	public int numAni;			// number of animation
	
	public int cntAni = 0;
	public int wS; 				// width of skill
	public int hS;				// height of skill
	
	public String key; 			// combo
	public Player player; 		// player need to render
	
	public boolean status = false;		// save the status of skill
	public int firsttime = 1;
	public int firstRender = 1;
	public int id;
	
	// path of png
	String path = new String();
	
	// private 
	
	public SkillRender() {};
	
	public SkillRender(SkillRender preSkill) {
		this.player = preSkill.player;
		this.key = preSkill.key;
		
		this.id = preSkill.id;
		
		this.name = preSkill.name;
		this.sl = preSkill.sl - 1;
		this.scale = preSkill.scale;
		this.xS = preSkill.xS + (this.id == 1 ? -20 : 20);
		this.yS = preSkill.yS + (this.sl % 2 == 0 ? -30 : 30);
		this.wS = preSkill.wS;
		this.hS = preSkill.hS;
		this.speed = preSkill.speed;
		this.damage = preSkill.damage;
		this.mana = 0;
		this.numAni = preSkill.numAni;
				
		this.status = true;
		this.firstRender = 0;
	};
	
	public SkillRender(Player player, String key) {
		this.player = player;
		this.key = key;
	}

	public void paint(Graphics g) 
	{
		BufferedImage i = null;
		if (this.key == null) return;
			
		if (this.key.length() > 4) {
				
			// if skill is created for the first time. After that, just update
			if (this.firsttime == 1) {
				
				// generate the skills
				path = "src/resource/skills/" + player.name + "/" + key + player.id + "0.png";
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
				
				
				// first time read the skill information
				if (this.firstRender == 1) {
					// Create skill for the first time
					String info = "src/resource/skills/" + player.name + "/" + key + "_info.txt";
					Scanner sc = null;
	
					try {
						sc = new Scanner(new File(info));
					} 
					catch (FileNotFoundException e) {
						// if "Skill" is wrong, set status in order to delete it from SkillMamager.Skill_List
						this.status = false;
						System.out.println("-----------------> Wrong skill <-----------------");
						return;
					}
					
					this.name = sc.nextLine();
					this.sl = sc.nextInt();
					this.scale = sc.nextInt();
					this.xS = sc.nextInt();
					this.yS = sc.nextInt();
					this.speed = sc.nextInt();
					this.damage = sc.nextInt();
					this.mana = sc.nextInt();
					this.numAni = sc.nextInt();
					
					sc.close();
					
					if (player.mana < this.mana) {
						System.out.println("Out of mana");
						destroy();
						return;
					}
					
					this.id = player.id;
					this.firstRender = 0;
					this.status = true;
					
					this.xS = (this.id == 1 ? xS : -1 * xS - 70) + player.posX;
					this.yS += player.posY;
					this.wS = i.getWidth() / scale; 
					this.hS = i.getHeight() / scale;
				
					player.mana -= this.mana;
				}
				
				this.firsttime = 0;
				
				g.drawImage(i, xS, yS, wS, hS, null);
					
				System.out.println(name);
				
				// if skill are multiple skill, render all of them
				if (this.sl != 1) {
					SkillRender nextSkill = new SkillRender(this);
					SkillManager.addSkill(nextSkill);
					this.sl = 1;
				}
			}
			
			else
				
			{
				//System.out.println("here?");
				i = update(i);
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
				System.out.println("-----------------> Wrong spell <-----------------");
				return;
			}
			
			wS = i.getWidth() / 3;
			hS = i.getHeight() / 3;
			g.drawImage(i, player.posX - (id == 1 ? wS : -1 * wS), player.posY, wS, hS, null);
		}
	}
	
	public BufferedImage update(BufferedImage i) {
		xS += (this.id == 1 ? speed : -speed);
		
		
		// generate the next animation of skill
		try {
			this.cntAni = (this.cntAni + 1) % this.numAni;
			path = "src/resource/skills/" + player.name + "/" + key + player.id + this.cntAni + ".png";
			return ImageIO.read(new File(path));
		}
		catch (IOException e) {
			destroy();
			System.out.println("-----------------> Wrong animation skill <-----------------");
		}
		
		
		// Out of window
		if (xS <= 0 || xS > Main.getInstance().j.getWidth() || yS <= 0 || yS > Main.getInstance().j.getHeight()) destroy();
		return null;
	}
	
	public void destroy() {
		this.status = false;
	}
	
	// bound of skill for intersects
	public Rectangle getBound() {
		return new Rectangle(this.xS, this.yS, this.wS, this.hS);
	}
}
