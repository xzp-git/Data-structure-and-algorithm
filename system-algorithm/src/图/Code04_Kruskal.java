package 图;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.PriorityQueue;


/**
 * 
 * @author bytedance
 * 
 * Kruskal算法的作用是找见所有连通图的最小权重的边的集合
 *
 */
public class Code04_Kruskal {
	public static class UnionFind {
		// key 某一个节点， value key节点的头节点
		private HashMap<Node, Node> parentMap;
		// key 某一个集合的代表节点, value key所在集合的节点个数
		private HashMap<Node, Integer> sizeMap;
		
		public UnionFind() {
			
			parentMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}
		
		public void makSet (Collection<Node> nodes) {
			parentMap.clear();
			sizeMap.clear();
			for(Node node : nodes) {
				parentMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}
		
		public Node findParent (Node node) {
			Stack<Node> stack = new Stack<>();
			Node cur = parentMap.get(node);
			while(cur != parentMap.get(node)) {
				stack.add(node);
				cur = parentMap.get(cur);
			}
			while(!stack.isEmpty()) {
				parentMap.put(stack.pop(), cur);
			}
			return cur;
		}
		
		
		public void union(Node a, Node b) {
			if(a == null || b == null) return;
			
			Node aParent = findParent(a);
			Node bParent = findParent(b);
			
			if(aParent != bParent) {
				
				int aSize = sizeMap.get(aParent);
				int bSize = sizeMap.get(bParent);
				
				Node bigNode = aSize >= bSize ? aParent : bParent;
				Node smallNode = bigNode == aParent ? bParent : aParent;
				
				parentMap.put(smallNode, bigNode);
				sizeMap.put(bigNode, aSize + bSize);
				sizeMap.remove(smallNode);
				
			}
		}
		
		public boolean isSameSet(Node a, Node b) {
			return findParent(a) == findParent(b);
		}
	}
	
	public static class EdgeComparator implements Comparator<Edge> {

		public int compare(Edge o1, Edge o2) {
			// TODO Auto-generated method stub
			return o1.weight - o2.weight;
		}
		
	}
	
	
	
	public static Set<Edge> kruskalMST(Graph graph){
		UnionFind unionfind  = new UnionFind();
		unionfind.makSet(graph.nodes.values());
		
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		
		for(Edge edge : graph.edges) {
			priorityQueue.add(edge);
		}
		HashSet<Edge> result = new HashSet<>();
		while(!priorityQueue.isEmpty()) {
			Edge cur = priorityQueue.poll();
			
			if(!unionfind.isSameSet(cur.from, cur.to)) {
				result.add(cur);
				unionfind.union(cur.from, cur.to);
			}
			
		}
		
		return result;
	}
	
	
}
