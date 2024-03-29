package 图;

public class GraphGenerator {
	
	
	/**
	 *  matrix 所有的边
	 *  
	 *  N * 3 的矩阵
	 *  [weight, from节点上面的值，to节点上面的值]
	 *  [5, 0, 7]
	 *  [3, 0, 1]
	 */
	
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		
		for (int i = 0; i < matrix.length; i++) {
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			
			if(!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if(!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			
			Node formNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			
			Edge edge = new Edge(weight, formNode, toNode);
			
			formNode.nexts.add(toNode);
			formNode.edges.add(edge);
			formNode.out++;
			toNode.in++;
			graph.edges.add(edge);
			
		}
		
		return graph;
	}
	
	
	

}
