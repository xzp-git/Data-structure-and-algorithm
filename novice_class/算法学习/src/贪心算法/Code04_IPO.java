package 贪心算法;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {
	
	/**
	 * 	输入正数数组costs、正数数组profits、正数K和正数M
	 * costs[i]表示i号项目的花费
	 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
	 * K表示你只能串行的最多做k个项目
	 * M表示你初始的资金
	 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
	 * 输出：最后获得的最大钱数
	 * 
	 * 
	 * @param args
	 */
	
	public static int findMaximizedCapital(int K, int W, int[] profits, int[] capital) {
		
		PriorityQueue<Program> minCostQ = new PriorityQueue<Program>(new MinCotsComparator());
		PriorityQueue<Program> maxProfitsQ = new PriorityQueue<Program>(new MaxProfitsComparator());
		
		for(int i = 0; i < profits.length; i++) {
			minCostQ.add(new Program(profits[i], capital[i]));
		}
		
		for(int i = 0; i < K; i++) {
			while(!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitsQ.add(minCostQ.poll());
			}
			
			if(maxProfitsQ.isEmpty()) {
				return W;
			}
			
			W += maxProfitsQ.poll().p;
		}
		
		
		return W;
	}
	
	
	
	public static class Program{
		public int p;
		public int c;
		
		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
	
	public static class MinCotsComparator implements Comparator<Program>{

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o1.c  - o2.c;
		}
		
	}
	
	public static class MaxProfitsComparator implements Comparator<Program>{

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o2.p - o1.p;
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
