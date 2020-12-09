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
	
	public SkillRender() {};
	
	public SkillRender(Player player, String key) {
		this.player = player;
		this.key = key;
	}

	public void paint(Graphics g) {
		BufferedImage i = null;
		String s = "src/resource/characters/" + this.key + ".png";
		
		try {
			if (this.key != "Space") 
				i = ImageIO.read(new File(s));
		}
		catch (IOException e) {
			System.out.println("-----------------> Wrong spell <-----------------");
			return;
		}
		
		int wP = i.getWidth() / 3;
		int hP = i.getHeight() / 3;
		g.drawImage(i, player.posX + 50, player.posY + 20, wP, hP, null);
	}
}
