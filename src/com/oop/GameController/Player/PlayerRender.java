package com.oop.GameController.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/*
 * Render image of player 
 */

public class PlayerRender extends JPanel {
	
	final int scale = 9;
	int bgwidth, bgheight, order;
	Player player;
	
	public PlayerRender() {}
	
	public PlayerRender(int width, int height, int order, Player player) {
		this.bgwidth = width;
		this.bgheight = height;
		this.order = order;
		this.player = player;		
	}
		
	public void paint(Graphics g) {
		BufferedImage i = null;
		
		// save position to fix and deploy health
		if (order == 1)
			player.posX = bgwidth / scale; 
		else
			player.posX = bgwidth - bgwidth / scale;
		
		player.posY = bgheight - 2 * bgheight / scale;
		
		
		// draw player depend on name and player object				
		try {
			i = ImageIO.read(new File("src/resource/characters/" + player.name + order + ".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		player.width = i.getWidth() / 3;
		player.height = i.getHeight() / 3;
		g.drawImage(i, player.posX, player.posY, player.width, player.height, null);
		
		
		// Draw health, mana, shield bar
		int cons = 0;
		
		g.setColor(Color.green);
		g.fillRect(player.posX, player.posY + player.height + cons, player.hp, 10);
		g.setColor(Color.black);
		g.drawRect(player.posX, player.posY + player.height + cons, player.true_hp, 10);
		
		g.setColor(Color.blue);
		g.fillRect(player.posX, player.posY + player.height + cons + 10, player.mana, 10);
		g.setColor(Color.black);
		g.drawRect(player.posX, player.posY + player.height + cons + 10, player.true_mana, 10);
		
		g.setColor(Color.gray);
		g.fillRect(player.posX, player.posY + player.height + cons + 20, player.shield, 10);
		g.setColor(Color.black);
		g.drawRect(player.posX, player.posY + player.height + cons + 20, player.true_shield, 10);
	}

}
