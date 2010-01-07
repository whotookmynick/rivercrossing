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
	private int _size;
	
	public Edge(int x1, int y1, int x2, int y2) {
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
		if (x1 == x2) {
			_size = y2 - y1 - 1;	
		}
		if (y1 == y2) {
			_size = x1 - x2 - 1;
		}
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
		return _x2;
	}
	public int getY2() {
		return _y2;
	}
	public int getSize() {
		return _size;
	}
	public boolean isHorizontal() {
		return (_x1 == _x2);
	}
	public boolean isVertical() {
		return (_y1 == _y2);
	}
	public boolean isTouching(Edge other) {
		if (this._x1 == other.getX1())
			return true;
		if (this._x1 == other.getX2())
			return true;
		if (this._x1 == other.getY1())
			return true;
		if (this._x1 == other.getY2())
			return true;
		if (this._x2 == other.getX1())
			return true;
		if (this._x2 == other.getX2())
			return true;
		if (this._x2 == other.getY1())
			return true;
		if (this._x2 == other.getY2())
			return true;
		if (this._y1 == other.getX1())
			return true;
		if (this._y1 == other.getX2())
			return true;
		if (this._y1 == other.getY1())
			return true;
		if (this._y1 == other.getY2())
			return true;
		if (this._y2 == other.getX1())
			return true;
		if (this._y2 == other.getX2())
			return true;
		if (this._y2 == other.getY1())
			return true;
		if (this._y2 == other.getY2())
			return true;
		return false;
	}

	public boolean isCrossing(Edge otherEdge) {
		if (this.isHorizontal()) {
			if (otherEdge.isHorizontal()) {
				return false;
			}
			else { //other is vertical
				if ((otherEdge._y1 > this._y1) && (otherEdge._y1 < this._y2)) {
					return true;
				}
			}
		}
		if (this.isVertical()) {
			if (otherEdge.isVertical()) {
				return false;
			}
			else { // other is horizontal
				if ((otherEdge._x1 > this._x1) && (otherEdge._x1 < this._x2)) {
					return true;
				}
			}
		}
		return false;
	}
}
