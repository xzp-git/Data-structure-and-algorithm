package class04;

import java.util.Iterator;

public class SelectBubbleInsert {
	
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
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			swap(arr, minIndex, i);
		}
	
	}
	
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for(int i = arr.length - 1; i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
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
				swap(arr, j + 1, j);
			}
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
