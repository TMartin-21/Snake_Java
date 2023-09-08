package fruit;

import java.util.Random;

import menu.Menu;
import snake.Snake;

public class Fruit {
	private int x, y;
	private Snake snake;
	
	public Fruit(Snake snake) {
		this.snake = snake;
		Random randX = new Random();
		Random randY = new Random();
		x = randX.nextInt(Menu.WIDTH/Snake.getDim())*Snake.getDim();
		y = randY.nextInt(Menu.HEIGHT/Snake.getDim())*Snake.getDim();
	}
	
	public void setPosition() {
		Random randX = new Random();
		Random randY = new Random();
		x = randX.nextInt(Menu.WIDTH/Snake.getDim())*Snake.getDim();
		y = randY.nextInt(Menu.HEIGHT/Snake.getDim())*Snake.getDim();
		
		for(int i = 0; i < snake.getBody().size(); i++) {
			while(snake.getBody().get(i).x==x && snake.getBody().get(i).y==y) {
				x = randX.nextInt(Menu.WIDTH/Snake.getDim())*Snake.getDim();
				y = randY.nextInt(Menu.HEIGHT/Snake.getDim())*Snake.getDim();
			}
		}
	}
	
	public void eatenBy(Snake s) {}
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
}
