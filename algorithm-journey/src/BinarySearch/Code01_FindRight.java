package BinarySearch;

import java.util.Arrays;

//有序数组中找<=num的最右位置

public class Code01_FindRight {
	
	// 为了验证
		public static void main(String[] args) {
			int N = 100;
			int V = 1000;
			int testTime = 500000;
			System.out.println("测试开始");
			for (int i = 0; i < testTime; i++) {
				int n = (int) (Math.random() * N);
				int[] arr = randomArray(n, V);
				Arrays.sort(arr);
				int num = (int) (Math.random() * V);
				if (right(arr, num) != findRight(arr, num)) {
					System.out.println("出错了!");
				}
			}
			System.out.println("测试结束");
		}

		// 为了验证
		public static int[] randomArray(int n, int v) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = (int) (Math.random() * v) + 1;
			}
			return arr;
		}

		// 为了验证
		// 保证arr有序，才能用这个方法
		public static int right(int[] arr, int num) {
			int ans = -1;
			if (arr == null || arr.length == 0) {
				return ans;
			}
			for (int i = arr.length - 1; i >= 0; i--) {
				if (arr[i] <= num) {
					return i;
				}
			}
			return -1;
		}

	
	public static int findRight(int[] arr, int num) {
		int ans = -1;
		if (arr == null || arr.length == 0) {
			return ans;
		}
		
		int l = 0, r = arr.length - 1, m = 0;
		
		while (l <= r) {
			m = l + ((r - l) >> 1);
			if (arr[m] <= num) {
				ans = m;
				l = m + 1;
			}else {
				r = m - 1;
			}
		}
		return ans;
	}
}
