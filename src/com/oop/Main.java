package com.oop;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.oop.GameController.Controllers.RenderManager;

public class Main implements ActionListener{
	
	public static Main main;
	
	public JFrame j;
	public Rectangle background;
	public RenderManager gameframe;
	public Timer t;
	
	public final int w = 750, h = 600;
	
	
    public void repaint() {
    	this.repaint();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void init() {
		j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		j.setSize(w,h);
		j.setResizable(true);
		j.getContentPane().add(new RenderManager());
		
		gameframe = new RenderManager();
		j.add(gameframe);
		
		t = new Timer(5, this);
		t.start();
		
	}
	
	public Main() {
		this.init();
	}
	
	public static void main(String[] args){
		//System.out.println("Hello");
        main = new Main(); 
    }
}
