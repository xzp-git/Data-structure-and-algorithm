package 并查集;


/**
 * 
 * 岛问题（递归解法 + 并查集解法 + 并行解法）
给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量

 * @author bytedance
 *
 */
public class Code02_NumberOfIslands {
	public static int numIslands(char[][] board) {
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
}
