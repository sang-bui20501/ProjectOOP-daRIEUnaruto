package com.oop;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.oop.GameController.Controllers.PlayerManager;
import com.oop.GameController.Controllers.RenderManager;
import com.oop.GameController.Player.Player;
import com.oop.GameController.Skill.SkillRender;

public class Main implements ActionListener{
	
	public static Main main;
	
	public JFrame j;
	public Rectangle background;
	public RenderManager gameframe;
	public SkillRender renSkill;
	public Timer t;
	
	int mainPlayerID = 1;
	Player mainPlayer;
	
	public final int w = 750, h = 600;
	
	StringBuilder List_Key = new StringBuilder();
	StringBuilder miniKey = new StringBuilder();
	

    private KeyListener kbListener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			// Press key
			if (e.getKeyCode() != 0) {	
				char key = (char) e.getKeyCode();
				
				miniKey.append(key);
				List_Key.append(key);
				
				renSkill = new SkillRender();
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					// If space then generate skill
					renSkill = mainPlayer.generateSkill(gameframe.save_g, List_Key.toString());
					List_Key = new StringBuilder();
				}
				else			
					// else generate "an chu"
					renSkill = mainPlayer.generateSkill(gameframe.save_g, miniKey.toString());
				
				// adding to jframe
				if (renSkill != null) j.add(renSkill);
				
				// repainting the game
				j.repaint();
				
				miniKey = new StringBuilder();			
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {}
	};

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	
	public void init(String name1, String name2) {
		// Generate information of 2 characters and save in List
		//For render and other
		Player player1 = new Player(name1);
		Player player2 = new Player(name2);
		PlayerManager List = new PlayerManager(player1, player2);
		
		// mainPlayer: only generate skill of this player
		if (mainPlayerID == 1) 
			mainPlayer = player1;
		else 
			mainPlayer = player2;
		
		
		j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		j.setSize(w,h);
		j.setResizable(true);
		j.getContentPane().add(new RenderManager());
		
		j.addKeyListener(kbListener);	// Add Keyboard Interactive 
		
		gameframe = new RenderManager(List);
		j.add(gameframe);
		
		t = new Timer(5, this);
		t.start();
	}
	
	public Main(String name1, String name2) {
		// Pipe to init() in oder to initial
		this.init(name1, name2);
	}
	
	public static void main(String[] args){
		// main = new Main(args[0], args[1])
		// Pipe names of 2 characters after connect and choose
        main = new Main("itachi", "sasuke");
    }
}
