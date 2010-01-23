package evolutionary;

import game.BoardState;
import game.Level;
import game.LevelFactory;

public class TestRealFitness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LevelFactory factory = new LevelFactory();
		Level level = factory.getLevel(1);
		


		BoardState state1 = new BoardState(level);
		state1.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state1.setChosenEdges(new int[]{1,1}, 2);
		state1.setChosenEdges(new int[]{}, 3);
		state1.print();
		
		BoardState state2 = new BoardState(level);
		state2.setChosenEdges(new int[]{0,1,0,0,0,0}, 1);
		state2.setChosenEdges(new int[]{1,1}, 2);
		state2.setChosenEdges(new int[]{}, 3);
		state2.print();
		

		
		BoardState state3 = new BoardState(level);
		state3.setChosenEdges(new int[]{0,0,1,0,0,0}, 1);
		state3.setChosenEdges(new int[]{1,1}, 2);
		state3.setChosenEdges(new int[]{}, 3);
		state3.print();
		
		BoardState state4 = new BoardState(level);
		state4.setChosenEdges(new int[]{0,0,0,1,0,0}, 1);
		state4.setChosenEdges(new int[]{1,1}, 2);
		state4.setChosenEdges(new int[]{}, 3);
		state4.print();
		
		BoardState[] seq = new BoardState[] {state1,state2,state3,state4};
		
		RealFitness rf = new RealFitness();
		double x = rf.gradeLegality(seq);
		System.out.println("contexed legality: "+x);
	}

}
