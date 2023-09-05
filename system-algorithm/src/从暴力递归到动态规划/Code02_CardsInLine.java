package 从暴力递归到动态规划;

public class Code02_CardsInLine {
	
	/**
	 * 
	 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
		玩家A和玩家B依次拿走每张纸牌
		规定玩家A先拿，玩家B后拿
		但是每个玩家每次只能拿走最左或最右的纸牌
		玩家A和玩家B都绝顶聪明
		请返回最后获胜者的分数
	 * 
	 */
	// 在 arr上拿到的最大的分数为 在 arr上 先手拿的分数 和 arr上后手拿的分数 最大值
	public static int win2(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}	
		int first = f2(arr, 0, arr.length - 1);
		int second = g2(arr, 0, arr.length - 1);
		return Math.max(first, second);
	}
	
	// 先手函数
	
	public static int f2(int[] arr, int l, int r) {
		if(l == r) {
			return arr[l];
		}
		int p1 = arr[l] + g2(arr, l + 1, r);
		int p2 = arr[r] + g2(arr, l, r - 1);
		return Math.max(p1, p2);
	}
	
	//后手函数
	
	public static int g2(int[] arr, int l, int r) {
		if(l == r) {
			return 0;
		}
		int p1 = f2(arr, l + 1, r);
		int p2 = f2(arr, l, r - 1);
		return Math.min(p1, p2);
	}
	
	public static int win1(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[][] fMap = new int[n][n];
		int[][] gMap = new int[n][n];
		int first = f1(arr, 0, n - 1, gMap);
		int second = g1(arr, 0, n - 1,fMap);
		return Math.max(first, second);
	}
	
	// 先手函数
	
	public static int f1(int[] arr, int l, int r, int[][] gMap) {
		if(gMap[l][r] != 0) {
			return gMap[l][r];
		}
		int ans;
		if(l == r) {
			ans = arr[l];
		}else {
			int p1 = arr[l] + g1(arr, l + 1, r, gMap);
			int p2 = arr[r] + g1(arr, l, r - 1, gMap);
			ans = Math.max(p1, p2);
		}
		gMap[l][r] = ans;
		return ans;
	}
	
	//后手函数
	
	public static int g1(int[] arr, int l, int r, int[][] fMap) {
		if(fMap[l][r] != 0) {
			return fMap[l][r];
		}
		int ans;
		if(l == r) {
			ans = 0;
		}else {
			int p1 = f1(arr, l + 1, r, fMap);
			int p2 = f1(arr, l, r - 1, fMap);
			ans = Math.min(p1, p2);
		}
		fMap[l][r] = ans;
		return ans;
	}
	
	
//	public static int win3(int[] arr) {
//		if(arr == null || arr.length == 0) {
//			return 0;
//		}
//		int n = arr.length;
//		int[][] fMap = new int[n][n];
//		int[][] gMap = new int[n][n];
//		for(int i = 0; i < n; i++) {
//			fMap[i][i] = arr[i];
//		}
//		for(int i = 1; i < n; i++) {
//			for(int l = 0, r = i; r < n; l++,r++) {
//				fMap[l][r] = Math.max(arr[l] + gMap[l + 1][r], arr[r] + gMap[l][r - 1]);
//				gMap[l][r] = Math.min(fMap[l + 1][r], fMap[l][r - 1]);
//			}
//		}
//		return Math.max(fMap[0][n-1], gMap[0][n-1]);
//	}
	
	
	
	
	public static int win3(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fMap = new int[N][N];
		int[][] gMap = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			fMap[i][i] = arr[i];
		}
		for(int startCol = 1; startCol < N; startCol++) {
			int L = 0;
			int R = startCol;
			while(R < N) {
				fMap[L][R] = Math.max(arr[L] + gMap[L + 1][R], arr[R] + gMap[L][R - 1]);
				gMap[L][R] = Math.min(fMap[L + 1][R], fMap[L][R - 1]);
				L++;
				R++;
			}
			
		}
		return Math.max(fMap[0][N-1], gMap[0][N - 1]);
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(win2(arr));
		System.out.println(win1(arr));
		System.out.println(win3(arr));
	}

}
