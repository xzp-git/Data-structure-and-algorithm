package 从暴力递归到动态规划;

public class Code01_RobotWalk {
	/**
	 * 题目：
		假设有排成一行的N个位置记为1~N，N一定大于或等于2
		开始时机器人在其中的M位置上(M一定是1~N中的一个)
		如果机器人来到1位置，那么下一步只能往右来到2位置；
		如果机器人来到N位置，那么下一步只能往左来到N-1位置；
		如果机器人来到中间位置，那么下一步可以往左走或者往右走；
		规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
		给定四个参数 N、M、K、P，返回方法数
	 */
	
	
	/**
	 * 
	 * @param N
	 * @param start
	 * @param aim
	 * @param K
	 * @return
	 * 暴力递归方法
	 */
	public static int ways1(int cur, int rest, int aim, int N) {
		if(N < 3 || cur < 1 || cur > N || aim > N || aim < 1 || rest < 1) {
			return -1;
		}
		
		return process1(cur, rest , aim, N);
	}
	
	/**
	 * 
	 * @param cur 目前在哪个位置
	 * @param rest 还剩多少步
	 * @param aim 目标是啥
	 * @param N 一共有多少个位置
	 * @return
	 */
	
	public static int process1(int cur, int rest, int aim, int N) {
		if(rest == 0) {
			return cur == aim ? 1 : 0;
		}
		
		if(cur == 1) {
			return process1(2, rest - 1, aim, N);
		}
		
		if(cur == N) {
			return process1(N - 1, rest - 1, aim, N);
		}
		
		return process1(cur - 1, rest - 1, aim , N) + process1(cur + 1, rest - 1, aim, N);
	}
	
	/**
	 * 从方法 process1中可以看出 函数的结果是随着 cur 和 rest 的变化而变化的，
	 * 因此我们可以构建一张缓存表，已经计算过的就不需要进行计算了
	 * @param args
	 */
	
	public static int ways2(int cur, int rest, int aim, int N) {
		if(N  < 2 || cur < 1 || cur > N || aim < 1 || aim > N || rest < 1) {
			return -1;
		}
		int[][] dp = new int[N + 1][rest + 1];
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= rest; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(cur, rest, aim, N, dp);
	}
	
	
	public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
		if(dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		int ans = -1;
		if(rest == 0) {
			ans = cur == aim ? 1 : 0;
		}else if(cur == 1) {
			ans = process2(2, rest - 1, aim, N, dp);
		}else if(cur == N) {
			ans = process2(N - 1, rest - 1, aim, N, dp);
		}else {
			ans = process2(cur + 1, rest - 1, aim, N, dp) + process2(cur - 1, rest - 1, aim, N, dp);;
		}
		dp[cur][rest] = ans;
		return ans;
	}
	
	public static int ways3(int start, int K, int aim, int N) {
		if(N  < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		int[][] dp = new int[N + 1][K + 1];
		
		dp[aim][0] = 1;
		// 填表的时候先填列的话就得先循环列
		
		for(int rest = 1; rest <= K; rest++) {
			//先填第一列
			dp[1][rest] = dp[2][rest - 1];
			//填中间列
			for(int cur = 1; cur < N; cur++) {
				dp[cur][rest] = dp[cur-1][rest-1] + dp[cur+1][rest-1];
			}
			//填最后一列
			dp[N][rest] = dp[N-1][rest-1];
		}
		
		return dp[start][K];
	}

	
	
	
	
	
	public static void main(String[] args) {
		// int cur, int rest, int aim, int N
		System.out.println(ways1(6, 6, 6, 8));
		System.out.println(ways2(6, 6, 6, 8));
		System.out.println(ways3(6, 6, 6, 8));
	}

}
