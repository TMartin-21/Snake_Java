package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JPanel;

import mainFrame.MainFrame;
import player.Player;
import player.Sort;

public class Menu extends JPanel {
	private JButton start, stat, exit;
	private MainFrame frame;
	public static int WIDTH = 600;
	public static int HEIGHT = 700;
	
	public class CreatButton extends JButton{
		private int button_width = 250;
		private int button_height = 85;
		
		public CreatButton(int x, int y, String text) {
			this.setText(text);
			this.setBackground(Color.ORANGE);
			this.setBounds(x/2 - button_width/2, y-button_height, button_width, button_height);
			this.setFont(new Font("Ink Free", Font.BOLD, 50));
			this.setBorderPainted(false);
			this.setFocusPainted(false);
		}
	}
	
	public Menu(MainFrame f) {
		frame = f;
		
		start = new CreatButton(WIDTH, HEIGHT/3, "START");
		stat = new CreatButton(WIDTH, HEIGHT/2, "STAT");
		exit = new CreatButton(WIDTH, (HEIGHT*2)/3, "EXIT");
		
		start.addActionListener(new StartButton());
		stat.addActionListener(new StatButton());
		exit.addActionListener(new ExitButton());
		
		this.add(start);
		this.add(stat);
		this.add(exit);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
	}
	
	public class StartButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.getCardLayout().show(frame.getMainPanel(), "enterName");
		}
	}
	
	public class StatButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Collections.sort(frame.getList(), Collections.reverseOrder(new Sort()));
			frame.getCardLayout().show(frame.getMainPanel(), "stat");
		}
	}
	
	public class ExitButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Player> ser = frame.getList();
			try {
				FileOutputStream f = new FileOutputStream("list.txt");
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(ser);
				out.close();
			} catch (IOException e1) {
				System.out.println("Failed!");
				e1.printStackTrace();
			}
			frame.dispose();
		}
	}
}
