package fruit;

import snake.Snake;

public class Pear extends Fruit {
	public Pear(Snake snake) {
		super(snake);
	}

	public void eatenBy(Snake s) {
		s.grow(2);
		setPosition();
	}
}
