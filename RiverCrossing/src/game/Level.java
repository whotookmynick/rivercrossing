package game;

import java.util.Vector;

public class Level {

	/**
	 * board is matrix of 5*5 meaning: 25 "points".
	 * each point either HAS a stump or DOES NOT have a stump.
	 * this field tells whether point<x,y> has a stump on it or not.
	 * example: [0][1][0][0][1]
	 * 			[0][0][0][1][0]
	 * 			[1][0][1][1][0]
	 * 			[0][0][0][0][0]
	 * 			[1][0][0][0][0]
	 * the zeros represent water, the ones represents stumps.
	 */
	private boolean[][] _stumps;
	
	/**
	 * indicates which row has the 'hiker' at initial state.
	 * convention: gameplay is from left to right.
	 * example: _startRow == 4 means: hiker initially stands 
	 * on the bottom left corner of the above drawn board.
	 * range: [0,4]
	 */
	private int _startRow;
	
	/**
	 * indicates on which row the hiker must stand on eventually.
	 * convention: gameplay is from left to right.
	 * example: _endRow == 0 means: hiker eventually stands 
	 * on the top right corner of the above drawn board.
	 * range: [0,4]
	 */
	private int _endRow;
	
	/**
	 * each level has a different amount of planks.
	 * planks come in exactly 3 sizes.
	 * size 1 plank: must be placed between 2 neighboring stumps.
	 * size 2 plank: must be placed between 2 stumps with distance==2
	 * (and no stump in between!)
	 * size 3 plank: must be placed between 2 stumps with distance==3
	 * (and no stump in between!)
	 * each level has its own set of planks. range for these fields: [0,5]
	 * (usually: 2-3 sizeOne planks, 1-2 sizeTwo planks, 0-1 sizeThreePlanks)
	 */
	private int _sizeOnePlanks;
	private int _sizeTwoPlanks;
	private int _sizeThreePlanks;
	
	/**
	 * a level is always defined with an initial state, meaning:
	 * the initial placement of each plank.
	 * note the 1:1 relationship between class Level and class BoardState!
	 */
	private BoardState _initialState;
	
	/**
	 * the following 3 fields are derived from _stumps field.
	 * they list the POSSIBLE LOCATIONS for each plank type to be placed on.
	 * example: _sizeTwoEdges = {<[0,2],[0,4]>,<[2,0],[2,2]>}
	 * for the above drawn board.
	 */
	private Vector<Edge> _sizeOneEdges;
	private Vector<Edge> _sizeTwoEdges;
	private Vector<Edge> _sizeThreeEdges;
}
