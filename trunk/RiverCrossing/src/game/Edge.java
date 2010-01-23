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
		if (x1 == x2) { //horizontal
			_size = Math.abs(y2 - y1);	
		}
		if (y1 == y2) { //vertical
			_size = Math.abs(x1 - x2);
		}
	}
	public String toString() {
		if (this.isHorizontal()) {
			return "[H"+this.getSize()+"<"+_x1+","+_y1+">/<"+_x2+","+_y2+">]";
		}
		else {
			return "[V"+this.getSize()+"<"+_x1+","+_y1+">/<"+_x2+","+_y2+">]";
		}

		//return "[<row "+_x1+",col "+_y1+">, <row "+_x2+",col "+_y2+">]";
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
	
	/**
	 * This method checks wether the edge this is touching the edge other.
	 * In order to check if there are touching edges the have to have at least one whole coordinate
	 * exactly the same 
	 */
	public boolean isTouching(Edge other) //Noams Version
	{
		if (this._x1 == other._x1 && this._y1 == other._y1){
			return true;
		}
		if (this._x1 == other._x2 && this._y1 == other._y2){
			return true;
		}
		if (this._x2 == other._x1 && this._y2 == other._y1){
			return true;
		}
		if (this._x2 == other._x2 && this._y2 == other._y2){
			return true;
		}
		return false;
	}
	/*AYALS VERSION
	 * This seems not to be working correctly.
	 * I found an example:
	 * [<row 0,col 2>, <row 2,col 2>]
	   [<row 0,col 0>, <row 0,col 1>]
	   returns true and shouldn't
	public boolean isTouching(Edge other) {
		if ((this.getX1() == other.getX1()) && (this.getY1()==other.getY1())) {
			return true;
		}
		if ((this.getX1() == other.getX2()) && (this.getY1()==other.getY2())) {
			return true;
		}
		if ((this.getX2() == other.getX1()) && (this.getY2()==other.getY1())) {
			return true;
		}
		if ((this.getX2() == other.getX2()) && (this.getY2()==other.getY2())) {
			return true;
		}
		return false;
	}
*/
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
	
	public boolean equals(Object other)
	{
		if (other instanceof Edge)
		{
			return equals((Edge)other);
		}
		return false;
	}
	
	public boolean equals(Edge other) {
		return (this._x1 == other._x1)&&
			   (this._x2 == other._x2)&&
			   (this._y1 == other._y1)&&
			   (this._y2 == other._y2);
	}
}
