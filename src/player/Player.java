package player;

import java.io.Serializable;

public class Player implements Serializable {
	private String name;
	private int score;
	
	public Player(String n, int s) {
		name = n;
		score = s;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
}


