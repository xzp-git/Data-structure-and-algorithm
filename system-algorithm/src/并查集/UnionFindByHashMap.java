package 并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;



public class UnionFindByHashMap<V> {
	public static class Node<V>{
		public V value;
		
		public Node(V val){
			this.value = val;
		}
	}
	public  HashMap<V, Node<V>> nodesMap;
	public  HashMap<Node<V>, Integer> sizeMap;
	public  HashMap<Node<V>, Node<V>> parentMap;
	
	public UnionFindByHashMap(List<V> values) {
		nodesMap = new HashMap<>();
		sizeMap = new HashMap<>();
		parentMap = new HashMap<>();
		for(V cur : values) {
			Node<V> node = new Node<V>(cur);
			nodesMap.put(cur, node);
			sizeMap.put(node, 1);
			parentMap.put(node, node);
		}
	}
	
	

	public Node<V> findParent(Node<V> cur){
		Stack<Node<V>> nodePaths = new Stack<>();
		
		while(parentMap.get(cur) != cur) {
			nodePaths.push(cur);
			cur = parentMap.get(cur);
		}
		
		//此处 是一处优化，会把每次涉及到的节点 平铺到代表节点上，方便后续的查找
		while(!nodePaths.isEmpty()){
			parentMap.put(nodePaths.pop(), cur);
		}
		return cur;
	}
	
	public boolean isSameSet(V a, V b) {
		return findParent(nodesMap.get(a)) == findParent(nodesMap.get(b));
	}
	public void union(V a, V b) {
		Node<V> aParent = findParent(nodesMap.get(a));
		Node<V> bParent = findParent(nodesMap.get(b));
		if(aParent != bParent) {
			int aSetSize = sizeMap.get(aParent);
			int bSetSize = sizeMap.get(bParent);
			Node<V> bigParent = aSetSize >= bSetSize ? aParent : bParent;
			Node<V> smallParent = bigParent == aParent ? bParent: aParent;
			
			//小挂大  小节点的父级 指向 大节点的父级
			parentMap.put(smallParent, bigParent);
			sizeMap.remove(smallParent);
			sizeMap.put(bigParent, aSetSize + bSetSize);
		}
	}
	
	public int sets() {
		return sizeMap.size();
	}
}
