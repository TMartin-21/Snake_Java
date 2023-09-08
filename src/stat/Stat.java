package stat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mainFrame.MainFrame;
import player.PlayerData;

public class Stat extends JPanel implements ActionListener {
	private PlayerData data;
	private MainFrame frame;
		
	public Stat(MainFrame f) {
		frame = f;
		data = new PlayerData();
		data.setData(frame.getList());
		
		JTable table = new JTable(data.getRowCount(), data.getColumnCount());
		table.setFillsViewportHeight(true);
	    JScrollPane scroll = new JScrollPane(table);
	    
	    table.setModel(data);
	    table.setShowGrid(false);
	    table.setRowHeight(30);
	    table.setFont(new Font("Ink Free", Font.BOLD, 30));
	    table.setBackground(Color.black);
	    table.setForeground(Color.orange);
	    
	    JButton back = new JButton("<-");
	    back.addActionListener(this);
	    back.setFont(new Font("Ink Free", Font.BOLD, 20));
		back.setBackground(Color.orange);
		back.setBounds(0, 0, back.getPreferredSize().width, back.getPreferredSize().height);
	    back.setBorderPainted(false);
		back.setFocusPainted(false);

	    this.add(back);
	    this.add(scroll, BorderLayout.CENTER);
		this.setBackground(Color.BLACK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getCardLayout().show(frame.getMainPanel(), "menu");
	}
}
