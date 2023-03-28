package 并查集;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import 并查集.UnionFindByHashMap;

/**
 * 
 * 岛问题（递归解法 + 并查集解法 + 并行解法）
给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量

 * @author bytedance
 *
 */
public class Code02_NumberOfIslands {
	public static int numIslands3(char[][] board) {
		int isLands = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == '1') {
					isLands++;
					infect(board,i,j);
				}
			}
		}
		
		return isLands;
	}
	
	
	public static void infect(char[][] board, int i, int j) {
		if(i < 0 || i >= board.length || j < 0 || j >= board[0].length ||  board[i][j] != '1') {
			return;
		}
		
		board[i][j] = '2';
		infect(board, i + 1, j);
		infect(board, i - 1, j);
		infect(board, i, j + 1);
		infect(board, i, j - 1);
	}
	
	public static class Dot{
		
	}
	
	
	public static int numIslands1 (char[][] board) {
		
		int row = board.length;
		int col = board[0].length;
		List<Dot> list = new ArrayList<>();
		Dot[][] dots = new Dot[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(board[i][j] == '1') {
					dots[i][j] = new Dot();
					list.add(dots[i][j]);
				}
			}
		}
		UnionFindByHashMap<Dot> union = new UnionFindByHashMap<>(list);
		for(int j = 1; j < col; j++) {
			if(board[0][j-1] == '1' && board[0][j] == '1') {
				union.union(dots[0][j], dots[0][j-1]);
			}
		}
		for(int j = 1; j < row; j++) {
			if(board[j-1][0] == '1' && board[j][0] == '1') {
				union.union(dots[j][0], dots[j-1][0]);
			}
		}
		for(int j = 1; j < row; j++) {
			for(int i = 1; i < col; i++) {
				
				//此处不能使用 else if 取判断 会少收集 1
				if(board[j][i] == '1' && board[j-1][i] == '1') {
					union.union(dots[j][i], dots[j-1][i]);
				}
				
				if(board[j][i] == '1' && board[j][i-1] == '1') {
					union.union(dots[j][i], dots[j][i-1]);
				}
			}
		}
		return union.sets();
	}
	
	
public static int numIslands2 (char[][] board) {
		
		int row = board.length;
		int col = board[0].length;
		
		UnionFindByArray1 union = new UnionFindByArray1(board);
		for(int j = 1; j < col; j++) {
			if(board[0][j-1] == '1' && board[0][j] == '1') {
				union.union(0, j, 0, j-1);
			}
		}
		for(int j = 1; j < row; j++) {
			if(board[j-1][0] == '1' && board[j][0] == '1') {
				union.union(j, 0, j - 1, 0);
			}
		}
		for(int j = 1; j < row; j++) {
			for(int i = 1; i < col; i++) {
				
				//此处不能使用 else if 取判断 会少收集 1
				if(board[j][i] == '1' && board[j-1][i] == '1') {
					union.union(j, i, j - 1, i);
				}
				
				if(board[j][i] == '1' && board[j][i-1] == '1') {
					union.union(j, i, j, i - 1);
				}
			}
		}
		return union.sets();
	}
	
	
	public static class UnionFindByArray1 {
		private  int col;
		private  int[] parent;
		private  int[] size;
		private  int[] help;
		private  int sets;
		
		public UnionFindByArray1(char[][] board) {
			col = board[0].length;
			sets = 0;
			int row = board.length;
			int len = row * col;
			parent = new int[len];
			size = new int[len];
			help = new int[len];
			for(int r = 0; r < row; r++) {
					for(int c = 0; c < col; c++) {
						if(board[r][c] == '1') {
							int idx = index(r, c);
							parent[idx] = idx;
							size[idx] = 1;
							sets++;
						}
					}
			}
		}
		
		public int index(int r, int c) {
			return r * col +c;
		}
		
		
		
		public int find( int r, int c){
			int cur = index(r, c);
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
		
		public boolean isSameSet(int r1, int c1, int r2, int c2) {
			
			return find(r1, c1) == find(r2, c2);
		}
		public void union(int r1, int c1, int r2, int c2) {
			int aHead = find(r1, c1);
			int bHead = find(r2, c2);
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
}
