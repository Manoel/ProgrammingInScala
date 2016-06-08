package domain.stringmatching;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NaiveSolutionPatternAllDifferentChars {

	public static List<Integer> match(String text, String pattern) {
		if (text == null || pattern == null) {
			throw new IllegalArgumentException();
		}

		text = text.trim().toLowerCase();
		pattern = pattern.trim().toLowerCase();

		if ("".equals(text) || "".equals(pattern)) {
			throw new IllegalArgumentException();
		}

		int n = text.length();
		int m = pattern.length();

		if (m > n) {
			return new ArrayList<>();
		}

		Set<Character> chars = new HashSet<>();

		for (Character c : pattern.toCharArray()) {
			if (chars.contains(c)) {
				throw new IllegalArgumentException(
						"All characters in the pattern P must be different.");
			}
			chars.add(c);
		}

		List<Integer> validShifts = new ArrayList<>();

		for (int i = 0; i <= n - m; i++) {

			boolean valid = true;

			int j = 0;
			for (;j < m; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					valid = false;
					break;
				}
			}
			
			if (valid) {
				validShifts.add(i);
			}
			
			if ( j > 0) {
				i += j - 1;
			}

		}

		return validShifts;
	}

	public static void main(String[] args) {
		System.out.println(match("aabc", "abc"));
	}

}
