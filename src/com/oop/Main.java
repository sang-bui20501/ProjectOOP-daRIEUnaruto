package com.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.oop.GameController.Controllers.PlayerManager;
import com.oop.GameController.Controllers.RenderManager;
import com.oop.GameController.Controllers.SkillManager;
import com.oop.GameController.Player.Player;
import com.oop.GameController.Skill.SkillRender;

public class Main implements ActionListener{
	
	private static Main mainInstance = null;
	
	public String name1 = "itachi";
	public String name2 = "sasuke";
	
	public JFrame j;
	
	public final int w = 1000, h = 650;
	
	public RenderManager gameframe;
	public SkillRender renSkill;
	public PlayerManager List_Player;
	public SkillManager List_Skill;
	
	public Timer t_Skill;
	public Timer t_Player;
	public Timer t_game;
	
	public boolean gameStatus = true;
	
	int mainPlayerID = 1;
	Player mainPlayer;
	
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
						SkillManager.addSkill(renSkill);
					
					// Reset list of key pressed
					List_Key = new StringBuilder();
					
					// Reset "an chu"
					SkillManager.Mini_Skill.set(mainPlayerID - 1, new SkillRender());
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
						SkillManager.Mini_Skill.set(mainPlayerID - 1, renSkill);
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
		
		if (gameStatus && this.mainPlayer.dead) {
			System.out.println("You dead!");
			endGame(1);
		}
		
		if (gameStatus && PlayerManager.List_Player.get(mainPlayerID % 2).dead) {
			System.out.println("You won!");
			endGame(2);
		}
	}
	
	public void init() {
		
		// Form the Frame
		j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(w,h);
		j.setResizable(true);
		j.getContentPane().add(new RenderManager());
	
		
		if (gameStatus) 
		{
			// Generate information of 2 characters and save in List
			// For render and other
			// 1 = host , 2 = connect <---
			Player player1 = new Player(name1, 1);
			Player player2 = new Player(name2, 2);
			List_Player = new PlayerManager(player1, player2);
			
			// mainPlayer: only generates skill for this player
			if (mainPlayerID == 1) 
				mainPlayer = player1;
			else 
				mainPlayer = player2;
			
			// Get a list store the existing skill on one frame
			List_Skill = SkillManager.getInstance(this.mainPlayerID);
					
			// Add Keyboard Interactive 
			j.addKeyListener(kbListener);
			
			// create timer for automatic skill on frame and player attributed
			t_Skill = new Timer(5, List_Skill);
			t_Player = new Timer(500, List_Player);
			
			t_Skill.start();
			t_Player.start();
		}
		
		// Create gameframe
		gameframe = new RenderManager(gameStatus);
		j.add(gameframe);
		
		t_game = new Timer(5, this);
		t_game.start();
		j.setVisible(true);
	}
	
	public void endGame(int term) {
		t_game.stop();
		t_Player.stop();
		t_Skill.stop();
		
		this.gameStatus = false;
		j.dispose();
		init();
		
		String name;		
		if (term == 1)
			if (mainPlayerID == 1)
				name = name2;
			else
				name = name1;
		else
			if (mainPlayerID == 1)
				name = name1;
			else
				name = name2;
		JOptionPane.showMessageDialog(null, name + " VICTORY !!!!!!!!!!" , "", JOptionPane.CLOSED_OPTION);
	}
	
	
	private Main() {
		// Identify the present player is id 1 or 2 based on host or client
		this.mainPlayerID = 1;
		
		// initial
		this.init();
	}
	
	private Main(int id) {
		this.mainPlayerID = id;	
		this.init();
	}
	
	private Main(int id, String charText) {
		this.mainPlayerID = id;
		
		if (mainPlayerID == 1)
			this.name1 = charText;
		else
			this.name2 = charText;
		
		this.init();
	}
	
	
	public static Main getInstance(){
		if(mainInstance == null)
			mainInstance = new Main();
		return mainInstance;
	}
	
	public static Main getInstance(int id){
		if(mainInstance == null)
			mainInstance = new Main(id);
		return mainInstance;
	}
	
	public static Main getInstance(int id, String charText){
		if(mainInstance == null)
			mainInstance = new Main(id, charText);
		return mainInstance;
	}
}
