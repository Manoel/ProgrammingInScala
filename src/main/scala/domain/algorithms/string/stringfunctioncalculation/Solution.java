package domain.algorithms.string.stringfunctioncalculation;

import domain.algorithms.string.Rank;
import domain.algorithms.string.StringUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String T = in.next();

		Rank[] suffixArray = StringUtils.suffixArray(T);

		int[] lcp = StringUtils.kasai(T, suffixArray);

		System.out.println(Arrays.toString(suffixArray));

		System.out.println(Arrays.toString(lcp));

		System.out.println(getMaxArea(lcp));

		in.close();
	}

	public static int getMaxArea(int[] hist) {
		int n = hist.length;
		Stack<Integer> s = new Stack<>();
		int max_area = 0;
		int tp;
		int area_with_top;
		int i = 0;
		while (i < n) {
			if (s.empty() || hist[s.peek()] <= hist[i])
				s.push(i++);
			else {
				tp = s.peek(); // store the top index
				s.pop(); // pop the top
				area_with_top = hist[tp] * (s.empty() ? i + 1 : i - s.peek());
				if (max_area < area_with_top)
					max_area = area_with_top;
			}
		}
		while (!s.empty()) {
			tp = s.peek();
			s.pop();
			area_with_top = hist[tp] * (s.empty() ? i + 1 : i - s.peek());
			if (max_area < area_with_top)
				max_area = area_with_top;
		}
		
		if(max_area > n)
			return max_area;
		else
			return n;
	}
}
