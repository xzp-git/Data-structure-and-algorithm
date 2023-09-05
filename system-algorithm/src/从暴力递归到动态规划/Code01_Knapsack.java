package 从暴力递归到动态规划;

public class Code01_Knapsack {
	
	/**
	 * 背包问题
		给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
		给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
		返回能装下的最大价值
	 */
	
	public static int maxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		
		//尝试函数的含义 来到 index 位置 我的背包能装下的最大价值
		return process(w , v, 0, bag);
	}
	
	// 尝试思路 我要index 位置的 值是多少， 不要index位置的值是多少，两者取最大值
	public static int process (int[] w, int[] v, int index, int rest) {
		// 背包容量小于 0 返回-1
		if(rest < 0) {
			return -1;
		}
		// 位置来到的 w.length 表示此位置没有东西可拿，价值是0
		if(index == w.length) {
			return 0;
		}
		// 一般位置的最大值 为 要此时index 位置的价值， 和 不要此时的价值，两者取最大值
		// 不要 index 位置的值那此时的价值 就是index+1的价值
		int p1 = process(w, v , index + 1, rest);
		int p2 = 0;
		// 看看下一个 是什么情况，装不装的下，装了才知道，假如 是 -1 ，则代表，index位置的也取不到
		int next = process(w, v, index + 1, rest - w[index]);
		// 如果下一个不是 -1 则需要把本次的价值 加上下次的next
		if(next != -1) {
			p2 = v[index] + next;
		}
		return Math.max(p2, p1);
	}
	
	// 分析依赖关系 从 process 中看出 index 和 rest 一直变化的量 index的范围 0~w.length, rest的范围 0 ~ bag
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
