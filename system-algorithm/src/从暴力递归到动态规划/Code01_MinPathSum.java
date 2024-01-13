package 从暴力递归到动态规划;

public class Code01_MinPathSum {
	
	/**
	 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
		沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
		返回最小距离累加和
	 * @param args
	 */
	public static int minPathSum1(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		
		return process1(matrix, 0, 0, 0);
		
	}
	/**递归含义 0，0 到达 row, col 位置时的最小距离累加和是多少 **/
	public static int process1(int[][] matrix, int row, int col, int sum) {
		int endRow = matrix.length - 1;
		int endCol = matrix[0].length - 1;
		int ans = matrix[row][col] + sum;
		if(row == endRow && col == endCol) {
			return ans;
		}
		
		if(row + 1 > endRow) {
			return process1(matrix, row, col + 1, ans);
		}
		if(col + 1 > endCol) {
			return process1(matrix, row + 1, col, ans);
		}
		
		int right = process1(matrix, row, col + 1, ans);
		int down =  process1(matrix, row + 1, col, ans);
		
		return Math.min(right, down);
	}
	
	public static int minPathSum2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		
		return process2(matrix, matrix.length - 1, matrix[0].length - 1);
		
	}
	
	public static int process2(int[][] matrix, int row, int col) {
		if(row == 0 && col == 0) {
			return matrix[0][0];
		}
		
		if(row < 1) {
			return process2(matrix, row, col - 1) + matrix[row][col];
		}
		if(col < 1) {
			return process2(matrix, row - 1, col) + matrix[row][col];
		}
		
		int up = process2(matrix, row - 1, col);
		int left =  process2(matrix, row, col - 1);
		
		return Math.min(up, left) + matrix[row][col];
	}
	
	
	public static int minPathSumDp1(int [][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int endR = matrix.length;
		int endC = matrix[0].length;
		int[][] dp = new int[endR][endC];
		
		dp[0][0] = matrix[0][0];
		
		for(int i = 1; i < endR; i++) {
			dp[i][0] = dp[i - 1][0] + matrix[i][0];
		}
		for(int j = 1; j < endC; j++) {
			dp[0][j] = dp[0][j - 1] +  matrix[0][j];
		}
		for(int i = 1; i < endR; i++) {
			for(int j = 1; j < endC; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
			}
		}
		
		return dp[endR - 1][endC - 1];
		
		
	}
	
	
	public static int minPathSum3(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0];
			for (int j = 1; j < col; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
			}
		}
		return dp[col - 1];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 100);
			}
		}
		return result;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rowSize = 10;
		int colSize = 10;
		int[][] m = generateRandomMatrix(rowSize, colSize);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));
		System.out.println(minPathSum3(m));
		System.out.println(minPathSumDp1(m));

	}

}
