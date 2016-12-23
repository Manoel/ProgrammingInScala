package domain.algorithms.string;

public class Rank {
	
	int originalIndex; // stores original index of suffix
	int firstHalf; // store rank for first half of suffix
	int secondHalf; // store rank for second half of suffix
	
	@Override
	public String toString() {
		return "Rank(" + originalIndex + ")";
	}
	
}
