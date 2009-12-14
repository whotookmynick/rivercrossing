package game;

public class Edge {

	/**
	 * an edge means: connection between 2 points <x1,y1> and <x2,y2>. 
	 * range for each coordinate is [0..4].
	 */
	private int _x1;
	private int _y1;
	private int _x2;
	private int _y2;
	
	public Edge(int x1, int y1, int x2, int y2) {
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
	}
	public String toString() {
		return "[<row "+_x1+",col "+_y1+">, <row "+_x2+",col "+_y2+">]";
	}
	
	public int getX1() {
		return _x1;
	}
	public int getY1() {
		return _y1;
	}
	public int getX2() {
		return _y2;
	}
	public int getY2() {
		return _y2;
	}
	public boolean isHorizontal() {
		return (_x1 == _x2);
	}
	public boolean isVertical() {
		return (_y1 == _y2);
	}
}
