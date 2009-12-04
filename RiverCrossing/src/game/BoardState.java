package game;

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
	
	public Level getLevel() {
		return _level;
	}
	
	/**
	 * the following 2 fields should not really exist!
	 * (hard coded matrices - only now for demonstration)
	 */
	char[][] _verEdgesMat = {{'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'},
							 {'|','|','|','|','|','|','|'}};

	char[][] _horEdgesMat = {{'-','-','-','-','-','-','-'},
							 {'p','p','p','-','-','-','-'},
							 {'-','-','-','-','-','-','-'},
							 {'-','-','-','-','-','-','-'},
							 {'-','-','-','-','-','-','-'}};
	
	public char[][] getVerEdgeMat() {
		return _verEdgesMat; //temporary!!!
		// to do: calculate matrix from the binary strings
		// _s1chosenEdges, _s2chosenEdges and _s3chosenEdges
	}
	
	public char[][] getHorEdgeMat() {
		return _horEdgesMat;
		// to do: calculate matrix from the binary strings
		// _s1chosenEdges, _s2chosenEdges and _s3chosenEdges
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
		_s1chosenEdges = edges;
	}

	public void setS2chosenEdges(int[] edges) {
		_s2chosenEdges = edges;
	}

	public void setS3chosenEdges(int[] edges) {
		_s3chosenEdges = edges;
	}

	public BoardState(Level level) {
		this._level = level;
	}

	/**
	 * prints the board in its current state.
	 */
	public void print() {

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
	}
	
}
