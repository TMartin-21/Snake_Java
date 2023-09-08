package player;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest {
	private Player player, player2, player3;
	private ArrayList<Player> list;
	
	@Before
	public void setup() {
		list = new ArrayList<>();
		player = new Player("Test", 25);
		player2 = new Player("Test2", 50);
		player3 = new Player("Test3", 35);
		list.add(player);
		list.add(player2);
		list.add(player3);
	}
	
	@Test
	public void testConstr() {
		assertEquals("Test", player.getName());
		assertEquals(25, player.getScore());
		assertEquals("Test2", player2.getName());
		assertEquals(50, player2.getScore());
	}
	
	@Test
	public void testSerialize() throws IOException {
		new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(list); 
	}
	
	@Test
	public void testSort() {
		assertTrue(list.get(0).getScore() < list.get(1).getScore());
		Collections.sort(list, Collections.reverseOrder(new Sort()));
		assertTrue(list.get(0).getScore() > list.get(1).getScore());
	}
	
	@Test
	public void testData() {
		PlayerData data = new PlayerData();
		data.setData(list);
		assertEquals(3, data.getRowCount());
		assertEquals(2, data.getColumnCount());
		assertEquals(50, data.getValueAt(1, 1));
		assertEquals("Name", data.getColumnName(0));
		assertEquals(String.class, data.getColumnClass(0));
		assertEquals(Integer.class, data.getColumnClass(1));
	}
}
