package game;

public class BoardState {

	/**
	 * a BoardState object corresponds to a single Level object.
	 */
	private Level _level;
	
	/**
	 * each of these field is a binary string that corresponds to a
	 * matching _sizeXXXEdges field of class Level.
	 * indicates which edge actually HAS a plank on it.
	 * the length of these strings varies from level to level however
	 * the length must be the same between a Level and a BoardState object.
	 * example:
	 * suppose _s2chosenEdge equals {0,1}.
	 * this means that the 2size plank is placed on the SECOND edge as
	 * ordered by the corresponding vector _sizeTwoEdges.
	 */
	private boolean[] _s1chosenEdges;
	private boolean[] _s2chosenEdges;
	private boolean[] _s3chosenEdges;
	

	
}
