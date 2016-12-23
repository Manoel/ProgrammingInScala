package domain.stringmatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class FiniteAutomatonMatcher {

	public static void main(String[] args) {
		String pattern = "31415";
		String text = "235902314152673992131415";
		
//		Transition transition = computeTransitionFunction(pattern, new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
//
//		System.out.println(match(text, pattern.length(), transition));
	}

	public static List<Integer> match(String text, int m,
			BiFunction<Integer, Character, Integer> transition) {
		int n = text.length();

		int q = 0;

		List<Integer> validShifts = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {

			char c = text.charAt(i);

			q = transition.apply(q, c);

			if (q == m) {
				validShifts.add(i - m + 1);
			}
		}

		return validShifts;
	}

	public static Transition computeTransitionFunction(String pattern,
			char[] alphabet) {
		int m = pattern.length();

		Transition transition = new Transition(m);

		Map<Integer, String> prefixes = new HashMap<Integer, String>();

		for (int q = 0; q <= m; q++) {

			for (char c : alphabet) {

				int k = Math.min(m + 1, q + 2);

				String prefix_k;

				String prefix_q;

				do {
					k--;

					prefix_k = getPrefix(prefixes, pattern, k);

					prefix_q = getPrefix(prefixes, pattern, q);

				} while (!isSuffix(prefix_k, prefix_q + c));

				transition.add(q, c, k);

			}

		}

		return transition;
	}

	private static boolean isSuffix(String a, String b) {
		int n = a.length() - 1;

		int m = b.length() - 1;

		for (int i = n, j = m; i >= 0; i--, j--) {
			if (a.charAt(i) != b.charAt(j)) {
				return false;
			}
		}

		return true;
	}

	private static String getPrefix(Map<Integer, String> prefixes,
			String pattern, int k) {
		if (k == 0) {
			return "";
		}

		int previous = k - 1;

		String prefix;

		if (k == 1) {
			prefix = pattern.charAt(0) + "";
		} else {
			prefix = prefixes.get(previous) + pattern.charAt(k - 1);
		}

		prefixes.put(k, prefix);

		return prefix;
	}

}

class Transition implements BiFunction<Integer, Character, Integer> {

	private Map<Integer, Map<Character, Integer>> transitions;

	public Transition(int m) {
		transitions = new HashMap<>();

		for (int q = 0; q <= m; q++) {
			transitions.put(q, new HashMap<>());
		}
	}

	public void add(int q, char c, int k) {
		transitions.get(q).put(c, k);
	}

	@Override
	public Integer apply(Integer q, Character a) {
		Map<Character, Integer> t = transitions.get(q);
		return t.getOrDefault(a, 0);
	}

}