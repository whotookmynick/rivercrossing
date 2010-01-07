package game;

import java.util.Vector;

public class BoardState {

	/**
	 * a BoardState object corresponds to a single Level object.
	 */
	private Level _level;
	
	/**
	 * each of these field is a binary string that corresponds to a
	 * matching _sizeXEdges field of class Level.
	 * indicates which edge actually HAS a plank on it.
	 * the length of these strings varies from level to level however
	 * the length must be the same between a Level and a BoardState object.
	 * example:
	 * suppose _s2chosenEdge equals {0,1}.
	 * this means that the 2size plank is placed on the SECOND edge as
	 * ordered by the corresponding vector _sizeTwoEdges.
	 */
	private int[] _s1chosenEdges;
	private int[] _s2chosenEdges;
	private int[] _s3chosenEdges;
	
	/**
	 * This is added in order to support more general getting and setting of chosen edges
	 * according to size of plank. (Not really functional but makes other code much nicer
	 */
	private int[][] _allChosenEdges;
	
	public Level getLevel() {
		return _level;
	}
	

	char[][] _verEdgesMat = {{'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'}};

	char[][] _horEdgesMat = {{'-','-','-','-','-','-','-'},
							 {'-','-','-','-','-','-','-'},
							 {'-','-','-','-','-','-','-'},
							 {'-','-','-','-','-','-','-'},
							 {'-','-','-','-','-','-','-'}};
	
	
	public void initVerEdgeMat() {
		for (int i=0; i<this._s1chosenEdges.length; i++) {
			if (this._s1chosenEdges[i] == 1) {
				Edge e = _level.getSizeOneEdgeMap().get(i);
				if (e.isVertical()) {
					for (int row = e.getX1(); row<e.getX2(); row++) {
						_verEdgesMat[row][e.getY1()] = 'p';
					}
				}
			}
		}
		for (int i=0; i<this._s2chosenEdges.length; i++) {
			if (this._s2chosenEdges[i] == 1) {
				Edge e = _level.getSizeTwoEdgeMap().get(i);
				if (e.isVertical()) {
					for (int row = e.getX1(); row<e.getX2(); row++) {
						_verEdgesMat[row][e.getY1()] = 'p';
					}
				}
			}
		}
		for (int i=0; i<this._s3chosenEdges.length; i++) {
			if (this._s3chosenEdges[i] == 1) {
				Edge e = _level.getSizeThreeEdgeMap().get(i);
				if (e.isVertical()) {
					for (int row = e.getX1(); row<e.getX2(); row++) {
						_verEdgesMat[row][e.getY1()] = 'p';
					}
				}
			}
		}
	}
	
	public void initHorEdgeMat() {
		for (int i=0; i<this._s1chosenEdges.length; i++) {
			if (this._s1chosenEdges[i] == 1) {
				Edge e = _level.getSizeOneEdgeMap().get(i);
				if (e.isHorizontal()) {
					for (int col = e.getY1(); col<e.getY2(); col++) {
						_horEdgesMat[e.getX1()][col] = 'p';
					}
				}
			}
		}
		for (int i=0; i<this._s2chosenEdges.length; i++) {
			if (this._s2chosenEdges[i] == 1) {
				Edge e = _level.getSizeTwoEdgeMap().get(i);
				if (e.isHorizontal()) {
					for (int col = e.getY1(); col<e.getY2(); col++) {
						_horEdgesMat[e.getX1()][col] = 'p';
					}
				}
			}
		}
		for (int i=0; i<this._s3chosenEdges.length; i++) {
			if (this._s3chosenEdges[i] == 1) {
				Edge e = _level.getSizeThreeEdgeMap().get(i);
				if (e.isHorizontal()) {
					for (int col = e.getY1(); col<e.getY2(); col++) {
						_horEdgesMat[e.getX1()][col] = 'p';
					}
				}
			}
		}
	}

	public int[] getChosenEdges(int sizeOfPlank)
	{
		return _allChosenEdges[sizeOfPlank-1];
	}
	
	public int[] getS1chosenEdges() {
		return _s1chosenEdges;
	}

	public int[] getS2chosenEdges() {
		return _s2chosenEdges;
	}

	public int[] getS3chosenEdges() {
		return _s3chosenEdges;
	}

	public void setLevel(Level _level) {
		this._level = _level;
	}

	public void setS1chosenEdges(int[] edges) {
		_allChosenEdges[0] = edges;
		_s1chosenEdges = edges;
	}

