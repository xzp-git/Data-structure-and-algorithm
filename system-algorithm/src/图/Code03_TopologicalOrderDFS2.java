package 图;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


//OJ链接：https://www.lintcode.com/problem/topological-sorting

public class Code03_TopologicalOrderDFS2 {
	   // 不要提交这个类
		public static class DirectedGraphNode {
			public int label;
			public ArrayList<DirectedGraphNode> neighbors;

			public DirectedGraphNode(int x) {
				label = x;
				neighbors = new ArrayList<DirectedGraphNode>();
			}
		}
		
		
		public static class Record{
			public DirectedGraphNode node;
			public long nodes;
			
			public Record (DirectedGraphNode n, long o) {
				node = n;
				nodes = o;
			}
		}
		public static class MyComparator implements Comparator<Record>{
			
			public int compare(Record r1, Record r2) {
				return  r1.nodes == r2.nodes ? 0 : (r1.nodes > r2.nodes ? -1 : 1);
			}
		}
		
		
		
		public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){
			HashMap<DirectedGraphNode, Record> order = new HashMap<>();
			
			for(DirectedGraphNode next : graph) {
				calcNodes(next, order);
			}
			
			ArrayList<Record> recordArr = new ArrayList<>();
			
			for(Record r : order.values()) {
				recordArr.add(r);
			}
			
			recordArr.sort(new MyComparator());
			
			ArrayList<DirectedGraphNode> ansArr = new ArrayList<>();
			
			for(Record r : recordArr) {
				
				ansArr.add(r.node);
			}
			return ansArr;
		}
		
		// 当前来到cur 点 请返回cur点所到之处，所有的点次
		// 返回 Record
		// 缓存 order
		
		public static Record calcNodes(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
			if(order.containsKey(cur)) {
				return order.get(cur);
			}
			
			long nodes = 0;
			
			for(DirectedGraphNode next : cur.neighbors) {
				nodes += calcNodes(next, order).nodes;
			}
			
			Record ans = new Record(cur, nodes + 1);
			
			order.put(cur, ans);
			
			return ans;
			
		}
		
		
		
}
