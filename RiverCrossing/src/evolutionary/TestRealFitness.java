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
		Level level = factory.getLevel(9);
		


		BoardState state1 = new BoardState(level);

		state1.setChosenEdges(new int[]{0,1,0,0,0,0}, 1);
		state1.setChosenEdges(new int[]{1,0,1,0,0,0}, 2);
		state1.setChosenEdges(new int[]{}, 3);
		state1.print();
		
		BoardState state2 = new BoardState(level);
		state2.setChosenEdges(new int[]{0,0,1,0,0,0}, 1);
		state2.setChosenEdges(new int[]{1,0,1,0,0,0}, 2);
		state2.setChosenEdges(new int[]{}, 3);
		state2.print();
		
		
		BoardState state3 = new BoardState(level);
		state3.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state3.setChosenEdges(new int[]{1,0,0,0,1,0}, 2);
		state3.setChosenEdges(new int[]{}, 3);
		state3.print();
		
		BoardState state4 = new BoardState(level);
		state4.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state4.setChosenEdges(new int[]{1,0,0,0,1,0}, 2);
		state4.setChosenEdges(new int[]{}, 3);
		state4.print();
		
		BoardState state5 = new BoardState(level);
		state5.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state5.setChosenEdges(new int[]{0,0,1,0,1,0}, 2);
		state5.setChosenEdges(new int[]{}, 3);
		state5.print();
		
		BoardState state6 = new BoardState(level);
		state6.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state6.setChosenEdges(new int[]{0,1,0,0,1,0}, 2);
		state6.setChosenEdges(new int[]{}, 3);
		state6.print();
		
		BoardState state7 = new BoardState(level);
		state7.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state7.setChosenEdges(new int[]{1,1,0,0,0,0}, 2);
		state7.setChosenEdges(new int[]{}, 3);
		state7.print();
		
		BoardState state8 = new BoardState(level);
		state8.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state8.setChosenEdges(new int[]{1,0,0,0,0,1}, 2);
		state8.setChosenEdges(new int[]{}, 3);
		state8.print();
		
		BoardState state9 = new BoardState(level);
		state9.setChosenEdges(new int[]{0,0,0,0,0,1}, 1);
		state9.setChosenEdges(new int[]{1,0,0,0,1,0}, 2);
		state9.setChosenEdges(new int[]{}, 3);
		state9.print();
		
		BoardState state10 = new BoardState(level);
		state10.setChosenEdges(new int[]{1,0,0,0,0,0,}, 1);
		state10.setChosenEdges(new int[]{1,0,0,1,0,0}, 2);
		state10.setChosenEdges(new int[]{}, 3);
		state10.print();
		
		BoardState state11 = new BoardState(level);
		state11.setChosenEdges(new int[]{1,0,0,0,0,0}, 1);
		state11.setChosenEdges(new int[]{1,0,0,0,1,0}, 2);
		state11.setChosenEdges(new int[]{}, 3);
		state11.print();
		
		BoardState state12 = new BoardState(level);
		state12.setChosenEdges(new int[]{0,1,0,1,0,0}, 1);
		state12.setChosenEdges(new int[]{0,0,0,0,1}, 2);
		state12.setChosenEdges(new int[]{}, 3);
		state12.print();
		
		BoardState[] seq = new BoardState[] {state1,state2,state3,state4,state5,state6,state7,state8,state9,state10,state11,state12};
		
		RealFitness rf = new RealFitness();
//		rf.fitnessFunction(seq);
		double x = rf.gradeLegality(seq);
		System.out.println("contexed legality: "+x);
	}

}
