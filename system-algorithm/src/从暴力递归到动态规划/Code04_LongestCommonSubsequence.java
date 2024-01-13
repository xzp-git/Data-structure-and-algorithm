package 从暴力递归到动态规划;

public class Code04_LongestCommonSubsequence {
	//	 样本对应模型，
	
	/**
	 * 给定两个字符串str1和str2，
		返回这两个字符串的最长公共子序列长度
		比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
		最长公共子序列是“123456”，所以返回长度6
	 * @param args
	 */
	
	
	public static int longestCommonSubsequence(String s1, String s2) {
		
		if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		
		return process1(str1, str2, str1.length - 1, str2.length - 1);
		
	}
	
	// 递归函数 含义 返回 str1 [0, ..., i] 和 str2 [0, ..., j] 上的最长公共子序列
	public static int process1(char[] str1, char[] str2, int i, int j) {
		if(i == 0 && j == 0) {
			return str1[i] == str2[j] ? 1 : 0;
		}else if(i == 0) {
			/**
			 * 这里的情况为：str1 只剩下一个字符了，但是 str2 剩下的字符还有很多
			 * 因为str1只剩下了一个字符那么最长的公共子序列 str1[0...0] str2[0...j]的长度只能是1
			 * 如果 str1[0] == str2[j] 那么找到了公共的 返回1
			 * 如果 str1[0] != str2[j] 代表只和 str2 j位置的字符不相等，
			 * 那么j-1前面的字符呢？不知道，需要递归继续找
			 * 
			 */
			if(str1[i] == str2[j]) {
				return 1;
			}else {
				return process1(str1, str2, i, j - 1);
			}
		}else if(j == 0 ) {
			// j==0 代表 str2 只剩下了一个字符 str1 还有很多字符，与上面的情况相似
			if(str1[i] == str2[j]) {
				return 1;
			}else {
				return process1(str1, str2, i-1, j);
			}
		}else {
			// 这里的情况表示 i != 0 && j != 0 有以下几种情况
			// 情况1 i j 位置的最长子序列 为 i - 1， j 位置的最长子序列
			int p1 = process1(str1, str2, i - 1, j);
			// 情况2 i j 位置的最长子序列 为 i， j - 1 位置的最长子序列
			int p2 = process1(str1, str2, i, j - 1);
			// 情况3 
			int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i-1, j - 1)) : 0 ;
			return Math.max(p1, Math.max(p2, p3));
		}
	}
	
	
	public static int longestCommonSubsequence2(String s1, String s2) {
		
		if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		
		int N = str1.length;
		int M = str2.length;
		
		int[][] dp = new int[N][M];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		
		for(int i = 1; i < N; i++ ) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		
		for(int j = 1; j < M; j++ ) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j-1];
		}
		
		for(int i = 1; i < N; i++ ) {
			for(int j = 1; j < M; j++ ) {
				int p1 = dp[i - 1][j];
				// 情况2 i j 位置的最长子序列 为 i， j - 1 位置的最长子序列
				int p2 = dp[i][j - 1];
				// 情况3 
				int p3 = str1[i] == str2[j] ? (1 + dp[i-1][j-1]) : 0 ;
				dp[i][j] = Math.max(p1, Math.max(p2, p3));
			}
		}
		return dp[N - 1][M-1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
