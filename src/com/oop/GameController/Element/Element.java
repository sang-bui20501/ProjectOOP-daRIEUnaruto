package com.oop.GameController.Element;

import java.util.List;

public enum Element {
	EARTH(0, 100),
	WATER(1, 100),
	AIR(2 , 100),
	FIRE(3 , 100),
	LIGHTNING(4 , 100);
	
	int id , shieldRecduction;
	 
    Element(int id, int shieldReduction) {
    	this.id = id;
    	this.shieldRecduction = shieldReduction;
    }
    
    public int getID() {
    	return this.id;
    }
    
    @SuppressWarnings("null")
	public Element[] getCounterElements(int id) {
    	List<Element> countersList = null;
    	for(Element e : Element.values()) {
    		if(ElementManager.getInstance().isCounterBy(id, e.getID()))
    			countersList.add(e);
    	}
    	return (Element[]) countersList.toArray();
    }
}
