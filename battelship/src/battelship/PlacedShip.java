package battelship;

import java.util.ArrayList;

public class PlacedShip {

	private int _xStart;
	private int _yStart;
	private int _xEnd;
	private int _yEnd;
	private ArrayList<Integer[]> shipStorage = new ArrayList<>();
	private Board _board;
	private int _size;
	private String _name;

	public PlacedShip(Ship ship, Board board, int xStart, int yStart) {
		_board = board;
		_size = ship.getSize();
		_name = ship.getName();

		_xStart = _xEnd = xStart;
		_yStart = _yEnd = yStart;
		if (_size % 2 > 0) {
			_yEnd += _size;
		} else {
			_xEnd += _size;
		}
		for (int x = _xStart; x <= _xEnd; x++) {
			for (int y = _yStart; y <= _yEnd; y++) {
				shipStorage.add(new Integer[] { x, y });
			}

		}
	}

	public boolean hit(String who, int x, int y) {
		// we hit , now record it - need to find it in our list
		boolean hit = false;
		for (Integer[] position : shipStorage) {
			if (position[0] == x && position[1] == y) {
				hit = true;
				if (_board._storage[x][y] == "H") {
					System.out.println(who + " - Already hit");
				} else {
					System.out.println(who + "  - Hit " + _name + " x:" + position[0] + " y:" + position[1]);
					_board._storage[x][y] = "H";
					if (sunk()) {
						System.out.println(who + " - Sunk " + _name);

					}
				}
			}

		}
		if (!hit) {
			if (_board._storage[x][y] == "-" && _board._storage[x][y] != "H")
				_board._storage[x][y] = "M";
		}
		return hit;
	}

	/**
	 * if the number of hits equals original size == we are sunk
	 * 
	 * @return
	 */
	public boolean sunk() {
		int count = 0;
		for (Integer[] position : shipStorage) {
			if (_board._storage[position[0]][position[1]] == "H")
				count++;
		}
		return count == _size;
	}

	public int getSize() {
		return _size;
	}

	public String getName() {
		return _name;
	}

}
