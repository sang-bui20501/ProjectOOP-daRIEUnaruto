package com.oop.GameController.State;

import com.oop.GameController.Player.Player;

public abstract class PlayerState {
    private String ip;
    private int sessionID, health , mana , shield;
    
	public abstract void initInstance(int heatlh , int mana);
    
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
    	
    
}