	public void setS2chosenEdges(int[] edges) {
		_allChosenEdges[1] = edges;
		_s2chosenEdges = edges;
	}

	public void setS3chosenEdges(int[] edges) {
		_allChosenEdges[2] = edges;
		_s3chosenEdges = edges;
	}

	public BoardState(Level level) {
		this._level = level;
		_allChosenEdges = new int[3][];
	}

	/**
	 * prints the board in its current state.
	 */
	public void print() {	
		System.out.print("State generated by arrays: ");
		for (int plankSize=0; plankSize<=2; plankSize++) {
			System.out.print("{");
			for (int i=0; i<_allChosenEdges[plankSize].length; i++) {
				System.out.print(_allChosenEdges[plankSize][i]);
			}
			System.out.print("}");			
		}
		System.out.println();
		for (int i=0; i<=4; i++) {
			for (int j=0; j<=6; j++) {
				if (_level.getStumps()[i][j] == 1) {
					System.out.print("@");
				}
				else {
					System.out.print(" ");
				}
				System.out.print(" ");
				System.out.print(_horEdgesMat[i][j]+" ");
			}
			System.out.println(i+"");
			for (int j=0; j<=6; j++) {
				System.out.print(_verEdgesMat[i][j]+"   ");
			}
			System.out.println("");
		}
		for (int j=0; j<=6; j++) {
			System.out.print(j+"   ");
		}
		System.out.println();
		System.out.println();

	}

	public boolean canReachEdge(int i, int plankSize) {
		Edge newEdge = _level.getEdge(i, plankSize);
		for (Edge e : this.getAllCurrentEdges()) {
			if (newEdge.isTouching(e)) {
				return true;
			}
		}
		return false;
	}
	
	public Vector<Edge> findAllTouchingEdges(Edge e) {
		return findAllTouchingEdges(new Vector<Edge>(), e);
	}
	
	public Vector<Edge> findAllTouchingEdges(Vector<Edge> touchingEdges, Edge e) {
		for (int i=0; i<this._s1chosenEdges.length && this._s1chosenEdges[i] == 1; i++) {
			Edge e1 = _level.getSizeOneEdgeMap().get(i);
			if ((e.isTouching(e1))) {
				touchingEdges.add(e1);
				touchingEdges = findAllTouchingEdges(touchingEdges, e1);			
			}
		}
		for (int i=0; i<this._s2chosenEdges.length && this._s2chosenEdges[i] == 1; i++) {
			Edge e2 = _level.getSizeTwoEdgeMap().get(i);
			if ((e.isTouching(e2))) {
				touchingEdges.add(e2);
				touchingEdges = findAllTouchingEdges(touchingEdges, e2);			
			}
		}
		for (int i=0; i<this._s3chosenEdges.length && this._s3chosenEdges[i] == 1; i++) {
			Edge e3 = _level.getSizeThreeEdgeMap().get(i);
			if ((e.isTouching(e3))) {
				touchingEdges.add(e3);
				touchingEdges = findAllTouchingEdges(touchingEdges, e3);			
			}
		}
		return touchingEdges;
	}

	public Edge findStartingEdge() {
		return this._level.findStartingEdge();
	}

	public boolean hasCrossedPlanks() {
		for (Edge e1 : this.getAllCurrentEdges()) {
			for (Edge e2 : this.getAllCurrentEdges()) {
				if (e1.isCrossing(e2)) {
					return true;
				}
			}
		}
		return false;
	}

	public Vector<Edge> getAllCurrentEdges() {
		Vector<Edge> result = new Vector<Edge>();
		for (int i=0; (i< this.getS1chosenEdges().length) && this.getS1chosenEdges()[i] == 1; i++) {
			result.add(this._level.getSizeOneEdgeMap().get(i));
		}
		for (int i=0; (i< this.getS2chosenEdges().length) && this.getS2chosenEdges()[i] == 1; i++) {
			result.add(this._level.getSizeTwoEdgeMap().get(i));
		}
		for (int i=0; (i< this.getS3chosenEdges().length) && this.getS3chosenEdges()[i] == 1; i++) {
			result.add(this._level.getSizeThreeEdgeMap().get(i));
		}
		return result;
	}

	public int getEdgeIndex(Edge e) {
		return this._level.getInverseEdgeMap().get(e);
	}

	public boolean hasPlankOn(Edge e) {
		return this.getChosenEdges(e.getSize())[this.getEdgeIndex(e)] == 1;
	}
	
}
