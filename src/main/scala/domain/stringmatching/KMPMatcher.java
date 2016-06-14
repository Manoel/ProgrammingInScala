package domain.stringmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KMPMatcher {
	
	public static void main(String[] args) {
		String pattern = "31415";
		String text = "235902314152673992131415";
		
		System.out.println(match(text, pattern));
	}

	public static List<Integer> match(String text, String pattern) {

		int n = text.length();

		int m = pattern.length();

		Function<Integer, Integer> prefixFn = computePrefixFn(pattern);

		int q = -1;

		List<Integer> validShifts = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {

			while (q > -1 && pattern.charAt(q + 1) != text.charAt(i)) {
				q = prefixFn.apply(q);
			}

			if (pattern.charAt(q + 1) == text.charAt(i)) {
				q++;
			}

			if (q + 1 == m) {
				validShifts.add(i - m + 1);
				
				q = prefixFn.apply(q);
			}

		}

		return validShifts;

	}

	public static Function<Integer, Integer> computePrefixFn(String pattern) {

		int m = pattern.length();
		
		PrefixFunction prefixFn = new PrefixFunction(m);

		int k = -1;
		
		for (int q = 1; q < m; q++) {
			
			while (k > -1 && pattern.charAt(k + 1) != pattern.charAt(q)) {
				k = prefixFn.apply(k);
			}
			
			if (pattern.charAt(k + 1) == pattern.charAt(q)) {
				k++;
			}
			
			prefixFn.updatePrefixFn(q, k);
			
		}
		
		return prefixFn;
	}

}

class PrefixFunction implements Function<Integer, Integer> {

	private int[] pi;

	public PrefixFunction(int m) {
		this.pi = new int[m];
		this.pi[0] = -1;
	}

	public void updatePrefixFn(int q, int k) {
		this.pi[q] = k;
	}

	@Override
	public Integer apply(Integer q) {
		return pi[q];
	}

}