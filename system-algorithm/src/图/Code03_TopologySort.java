package 图;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Code03_TopologySort {
	
	
	public static List<Node> sortedTopology(Graph graph){
		
		HashMap<Node, Integer>  inMap = new HashMap<>();
		Queue<Node> zeroQueue = new LinkedList <>();
		
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if(node.in == 0) {
				zeroQueue.add(node);
			}
		}
		
		List<Node> result = new ArrayList<>();
		
		while(!zeroQueue.isEmpty()) {
			Node cur = zeroQueue.poll();
			result.add(cur);
			for(Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if(inMap.get(next) == 0) {
					zeroQueue.add(next);
				}
			}
		}
		
		
		return result;
	}
	
}
