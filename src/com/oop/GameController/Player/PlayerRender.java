package com.oop.GameController.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlayerRender extends JPanel {
	private static final long serialVersionUID = 2L;
	
	final int scale = 7;
	int width, height, order;
	Player player;
	
	public PlayerRender() {}
	
	public PlayerRender(int width, int height, int order, Player player) {
		this.width = width;
		this.height = height;
		this.order = order;
		this.player = player;		
	}
		
	public void paint(Graphics g) {
		BufferedImage i = null;
		
		// save position to fix and deploy health
		if (order == 1)
			player.posX = width / scale; 
		else
			player.posX = width - 2 * width / scale;
		
		player.posY = height - 2 * height / scale;
		
		
		// draw player depend on name and player object				
		try {
			i = ImageIO.read(new File("src/resource/characters/" + player.name + order + ".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		int wP = i.getWidth() / 3;
		int hP = i.getHeight() / 3;
		g.drawImage(i, player.posX, player.posY, wP, hP, null);
		
		
		// Draw health bar
		int cons = 40;
		
		g.setColor(Color.green);
		g.fillRect(player.posX, height - cons, player.hp, 10);
		g.setColor(Color.black);
		g.drawRect(player.posX, height - cons, player.true_hp, 10);
		
		g.setColor(Color.blue);
		g.fillRect(player.posX, height - cons + 10, player.mana, 10);
		g.setColor(Color.black);
		g.drawRect(player.posX, height - cons + 10, player.true_mana, 10);
	}

}
