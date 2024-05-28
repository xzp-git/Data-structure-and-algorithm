package 从暴力递归到动态规划;

public class Code02_MinCoinsNoLimit {
/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
每个值都认为是一种面值，且认为张数是无限的。
返回组成aim的最少货币数


需要的组成目标值的货币的最小张数.png
 */
	public static int minCoins(int[] arr, int aim) {
		return process(arr, 0, aim);
	}
	
	public static int process(int[] arr, int index, int rest) {
		if(index == arr.length) {
			return rest == 0 ? 0 : Integer.MAX_VALUE;
		}
		int ans = Integer.MAX_VALUE;
		for(int sheet = 0; arr[index] * sheet <= rest; sheet++ ) {
			int next =  process(arr, index + 1, rest - arr[index] * sheet);
			if(next != Integer.MAX_VALUE) {
				ans = Math.min(ans, sheet + next);
			}
		}
		return ans;
	}
	
	public static int dp(int[] arr, int aim) {
		
		int N = arr.length;
		int[][] dp = new int[ N + 1][ aim + 1 ];
		
		
		for(int j = 1; j <= aim; j++) {
			dp[N][j] = Integer.MAX_VALUE;
		}
		dp[N][0] = 0;
		for(int index = N - 1; index >= 0; index--) {
			for(int rest = 0; rest <= aim; rest++) {
				int ans = Integer.MAX_VALUE;
				for(int sheet = 0; arr[index] * sheet <= rest; sheet++ ) {
					int next =  dp[index + 1][rest - arr[index] * sheet];
					if(next != Integer.MAX_VALUE) {
						ans = Math.min(ans, sheet + next);
					}
				}
				dp[index][rest] = ans;
			}
		}
		return dp[0][aim];
	}
	
	public static int dp4(int[] arr, int aim) {
			
			int N = arr.length;
			int[][] dp = new int[ N + 1][ aim + 1 ];
			
			
			for(int j = 1; j <= aim; j++) {
				dp[N][j] = Integer.MAX_VALUE;
			}
			dp[N][0] = 0;
			for(int index = N - 1; index >= 0; index--) {
				for(int rest = 0; rest <= aim; rest++) {
					dp[index][rest] = dp[index + 1][rest];
					
					if(rest >= arr[index] && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
						dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
					}
					
				}
			}
			return dp[0][aim];
		}
	// 为了测试
		public static int[] randomArray(int maxLen, int maxValue) {
			int N = (int) (Math.random() * maxLen);
			int[] arr = new int[N];
			boolean[] has = new boolean[maxValue + 1];
			for (int i = 0; i < N; i++) {
				do {
					arr[i] = (int) (Math.random() * maxValue) + 1;
				} while (has[arr[i]]);
				has[arr[i]] = true;
			}
			return arr;
		}
		
		
		public static int dp3(int[] arr, int aim) {
			if (aim == 0) {
				return 0;
			}
			int N = arr.length;
			int[][] dp = new int[N + 1][aim + 1];
			dp[N][0] = 0;
			for (int j = 1; j <= aim; j++) {
				dp[N][j] = Integer.MAX_VALUE;
			}
			for (int index = N - 1; index >= 0; index--) {
				for (int rest = 0; rest <= aim; rest++) {
					dp[index][rest] = dp[index + 1][rest];
					if (rest - arr[index] >= 0 
							&& dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
						dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
					}
				}
			}
			return dp[0][aim];
		}


		// 为了测试
		public static void printArray(int[] arr) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}

		// 为了测试
		public static void main(String[] args) {
			int maxLen = 20;
			int maxValue = 30;
			int testTime = 300000;
			System.out.println("功能测试开始");
			for (int i = 0; i < testTime; i++) {
				int N = (int) (Math.random() * maxLen);
				int[] arr = randomArray(N, maxValue);
				int aim = (int) (Math.random() * maxValue);
				int ans1 = minCoins(arr, aim);
				int ans2 = dp(arr, aim);
				int ans3 = dp3(arr, aim);
				int ans4 = dp4(arr, aim);
				if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
					System.out.println("Oops!");
					printArray(arr);
					System.out.println(aim);
					System.out.println(ans1);
					System.out.println(ans2);
					break;
				}
			}
			System.out.println("功能测试结束");
		}

}
