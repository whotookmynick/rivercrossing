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
						   {0,0,0,0,0,0,0} };
		level_0.setStumps(stumps);
		level_0.initPossibleEdges();
				
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
