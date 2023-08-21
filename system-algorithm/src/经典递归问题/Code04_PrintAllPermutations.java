package 经典递归问题;

import java.util.List;
import java.util.ArrayList;

public class Code04_PrintAllPermutations {
	
	/**
	 * 打印字符串的所有全排列，
	 * @param s
	 * @return
	 */
	
	public static List<String> permutations1 (String s){
		List<String> ans = new ArrayList<>();
		if(s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		
		ArrayList<Character> rest = new ArrayList<>();
		
		for(char cur : str) {
			rest.add(cur);
		}
		String path = "";
		f(rest, path, ans);
		return ans;
	}
	/**
	 *  这个方法的作用是在剩余的字符里 选择一个字符 组装到 path 中，
	 *  如果剩余的字符为空，就代表一次全排列已经生成，需要注意 回归现场， 你用完这个 字符 把他删除后，需要恢复回来
	 * @param rest 剩余的可用字符
	 * @param path 前面已经组装好的字符
	 * @param ans 最后的答案
	 */
	public static void f (ArrayList<Character> rest, String path, List<String> ans) {
		if(rest.isEmpty()) {
			ans.add(path);
		}else {
			int N = rest.size();
			for(int i = 0; i < N; i++) {
				char cur = rest.get(i);
				rest.remove(i);
				f(rest, path + cur, ans);
				rest.add(i,cur); // 回归现场， 你用完这个 字符 把他删除后，需要恢复回来
			}
		}
		
	}
	
	public static List<String> permutations2(String s){
		if(s == null || s.length() == 0) {
			return null;
		}
		ArrayList<String> ans = new ArrayList<>();
		char[] str = s.toCharArray();
		g1(str, 0, ans);
		return ans;
	}
	
	
	
	/**
	 * 字符 [a, b, c]
	 * 
	 * 递归
	 *  0-0 交换 [a, b, c]传下去，  1-1交换 [a, b, c]传下去， 2-2交换  [a, b, c]完毕这是一种全排列
	 *  						2-2交换完毕后 递归会返回到 1-1交换这步，然后进行 1-2交换 [a, b, c]传下来
	 *  						[a, c, b] 交换完毕后 index+1 为 3 等于 length 记录这个分支的结果，然后
	 *  						恢复现场为[a, b, c]，然后 递归返回到 0-0这一步，继续下面的0-1交换。。。
	 * 							0-1 [b, a, c] -> 1-1 [b, a, c] -> 2-2 [b, a, c]
	 * 											 1-2 [b, c, a] -> 2-2 [b, c, a]	
	 */		
	public static void g1(char[] str, int index, List<String> ans) {
		if(index == str.length) {
			ans.add(String.valueOf(str));
		}else {
			for(int i = index; i < str.length; i++) {
				swap(str, index, i);
				g1(str, index + 1, ans);
				swap(str, index, i);
			}
		}
	}
	
	/**
	 * 去重版
	 */
	public static List<String> permutations3(String s){
		if(s == null || s.length() == 0) {
			return null;
		}
		ArrayList<String> ans = new ArrayList<>();
		char[] str = s.toCharArray();
		g2(str, 0, ans);
		return ans;
	}
	
	
	
	public static void g2(char[] str, int index, List<String> ans) {
		if(index == str.length) {
			ans.add(String.valueOf(str));
		}else {
			// index 和 i位置的字符 做交换 ， 假如  index位置的字符 和 a字符已经交换过了， 
			// 如果再遇到 a 字符就没有必要重复交换了，如果交换了index位置 还是a  和原来没有变化就会重复出现排列 
			boolean[] visited = new boolean[256];
			for(int i = index; i < str.length; i++) {
				if(!visited[str[i]]) {
					visited[str[i]] = true;
					swap(str, index, i);
					g2(str, index + 1, ans);
					swap(str, index, i);
				}
			}
		}
	}
	
	
	public static void swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	
	public static void main (String[] args) {
		String s = "acc";
		List<String> ans1 = permutations1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("================");
		List<String> ans2 = permutations2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("================");
		List<String> ans3 = permutations3(s);
		for (String str : ans3) {
			System.out.println(str);
		}
	}
}
