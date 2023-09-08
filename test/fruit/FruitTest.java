package fruit;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import snake.Snake;

public class FruitTest {
	private Snake snake;
	
	@Before
	public void setup() {
		snake = new Snake();
	}
	
	@Test
	public void testSetPosition() {
		Fruit fruit = new Fruit(snake);
		int before[] = {fruit.getX(), fruit.getY()};
		fruit.setPosition();
		int after[] = {fruit.getX(), fruit.getY()};
		assertFalse("It did not choose a new position!", Arrays.equals(before, after));
		
		int snakePos[][] = new int[2][snake.getBody().size()];
		for(int i=0; i<snake.getBody().size()-1; i++) {
			snakePos[i][0] = (int)snake.getBody().get(i).getX();
			snakePos[i][1] = (int)snake.getBody().get(i).getY();
		}
		
		for(int i=0; i<snake.getBody().size()-1; i++) {
			assertFalse("It put it on the snake!", Arrays.equals(after, snakePos[i]));
		}
		
	}

	@Test
	public void testApple() {
		Fruit apple = new Apple(snake);
		assertEquals(2, snake.getBody().size());
		int before[] = { apple.getX(), apple.getY() };
		apple.eatenBy(snake);
		int after[] = { apple.getX(), apple.getY() };
		assertFalse("It did not choose a new position!", Arrays.equals(before, after));
		assertEquals(3, snake.getBody().size());
	}
	
	@Test
	public void testPear() {
		Fruit pear = new Pear(snake);
		assertEquals(2, snake.getBody().size());
		int before[] = { pear.getX(), pear.getY() };
		pear.eatenBy(snake);
		int after[] = { pear.getX(), pear.getY() };
		assertFalse("It did not choose a new position!!", Arrays.equals(before, after));
		assertEquals(4, snake.getBody().size());
	}
	
	@Test
	public void testPoisonedApple() {
		Fruit pApple = new PoisonedApple(snake);
		assertEquals(false, snake.getState());
		pApple.eatenBy(snake);
		assertEquals(true, snake.getState());
	}
}
