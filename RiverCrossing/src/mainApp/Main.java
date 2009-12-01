package mainApp;

import game.Level;
import game.LevelFactory;

/**
 * run this to see level printing
 * @author AWARMAN
 *
 */
public class Main {

	public static void main(String[] args) {
		LevelFactory factory = new LevelFactory();
		Level level = factory.getLevel(0);
		level.print();
	}
	
}
