package domain.algorithms.string;

import java.util.Arrays;

public class StringUtils {

	public static final RankComparator BY_RANK = new RankComparator();

	public static Rank[] suffixArray(String s) {

		int N = s.length();

		int[][] suffixRank = new int[1000][N];

		// Initialize suffix ranking on the basis of only single character
		// for single character ranks will be 'a' = 0, 'b' = 1, 'c' = 2 ... 'z'
		// = 25
		for (int i = 0; i < N; i++) {
			suffixRank[0][i] = s.charAt(i) - 'a';
		}

		// Create a rank array for each suffix
		Rank[] L = new Rank[N];

		// Iterate log(n) times i.e. till when all the suffixes are sorted
		// 'stp' keeps the track of number of iteration
		// 'cnt' store length of suffix which is going to be compared

		// On each iteration we initialize tuple for each suffix array
		// with values computed from previous iteration

		for (int cnt = 1, stp = 1; cnt < N; cnt *= 2, ++stp) {

			for (int i = 0; i < N; ++i) {
				L[i] = new Rank();
				L[i].firstHalf = suffixRank[stp - 1][i];
				L[i].secondHalf = i + cnt < N ? suffixRank[stp - 1][i + cnt]
						: -1;
				L[i].originalIndex = i;
			}

			// On the basis of tuples obtained sort the tuple array
			Arrays.sort(L, BY_RANK);

			for (int i = 1, currRank = 0; i < N; ++i) {

				// compare ith ranked suffix ( after sorting ) to (i - 1)th
				// ranked suffix
				// if they are equal till now assign same rank to ith as that of
				// (i - 1)th
				// else rank for ith will be currRank ( i.e. rank of (i - 1)th )
				// plus 1, i.e ( currRank + 1 )

				if (L[i - 1].firstHalf != L[i].firstHalf
						|| L[i - 1].secondHalf != L[i].secondHalf)
					++currRank;

				suffixRank[stp][L[i].originalIndex] = currRank;
			}
		}

		return L;
	}

	public static int[] kasai(String s, Rank[] suffix_array) {
		int n = s.length();

		int[] lcp = new int[n];

		int[] rank = new int[n];

		for (int i = 0; i < n; i++) {
			rank[suffix_array[i].originalIndex] = i;
		}

		for (int i = 0, k = 0; i < n; i++) {
			if (rank[i] == n - 1) {
				k = 0;
				continue;
			}

			int j = suffix_array[rank[i] + 1].originalIndex;

			while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k))
				k++;

			lcp[rank[i]] = k;

			if (k > 0)
				k--;
			else
				k = 0;
		}

		return lcp;
	}

}
