package 从暴力递归到动态规划;

public class Code01_PalindromeSubsequence {
	/**
	 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
		比如 ： str = “a12b3c43def2ghi1kpm”
		最长回文子序列是“1234321”或者“123c321”，返回长度7
	 * @param args
	 */
	
	public static int longestPalindromeSubseq(String s) {
		if(s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		return process(str, 0, str.length - 1);
	}
	
	// 递归函数含义：返回 str [L, R] 上 的最长回文子序列的长度
	public static int process(char[] str, int L, int R) {
		if(L == R) {
			return 1;
		}else if(L == R - 1) {
			return str[L] == str[R] ? 2 : 1;
		}else{
			int p1 = process(str, L, R - 1);
			int p2 = process(str, L + 1, R - 1);
			int p3 = process(str, L + 1, R);
			int p4 = str[L] == str[R] ? (2 + p2) : 0;
			return Math.max(Math.max(p1, p2), Math.max(p3, p4));
		}
	}
	
	public static int longestPalindromeSubseq1(String s) {
		if(s == null || s.length() == 0) {
			return 0;
		}
		
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];
		dp[N-1][N-1] = 1;
		for(int l = 0; l < N - 1; l++) {
			dp[l][l] = 1;
			dp[l][l+1] = str[l] == str[l+1] ? 2 : 1;
		}
		for(int l = N - 3; l >= 0; l--) {
			for(int r = l + 2; r < N ; r++) {
				int p1 =dp[l][r - 1];
				int p2 =dp[ l + 1][ r - 1];
				int p3 = dp[ l + 1][r];
				int p4 = str[l] == str[r] ? (2 + p2) : 0;
				dp[l][r] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
			}
		}
		return dp[0][N-1];
	}
	
	
	public static int dp(String s) {
		if(s == null || s.length() == 0) {
			return 0;
		}
		
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];
		dp[N-1][N-1] = 1;
		for(int l = 0; l < N - 1; l++) {
			dp[l][l] = 1;
			dp[l][l+1] = str[l] == str[l+1] ? 2 : 1;
		}
		for(int l = N - 3; l >= 0; l--) {
			for(int r = l + 2; r < N ; r++) {
				dp[l][r] = Math.max(dp[l][r - 1], dp[ l + 1][r]);			
				if( str[l] == str[r]) {
					dp[l][r] = Math.max(dp[l][r], 2 + dp[l+1][r-1]);
				}
			}
		}
		return dp[0][N-1];
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
