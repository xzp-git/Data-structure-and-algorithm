package 从暴力递归到动态规划;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code03_Coffee {
	
	/** 
	 * ######## 业务限制模型
	 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
		给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
		只有一台洗咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
		每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
		假设所有人拿到咖啡之后立刻喝干净，
		返回从开始等到所有咖啡杯变干净的最短时间
		三个参数：int[] arr、int N，int a、int b
	 * @param args
	 */
	
	
	public static class Machine{
		public int timePoint;
		public int workTime;
		public Machine (int w){
			this.workTime = w;
			this.timePoint = 0;
		}
	}
	
	public static class MachineComparator implements Comparator<Machine> {
		public int compare(Machine o1, Machine o2) {
			return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
		}
	}
	
	public static int bestTime(int[] arr,int n, int a, int b ) {
		PriorityQueue<Machine> pq = new PriorityQueue<Machine>(new MachineComparator());
		for(int i = 0; i < arr.length; i++) {
			pq.add(new Machine(arr[i]));
		}
		int[] drinks = new int[n];
		for(int i = 0; i < n; i++) {
			Machine cur = pq.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			pq.add(cur);
		}
		// 递归含义 所有人喝完的时间点数组 drinks，从第0个人开始，洗杯子的机器等待时间是0，那么全部吧杯子洗干净需要多久的时间
		return process(drinks, a, b, 0, 0);
	}
	
	/**
	 * 
	 * @param drinks 每个人喝完咖啡的时间
	 * @param a 机器洗干净一个杯子的时间
	 * @param b 挥发干净的时间
	 * @param free 咖啡机可用的时间
	 * index 目前洗到了哪个杯子
	 * @return
	 */
	
	public static int process(int[] drinks, int a, int b, int free, int index) {
		if(index == drinks.length) {
			return 0;
		}
		// 当前位置的人用机器洗
		int selfClean1 = Math.max(drinks[index], free) + a;
		int restClean1 = process(drinks, a, b,  selfClean1, index + 1);
		int allClean1 = Math.max(selfClean1, restClean1);
		
		int selfClean2 = drinks[index] + b;
		int restClean2 = process(drinks, a, b, free, index + 1);
		int allClean2 = Math.max(selfClean2, restClean2);
		
		return Math.min(allClean2, allClean1);
		
	}
	
	public static int bestTimeDp(int[] arr,int n, int a, int b) {
		PriorityQueue<Machine> pq = new PriorityQueue<>(new MachineComparator());
		for(int i = 0; i < arr.length; i++) {
			pq.add(new Machine(arr[i]));
		}
		int[] drinks = new int[n];
		for(int i = 0; i < n; i++) {
			Machine cur = pq.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			pq.add(cur);
		}
		int maxFree = 0;
		for(int i = 0; i < drinks.length; i++) {
			maxFree  = Math.max(maxFree, drinks[i]) + a;
		}
		
		int[][] dp = new int[n+1][maxFree + 1];
		
		for(int index = n - 1; index >= 0; index--) {
			for(int free = 0; free <= maxFree; free++) {
				int selfClean1 =Math.max(drinks[index], free) + a;
				if(selfClean1 > maxFree ) {
					break;
				}
				
				
				int restClean1 = dp[index + 1][selfClean1];
				int allClean1 = Math.max(selfClean1, restClean1);
				
				int selfClean2 = drinks[index] + b;
				int restClean2 = dp[index + 1][free];
				int allClean2 = Math.max(selfClean2, restClean2);
				
				dp[index][free] = Math.min(allClean2, allClean1);
				
			}
		}
		
		return dp[0][0];
		
		
	}
	
	
	// 验证的方法
		// 彻底的暴力
		// 很慢但是绝对正确
		public static int right(int[] arr, int n, int a, int b) {
			int[] times = new int[arr.length];
			int[] drink = new int[n];
			return forceMake(arr, times, 0, drink, n, a, b);
		}

		// 每个人暴力尝试用每一个咖啡机给自己做咖啡
		public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
			if (kth == n) {
				int[] drinkSorted = Arrays.copyOf(drink, kth);
				Arrays.sort(drinkSorted);
				return forceWash(drinkSorted, a, b, 0, 0, 0);
			}
			int time = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				int work = arr[i];
				int pre = times[i];
				drink[kth] = pre + work;
				times[i] = pre + work;
				time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
				drink[kth] = 0;
				times[i] = pre;
			}
			return time;
		}

		public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
			if (index == drinks.length) {
				return time;
			}
			// 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
			int wash = Math.max(drinks[index], washLine) + a;
			int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

			// 选择二：当前index号咖啡杯，选择自然挥发
			int dry = drinks[index] + b;
			int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
			return Math.min(ans1, ans2);
		}

	// for test
		public static int[] randomArray(int len, int max) {
			int[] arr = new int[len];
			for (int i = 0; i < len; i++) {
				arr[i] = (int) (Math.random() * max) + 1;
			}
			return arr;
		}

		// for test
		public static void printArray(int[] arr) {
			System.out.print("arr : ");
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[j] + ", ");
			}
			System.out.println();
		}

		public static void main(String[] args) {
			int len = 10;
			int max = 10;
			int testTime = 10;
			System.out.println("测试开始");
			for (int i = 0; i < testTime; i++) {
				int[] arr = randomArray(len, max);
				int n = (int) (Math.random() * 7) + 1;
				int a = (int) (Math.random() * 7) + 1;
				int b = (int) (Math.random() * 10) + 1;
				int ans1 = right(arr, n, a, b);
				int ans2 = bestTime(arr, n, a, b);
				int ans3 = bestTimeDp(arr, n, a, b);
				if (ans1 != ans2 || ans2 != ans3) {
					printArray(arr);
					System.out.println("n : " + n);
					System.out.println("a : " + a);
					System.out.println("b : " + b);
					System.out.println(ans1 + " , " + ans2 + " , " + ans3);
					System.out.println("===============");
					break;
				}
			}
			System.out.println("测试结束");

		}

}
