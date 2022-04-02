package class01;

public class Code09_BSAwesome {
	
	public static int getLessIndex(int[] arr){
	    int N = arr.length;
	    if(arr == null || N == 0){
	    	return -1;
	    }
	    if(N == 1 || arr[0] < arr[1]){
	    	return 0;
	    }
	    if(arr[N - 1] < arr[N - 2]){
	        return N - 1;
	    }
	    
	    int L = 1;
	    int R = N - 2;
	    int mid = 0;
	    while(L < R){
	        mid = L + ((R - L) >> 1);
	        if(arr[mid] > arr[mid - 1]){
	            R = mid - 1;
	        }else if(arr[mid] > arr[mid + 1]){
	            L = mid + 1;
	        }else{
	            return mid;
	        }
	    }
	    return L;
	}
	
	
	// 验证得到的结果，是不是局部最小
		public static boolean isRight(int[] arr, int index) {
			if (arr.length <= 1) {
				return true;
			}
			if (index == 0) {
				return arr[index] < arr[index + 1];
			}
			if (index == arr.length - 1) {
				return arr[index] < arr[index - 1];
			}
			return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
		}

		// 为了测试
		// 生成相邻不相等的数组
		public static int[] generateRandomArray(int maxSize, int maxValue) {
			int[] arr = new int[(int) (Math.random() * maxSize) + 1];
			arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
			for (int i = 1; i < arr.length; i++) {
				do {
					arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
				} while (arr[i] == arr[i - 1]);
			}
			return arr;
		}

		// 为了测试
		public static void main(String[] args) {
			int testTime = 500000;
			int maxSize = 30;
			int maxValue = 100;
			System.out.println("测试开始");
			for (int i = 0; i < testTime; i++) {
				int[] arr = generateRandomArray(maxSize, maxValue);
				int ans = getLessIndex(arr);
				if (!isRight(arr, ans)) {
					System.out.println("出错了！");
					break;
				}
			}
			System.out.println("测试结束");
		}
}
