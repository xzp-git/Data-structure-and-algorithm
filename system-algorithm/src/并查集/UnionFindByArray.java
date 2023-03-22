package 并查集;




public class UnionFindByArray {
	
	private  int[] parent;
	private  int[] size;
	private  int[] help;
	private  int sets;
	
	public UnionFindByArray(int N) {
		parent = new int[N];
		size = new int[N];
		help = new int[N];
		sets = N;
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	
	
	public int find( int cur){
		int hi = 0;
		while(cur != parent[cur]) {
			help[hi++] = cur;
			cur = parent[cur];
		}
		for(hi--;hi >= 0; hi--) {
			parent[help[hi]] = cur;
		}
		return cur;
	}
	
	public boolean isSameSet(int a, int b) {
		return find(a) == find(b);
	}
	public void union(int a, int b) {
		int aHead = find(a);
		int bHead = find(b);
		if(aHead != bHead) {
			int aSetSize = size[aHead];
			int bSetSize = size[bHead];
			int bigSetHead = aSetSize > bSetSize ? aHead : bHead;
			int smallSetHead = bigSetHead == aHead ? bHead : aHead;
			parent[smallSetHead] = bigSetHead;
			size[bigSetHead] = aSetSize + bSetSize;
			sets--;
		}
	}
	
	public int sets() {
		return sets;
	}
}
