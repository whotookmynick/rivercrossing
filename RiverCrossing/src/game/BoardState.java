package game;

import java.security.AllPermission;
import java.util.Arrays;
import java.util.HashMap;
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

	int _fitness;


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
			if (this._s1chosenEdges[i] == 0) {
				Edge e = _level.getSizeOneEdgeMap().get(i);
				if ((e != null) && (e.isVertical())) {
					for (int row = e.getX1(); row<e.getX2(); row++) {
						_verEdgesMat[row][e.getY1()] = '|';
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
			if (this._s2chosenEdges[i] == 0) {
				Edge e = _level.getSizeTwoEdgeMap().get(i);
				if ((e != null) && (e.isVertical())) {
					for (int row = e.getX1(); row<e.getX2(); row++) {
						_verEdgesMat[row][e.getY1()] = '|';
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
			if (this._s3chosenEdges[i] == 0) {
				Edge e = _level.getSizeThreeEdgeMap().get(i);
				if ((e != null) && (e.isVertical())) {
					for (int row = e.getX1(); row<e.getX2(); row++) {
						_verEdgesMat[row][e.getY1()] = '|';
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
			if (this._s1chosenEdges[i] == 0) {
				Edge e = _level.getSizeOneEdgeMap().get(i);
				if ((e != null) && (e.isHorizontal())) {
					for (int col = e.getY1(); col<e.getY2(); col++) {
						_horEdgesMat[e.getX1()][col] = '-';
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
			if (this._s2chosenEdges[i] == 0) {
				Edge e = _level.getSizeTwoEdgeMap().get(i);
				if ((e != null) && (e.isHorizontal())) {
					for (int col = e.getY1(); col<e.getY2(); col++) {
						_horEdgesMat[e.getX1()][col] = '-';
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
			if (this._s3chosenEdges[i] == 0) {
				Edge e = _level.getSizeThreeEdgeMap().get(i);
				if ((e != null) && (e.isHorizontal())) {
					for (int col = e.getY1(); col<e.getY2(); col++) {
						_horEdgesMat[e.getX1()][col] = '-';
					}
				}
			}
		}
	}

	public int[] getChosenEdges(int sizeOfPlank)
	{
		return _allChosenEdges[sizeOfPlank];
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

	public int getFitness() {
		return this._fitness;
	}

	public void setFitness(int fitness) {
		this._fitness = fitness;
	}

	public void setLevel(Level _level) {
		this._level = _level;
	}

	public void setS1chosenEdges(int[] edges) {
		_allChosenEdges[0] = edges;
		_s1chosenEdges = edges;
		this.initHorEdgeMat();
		this.initVerEdgeMat();
	}

	public void setS2chosenEdges(int[] edges) {
		_allChosenEdges[1] = edges;
		_s2chosenEdges = edges;
		this.initHorEdgeMat();
		this.initVerEdgeMat();
	}

	public void setS3chosenEdges(int[] edges) {
		_allChosenEdges[2] = edges;
		_s3chosenEdges = edges;
		this.initHorEdgeMat();
		this.initVerEdgeMat();
	}

	public void setChosenEdges(int[] edges,int size)
	{
		_allChosenEdges[size - 1] = edges;
		switch (size) {
		case 1:
			_s1chosenEdges = edges;
			_allChosenEdges[0] = edges;
			break;
		case 2:
			_allChosenEdges[1] = edges;
			_s2chosenEdges = edges;
			break;
		case 3:
			_allChosenEdges[2] = edges;
			_s3chosenEdges = edges;
			break;
		default:
			break;
		}
		this.initHorEdgeMat();
		this.initVerEdgeMat();
	}

	public BoardState(Level level) {
		this._level = level;
		_allChosenEdges = new int[3][1];
		this._s1chosenEdges = new int[1];
		this._s2chosenEdges = new int[1];
		this._s3chosenEdges = new int[1];
		this._fitness = 0;
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

	public String toString()
	{
		String ans = "";
		for (int plankSize=0; plankSize<=2; plankSize++) {
			ans += "{";
			for (int i=0; i<_allChosenEdges[plankSize].length; i++) {
				ans += _allChosenEdges[plankSize][i];
			}
			ans += "}";			
		}
		return ans;
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
		return findAllTouchingEdges(new Vector<Edge>(), e); //AYALS VERSION
		//Noams VERSION
//		Vector<Edge> touchingPlanks = new Vector<Edge>();
//		touchingPlanks.add(e);
//		return findAllTouchingEdges(touchingPlanks, new Vector<Edge>());
	}

	/*Noam's version*/
	public Vector<Edge> findAllTouchingEdges(Vector<Edge> touchingPlanks, Vector<Edge> touchingEdges) {
		Vector<Edge> ansEdges = new Vector<Edge>();
		Vector<Edge> ansPlanks = new Vector<Edge>();
		int[] currEdges = _s1chosenEdges;
		//Vector<Edge> currEdges = _level.getEdgesInSize(1);
		findTouchingEdgesForChosenEdges(currEdges, touchingPlanks,touchingEdges, ansEdges,ansPlanks,_level.getSizeOneEdgeMap());
		
		currEdges = _s2chosenEdges;
		findTouchingEdgesForChosenEdges(currEdges, touchingPlanks,touchingEdges, ansEdges,ansPlanks,_level.getSizeTwoEdgeMap());
		
		currEdges = _s3chosenEdges;
		findTouchingEdgesForChosenEdges(currEdges, touchingPlanks,touchingEdges, ansEdges,ansPlanks,_level.getSizeThreeEdgeMap());
		
		touchingEdges.addAll(ansEdges);
		touchingPlanks.addAll(ansPlanks);
		if (!ansEdges.isEmpty() | !ansPlanks.isEmpty())
		{
			findAllTouchingEdges(touchingPlanks,touchingEdges);
		}
//		touchingEdges.addAll(touchingPlanks);
		return touchingEdges;
	}

	private void findTouchingEdgesForChosenEdges(int[] currEdges,
												Vector<Edge> touchingPlanks,Vector<Edge> touchingEdges,
												Vector<Edge> ansEdges,Vector<Edge> ansPlanks,HashMap<Integer,Edge> edgeMap)
	{
		
		for (int i = 0; i < currEdges.length; i++)
		{
			Edge curr = edgeMap.get(i);
			if (!touchingPlanks.contains(curr) & !touchingEdges.contains(curr) & 
					!ansEdges.contains(curr) & !ansPlanks.contains(curr))
			{
				for (Edge goodEdge : touchingPlanks)
				{
					if (curr.isTouching(goodEdge))
					{
						if (currEdges[i] == 1)
							ansPlanks.add(curr);
						else
							ansEdges.add(curr);
					}
				}
			}
		}
	}
	
	/*AYAL's Version*/
	public Vector<Edge> findAllTouchingEdges(Vector<Edge> touchingEdges, Edge e) {
		//System.out.println("inside findAllTouchingEdges for:");
		//System.out.println(touchingEdges);
		//System.out.println(e);
		//System.out.println("##########");
		for (int i=0; i<this._s1chosenEdges.length; i++) {
			if (this._s1chosenEdges[i] == 1) {
				Edge e1 = _level.getSizeOneEdgeMap().get(i);
				if ((! touchingEdges.contains(e1)) && (e.isTouching(e1))) {
					touchingEdges.add(e1);
					touchingEdges = findAllTouchingEdges(touchingEdges, e1);			
				}
			}
		}
		for (int i=0; i<this._s2chosenEdges.length; i++) {
			if (this._s2chosenEdges[i] == 1) {
				Edge e2 = _level.getSizeTwoEdgeMap().get(i);
				if ((! touchingEdges.contains(e2)) && (e.isTouching(e2))) {
					touchingEdges.add(e2);
					touchingEdges = findAllTouchingEdges(touchingEdges, e2);			
				}
			}
		}
		for (int i=0; i<this._s3chosenEdges.length; i++) {
			if (this._s3chosenEdges[i] == 1){
				Edge e3 = _level.getSizeThreeEdgeMap().get(i);
				if ((! touchingEdges.contains(e3)) && (e.isTouching(e3))) {
					touchingEdges.add(e3);
					touchingEdges = findAllTouchingEdges(touchingEdges, e3);			
				}	
			}
		}
		touchingEdges.add(e);
		touchingEdges = removeDups(touchingEdges);
		return touchingEdges;
	}
	 

	private Vector<Edge> removeDups(Vector<Edge> edges) {
		Vector<Edge> filtered = new Vector<Edge>();
		for (Edge e1 : edges) {
			boolean foundEqual = false;
			for (Edge e2 : filtered) {
				if (e1.equals(e2)) {
					foundEqual = true;
				}
			}
			if (foundEqual == false) {
				filtered.add(e1);
			}
		}
		return filtered;
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
		for (int i=0; (i< this.getS1chosenEdges().length); i++) {
			if (this.getS1chosenEdges()[i] == 1) {
				result.add(this._level.getSizeOneEdgeMap().get(i));	
			}
		}
		for (int i=0; (i< this.getS2chosenEdges().length); i++) {
			if (this.getS2chosenEdges()[i] == 1) {
				result.add(this._level.getSizeTwoEdgeMap().get(i));	
			}
		}
		for (int i=0; (i< this.getS3chosenEdges().length); i++) {
			if (this.getS3chosenEdges()[i] == 1) {
				result.add(this._level.getSizeThreeEdgeMap().get(i));	
			}
		}
		return result;
	}

	public int getEdgeIndex(Edge e) {
		//System.out.println("inside getEdgeIndex for e="+e);
		if (e.getSize() == 1) {
			for (int k : this._level.getSizeOneEdgeMap().keySet()) {
				if (this._level.getSizeOneEdgeMap().get(k).equals(e)) {
					return k;
				}
			}
		}
		if (e.getSize() == 2) {
			for (int k : this._level.getSizeTwoEdgeMap().keySet()) {
				if (this._level.getSizeTwoEdgeMap().get(k).equals(e)) {
					return k;
				}
			}
		}
		if (e.getSize() == 3) {
			for (int k : this._level.getSizeThreeEdgeMap().keySet()) {
				if (this._level.getSizeThreeEdgeMap().get(k).equals(e)) {
					return k;
				}
			}
		}
		return 0;
	}

	public boolean hasPlankOn(Edge e) {
		if (this.getChosenEdges(e.getSize()-1).length == 0) {
			return false;
		}
		return (this.getChosenEdges(e.getSize()-1)[this.getEdgeIndex(e)] == 1);
	}

	public boolean equals(Object o)
	{
		if (o instanceof BoardState)
		{
			BoardState other = (BoardState)o;
			boolean isEqual = true;
			isEqual = isEqual & equalArrays(_s1chosenEdges,other._s1chosenEdges);
			isEqual = isEqual & equalArrays(_s2chosenEdges,other._s2chosenEdges);
			isEqual = isEqual & equalArrays(_s3chosenEdges,other._s3chosenEdges);
			return isEqual;
		}
		else
			return false;
	}

	private static boolean equalArrays(int[] a,int b[])
	{
		for (int i = 0; i < a.length;i++)
		{
			if (a[i] != b[i])
				return false;
		}
		return true;
	}
	
	public boolean equals(BoardState other) {
		boolean ans = false;
		boolean flag0 = (this._level.getName().equals(other.getLevel().getName()));
		boolean flag1 = (this._s1chosenEdges.length == other.getS1chosenEdges().length);
		boolean flag2 = (this._s2chosenEdges.length == other.getS2chosenEdges().length);
		boolean flag3 = (this._s3chosenEdges.length == other.getS3chosenEdges().length);
		//System.out.println(flag0 +" "+ flag1 + " "+ flag2 +" "+ flag3);
		if (!flag0 || !flag1 || !flag2 || !flag3) {
			return false;
		}
		boolean flag4 = true; boolean flag5 = true; boolean flag6 = true;
		for (int i = 0; i< this._s1chosenEdges.length; i++) {
			if  ( ! (this._s1chosenEdges[i] == other.getS1chosenEdges()[i])) {
				flag4 = false;
			}
		}
		for (int i = 0; i< this._s2chosenEdges.length; i++) {
			if ( ! (this._s2chosenEdges[i] == other.getS2chosenEdges()[i])) {
				flag5 = false;
			}
		}
		for (int i = 0; i< this._s3chosenEdges.length; i++) {
			if ( ! (this._s3chosenEdges[i] == other.getS3chosenEdges()[i])) {
				flag6 = false;
			}
		}
		//System.out.println(flag4 +" "+ flag5 + " "+ flag6);
		ans = flag1 && flag2 && flag3 && flag4 && flag5 && flag6;
		return ans;
	}
}
