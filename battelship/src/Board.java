import java.util.ArrayList;
import java.util.Random;
/**
 * Class to represent a board of play
 * @author family
 */
public class Board {
	Random random = new Random();
	String[][] _storage = new String[10][10];
	public ArrayList<Integer[]> _missed = new ArrayList<>();

	Board() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++)
				_storage[x][y] = "-";
		}
	} 

	public PlacedShip placePiece(Ship ship) {
		int x, y;
		PlacedShip ret = null;
		while (ret == null) {
			x = random.nextInt(10);
			y = random.nextInt(10);
			ret = placePiece(ship, x, y);
		}
		return ret;
	}

	public PlacedShip placePiece(Ship ship, int x, int y) {
		if (_storage[x][y] != "-") {
			// not a free block
			return null;
		}
		int size = ship._size;
		int availSpots = 0;
		ArrayList<Integer[]> storageData = new ArrayList<>();
		// even go across, odd go down
		if (size % 2 > 0) {
			for (int p = y; p < 10 && p < y + size; p++) {
				if (_storage[x][p].equals("-"))
					availSpots++;
			}
			if (availSpots == size) {
				for (int p = y; p < y + size; p++) {
					_storage[x][p] = "S";
					storageData.add(new Integer[] { x,p});
				}
			}
		} else {
			for (int p = x; p < 10 && p < x + size; p++) {
				if (_storage[p][y].equals("-"))
					availSpots++;
			}
			if (availSpots == size) {
				for (int p = x; p < x + size; p++) {
					_storage[p][y] = "S";
					storageData.add(new Integer[] {p,y});

				}
			}
		}
		if (availSpots != size) {
			return null;
		}
		return new PlacedShip(ship, this, storageData);

	}
	public String toString()
	{
		return printFull();
	}
	public String printHits() {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				// Hide ships
				if (_storage[x][y].equals("S"))
					sb.append("-");
				else {
					sb.append(_storage[x][y]);
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public String printFull() {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				sb.append(_storage[x][y]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
