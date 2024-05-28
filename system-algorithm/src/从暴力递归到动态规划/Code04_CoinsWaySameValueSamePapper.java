package 从暴力递归到动态规划;
import java.util.HashMap;
import java.util.Map.Entry;

public class Code04_CoinsWaySameValueSamePapper {
	
	
	/**
	 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
		每个值都认为是一张货币，
		认为值相同的货币没有任何不同，
		返回组成aim的方法数
		例如：arr = {1,2,1,1,2,1,2}，aim = 4
		方法：1+1+1+1、1+1+2、2+2
		一共就3种方法，所以返回3
	 */
	
	public static class Info{
		public int[] coins;
		public int[] sheets;
		
		public Info(int[] coins, int[] sheets) {
			this.coins = coins;
			this.sheets = sheets;
		}
	}
	
	
	public static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		
		for(int coin : arr) {
			if(!counts.containsKey(coin)) {
				counts.put(coin, 1);
			}else {
				counts.put(coin, counts.get(coin) + 1);
			}
		}
		int N = counts.size();
		int index = 0;
		int[] coins = new int[N];
		int[] sheets = new int[N];
		
		for(Entry<Integer, Integer> entry : counts.entrySet()) {
			coins[index] = entry.getKey();
			sheets[index++] = entry.getValue();
		}
		
		return new Info(coins, sheets);
	}
	
	public static int coinsWay(int[] arr, int aim) {
		if(arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		
		return process(info, 0, aim);
	}
	
	public static int process(Info info, int index, int rest) {
		int[] coins = info.coins;
		int[] sheets = info.sheets;
		if(index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for(int sheet = 0; sheet <= sheets[index] && rest <= (sheet * coins[index]); sheet++) {
			ways += process(info, index + 1, rest - (sheet * coins[index]));
		}
		return ways;
	}
	
	
	public static int dp(int[] arr, int aim) {
		if(arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] sheets = info.sheets;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		
		dp[N][0] = 1;
		
		for(int index = N -1 ; index <= 0; index++) {
			for(int rest = 0; rest <= aim; rest++) {
				for(int sheet = 0; sheet <= sheets[index] && rest >= (sheet * coins[index]); sheet++) {
					dp[index][rest] += dp[ index + 1][ rest - (sheet * coins[index])];
				}
			}
		}
		return dp[0][aim];
	}
	
	
	public static int dp1(int[] arr, int aim) {
		if(arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] sheets = info.sheets;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		
		dp[N][0] = 1;
		
		for(int index = N -1 ; index <= 0; index++) {
			for(int rest = 0; rest <= aim; rest++) {
				dp[index][rest] += dp[index + 1][rest];
				if(rest >= coins[index]) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
				if(rest >= coins[index] * (sheets[index] + 1)) {
					dp[index][rest] -= dp[index][rest - coins[index] * (sheets[index] + 1)];
				}
			}
		}
		return dp[0][aim];
	}
	
	
	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * maxValue) + 1;
		}
		return arr;
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
		int maxLen = 10;
		int maxValue = 20;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);
			int ans2 = dp(arr, aim);
			int ans3 = dp1(arr, aim);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}
	
	
	
	

}
