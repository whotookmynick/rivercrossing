package solution;

import java.util.Vector;

import game.BoardState;
import game.Level;

public class Solution {

	/**
	 * a solution is defined for a single level.
	 */
	protected Level _level;
	
	/**
	 * a solution (legal or not) is defined as a sequence of states.
	 * ie. sequence of plank-placing on the board.
	 */
	protected Vector<BoardState> _sequence;
	
	
}
