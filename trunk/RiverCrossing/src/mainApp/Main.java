package mainApp;

import evolutionary.GeneralAlg;
import game.Level;
import game.LevelFactory;

/**
 * run this to see level printing
 * @author AWARMAN, NARAD
 *
 */
public class Main {

	public static void main(String[] args) {
		LevelFactory factory = new LevelFactory();
		Level level = factory.getLevel(0);
		level.print();
		
		//GeneralAlg.evolutionaryRiverCrossing(level);
	}
	
}
