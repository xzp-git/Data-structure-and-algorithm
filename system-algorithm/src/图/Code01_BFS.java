package 图;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

public class Code01_BFS {
	
	//宽度优先遍历
	public static void bfs (Node node) {
		if(node == null) return;
		
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>();
		
		queue.add(node);
		set.add(node);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if(!set.contains(next)) {
					queue.add(next);
					set.add(next);
				}
			}
		}
		
	}
}
