package class02;

public class Code01_Swap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 16;
		int b = 603;
		
		System.out.println(b);
		System.out.println(a);
		
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println("");
		
		int[] arr = {3, 1, 9};
		swap(arr, 0, 2);
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
	}
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

}
