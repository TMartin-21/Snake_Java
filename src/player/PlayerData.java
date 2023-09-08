package player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PlayerData extends AbstractTableModel {
	 private List<Player> player = new ArrayList<Player>();
	 
	 public void setData(ArrayList<Player> list) {
	   	player = list;
	 }
	    
	@Override
	public int getRowCount() {
		return player.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Player p = player.get(rowIndex);
		switch(columnIndex) {
			case 0: return p.getName();
			default: return p.getScore();
		 }
	}
		
	@Override
	public String getColumnName(int col){
		switch(col) {
			case 0: return "Name";
			default: return "Score";
	    }
	}
		
	@SuppressWarnings("rawtypes")
	@Override
	public Class getColumnClass(int c) {
		switch(c) {
			case 0: return String.class;
			default: return Integer.class;
		}
    }
}
