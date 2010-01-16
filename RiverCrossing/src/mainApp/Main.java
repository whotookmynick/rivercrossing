package mainApp;

import java.util.Random;

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
		Level level = factory.getLevel(5);
		level.print();
		GeneralAlg.evolutionaryRiverCrossing(level);
//		BoardState[][] states = GeneralAlg.generateRandomSolutions(level);
//		for (int i=0; i<20; i++) {
//			states[0][i].print();
//		}	
//		//GeneralAlg.evolutionaryRiverCrossing(level);
//		System.out.println("******************** NEW POPULATION **************");
//		BoardState[][] newStates = GeneralAlg.createNewPopulation(states);
//		for (int i=0; i<20; i++) {
//			newStates[0][i].print();
//		}
	}
	
}
