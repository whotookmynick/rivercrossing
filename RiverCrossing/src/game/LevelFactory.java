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
		_levels.add(createLevel_0());
		_levels.add(createLevel_1());
		_levels.add(createLevel_2());
		_levels.add(createLevel_3());
		_levels.add(createLevel_4());
		_levels.add(createLevel_5());
		_levels.add(createLevel_6());
		_levels.add(createLevel_7());
		_levels.add(createLevel_8());
		_levels.add(createLevel_9());
		
	}
	
	private Level createLevel_0() {
		Level lev = new Level();
		lev.setName("0");
		
		lev.setStartRow(1);
		lev.setEndRow(3);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(1);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {0,0,0,0,0,0,0}, 
						   {1,0,1,1,0,0,0}, 
						   {0,0,0,0,0,0,0}, 
						   {0,0,0,1,1,0,1}, 
						   {0,0,0,0,0,0,0} };
						
		int[] initialSizeOne = {1,0};
		int[] initialSizeTwo = {1,0,0};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_1() {
		Level lev = new Level();
		lev.setName("1");
		
		lev.setStartRow(0);
		lev.setEndRow(4);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(2);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {1,1,1,0,0,0,0}, 
						   {0,0,0,0,0,0,0}, 
						   {0,0,1,1,1,0,0}, 
						   {0,0,0,0,0,0,0}, 
						   {0,0,0,0,1,1,1} };
						
		int[] initialSizeOne = {1,0,0,0,0,0};
		int[] initialSizeTwo = {1,1};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_2() {
		Level lev = new Level();
		lev.setName("2");
		
		lev.setStartRow(2);
		lev.setEndRow(3);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(1);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {0,0,0,1,0,0,0}, 
						   {0,1,0,1,0,0,0}, 
						   {1,1,0,0,0,0,0}, 
						   {0,0,0,1,0,1,1}, 
						   {0,0,0,1,0,0,0} };
						
		int[] initialSizeOne = {0,0,1,0,0};
		int[] initialSizeTwo = {1,0,0};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_3() {
		Level lev = new Level();
		lev.setName("3");
		
		lev.setStartRow(1);
		lev.setEndRow(3);

		lev.setSizeOnePlanks(0);
		lev.setSizeTwoPlanks(2);
		lev.setSizeThreePlanks(1);
		
		int[][] stumps = { {0,1,0,1,0,0,0}, 
						   {1,0,1,0,0,0,0}, 
						   {0,1,0,0,1,0,0}, 
						   {0,0,0,1,0,0,1}, 
						   {0,0,1,0,1,0,0} };
						
		int[] initialSizeOne = {};
		int[] initialSizeTwo = {0,0,1,0,1};
		int[] initialSizeThree = {0,1,0,0};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}

	private Level createLevel_4() {
		Level lev = new Level();
		lev.setName("4");
		
		lev.setStartRow(2);
		lev.setEndRow(2);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(2);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {0,1,1,1,0,1,0}, 
						   {0,0,0,1,0,0,0}, 
						   {1,0,1,0,1,0,1}, 
						   {0,0,0,1,0,0,0}, 
						   {0,0,0,0,0,0,0} };
						
		int[] initialSizeOne = {1,0,0};
		int[] initialSizeTwo = {0,0,1,1,0,0};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_5() {
		Level lev = new Level();
		lev.setName("5");
		
		lev.setStartRow(1);
		lev.setEndRow(3);

		lev.setSizeOnePlanks(2);
		lev.setSizeTwoPlanks(1);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {0,1,0,1,1,0,0}, 
						   {1,1,0,0,0,0,0}, 
						   {0,0,1,0,1,0,0}, 
						   {0,0,0,0,0,1,1}, 
						   {0,0,1,1,0,1,0} };
						
		int[] initialSizeOne = {0,0,1,0,0,1};
		int[] initialSizeTwo = {1,0,0,0,0};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_6() {
		Level lev = new Level();
		lev.setName("6");
		
		lev.setStartRow(1);
		lev.setEndRow(3);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(1);
		lev.setSizeThreePlanks(1);
		
		int[][] stumps = { {0,1,0,0,1,0,0}, 
						   {1,0,1,0,0,0,0}, 
						   {0,0,0,0,0,0,0}, 
						   {0,1,1,0,1,1,1}, 
						   {0,1,0,0,0,0,0} };
						
		int[] initialSizeOne = {1,0,0,0};
		int[] initialSizeTwo = {1,0,0};
		int[] initialSizeThree = {0,0,1};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}

	private Level createLevel_7() {
		Level lev = new Level();
		lev.setName("7");
		
		lev.setStartRow(1);
		lev.setEndRow(3);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(2);
		lev.setSizeThreePlanks(1);
		
		int[][] stumps = { {0,0,0,0,1,0,0}, 
						   {1,0,1,0,1,0,0}, 
						   {0,0,0,0,0,0,0}, 
						   {0,0,1,0,0,1,1}, 
						   {0,0,0,1,1,0,0} };
						
		int[] initialSizeOne = {0,0,1};
		int[] initialSizeTwo = {1,1,0};
		int[] initialSizeThree = {0,1};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_8() {
		Level lev = new Level();
		lev.setName("8");
		
		lev.setStartRow(2);
		lev.setEndRow(1);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(2);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {0,1,0,1,0,0,0}, 
						   {0,0,0,0,0,1,1}, 
						   {1,0,1,1,0,0,0}, 
						   {0,0,0,1,0,1,0}, 
						   {0,0,1,0,1,0,0} };
						
		int[] initialSizeOne = {0,1,0};
		int[] initialSizeTwo = {0,0,0,1,0,0,1};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
	private Level createLevel_9() {
		Level lev = new Level();
		lev.setName("9");
		
		lev.setStartRow(2);
		lev.setEndRow(2);

		lev.setSizeOnePlanks(1);
		lev.setSizeTwoPlanks(2);
		lev.setSizeThreePlanks(0);
		
		int[][] stumps = { {0,1,0,1,0,1,0}, 
						   {0,1,0,0,0,1,0}, 
						   {1,1,0,1,0,1,1}, 
						   {0,0,0,0,0,0,0}, 
						   {0,0,0,1,0,0,0} };
						
		int[] initialSizeOne = {0,0,0,0,1,0};
		int[] initialSizeTwo = {1,0,0,0,1,0};
		int[] initialSizeThree = {};
		
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}
	
/*	public Level createLevel(int startRow, int endRow,
			int[][] stumps,
			int numOfSizeOne, int numOfSizeTwo, int numOfSizeThree,
			int[] initialSizeOne, int[] initialSizeTwo, int[] initialSizeThree) {
		Level lev = new Level();
		lev.setStartRow(startRow);
		lev.setEndRow(endRow);
		lev.setSizeOnePlanks(numOfSizeOne);
		lev.setSizeTwoPlanks(numOfSizeTwo);
		lev.setSizeThreePlanks(numOfSizeThree);
		lev.setStumps(stumps);
		lev.initPossibleEdges();
		lev.getInitialState().setS1chosenEdges(initialSizeOne);
		lev.getInitialState().setS2chosenEdges(initialSizeTwo);
		lev.getInitialState().setS3chosenEdges(initialSizeThree);
		lev.getInitialState().initHorEdgeMat();
		lev.getInitialState().initVerEdgeMat();
		return lev;
	}*/
	
	/**
	 * returns level by index.
	 * for use by encapsulating application.
	 */
	public Level getLevel(int i) {
		return _levels.elementAt(i);
	}
}
