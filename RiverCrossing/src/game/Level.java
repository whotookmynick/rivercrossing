package game;

import java.util.HashMap;
import java.util.Vector;

public class Level {

	/**
	 * board is matrix of 5*7 meaning: 35 "points".
	 * however column zero and 6 are not really part of the game,
	 * they represent river banks, always having one "stump" as
	 * start point and one as end point.
	 * each point either HAS a stump or DOES NOT have a stump.
	 * this field tells whether point<x,y> has a stump on it or not.
	 * example: [0][1][0][0][1][0][0]
	 * 			[0][0][0][1][0][0][1]
	 * 			[1][0][1][1][0][0][0]
	 * 			[0][0][0][0][0][0][0]
	 * 			[1][0][0][0][0][0][0]
	 * the zeros represent water, the ones represents stumps.
	 */
	private int[][] _stumps;
	
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
	
	/**
	 * mapping between index number and edge.
	 * this is an important field!
	 * this is used to retrieve actual edge from the binary string that
	 * represents where each edge is on the board.
	 */
	private HashMap<Integer, Edge> _SizeOneEdgeMap;
	private HashMap<Integer, Edge> _SizeTwoEdgeMap;
	private HashMap<Integer, Edge> _SizeThreeEdgeMap;
	
	public Level() {

		this._endRow = 0;
		this._startRow = 0;
		this._sizeOneEdges = new Vector<Edge>();
		this._sizeTwoEdges = new Vector<Edge>();
		this._sizeThreeEdges = new Vector<Edge>();
		this._initialState = new BoardState(this);
		this._SizeOneEdgeMap = new HashMap<Integer, Edge>();
		this._SizeTwoEdgeMap = new HashMap<Integer, Edge>();
		this._SizeThreeEdgeMap = new HashMap<Integer, Edge>();
		this._stumps = new int[5][7];
		for (int i=0; i<=4; i++) {
			for (int j=0; j<=6; j++) {
				this._stumps[i][j]=0;
			}
		}
	}
	
	public void initPossibleEdges() {
	_sizeOneEdges = findPossibleEdges(_stumps, 0);
	_sizeTwoEdges = findPossibleEdges(_stumps, 1);
	_sizeThreeEdges = findPossibleEdges(_stumps, 2);
	}
	
	public void print() {
		this._initialState.print();
		this.printMapping();
	}
	
	public void printMapping() {
		System.out.println("**************** EDGES MAPPING ***************");
		System.out.println("*** size 1 edges mapping ***");
		for (int i : this._SizeOneEdgeMap.keySet()) {
			System.out.println("index "+ i +" mapped to edge: "+_SizeOneEdgeMap.get(i));
		}
		System.out.println("*** size 2 edges mapping ***");
		for (int i : this._SizeTwoEdgeMap.keySet()) {
			System.out.println("index "+ i +" mapped to edge: "+_SizeTwoEdgeMap.get(i));
		}
		System.out.println("*** size 3 edges mapping ***");
		for (int i : this._SizeThreeEdgeMap.keySet()) {
			System.out.println("index "+ i +" mapped to edge: "+_SizeThreeEdgeMap.get(i));
		}
		System.out.println("**********************************************");
	}
	
