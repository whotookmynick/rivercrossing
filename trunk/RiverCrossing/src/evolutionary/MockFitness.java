package evolutionary;

import game.BoardState;

public class MockFitness implements Fitness {

	private final int MAX_FITNESS = 20;
	
	public FitnessResult fitnessFunction(BoardState[] sequence) {
		FitnessResult fr = new FitnessResult();
		fr.total = (int)(Math.random() * MAX_FITNESS);
		fr.legal = 0;
		fr.progress = 0;
		fr.repeat = 0;
		return fr;
	}

}
