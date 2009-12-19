package evolutionary;

import game.BoardState;

public interface Fitness {

	int fitnessFunction(BoardState[] sequence);
}
