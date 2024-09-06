package 图;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * 戴克斯特拉算法
 * @author bytedance
 * 
 * 一个顶点作为源结点然后找到该顶点到图中所有其它结点的最短路径，产生一个最短路径树
 *
 */
public class Code06_Dijkstra {
	
	
	public static HashMap<Node, Integer> dijkstra1(Node from){
		
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(from, 0);
		//打过对好的点
		HashSet<Node> selectedNode = new HashSet<>();
		Node minNode = gitMinDistanceAndUnselectedNode(distanceMap,selectedNode);
		
		while(minNode != null) {
			
			// 原始点 -> minNode 跳转点 的最小距离
			int distance = distanceMap.get(minNode);
			
			for(Edge edge : minNode.edges) {
				Node toNode = edge.to;
				if(!distanceMap.containsKey(toNode)) {
					distanceMap.put(toNode, distance + edge.weight);
				}else {
					distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
				}
			}
			selectedNode.add(minNode);
			minNode = gitMinDistanceAndUnselectedNode(distanceMap,selectedNode);
		}
		return distanceMap;
	}
	
	
	public static Node gitMinDistanceAndUnselectedNode (HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNode) {
		Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		
		for(Entry<Node, Integer> entry : distanceMap.entrySet()) {
			Node node = entry.getKey();
			int distance = entry.getValue();
			
			if(!selectedNode.contains(node) && distance < minDistance) {
				minNode = node;
				minDistance = distance;
			}
		}
		return minNode;
	}
	
	
	public static class NodeRecord {
		public Node node;
		public int distance;

		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}
	
	
	
	// 改进后的dijkstra算法
	// 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
	
	public static class NodeHeap{
		//堆
		private Node[] nodes;
		
		private HashMap<Node,Integer> heapIndexMap;
		
		private HashMap<Node, Integer> distanceMap;
		
		private int size;
		
		public NodeHeap(int size) {
			nodes = new Node[size];
			heapIndexMap = new HashMap<>();
			distanceMap = new HashMap<>();
			size = 0;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		private boolean isEntered (Node node) {
			return heapIndexMap.containsKey(node);
		}
		
		private boolean inHeap(Node node) {
			return isEntered(node) && heapIndexMap.get(node) != -1;
		}
		
		public NodeRecord pop() {
			NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
			swap(0, size - 1);
			heapIndexMap.put(nodes[size - 1], -1);
			distanceMap.remove(nodes[size - 1]);
			nodes[size - 1] = null;
			heapIfy(0, --size);
			return nodeRecord;
		}
		
		
		public void heapIfy(int index, int size) {
			int left = index * 2 + 1;
			while(left < size) {
				int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
				smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
				if(smallest == index) {
					break;
				}
				swap(smallest, index);
				index = smallest;
				left = index * 2 + 1;
			}
		}
		//和他的父亲比 如果小就上去
		public void insertHeapIfy(int index) {
			while(distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}
		
		
		public void swap(int i, int j) {
			heapIndexMap.put(nodes[i], j);
			heapIndexMap.put(nodes[j], i);
			Node tmp = nodes[i];
			nodes[i] = nodes[j];
			nodes[j] = tmp;
		}
		// 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
		// 判断要不要更新，如果需要的话，就更新
		public void addOrUpdateOrIgnore(Node node, int distance) {
			if(inHeap(node)) {
				distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
				insertHeapIfy(heapIndexMap.get(node));
			}
			if(!isEntered(node)) {
				distanceMap.put(node, distance);
				nodes[size] = node;
				heapIndexMap.put(node, size);
				insertHeapIfy(size++);
				
			}
		}
	}
	
	
	public static HashMap<Node, Integer> dijkstra2(Node head, int size){
		NodeHeap nodeHeap = new NodeHeap(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0);
		HashMap<Node, Integer> result = new HashMap<>();
		
		while(!nodeHeap.isEmpty()) {
			NodeRecord nodeRecord = nodeHeap.pop();
			Node cur = nodeRecord.node;
			int distance = nodeRecord.distance;
			
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
			}
			result.put(cur, distance);
		}
		return result;
	}
	
	
}
