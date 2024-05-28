package 从暴力递归到动态规划;


/**
 * 给定5个参数，N，M，row，col，k
	表示在N*M的区域上，醉汉Bob初始在(row,col)位置
	Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
	任何时候Bob只要离开N*M的区域，就直接死亡
	返回k步之后，Bob还在N*M的区域的概率
 * @author bytedance
 *
 */
public class Code05_BobDie {
   public static double livePosibility1(int N, int M, int row, int col, int k) {
	   
	   return (double) process(N, M, row, col, k) / Math.pow(4, k);
   }
   
   public static boolean isLive(int N, int M, int row, int col) {
	   return row < N && col < M && row >= 0 && col >= 0;
//	   return !(row < 0 || row == N || col < 0 || col == M);
   }
   
   public static long process(int N, int M, int row, int col, int rest) {
	   if(!isLive(N, M, row, col)) {
		   return 0;
	   }
	   
	   if(rest == 0) {
		   return 1;
	   }
	   
	   return process(N, M, row - 1, col, rest - 1) + process(N, M, row + 1, col, rest - 1) 
	   +  process(N, M, row, col - 1, rest - 1) + process(N, M, row, col + 1, rest - 1);
	   
   }
   
   public static long pick (long[][][] dp, int N, int M, int row, int col, int k) {
	   if(!isLive(N, M, row, col)) {
		   return 0;
	   }
	   return dp[row][col][k];
   }
   
   public static double dp(int N, int M, int row, int col, int k) {
	   long[][][] dp = new long[N][M][k + 1];
	   
	   for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j][0] = 1;
			}
		}
	   for(int rest = 1; rest <= k; rest++) {
		   for(int i = 0; i < N; i++) {
			   for(int j = 0; j < M; j++) {
				   dp[i][j][rest] = pick(dp, N, M, i - 1, j, rest - 1);
				   dp[i][j][rest] += pick(dp, N, M, i + 1, j, rest - 1);
				   dp[i][j][rest] += pick(dp, N, M, i, j + 1, rest - 1);
				   dp[i][j][rest] += pick(dp, N, M, i, j - 1, rest - 1);
			   }
		   }
	   }
	   return (double)dp[row][col][k] / Math.pow(4, k);
   }
   
   public static void main(String[] args) {
//		System.out.println(livePosibility1(6, 6, 10, 50, 50));
//		System.out.println(dp(6, 6, 10, 50, 50));
//		System.out.println(livePosibility1(15, 15, 10, 6, 6));
//		System.out.println(dp(15, 15, 10, 6, 6));
		System.out.println(livePosibility1(50, 50,  6, 6, 10));
		System.out.println(dp(50, 50,  6, 6, 10));
	}
}
