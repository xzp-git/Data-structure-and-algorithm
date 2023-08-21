package 经典递归问题;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class Code03_PrintAllSubsquences {
	
	public static List<String> subs(String s){
		char[] str = s.toCharArray();
		
		String path = "";
		
		List<String> ans = new ArrayList<>();
		
		process1(str, 0, ans, path);
		
		return ans;
	}
	/**
	 * 
	 * @param str 固定参数 
	 * @param index 来到了 str[index] 字符，index 是位置 
	 * str[0...index - 1]已经走过了 之前的决定，都在path上，之前的决定已经不能改变了就是 path上
	 * @param ans 把所有生成的子序列，放入到ans里去
	 * @param path
	 */
	
	public static void process1(char[] str, int index, List<String> ans, String path) {
		if(index == str.length) {
			ans.add(path);
			return;
		}
		process1(str, index + 1, ans, path);
		process1(str, index + 1, ans, path + String.valueOf(str[index]));
		
	}
	
	public static List<String> subsNoRepeat(String s){
		char[] str = s.toCharArray();
		
		String path = "";
		
		HashSet<String> set = new HashSet<>();
		
		List<String> ans = new ArrayList<>();
		
		process2(str, 0, set, path);
		
		for(String cur:set) {
			ans.add(cur);
		}
		return ans;
	}
	
	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if(index == str.length) {
			set.add(path);
			return;
		}
		process2(str, index + 1, set, path);
		process2(str, index + 1, set, path + String.valueOf(str[index]));
		
	}
	
	public static void main(String [] args) {
		String test = "accc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}
}
