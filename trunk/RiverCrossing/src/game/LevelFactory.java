package game;

import java.util.Vector;

public class LevelFactory {
	
	protected Vector<Level> _levels;
	
	/**
	 * this class will generate levels.
	 * they will be created here manually according to the
	 * 40 level-cards i have :) now only first level is available.
	 */
	public LevelFactory() {
		
		_levels = new Vector<Level>();
		
		Level level_0 = new Level();
		level_0.setStartRow(1);
		level_0.setEndRow(3);
		level_0.setSizeOnePlanks(1);
		level_0.setSizeTwoPlanks(1);
		level_0.setSizeThreePlanks(0);
		int[][] stumps = { {0,0,0,0,0,0,0}, 
						   {1,0,1,1,0,0,0}, 
						   {0,0,0,0,0,0,0}, 
						   {0,0,0,1,1,0,1}, 
						   {0,0,0,0,0,0,0}};
		level_0.setStumps(stumps);
		Edge[] validEdges = {new Edge(1,0,1,2), //initial plank
							 new Edge(1,2,1,3), // initial plank
							 new Edge(1,3,3,3),
							 new Edge(3,3,3,4),
							 new Edge(3,4,3,6)};
		
		level_0.getSizeOneEdges().add(validEdges[1]); //initial plank
		level_0.getSizeOneEdges().add(validEdges[3]);
		
		level_0.getSizeTwoEdges().add(validEdges[0]); //initial
		level_0.getSizeTwoEdges().add(validEdges[2]);
		level_0.getSizeTwoEdges().add(validEdges[4]);
		
		int[] initialSizeOne = {1,0};
		int[] initialSizeTwo = {1,0,0};
		level_0.getInitialState().setS1chosenEdges(initialSizeOne);
		level_0.getInitialState().setS2chosenEdges(initialSizeTwo);
		
		_levels.add(level_0);
	}
	
	/**
	 * returns level by index.
	 * for use by encapsulating application.
	 */
	public Level getLevel(int i) {
		return _levels.elementAt(i);
	}
}
