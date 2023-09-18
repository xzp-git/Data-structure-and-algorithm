package 从暴力递归到动态规划;

public class ConvertToLetterString {
	
	/**
	 * 
	 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
		那么一个数字字符串比如"111”就可以转化为:
		"AAA"、"KA"和"AK"
		给定一个只有数字字符组成的字符串str，返回有多少种转化结果 
		
		 str只含有数字字符0~9
		 返回多少种转化方案
		 
		 因为 字母只有 26个 对应 1 - 26个数字
		 所以从题意 可以分析出字符 0 不会有对应的字母 所以在转化的时候 
		 要么是单一数字字符转化要么是双字符转化 
	 */
	public static int number(String str) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		
		return process(str.toCharArray(), 0);
	}
	
	// str[0,...,i - 1] 位置已经转化完毕无需关心
	// str[i,...] 到完 转化为字母字符串有多少种方法
	public static int process(char[] str, int i) {
		
		// 思考一个问题什么时候算是一种方法呢
		// 就是 一直转化都没问题 转化到 i = str.length 证明把这个字符串转化完了，此时代表一种方法
		if(i == str.length) {
			return 1;
		}
		// 如果让0独自面对转化 则代表这条路不通 此方法不算
		if(str[i] == '0') {
			return 0;
		}
		
		// 走到这里 代表 str[i] != '0' 那此时有两种方法转化 独自转化 和 和下一个字符 组合一起转化
		// i位置可以 单独转化， 单独转化时 i位置的方法数取决于 他后续的字符能否转化完
		int ways = process(str, i + 1);
		// i 位置 如果可以和后面 i+1 位置组合 则需要加上组合的方法数
		if(i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			ways += process(str, i + 2);
		}
		
		return ways;
	}
	
	// 分析依赖关系 转化的种类 只和 i 有关系
	public static int dp1(String str) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		
		int N = str.length();
		int[] dp = new int[N + 1];
		char[] chars = str.toCharArray();
		dp[N] = 1;
		for(int i = N-1; i >= 0; i--) {
			if(chars[i] != '0') {
				dp[i] = dp[i + 1];
				if(i + 1 < N && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
					dp[i] += dp[i + 2];
				}
			}
		}
		
		return dp[0];
	}
	
	

	// 为了测试
		public static String randomString(int len) {
			char[] str = new char[len];
			for (int i = 0; i < len; i++) {
				str[i] = (char) ((int) (Math.random() * 10) + '0');
			}
			return String.valueOf(str);
		}

		// 为了测试
		public static void main(String[] args) {
			int N = 30;
			int testTime = 1000000;
			System.out.println("测试开始");
			for (int i = 0; i < testTime; i++) {
				int len = (int) (Math.random() * N);
				String s = randomString(len);
				int ans0 = number(s);
				int ans1 = dp1(s);
//				int ans2 = dp2(s);
				if (ans0 != ans1 ) {
					System.out.println(s);
					System.out.println(ans0);
					System.out.println(ans1);
//					System.out.println(ans2);
					System.out.println("Oops!");
					break;
				}
			}
			System.out.println("测试结束");
		}

}
