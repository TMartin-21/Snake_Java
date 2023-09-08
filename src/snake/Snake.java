package snake;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import fruit.Fruit;
import game.Game;
import menu.Menu;

public class Snake {
	private ArrayList<Rectangle> body;
	private Rectangle head;
	private static final int dim = Game.dim;
	private int speedX = 0, speedY = 0;
	private boolean dead;
	private int length;
	private int MAX_LENGTH = (Menu.WIDTH*Menu.HEIGHT)/dim;
	private int score;
	
	public Snake() {
		score = 0;
		dead = false;
		length = 2;
		body = new ArrayList<>();
		
		head = new Rectangle(dim , dim);
		head.setLocation(Menu.WIDTH/2-dim, Menu.HEIGHT/2-dim/2);
		body.add(head);
		
		Rectangle rec = new Rectangle(dim, dim);
		rec.setLocation(Menu.WIDTH/2-2*dim, Menu.HEIGHT/2-dim/2);
		body.add(rec);
	}
	
	public void dir(int x, int y) {
		speedX = x*dim;
		speedY = y*dim;
	}
	
	public void getInput(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP && speedY != dim) {
			dir(0, -1);
		}
		if(key == KeyEvent.VK_DOWN && speedY != -dim) {
			dir(0, 1);
		}
		if(key == KeyEvent.VK_LEFT && speedX != dim) {
			dir(-1, 0);
		}
		if(key == KeyEvent.VK_RIGHT && speedX != -dim) {
			dir(1, 0);
		}
	}
	
	public void move() {
		for(int i = length-1; i > 0; i--) {
			body.set(i, body.get(i-1));
		}
		Rectangle rec = new Rectangle(dim, dim);
		rec.setLocation(head.x+speedX, head.y+speedY);
		head = rec;
		body.set(0, head);
	}
	
	public void eat(Fruit f) {
		f.eatenBy(this);
	}
	
	public void grow(int n) {
		length += n;
		score += n;
		for(int i = 0; i < n; i++) {
			Rectangle tmp = new Rectangle(dim, dim);
			tmp.setLocation(body.get(body.size()-i-1).getLocation());
			body.add(tmp);
		}
	}
	
	public boolean collisionFruit(Fruit f) {
		return head.getX() == f.getX() && head.getY() == f.getY();
	}
	
	public void collision() {
		if(head.getLocation().x < 0 || head.getLocation().x >= Menu.WIDTH || 
		   head.getLocation().y < 0 || head.getLocation().y >= Menu.HEIGHT) {
			kill();
		}
		
		for(int i = 1; i < body.size(); i++) {
			if(head.getLocation().equals(body.get(i).getLocation())) {
				kill();
			}
		}
	}
	
	public boolean win() {
		return length == MAX_LENGTH;
	}
	
	public void kill() {
		dead = true;
	}
	
	
	public static int getDim() { return dim; }
	public ArrayList<Rectangle> getBody(){ return body; }
	public boolean getState() { return dead; }
	public int getScore() { return score; }

	public void setLength(int n) { length = n; }
	public int getMaxLength() { return MAX_LENGTH; }
	public int getSpeedY() { return speedY; }
	public int getSpeedX() { return speedX; }
}
