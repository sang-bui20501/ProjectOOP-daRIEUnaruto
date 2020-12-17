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
import com.oop.GameController.Controllers.SkillManager;
import com.oop.GameController.Player.Player;
import com.oop.GameController.Skill.SkillRender;

public class Main implements ActionListener{
	
	public static Main main;
	
	public static JFrame j;
	public Rectangle background;
	public RenderManager gameframe;
	public SkillRender renSkill;
	public PlayerManager List_Player;
	public SkillManager List_Skill;
	
	public Timer t_Skill;
	public Timer t_Player;
	public Timer t_game;
	
	
	int mainPlayerID = 2;
	Player mainPlayer;
	
	public final int w = 1000, h = 650;
	
	StringBuilder List_Key = new StringBuilder();
	StringBuilder miniKey = new StringBuilder();
	

    private KeyListener kbListener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			// Press key
			if (e.getKeyCode() != 0) {	
				char key = (char) e.getKeyCode();
				
				// Skill after rendered
				renSkill = new SkillRender();
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE) 
				{
					// If space then generate skill and adding to list of existing skill
					renSkill = mainPlayer.generateSkill(List_Key.toString());
					
					if (renSkill != null) 
						SkillManager.List_Skill.add(renSkill);
					
					// Reset list of key pressed
					List_Key = new StringBuilder();
					
					// Reset "an chu"
					List_Skill.Mini_Skill = new SkillRender();
				}
				else	// else generate "an chu" and add to Mini_Skill in order to generate. 
				{
					// A single "an chu"
					miniKey.append(key);
					
					// A complete chain of "an chu"
					List_Key.append(key);
					
					// generate and save in Mini_Skill
					renSkill = mainPlayer.generateSkill(miniKey.toString());
					
					if (renSkill != null) 
						List_Skill.Mini_Skill = renSkill;
				}
				
				// Reset list of key pressed
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
		j.repaint();
	}
	
	
	public void init(String name1, String name2) {
		// Generate information of 2 characters and save in List
		// For render and other
		Player player1 = new Player(name1, 1);
		Player player2 = new Player(name2, 2);
		List_Player = new PlayerManager(player1, player2);
		
		// mainPlayer: only generates skill for this player
		if (mainPlayerID == 1) 
			mainPlayer = player1;
		else 
			mainPlayer = player2;
		
		// Get a list store the existing skill on one frame
		List_Skill = SkillManager.getInstance();
				
		// Form the Frame
		j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		j.setSize(w,h);
		j.setResizable(true);
		
		// Add Keyboard Interactive 
		j.addKeyListener(kbListener);
		
		// add content of Render
		j.getContentPane().add(new RenderManager());
		
		// Create gameframe
		gameframe = new RenderManager(List_Player, List_Skill);
		j.add(gameframe);
		
		
		t_Skill = new Timer(5, List_Skill);
		t_Player = new Timer(400, List_Player);
		t_game = new Timer(5, this);
		t_Skill.start();
		t_Player.start();
		t_game.start();
	}
	
	public Main(String name1, String name2) {
		//Must be identify the present player is id 1 or 2
		//this.mainPlayerID = 1;
		
		// Pipe to init() in oder to initial
		this.init(name1, name2);
	}
	
	public static void main(String[] args){
		// main = new Main(args[0], args[1])
		// Pipe names of 2 characters after connect and choose
		//main = new Main("sasuke", "itachi");
        main = new Main("itachi", "sasuke");
    }
}
