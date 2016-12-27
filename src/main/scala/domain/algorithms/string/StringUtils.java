package domain.algorithms.string;

import hackerrank.string.Rank;
import hackerrank.string.RankComparator;

import java.util.Arrays;

public class StringUtils {

	public static final RankComparator BY_RANK = new RankComparator();

	private static final int MAXSIZE = 200100;
	private static final int ALPHABET = 128;
	private static final int[] p = new int[MAXSIZE];
	private static final int[] c = new int[MAXSIZE];
	private static final int[] cnt = new int[MAXSIZE];
	private static final int[] pn = new int[MAXSIZE];
	private static final int[] cn = new int[MAXSIZE];

	public static int[] getSuffixArray(String s) {
		for (int i = 0; i < cnt.length; ++i) {
			cnt[i] = 0;
		}

		int n = s.length();
		for (int i = 0; i < n; ++i) {
			++cnt[s.charAt(i)];
		}
		for (int i = 1; i < ALPHABET; ++i) {
			cnt[i] += cnt[i - 1];
		}
		for (int i = 0; i < n; ++i) {
			p[--cnt[s.charAt(i)]] = i;
		}
		int count = 1;
		c[p[0]] = count - 1;
		for (int i = 1; i < n; ++i) {
			if (s.charAt(p[i]) != s.charAt(p[i - 1]))
				++count;
			c[p[i]] = count - 1;
		}
		for (int h = 0; (1 << h) < n; ++h) {
			for (int i = 0; i < n; ++i) {
				pn[i] = p[i] - (1 << h);
				if (pn[i] < 0)
					pn[i] += n;
			}

			for (int i = 0; i < cnt.length; ++i) {
				cnt[i] = 0;
			}

			for (int i = 0; i < n; ++i) {
				++cnt[c[i]];
			}
			for (int i = 1; i < count; ++i) {
				cnt[i] += cnt[i - 1];
			}
			for (int i = n - 1; i >= 0; --i) {
				p[--cnt[c[pn[i]]]] = pn[i];
			}
			count = 1;
			cn[p[0]] = count - 1;
			for (int i = 1; i < n; ++i) {
				int pos1 = (p[i] + (1 << h)) % n;
				int pos2 = (p[i - 1] + (1 << h)) % n;
				if (c[p[i]] != c[p[i - 1]] || c[pos1] != c[pos2])
					++count;
				cn[p[i]] = count - 1;
			}
			for (int i = 0; i < n; ++i) {
				c[i] = cn[i];
			}
		}
		int[] res = new int[n];

		for (int i = 0; i < n; ++i) {
			res[i] = c[i];
		}
		return res;
	}

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
