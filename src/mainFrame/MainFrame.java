package mainFrame;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import enterName.EnterName;
import game.Game;
import menu.Menu;
import player.Player;
import stat.Stat;

public class MainFrame extends JFrame {
	private JPanel mainPanel;
	private Menu menu;
	private Stat stat;
	private EnterName enterName;
	private Game game;
	private CardLayout cardLayout;
	private ArrayList<Player> list = new ArrayList<>();;
	
	@SuppressWarnings("unchecked")
	public MainFrame() {
		super("SNAKE");

		try {
			FileInputStream f = new FileInputStream("list.txt");
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(f);
			try {
				list = (ArrayList<Player>) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("List is not exist!");
		}
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		game = new Game(this);
		menu = new Menu(this);
		stat = new Stat(this);
		enterName = new EnterName(this);	
		
		mainPanel.setLayout(cardLayout);
		mainPanel.add(game, "game");
		mainPanel.add(menu, "menu");
		mainPanel.add(stat, "stat");
		mainPanel.add(enterName, "enterName");

		cardLayout.show(mainPanel, "menu");
		
		this.add(mainPanel);
		
		this.getContentPane().setBackground(Color.BLACK);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addElement(Player player) { list.add(player); }
	public ArrayList<Player> getList() { return list; }
	public CardLayout getCardLayout() { return cardLayout; }
	public JPanel getMainPanel() { return mainPanel; }
}
