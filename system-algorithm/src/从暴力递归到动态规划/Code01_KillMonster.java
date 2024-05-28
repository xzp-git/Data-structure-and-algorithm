package 从暴力递归到动态规划;

public class Code01_KillMonster {
	
	/**
	 * 给定3个参数，N，M，K
		怪兽有N滴血，等着英雄来砍自己
		英雄每一次打击，都会让怪兽流失[0~M]的血量
		到底流失多少？每一次在[0~M]上等概率的获得一个值
		求K次打击之后，英雄把怪兽砍死的概率
	 */
	
	public static double killMonsterProbability(int N, int M, int K) {
		if (N < 1) {
			return 1;
		}
		if (M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = process(K, M, N);
		return (double) ((double) kill / (double) all);
	}
	
	public static long process(int rest, int M, int hp) {
		// 如果砍完了，看怪兽还有还有血量吗，如果没有血算1次
		if(rest == 0) {
			return hp <= 0 ? 1 : 0;
		}
		// 如果没砍完，怪兽却没血了，看还能砍多少次，那总得可能性次数是 Math.pow(M + 1, rest)
		if(hp <= 0) {
			return (long) Math.pow(M + 1, rest);
		}
		
		long ans = 0;
		for(int i = 0; i <= M; i++) {
			ans += process(rest - 1, M, hp - i);
		}
		return ans;
	}
	
	public static long pick(int hp, int rest, int M, long[][] dp) {
		if(hp < 0) {
			return (long) Math.pow(M +1, rest);
		}
		return dp[hp][rest];
	}
	
	public static double dp(int N, int M, int K) {
		if (N < 1) {
			return 1;
		}
		if (M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[N + 1][K + 1];

		dp[0][0] = 1;
		for(int i = 1; i <= K; i++) {
			dp[0][i] = (long) Math.pow(M + 1, i);
		}
		
		for(int hp = 1; hp <= N; hp++) {
			for(int rest = 1; rest <= K; rest++) {
				for(int i = 0; i <= M; i++) {
					dp[hp][rest] += pick(hp - i, rest - 1, M, dp);
				}
			}
		}
		return (double) ((double) dp[N][K] / (double) all);
	}
	
	public static double dp1(int N, int M, int K) {
		if (N < 1) {
			return 1;
		}
		if (M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[N + 1][K + 1];

		dp[0][0] = 1;
		for(int i = 1; i <= K; i++) {
			dp[0][i] = (long) Math.pow(M + 1, i);
		}
		
		for(int hp = 1; hp <= N; hp++) {
			for(int rest = 1; rest <= K; rest++) {
					dp[hp][rest] = pick(hp, rest - 1, M, dp) + pick(hp - 1, rest, M, dp) - pick(hp - 1 - M, rest - 1, M, dp);
			}
		}
		return (double) ((double) dp[N][K] / (double) all);
	}
	
	
	public static double dp3(int N, int M, int K) {
		if (N < 1) {
			return 1;
		}
		if (M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
				if (hp - 1 - M >= 0) {
					dp[times][hp] -= dp[times - 1][hp - 1 - M];
				} else {
					dp[times][hp] -= Math.pow(M + 1, times - 1);
				}
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
	}
	
	public static void main(String[] args) {
		int NMax = 10;
		int MMax = 10;
		int KMax = 10;
		int testTime = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * NMax);
			int M = (int) (Math.random() * MMax);
			int K = (int) (Math.random() * KMax);
			double ans1 = killMonsterProbability(N, M, K);
			double ans2 = dp(N, M, K);
			double ans3 = dp1(N, M, K);
			double ans4 = dp3(N, M, K);
			if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
				System.out.println("Oops!");
				break;
			}
//			System.out.print(ans1 + "-"); System.out.println(ans2);
//			if (ans1 != ans2 ) {
//				System.out.println("Oops!");
//				break;
//			}
		}
		System.out.println("测试结束");
	}

}
