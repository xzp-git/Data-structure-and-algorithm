package 从暴力递归到动态规划;

public class Code02_CoinsWayEveryPaperDifferent {

	
	
	
//	arr是货币数组，其中的值都是正数。再给定一个正数aim。
//	每个值都认为是一张货币，
//	即便是值相同的货币也认为每一张都是不同的，
//	返回组成aim的方法数
//	例如：arr = {1,1,1}，aim = 2
//	第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
//	一共就3种方法，所以返回3
	
	public static int coinWays(int[] arr, int aim) {
		if(arr == null || arr.length == 0 || aim == 0 ) {
			return 0;
		}
		
		return process1(arr, 0, aim);
	}
	
	public static int process1(int[] arr, int index, int rest) {
		
		
		return index;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
