package 贪心算法;

import java.util.HashSet;

public class Code01_Light {
	
	
	/**
	 *  给定一个字符串str，只由'X'和'.'两种字符构成
 		'X'表示墙，不能放灯，也不需要点亮；'.'表示居民点，可以放灯，需要点亮
		如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
		返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
	 * @param args
	 * 
	 * 解析 ： i 位置 是  X 直接跳过  i++ 下一个
	 * 		  i 位置 是  . 看 i+1 位置 i+1 是 X light++; 然后去 i+2 位置看
	 * 								 i+1 是 . 看 i+2位置  i+2 是 X light++；看i+3
	 * 													 i+2 是 . ligth++; 看i+3  
	 * 													因为无论 i+2 是. 还是X light都会++ 都会看i+3 
	 * 													所以这两种情况可以合并
	 * 		  还可以看出 只要 i 位置 不是 X light就会++；
	 * 		 while 还有 一个终止条件 就是 如果 i位置是.  i+1 位置 已经不存在了就直接 break
	 * 
	 * 
	 */
	public static int minLight2(String road) {
		char[] str = road.toCharArray();
		int light = 0;
		int i = 0;
		while(i < str.length) {
			char cur = str[i];
			if(cur == 'X') {
				i++;
			}else {
				light++;
				if(i+1 == str.length) {
					break;
				}else {
					if(str[i+1] == 'X') {
						i = i + 2;
					}else {
						i = i + 3;
					}
				}
				
			}
		}
		return light;
	}
	
	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	// str[index....]位置，自由选择放灯还是不放灯
	// str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
	// 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) { // 结束的时候
			for (int i = 0; i < str.length; i++) {
				if (str[i] != 'X') { // 当前位置是点的话
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else { // str还没结束
			// i X .
			int no = process(str, index + 1, lights);
			int yes = Integer.MAX_VALUE;
			if (str[index] == '.') {
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}
	
	// 更简洁的解法
	// 两个X之间，数一下.的数量，然后除以3，向上取整
	// 把灯数累加
	public static int minLight3(String road) {
		char[] str = road.toCharArray();
		int cur = 0;
		int light = 0;
		for (char c : str) {
			if (c == 'X') {
				light += (cur + 2) / 3;
				cur = 0;
			} else {
				cur++;
			}
		}
		light += (cur + 2) / 3;
		return light;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight2(test);
			int ans3 = minLight3(test);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
	

}
