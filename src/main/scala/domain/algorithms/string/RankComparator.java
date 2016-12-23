package domain.algorithms.string;

import java.util.Comparator;

public class RankComparator implements Comparator<Rank> {
	public int compare(Rank a, Rank b) {
		if (a.firstHalf == b.firstHalf) 
			if (a.secondHalf == b.secondHalf)
				return 0;
			else if (a.secondHalf < b.secondHalf)
				return -1;
			else
				return 1;
		
		if (a.firstHalf < b.firstHalf)
			return -1;
			
		return 1;			
	}
}
