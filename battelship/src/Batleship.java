import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Batleship {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ArrayList<Ship> ships = new ArrayList<>();
		ships.add(new Ship("Aircraft Carrier", 5));
		ships.add(new Ship("Battleship", 4));
		ships.add(new Ship("Cruiser", 3));
		ships.add(new Ship("Submarine", 3));
		ships.add(new Ship("Destroyer", 2));

		ArrayList<PlacedShip> computerShips = new ArrayList<>();
		Board computerBoard = new Board();
		for (Ship ship : ships) {
			computerShips.add(computerBoard.placePiece(ship));
		}
		System.out.println(computerBoard.printFull());
		ArrayList<PlacedShip> userShips = new ArrayList<>();
		Board userBoard = new Board();

		Scanner input = new Scanner(System.in);
		System.out.print("Would you like the computer to place your board (y/n)? ");
		String answer = input.next();
		if (answer.equals("y")) {
			for (Ship ship : ships) {
				userShips.add(userBoard.placePiece(ship));
			}

		} else
			for (Ship ship : ships) {
				PlacedShip placedShip = null;
				while (placedShip == null) {
					System.out.println("Place: " + ship._name + " Size:" + ship._size);
					System.out.print("Enter x position: ");
					int x = input.nextInt();
					System.out.print("Enter y position: ");
					int y = input.nextInt();
					placedShip = userBoard.placePiece(ship, x, y);
					if (placedShip == null)
						System.out.println("Try again, spot occupied");
					else
						userShips.add(placedShip);
				} 
			}
		// lets play
		Random random = new Random();
		while (shipsAfloat(userShips) && shipsAfloat(computerShips)) {
			System.out.println("User's Board: ");
			System.out.println(userBoard.printFull());
			System.out.println("Computer's Hit Board: ");
			System.out.println(computerBoard.printHits());

			System.out.print("Enter x position: ");
			int x = input.nextInt();
			System.out.print("Enter y position: ");
			int y = input.nextInt();
			checkHit("User", computerShips, x, y);
			// now let the computer try - for now do it dump - just call random
			// we really should do something smart like:
			// a) don't hit where we have hit before
			// b) hit around previous hit
			checkHit("Computer", userShips, random.nextInt(10), random.nextInt(10));
		}
	}

	private static void checkHit(String who, ArrayList<PlacedShip> ships, int x, int y) {
		for (PlacedShip ship : ships) {
			// if we got a hit, stop processing the rest of the ship - no point
			if (ship.hit(who, x, y))
				return;
		}
	}

	private static boolean shipsAfloat(ArrayList<PlacedShip> ships) {
		for (PlacedShip ship : ships) {
			if (!ship.sunk())
				return true;
		}
		return false;
	}
}