	public Vector<Edge> findPossibleEdges(int[][] mat, int size) {
		int[] counters = new int[3];
		for (int i=0; i<=2; i++) {
			counters[i] = 0;
		}
		Vector<Edge> result = new Vector<Edge>();
		for (int i=0; i<=4; i++) {
			for (int j=0; j<=6; j++) {
				if ( (validVerEdge(size,i,j,i+size+1,j))) {
					Edge e = new Edge(i,j,i+size+1,j);
					result.add(e);
					if (size == 0) {
						_SizeOneEdgeMap.put(counters[0], e);
					}
					if (size == 1) {
						_SizeTwoEdgeMap.put(counters[1], e);
					}
					if (size == 2) {
						_SizeThreeEdgeMap.put(counters[2], e);
					}
					counters[size]++;
				}
				if ((validHorEdge(size,i,j,i,j+size+1))) {
					Edge e = new Edge(i,j,i,j+size+1);
					result.add(e);
					if (size == 0) {
						_SizeOneEdgeMap.put(counters[0], e);
					}
					if (size == 1) {
						_SizeTwoEdgeMap.put(counters[1], e);
					}
					if (size == 2) {
						_SizeThreeEdgeMap.put(counters[2], e);
					}
					counters[size]++;
				}	
			}
		}
		return result;
	}

	
	private boolean withinBoardLimits(int x1, int y1, int x2, int y2) {
		return ((x1>=0) && (x1<=4) &&
		       (y1>=0) && (y1<=6) &&
		       (x2>=0) && (x2<=4) &&
		       (y2>=0) && (y2<=6));
	}
	
	private boolean validVerEdge(int size, int x1, int y1, int x2, int y2) {
		if (!withinBoardLimits(x1,y1,x2,y2)) {
			return false;
		}
		boolean edgeBetweenStumps = ((_stumps[x1][y1]==1) &&
									 (_stumps[x2][y1]==1));
		if (!edgeBetweenStumps) {
			return false;
		}	
		boolean noStumpBetween = true;
		for (int i = x1+1; i<x2; i++) {
			if ((i<=4) && (_stumps[i][y1] == 1)) {
				noStumpBetween = false;
			}
		}		
		return noStumpBetween;
	}
	
	private boolean validHorEdge(int size, int x1, int y1, int x2, int y2) {
		if (!withinBoardLimits(x1,y1,x2,y2)) {
			return false;
		}
		boolean edgeBetweenStumps = ((_stumps[x1][y1]==1) &&
									 (_stumps[x1][y2]==1));
		if (!edgeBetweenStumps) {
			return false;
		}	
		boolean noStumpBetween = true;
		for (int i = y1+1; i<y2; i++) {
			if ((i<=6) && (_stumps[x1][i] == 1)) {
				noStumpBetween = false;
			}
		}		
		return noStumpBetween;
	}


	/**
	 * getters and setters
	 */
	public int[][] getStumps() {
		return _stumps;
	}
	public int getStartRow() {
		return _startRow;
	}
	public int getEndRow() {
		return _endRow;
	}
	public int getSizeOnePlanks() {
		return _sizeOnePlanks;
	}
	public int getSizeTwoPlanks() {
		return _sizeTwoPlanks;
	}
	public int getSizeThreePlanks() {
		return _sizeThreePlanks;
	}
	public BoardState getInitialState() {
		return _initialState;
	}
	public Vector<Edge> getSizeOneEdges() {
		return _sizeOneEdges;
	}
	public Vector<Edge> getSizeTwoEdges() {
		return _sizeTwoEdges;
	}
	public Vector<Edge> getSizeThreeEdges() {
		return _sizeThreeEdges;
	}
	public void setStumps(int[][] _stumps) {
		this._stumps = _stumps;
	}
	public void setStartRow(int row) {
		_startRow = row;
	}
	public void setEndRow(int row) {
		_endRow = row;
	}
	public void setSizeOnePlanks(int onePlanks) {
		_sizeOnePlanks = onePlanks;
	}
	public void setSizeTwoPlanks(int twoPlanks) {
		_sizeTwoPlanks = twoPlanks;
	}
	public void setSizeThreePlanks(int threePlanks) {
		_sizeThreePlanks = threePlanks;
	}
	public void setInitialState(BoardState state) {
		_initialState = state;
	}
	public void setSizeOneEdges(Vector<Edge> oneEdges) {
		_sizeOneEdges = oneEdges;
	}
	public void setSizeTwoEdges(Vector<Edge> twoEdges) {
		_sizeTwoEdges = twoEdges;
	}
	public void setSizeThreeEdges(Vector<Edge> threeEdges) {
		_sizeThreeEdges = threeEdges;
	}
}


