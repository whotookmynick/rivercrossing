package mainApp;

import evolutionary.GeneralAlg;
import game.BoardState;
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
		BoardState[][] states = GeneralAlg.generateRandomSolutions(level);
		for (int i=0; i<3; i++) {
			states[0][i].print();
		}	
//		GeneralAlg.evolutionaryRiverCrossing(level);
	}
	
}
