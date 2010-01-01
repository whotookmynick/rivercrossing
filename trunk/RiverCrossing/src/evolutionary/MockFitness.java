package evolutionary;

import game.BoardState;

public class MockFitness implements Fitness {

	private final int MAX_FITNESS = 20;
	
	public int fitnessFunction(BoardState[] sequence) {
		
		return (int)(Math.random() * MAX_FITNESS);
	}

}
