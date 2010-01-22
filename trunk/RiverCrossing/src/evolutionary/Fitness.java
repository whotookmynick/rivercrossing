package evolutionary;

import game.BoardState;

public interface Fitness {

	FitnessResult fitnessFunction(BoardState[] sequence);
}
