import java.util.ArrayList;

public class PlacedShip {

	private Board _board;
	private int _size;
	private String _name;
	private ArrayList<Integer[]> _shipStorage;

	public PlacedShip(Ship ship, Board board, ArrayList<Integer[]> shipStorage) {
		_board = board;
		_shipStorage = shipStorage;
		_size = ship.getSize();
		_name = ship.getName();

	}

	public boolean hit(String who, int x, int y) {
		// we hit , now record it - need to find it in our list
		boolean hit = false;
		for (Integer[] position : _shipStorage) {
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
		for (Integer[] position : _shipStorage) {
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
