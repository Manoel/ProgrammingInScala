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

		System.out.println(area(lcp));
		
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

		if (max_area > n)
			return max_area;
		else
			return n;
	}

	public static int area(int[] height) {

		int n = height.length;
		if (n == 0)
			return 0;

		Stack<Integer> left = new Stack<Integer>();
		Stack<Integer> right = new Stack<Integer>();

		int[] width = new int[n];// widths of intervals.
		Arrays.fill(width, 1);// all intervals should at least be 1 unit wide.

		for (int i = 0; i < n; i++) {
			// count # of consecutive higher bars on the left of the (i+1)th bar
			while (!left.isEmpty() && height[i] <= height[left.peek()]) {
				// while there are bars stored in the stack, we check the bar on
				// the top of the stack.
				left.pop();
			}

			if (left.isEmpty()) {
				// all elements on the left are larger than height[i].
				width[i] += i;
			} else {
				// bar[left.peek()] is the closest shorter bar.
				width[i] += i - left.peek() - 1;
			}
			left.push(i);
		}

		for (int i = n - 1; i >= 0; i--) {

			while (!right.isEmpty() && height[i] <= height[right.peek()]) {
				right.pop();
			}

			if (right.isEmpty()) {
				// all elements to the right are larger than height[i]
				width[i] += n - 1 - i;
			} else {
				width[i] += right.peek() - i - 1;
			}
			right.push(i);
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			// find the maximum value of all rectangle areas.
			max = Math.max(max, width[i] * height[i]);
		}
		
		return max > n ? max : n;
	}

}
