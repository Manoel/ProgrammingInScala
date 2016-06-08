package domain.stringmatching;

import java.util.ArrayList;
import java.util.List;

public class NaiveSolution {

	public static List<Integer> match(String text, String pattern) {
		if (text == null || pattern == null)
			throw new IllegalArgumentException();

		text = text.trim().toLowerCase();
		pattern = pattern.trim().toLowerCase();

		if ("".equals(text) || "".equals(pattern))
			throw new IllegalArgumentException();

		int n = text.length();
		int m = pattern.length();

		if (m > n)
			return new ArrayList<>();

		List<Integer> validShifts = new ArrayList<>();

		for (int i = 0; i <= n - m; i++) {
			
			boolean valid = true;
			
			for (int j = 0; valid && j < m; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					valid = false;
				}
			}
			
			if (valid) {
				validShifts.add(i);
			}
		}

		return validShifts;
	}
	
	public static void main(String[] args) {
		System.out.println(match("abcabaabcabac", "abaa"));
	}

}
