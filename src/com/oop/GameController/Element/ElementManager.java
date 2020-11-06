package com.oop.GameController.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ElementManager {
	private static ElementManager instance = null;
	private ElementManager() {}
	public static ElementManager getInstance() {
		if(instance == null) 
			instance = new ElementManager();
		return instance;
	}
	
	private int counters[][];
	private File counterConfig;
	
	public void init() throws IOException{
		counterConfig = new File("/element.config");
		
		this.counters = this.handleConfig();
		
	}
	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @summary Get element.config then translate into two dimension array
	 */
	private int[][] handleConfig() throws FileNotFoundException{
		Scanner configInput = new Scanner(this.counterConfig);
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		
		while(configInput.hasNext()) {
			int counterElement = configInput.nextInt();
			int counteredElement = configInput.nextInt();
			
			result.get(counterElement).add(counteredElement);
		}
		
		configInput.close();
	
		return (int[][]) result.toArray();
				
	}
	public boolean isCounterBy(int ID, int selectedID) {
		for(int counteredElement : this.counters[ID]) {
			if(counteredElement == selectedID) return true;
		}
		return false;
	}
	
	
}
