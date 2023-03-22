package 并查集;
import 并查集.UnionFindByArray;
/**
 * 题目：

一群朋友中，有几个不相交的朋友圈
Leetcode题目：https://leetcode.com/problems/friend-circles/
 * @author bytedance
 *
 */
public class Code02_FriendCircle {
	
	public static int friendCircleNum(int[][] M) {
		int N = M.length;
		UnionFindByArray union = new UnionFindByArray(N);
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(M[i][j] == 1) {
					union.union(i, j);
				}
			}
		}
		return union.sets();
	}
	
}
