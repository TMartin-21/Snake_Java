package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import enterName.EnterName;
import fruit.Apple;
import fruit.Fruit;
import fruit.Pear;
import fruit.PoisonedApple;
import mainFrame.MainFrame;
import menu.Menu;
import player.Player;
import snake.Snake;

public class Game extends JPanel implements ActionListener{
	private Timer timer;
	private MainFrame frame;
	private Snake snake;
	private Fruit apple, pApple, pear;
	private Player player;
	private int appleTimer;
	private int pearTimer;
	private int returnTimer;
	private static final int WIDTH = Menu.WIDTH;
	private static final int HEIGHT = Menu.HEIGHT;
	
	public static final int dim = 20;
	
	public Game(MainFrame frame) {
		this.frame = frame;

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Game.this.requestFocusInWindow();
			}
		});
		
		this.setBackground(Color.black);
		this.addKeyListener(new MoveSnake());
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void start() {
		appleTimer = 0;
		pearTimer = 0;
		returnTimer = 5;
		timer = new Timer(75, this);
		timer.start();
		snake = new Snake();
		apple = new Apple(snake);
		pApple = new PoisonedApple(snake);
		pear = new Pear(snake);
		snake.dir(1, 0);		
	}
	
	public void gameOver(Graphics g) {		
		super.paint(g);
		repaint();
		
		g.setColor(Color.orange);
		g.setFont(new Font("Ink Free", Font.BOLD, 50));
		FontMetrics m = getFontMetrics(g.getFont());
		g.drawString("Game Over", (WIDTH-m.stringWidth("Game Over"))/2, HEIGHT/2);
		
		g.setFont(new Font("Ink Free", Font.BOLD, 20));
		FontMetrics m2 = getFontMetrics(g.getFont());
		g.drawString("Score: "+snake.getScore(), (WIDTH-m2.stringWidth("Score: "+snake.getScore()))/2, HEIGHT/2+g.getFont().getSize()+10);
		
		int h = HEIGHT/2+g.getFont().getSize()+10;
		g.drawString("Return to menu in "+returnTimer, (WIDTH-m2.stringWidth("Return to menu in "+returnTimer))/2, h+g.getFont().getSize()+10);
	}
	
	public void paint(Graphics g) {
		if(!snake.getState() && !snake.win()) {
			super.paintComponent(g);			
			Graphics2D g2D = (Graphics2D) g;
			
			g2D.setColor(Color.green);
			g2D.fillOval(pApple.getX(), pApple.getY(), dim, dim);
		
			g2D.setColor(Color.red);
			g2D.fillOval(apple.getX(), apple.getY(), dim, dim);
			
			g2D.setColor(Color.yellow);
			g2D.fillOval(pear.getX(), pear.getY(), dim, dim);
			
			g2D.setColor(Color.orange);
			for(Rectangle r: snake.getBody()) {
				g2D.setColor(Color.orange);
				g2D.fill(r);
				g2D.setColor(Color.red);
				g2D.drawRect(r.x, r.y, dim, dim);
			}
		
			g2D.setColor(Color.cyan);
			g2D.setFont(new Font("Ink Free", Font.BOLD, 20));
			FontMetrics m = getFontMetrics(g.getFont());
			g2D.drawString("Score: " + snake.getScore(), (WIDTH-m.stringWidth("Score: " + snake.getScore()))/2, g.getFont().getSize());
		} else {
			gameOver(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!snake.getState() && !snake.win()) {
			repaint();	
			snake.move();
			appleTimer++;
			pearTimer++;
			if(appleTimer == 50) {
				appleTimer = 0;
				pApple.setPosition();
			} 
			if(pearTimer == 30) {
				pearTimer = 0;
				pear.setPosition();
			}
			if(snake.collisionFruit(apple)) {
				snake.eat(apple);
			}
			if(snake.collisionFruit(pApple)) {
				snake.eat(pApple);
			}
			if(snake.collisionFruit(pear)) {
				snake.eat(pear);
				pearTimer = 0;
			}
			snake.collision();
		} else {
			timer.stop();
			timer = new Timer(1000, this);
			timer.start();
			synchronized(timer){
				try {
					timer.wait(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if(returnTimer!=0) {
				returnTimer -= 1;
			} else {
				timer.stop();
				EnterName panel = (EnterName) frame.getMainPanel().getComponent(3);
				player = new Player(panel.getName(), snake.getScore());
				frame.addElement(player);
				frame.getCardLayout().show(frame.getMainPanel(), "menu");
			}
		}
	}
		
	public class MoveSnake extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			snake.getInput(e);
		}
	}
}
