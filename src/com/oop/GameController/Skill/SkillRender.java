package com.oop.GameController.Skill;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.oop.GameController.Player.Player;

public class SkillRender extends JPanel {
	private static final long serialVersionUID = 3L;
	
	public String key;
	public Player player;
	
	// save the status of skill
	public boolean status = true;
	
	public SkillRender() {};
	
	public SkillRender(Player player, String key) {
		this.player = player;
		this.key = key;
	}

	public void paint(Graphics g) {
		if (this.key != null) {
			BufferedImage i = null;
			String s = new String();
			
			if (this.key.length() > 4)
				s = "src/resource/characters/" + this.key + player.id + ".png";
			else
			if (this.key.length() == 1)
				s = "src/resource/characters/" + this.key + ".png";
			
			try {
				
				// Check if generate skill, not "an chu"
				if (this.key != "Space") 
					i = ImageIO.read(new File(s));
				
			}
			catch (IOException e) {
				// if "Skill" is wrong, delete it from SkillMamager.Skill_List
				if (this.key.length() > 1) this.status = false;
					
				System.out.println("-----------------> Wrong skill <-----------------");
				return;
			}
			
			int wP = i.getWidth() / 3;
			int hP = i.getHeight() / 3;
			
			// if skill, draw upper of character
			if (this.key.length() > 4)
				g.drawImage(i, player.posX, player.posY - hP - 10, wP, hP, null);
			
			// if "an chu", draw beside of character
			if (this.key.length() == 1)
				g.drawImage(i, player.posX - (player.id == 1 ? wP : -1 * wP), player.posY, wP, hP, null);
		}
	}
}
