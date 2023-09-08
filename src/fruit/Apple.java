package fruit;

import snake.Snake;

public class Apple extends Fruit {
	public Apple(Snake snake) {
		super(snake);
	}

	public void eatenBy(Snake s) {
		s.grow(1);
		setPosition();
	}
}
