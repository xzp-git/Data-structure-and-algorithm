package 从暴力递归到动态规划;

public class Code02_HorseJump {
	
	// 当前来到的位置是（x,y）
	// 还剩下rest步需要跳
	// 跳完rest步，正好跳到a，b的方法数是多少？
	// 10 * 9
	
	public static int jump(int a, int b, int k) {
		return process(0, 0, k, a, b);
	}
	
	public static int process(int y, int x, int rest, int a, int b) {
		if(y < 0 || y > 9 || x < 0 || x > 8) {
			return 0;
		}
		if(rest == 0 ) {
			return (y == a && x == b) ? 1 : 0;
		}
		
		int ways = process(y + 2, x + 1, rest - 1, a, b);
		ways += process(y + 2, x - 1, rest - 1, a, b);
		ways += process(y + 1, x - 2, rest - 1, a, b);
		ways += process(y + 1, x + 2, rest - 1, a, b);
		ways += process(y - 1, x - 2, rest - 1, a, b);
		ways += process(y - 1, x + 2, rest - 1, a, b);
		ways += process(y - 2, x + 1, rest - 1, a, b);
		ways += process(y - 2, x - 1, rest - 1, a, b);
		return ways;
	}
	
	public static int dp(int a, int b, int k) {
		int[][][] dp = new int[10][9][ k + 1];
		dp[a][b][0] = 1;
		
		for (int y = 0; y < 10; y++) {
			for(int x = 0; x < 9; x++) {
				for(int r = 0; r < k+1; r++) {
					int ways = pick(dp, y + 2, x + 1,r);
					ways += pick(dp, y + 2, x - 1, r);
					ways += pick(dp, y + 1, x - 2, r);
					ways += pick(dp, y + 1, x + 2, r);
					ways += pick(dp, y - 1, x - 2, r); 
					ways += pick(dp, y - 1, x + 2, r);
					ways += pick(dp, y - 2, x + 1, r);
					ways += pick(dp, y - 2, x - 1, r);
				}
			}
		}
		
		return dp[0][0][k];
		
	}

	public static int pick(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		return dp[x][y][rest];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(jump(x, y, step));
		System.out.println(jump(x, y, step));

	}

}
