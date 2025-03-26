package Sort;

public class Validate {
	
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	
	public static void selectSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for(int minIndex, i = 0; i < arr.length; i++) {
			minIndex = i;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			swap(arr, i, minIndex);
		}
	}
	
	public static void bubbleSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		for(int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(arr[j + 1] < arr[j]) {
					swap(arr, j+1, j);
				}
			}
		}
	}
	
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for(int i = 1; i < arr.length; i++) {
			for(int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}
	
	
	
	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * v) + 1;
		}
		return arr;
	}
	
	public static int[] copyArray(int[] arr) {
		int[] newArr = new int[arr.length];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}
	
	public static boolean sameArray(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 100;
		int V = 1000;
		int testTimes = 10000;
		int n = (int)(Math.random() * N);
		System.out.println("测试开始！");
		for(int i = 0; i < testTimes; i++) {
			int[] arr = randomArray(n, V);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			int[] arr3 = copyArray(arr);
			bubbleSort(arr1);
			selectSort(arr2);
			insertSort(arr3);
			if (!sameArray(arr1, arr2) || !sameArray(arr1, arr3)) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束！");
	}

}
