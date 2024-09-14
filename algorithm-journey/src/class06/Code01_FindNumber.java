package class06;

import java.util.Arrays;

public class Code01_FindNumber {

	public static void main(String[] args) {
		int N = 100;
		int V = 1000;
		int testTime = 500;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N);
			int[] arr = randomArray(n, V);
			Arrays.sort(arr);
			int num = (int) (Math.random() * V);
			if (right(arr, num) != exist(arr, num)) {
				System.out.println(arr);
				System.out.println( num);
				System.out.println(right(arr, num));
				System.out.println(exist(arr, num));
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
	public static boolean right(int[] sortedArr, int num) {
		if (sortedArr == null || sortedArr.length == 0) {
			return false;
		}
		for (int i = 0; i < sortedArr.length; i++) {
			if (sortedArr[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean exist(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		
		int l = 0, r = arr.length - 1, m = 0, ans = -1;
		
		while (l <= r) {
			m = (r + l) / 2;
			if (arr[m] > num) {
				r = m - 1;
			}else if(arr[m] < num){
				l = m + 1;
			}else {
				return true;
			}
		}
		return false;
	}

}
