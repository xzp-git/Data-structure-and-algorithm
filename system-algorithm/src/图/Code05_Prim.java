package 图;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashSet;
/**
 * 
 * @author bytedance
 * Prim算法的作用是 找出连通整个图的最小权重的边
 */

public class Code05_Prim {
	public static Set<Edge> primMST(Graph graph){
		//解锁的边进入小根堆
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
		//哪些点被解锁出来
		HashSet<Edge> result = new HashSet<>();
		
		HashSet<Node> nodeSet = new HashSet<>(); //依次挑选的边进入set
		
		for(Node node : graph.nodes.values()) {// 随便挑选一个点
			
			//node是开始点
			if(!nodeSet.contains(node)) {
				nodeSet.add(node);
				//由一个节点解锁其相关的边
				for(Edge edge : node.edges) {
					priorityQueue.add(edge);
				}
				
				while(!priorityQueue.isEmpty()) {
					Edge edge = priorityQueue.poll(); //弹出解锁的边中最小的边
					Node toNode = edge.to; //可能的一个新点
					if(!nodeSet.contains(toNode)) { //不含有的时候就是新点
						nodeSet.add(toNode);
						result.add(edge);
						for(Edge nextEdge : toNode.edges) {
							priorityQueue.add(nextEdge);
						}
					}
				}
				
			}
			break;
		}
		return result;
	}
}
