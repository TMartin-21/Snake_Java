package fruit;

import snake.Snake;

public class PoisonedApple extends Fruit {
	public PoisonedApple(Snake snake) {
		super(snake);
	}

	public void eatenBy(Snake s) {
		s.kill();
	}
}
