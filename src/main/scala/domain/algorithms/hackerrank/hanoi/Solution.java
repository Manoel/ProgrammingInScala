package domain.algorithms.hackerrank.hanoi;

public class Solution {

	public static void hanoi(int discs, int start, int intermediary, int end, int[] moves) {
		if (discs == 1) {
			System.out.println(start + " -> " + end);
			moves[0]++;
		} else {
			hanoi(discs - 1, start, end, intermediary, moves);
			System.out.println(start + " -> " + end);
			moves[0]++;
			hanoi(discs - 1, intermediary, start, end, moves);
		}
	}

	public static void hanoi(int discs, int start, int intermediary1, int intermediary2, int end, int[] moves) {
		if (discs == 1) {
			System.out.println(start + " -> " + end);
			moves[0]++;
		} else if (discs == 2) {
			System.out.println(start + " -> " + intermediary1);
			System.out.println(start + " -> " + end);
			System.out.println(intermediary1 + " -> " + end);
			moves[0] += 3;
		} else {
			hanoi(discs - 2, start, intermediary1, end, intermediary2, moves);
			System.out.println(start + " -> " + intermediary1);
			System.out.println(start + " -> " + end);
			System.out.println(intermediary1 + " -> " + end);
			moves[0] += 3;
			hanoi(discs - 2, intermediary2, start, intermediary1, end, moves);
		}
	}

	// iterative solution:
	// the smallest disc cycles around the poles in the same direction
	// the disc to be moved alternates between the smallest and some other disc
	//
	
	private static int moves;

	public static int solution(int[] discs) {
		if (initialState(discs))
			return 0;

		int n = discs.length;

		moves = 0;
		
		if (n % 2 == 0) {
			while (true) {
				moveOneToNext(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOneToNext(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
			}
		} else {
			while (true) {
				moveOneToNext(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
				
				moveOther(discs);
				
				if (allButOneInInitialRod(discs)) {
					moves++;
					break;
				}
			}
		}

		return moves;
	}

	private static int oneRod(int[] discs) {
		return discs[0];
	}

	private static void moveOneToNext(int[] discs) {
		int oneRod = oneRod(discs);
		int next = getNextRod(oneRod);
		discs[0] = next;
		moves++;
	}

	private static int getNextRod(int rod) {
		return (rod % 4) + 1;
	}

	private static boolean initialState(int[] discs) {
		for (int d : discs) {
			if (d != 1)
				return false;
		}
		return true;
	}

	private static void move(int[] discs, int disc) {
		int rod = discs[disc];
		int nextRod = -1;

		do {
			nextRod = getNextRod(rod);
			rod = nextRod;
		} while (!canMove(discs, disc, nextRod));

		discs[disc] = nextRod;
		moves++;
	}

	private static boolean allButOneInInitialRod(int[] discs) {
		for (int i = 1; i < discs.length; i++) {
			if (discs[i] != 1)
				return false;
		}
		return true;
	}

	private static boolean canMove(int[] discs, int disc, int rod) {
		for (int i = 0; i < disc; i++) {
			if (discs[i] == rod) {
				return false;
			}
		}
		return true;
	}

	private static void moveOther(int[] discs) {
		int disc = getNextToMove(discs);
		move(discs, disc);
	}

	private static int getNextToMove(int[] discs) {
		for (int i = 1; i < discs.length; i++) {
			boolean canMove = true;
			int nextRod = getNextRod(discs[i]);
			while (nextRod != discs[i] && !(canMove = canMove(discs, i, nextRod))) {
				nextRod = getNextRod(nextRod);
			}
			
			if (nextRod != discs[i] && canMove)
				return i;
		}			
		return -1;
	}

	public static void main(String[] args) {
		int[] moves = new int[1];
		System.out.println("Hanoi tower with 3 discs and 3 pegs");
		hanoi(3, 1, 2, 3, moves);
		System.out.println("Moves: " + moves[0]);

		moves = new int[1];
		System.out.println("Hanoi tower with 4 discs and 4 pegs");
		hanoi(4, 1, 2, 3, 4, moves);
		System.out.println("Moves: " + moves[0]);
		
		System.out.println(solution(new int[]{2, 3, 1, 1}));
	}

}
