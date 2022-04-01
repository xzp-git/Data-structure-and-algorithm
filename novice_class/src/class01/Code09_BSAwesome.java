package class01;

public class Code09_BSAwesome {
	
	public static int getLessIndex(int[] arr) {
		int N = arr.length;
		
		if(arr == null || N == 0) {
			return -1;
		}
		if(N == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if(arr[N - 1] < arr[N - 2]) {
			return N - 1;
		}
		
		int index = -1;
		int L = 0;
		int R = N - 1;
		int mid = 0;
		
		return -1;
	}
}
