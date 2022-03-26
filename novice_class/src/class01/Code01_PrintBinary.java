package class01;

public class Code01_PrintBinary {
	
	public static void print(int num) {
		for (int i = 31; i >= 0; i--) {
			System.out.print((num & (1 << i)) == 0 ? "0" : "1");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
//		int num = 635963;
//		
//		print(num);
		
//		int a = Integer.MAX_VALUE;
//		System.out.println(a);
//		print(a);
//		print(a >> 1);
//		print(a >>> 1);
//		int a = 12364566;
//		int b = 5;
//		int c = ~b;
//		System.out.println(b);
//		System.out.println(c);
//		print(b);
//		print(c);
//		System.out.println("===========");
//		print(a | b);
//		System.out.println("===========");
//		print(a & b);
//		System.out.println("===========");
//		print(a ^ b);
		
		int min = Integer.MIN_VALUE;
		print(min);
		print(min >> 1); //带符号右移，最高位用符号位补齐
		print(min >>> 1); //无符号右移，最高位用0补齐
	}

}
