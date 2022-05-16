package class02;

public class Code02_EvenTimesOddTimes {
	
	//arr中只有一种数，出现奇数次
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for(int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);
		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);
	}
	
	//arr中，有两种数，出现奇数次
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for(int i = 0; i<arr.length; i++) {
			eor ^= arr[i];
		}
		//提取最右侧的1
		int rightOne = eor & (-eor);
		int onlyOne = 0;
		for(int i = 0; i < arr.length; i++) {
			if((rightOne & arr[i]) != 0) {
				onlyOne ^= arr[i];
			}
		}
		System.out.println(onlyOne + "   " + (eor ^ onlyOne));
		
	}

}
