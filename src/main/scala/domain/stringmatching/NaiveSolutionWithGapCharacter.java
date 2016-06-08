package domain.stringmatching;

import java.util.Arrays;

public class NaiveSolutionWithGapCharacter {

	private static final String GAP = "\\*";

	public static boolean match(String text, String pattern) {
		if (text == null || pattern == null) {
			throw new IllegalArgumentException();
		}

		text = text.trim().toLowerCase();
		pattern = pattern.trim().toLowerCase();

		if ("".equals(text) || "".equals(pattern)) {
			throw new IllegalArgumentException();
		}

		String[] patterns = pattern.split(GAP);

		if (patterns.length == 0) {
			return true;
		}

		int n = text.length();

		int index = 0;

		for (String p : patterns) {
			
			if (!p.equals("")) {
				
				int m = p.length();
				
				if (index > n - m) {
					return false;
				}
				
				int i = text.indexOf(p, index);
								
				if (i < 0) {
					return false;
				}
				
				index = i + m;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.asList("ac*ad*bc".split(GAP)));
		System.out.println(Arrays.asList("**ac**ad**bc".split(GAP)).get(0)
				.length());
		System.out.println(Arrays.asList("*".split(GAP)));
		System.out.println(Arrays.asList("**".split(GAP)));
		System.out.println(Arrays.asList("***".split(GAP)).size());
		
		System.out.println(match("cabccbacbacab", "ab*ba*c"));
	}

}
