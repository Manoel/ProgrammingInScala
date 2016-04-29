package domain.algorithms.hackerrank.hanoitower;

public class Solution {

	private static void hanoi(int disks, int source, int intermediary1,
			int intermediary2, int end, int[] moves) {
		if (disks == 1)
			moves[0]++;
		else if (disks == 2)
			moves[0] += 3;
		else {
			hanoi(disks - 2, source, intermediary2, end, intermediary1, moves);
			moves[0] += 3;
			hanoi(disks - 2, intermediary1, source, intermediary2, end, moves);
		}
	}

	private static boolean rodOneIsGood(int[] discs) {
		int n = discs.length;

		if (discs[n - 1] != 1)
			return false;

		int previous = n - 1;
		
		for (int i = n - 2; i >= 0; i--) {
			if (discs[i] != 1 && i + 1 != previous)
				return false;
			if (discs[i] != 1)
				break;
			previous--;
		}
		
		return true;
	}
	
	private static void turnRodOneGood(int[] discs) {
		
	}
	
	private static int getSmallestDisc(int[] discs, int rod) {
		for (int i = 0; i < discs.length; i++) {
			if (discs[i] == rod)
				return i;
		}
		return -1;
	}
	
	private static boolean canMove(int[] discs, int diameter, int rod) {
		int smallest = getSmallestDisc(discs, rod);
		return diameter < smallest || smallest == -1;
	}

	private void minimumMoves() {
		// can move disc d to rod r?
		// In r there is no disc smaller than d
		// rod one is already in sequential order? (the biggest, the second
		// biggest, the third biggest, and so on)
		// Yes: find the biggest from others rods to move to rod one until done
		// No: find the biggest disc that should move to rod one. move disc from
		// rod one to other rod to open space.
	}

	public static void main(String[] args) {
		int[] moves = new int[1];
		hanoi(10, 0, 0, 1, 1, moves);
	}

}
