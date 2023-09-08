package enterName;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Game;
import mainFrame.MainFrame;
import menu.Menu;

public class EnterName extends JPanel {
	private JTextField textField;
	private JLabel text, text2;
	private Font font;
	private MainFrame frame;
	private Game game;
	private String playerName;
	
	private static int WIDTH = Menu.WIDTH;
	private static int HEIGHT = Menu.HEIGHT;
	
	public EnterName(MainFrame frame) {
		this.frame = frame;
		font = new Font("Ink Free", Font.BOLD, 50);

		text = new JLabel("Type your name");
		text.setFont(font);
		text.setBounds(WIDTH/2-175, HEIGHT/3-100, 350, 200);
		text.setForeground(Color.ORANGE);

		text2 = new JLabel("Press enter to start game");
		text2.setFont(new Font("Ink Free", Font.BOLD, 20));
		text2.setBounds(WIDTH/2-122, (HEIGHT*2)/3-100, 245, 50);
		text2.setForeground(Color.ORANGE);
		
		textField = new JTextField(8);
		textField.setSize(300, 85);
		textField.setBounds(WIDTH/2-textField.getWidth()/2, HEIGHT/2-textField.getHeight(), textField.getWidth(), textField.getHeight());
		textField.setFont(font);
		textField.setBackground(Color.ORANGE);
		
		textField.setFocusable(true);
		textField.addKeyListener(new EnterPressed());
		
		JButton back = new JButton("<-");
		back.addActionListener(new BackPressed());
	    back.setFont(new Font("Ink Free", Font.BOLD, 20));
		back.setBackground(Color.orange);
		back.setBounds(0, 0, back.getPreferredSize().width, back.getPreferredSize().height);
	    back.setBorderPainted(false);
		back.setFocusPainted(false);
		
		this.add(back);
		this.add(text);
		this.add(text2);
		this.add(textField);
		this.setLayout(null);
		this.setBackground(Color.black);
	}
	
	public class BackPressed implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.getCardLayout().show(frame.getMainPanel(), "menu");
		}
	}
	
	public class EnterPressed extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_ENTER) {
				playerName = textField.getText();	
				textField.setText(null);
				game = (Game) frame.getMainPanel().getComponent(0);
				game.start();
				frame.getCardLayout().show(frame.getMainPanel(), "game");
			}
		}
	}
	
	public String getName() {
		return playerName;
	}
}
