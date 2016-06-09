package domain.stringmatching;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpMatcher {

	public static List<Integer> match(String text, String pattern, int d, int q) {
		if (text == null || pattern == null)
			throw new IllegalArgumentException();

		text = text.trim().toLowerCase();
		pattern = pattern.trim().toLowerCase();

		if ("".equals(text) || "".equals(pattern))
			throw new IllegalArgumentException();

		int n = text.length();
		int m = pattern.length();

		if (m > n) {
			return new ArrayList<>();
		}

		int p = 0;
		int t = 0;

		for (int i = 0; i < m; i++) {
			p = (d * p + Integer.parseInt("" + pattern.charAt(i))) % q;
			t = (d * t + Integer.parseInt("" + text.charAt(i))) % q;
		}

		int h = (int) Math.pow(d, m - 1) % q;

		List<Integer> validShifts = new ArrayList<>();

		for (int s = 0; s <= n - m; s++) {

			if (p == t) {

				boolean valid = true;

				for (int i = 0; valid && i < m; i++) {

					if (pattern.charAt(i) != text.charAt(i + s)) {
						valid = false;
					}

				}

				if (valid) {
					validShifts.add(s);
				}
			}

			if (s < n - m) {
				t = (d * (t - Integer.parseInt("" + text.charAt(s)) * h) + Integer
						.parseInt("" + text.charAt(s + m))) % q;
				if ( t < 0) {
					t += q;
				}
			}
		}

		return validShifts;

	}

	public static void main(String[] args) {
		String pattern = "31415";
		String text = "235902314152673992131415";
		int d = 10;
		int q = 13;

		System.out.println(match(text, pattern, d, q));

	}

}
