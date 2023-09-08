package snake;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import fruit.Apple;
import fruit.Fruit;
import fruit.Pear;
import fruit.PoisonedApple;
import game.Game;


public class SnakeTest {
	private Snake snake;
	
	@Before
	public void setup() {
		snake = new Snake();
	}
	
	@Test
	public void testConstr() {
		assertEquals(2, snake.getBody().size());
		assertEquals(0, snake.getScore());
		assertEquals(false, snake.getState());
		assertNotNull(snake.getBody());
	}
	
	@Test
	public void testDir() {
		snake.dir(4, 0);
		assertEquals(4*Snake.getDim(), snake.getSpeedX());
		
		snake.dir(3, -7);
		assertEquals(3*Snake.getDim(), snake.getSpeedX());
		assertEquals(-7*Snake.getDim(), snake.getSpeedY());
	}
	
	@Test
	public void testGetInput() {
		Game game = new Game(null);
		KeyEvent up = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP, 'W');
		KeyEvent down = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'S');
		KeyEvent left = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'A');
		KeyEvent right = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'D');
		
		snake.getInput(up);
	    assertEquals(-Snake.getDim(), snake.getSpeedY());
	    
	    snake.getInput(left);
	    assertEquals(-Snake.getDim(), snake.getSpeedX());
	    
	    snake.getInput(down);
	    assertEquals(Snake.getDim(), snake.getSpeedY());
	    
	    snake.getInput(right);
	    assertEquals(Snake.getDim(), snake.getSpeedX());
	}
	
	@Test
	public void testMove() {
		int before[] = {(int) snake.getBody().get(0).getX(), (int) snake.getBody().get(0).getY()};
		snake.dir(0, -1);
		snake.move();
		int after[] = {(int) snake.getBody().get(0).getX(), (int) snake.getBody().get(0).getY()};
		assertFalse("Head position did not change!", Arrays.equals(before, after));
		
		int before2[] = after;
		snake.dir(1, 0);
		snake.move();
		int after2[] = {(int) snake.getBody().get(0).getX(), (int) snake.getBody().get(0).getY()};
		assertFalse("Head position did not change!", Arrays.equals(before2, after2));
	}
	
	@Test
	public void testEat() {
		Fruit apple = new Apple(snake);
		Fruit pApple = new PoisonedApple(snake);
		Fruit pear = new Pear(snake);
		
		assertEquals(2, snake.getBody().size());
		
		snake.eat(apple);
		assertEquals(3, snake.getBody().size());
		
		snake.eat(pear);
		assertEquals(5, snake.getBody().size());
		
		assertEquals(false, snake.getState());
		snake.eat(pApple);
		assertEquals(true, snake.getState());
	}
	
	@Test
	public void testGrow() {
		snake.grow(2);
		assertEquals(4, snake.getBody().size());
		assertEquals(2, snake.getScore());
		assertNotNull(snake.getBody().get(2));
		assertNotNull(snake.getBody().get(3));
		
		snake = new Snake();
		snake.grow(1);
		assertEquals(3, snake.getBody().size());
		assertEquals(1, snake.getScore());
		assertNotNull(snake.getBody().get(2));
	}
	
	@Test
	public void testCollisionFruit() {
		Fruit fruit = new Fruit(snake);
		assertEquals(false, snake.collisionFruit(fruit));
		fruit.setX((int) snake.getBody().get(0).getX());
		fruit.setY((int) snake.getBody().get(0).getY());
		assertEquals(true, snake.collisionFruit(fruit));
	}
	
	@Test
	public void testCollision() {
		//Wall collision
		assertEquals(false, snake.getState());
		snake.dir(1, 0);
		for(int i = 0; i <= 15; i++) {
			snake.move();
		}
		snake.collision();
		assertEquals(true, snake.getState());

		snake = new Snake(); 
		assertEquals(false, snake.getState());
		snake.dir(-1, 0);
		for(int i = 0; i <= 15; i++) {
			snake.move();
		}
		snake.collision();
		assertEquals(true, snake.getState());
		
		snake = new Snake();
		assertEquals(false, snake.getState());
		snake.dir(0, -1); //fel
		for(int i = 0; i <= 17; i++) {
			snake.move();
		}
		snake.collision();
		assertEquals(true, snake.getState());
		
		snake = new Snake();
		assertEquals(false, snake.getState());
		snake.dir(0, 1); //le
		for(int i = 0; i <= 17; i++) {
			snake.move();
		}
		snake.collision();
		assertEquals(true, snake.getState());
		
		//SelfCollision
		snake = new Snake();
		Fruit pear = new Pear(snake);
		assertEquals(false, snake.getState());
		snake.dir(1, 0);
		snake.move();
		snake.eat(pear);
		snake.eat(pear); 
		snake.move();
		snake.dir(0, -1);
		snake.move();
		snake.dir(-1, 0);
		snake.move();
		snake.collision();
		assertEquals(false, snake.getState());
		snake.dir(0, 1);
		snake.move();
		snake.collision();
		assertEquals(true, snake.getState());
	}
	
	@Test
	public void testWin() {
		assertEquals(false, snake.win());
		snake.setLength(snake.getMaxLength());
		assertEquals(true, snake.win());
	}
	
	@Test
	public void testKill() {
		assertEquals(false, snake.getState());
		snake.kill();
		assertEquals(true, snake.getState());
	}
}
