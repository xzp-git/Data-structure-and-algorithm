package 从暴力递归到动态规划;

import java.util.HashMap;
//https://leetcode.cn/problems/stickers-to-spell-word/description/
public class Code03_StickersToSpellWord {
	
	/**
	 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
		arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
		返回需要至少多少张贴纸可以完成这个任务
		例子：str= "babac"，arr = {"ba","c","abcd"}
		ba + ba + c  3  abcd + abcd 2  abcd+ba 2
		所以返回2
	 * @param stickers
	 * @param target
	 * @return
	 */
	
	
	public static int minStickers(String[] stickers, String target) {
		int ans = process1(stickers, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
	
	public static int process1(String[] stickers, String target) {
		
		if(target.length() == 0) {
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		
		for(String str : stickers) {
			String rest = getRestTarget(str, target);
			if(rest.length() != target.length()) {
				min = Math.min(min, process1(stickers,rest));
			}
		}
		/**
		 * 因为 最后把 target 消完后 返回的是0
		 * 
		 * 所以如果 min不是最大值代表这种方式有效， 最后 需要把自己这一张贴纸的数量加上，
		 * min是最大值的话加0就好了
		 */
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}
	
	public static String getRestTarget(String sticker, String rest) {
		char[] stickerChars = sticker.toCharArray();
		char[] restChars = rest.toCharArray();
		
		int[] count = new int[26];
		
		for(char cur:stickerChars) {
			count[cur - 'a']--;
		}
		
		for(char cur:restChars) {
			count[cur - 'a']++;
		}
		
		StringBuilder string = new StringBuilder();
		
		for(int i = 0; i < 26; i++) {
			if(count[i] > 0) {
				for(int j = 0 ; j < count[i]; j++) {
					string.append((char)(i + 'a'));
				}
			}
		}
		return string.toString();
	}
	
	 public static int minStickers1(String[] stickers, String target) {
		 
		 int N = stickers.length;
		 int[][] stickersCounts = new int[N][26];
		 
		 for(int i = 0; i < N; i++) {
			 char[] sticker = stickers[i].toCharArray();
			 int[] stickerCounts = stickersCounts[i];
			 for(char cur : sticker) {
				 stickerCounts[cur - 'a']++;
			 }
		 }
		 
		 int ans = process2(stickersCounts, target);
		 return ans == Integer.MAX_VALUE ? -1 : ans;
		 
		 
	 }
	 
	 public static int process2 (int[][] stickersCounts, String target) {
		 
		 if(target.length() == 0) {
			 return 0;
		 }
		 
		 int min = Integer.MAX_VALUE;
		 
		 char[] targetChars = target.toCharArray();
		 int[] targetCounts = new int[26];
		 for(char cur : targetChars) {
			 targetCounts[cur-'a']++;
		 }
		 
		 for(int i = 0; i < stickersCounts.length; i++) {
			 int[] stickerCounts = stickersCounts[i];
			 if(stickerCounts[targetChars[0] - 'a'] > 0) {
				 StringBuilder string = new StringBuilder();
				 for(int j = 0; j < 26; j++) {
					 if(targetCounts[j] > 0) {
						 int nums = targetCounts[j] - stickerCounts[j];
						 for(int k = 0; k < nums; k++) {
							 string.append((char)(j + 'a'));
						 }
					 }
				 }
				 String rest = string.toString();
				 if(rest.length() != target.length()) {
					 min = Math.min(min, process2(stickersCounts, rest));
				 }
			 }
		 }

		 return min + (min == Integer.MAX_VALUE ? 0 : 1);
	 }
	 
	 
	 public static int minStickers2(String[] stickers, String target) {
		 
		 int N = stickers.length;
		 int[][] stickersCounts = new int[N][26];
		 
		 for(int i = 0; i < N; i++) {
			 char[] sticker = stickers[i].toCharArray();
			 int[] stickerCounts = stickersCounts[i];
			 for(char cur : sticker) {
				 stickerCounts[cur - 'a']++;
			 }
		 }
		 
		 HashMap<String, Integer> dp = new HashMap<>();
		 dp.put("",0);
		 int ans = process3(stickersCounts, target, dp);
		 return ans == Integer.MAX_VALUE ? -1 : ans;
		 
		 
	 }
	 
	 public static int process3 (int[][] stickersCounts, String target, HashMap<String, Integer> dp) {
		 
		 if(dp.containsKey(target)) {
			 return dp.get(target);
		 }
		 
		 int min = Integer.MAX_VALUE;
		 
		 char[] targetChars = target.toCharArray();
		 int[] targetCounts = new int[26];
		 for(char cur : targetChars) {
			 targetCounts[cur-'a']++;
		 }
		 
		 for(int i = 0; i < stickersCounts.length; i++) {
			 int[] stickerCounts = stickersCounts[i];
			 if(stickerCounts[targetChars[0] - 'a'] > 0) {
				 StringBuilder string = new StringBuilder();
				 for(int j = 0; j < 26; j++) {
					 if(targetCounts[j] > 0) {
						 int nums = targetCounts[j] - stickerCounts[j];
						 for(int k = 0; k < nums; k++) {
							 string.append((char)(j + 'a'));
						 }
					 }
				 }
				 String rest = string.toString();
				 if(rest.length() != target.length()) {
					 min = Math.min(min, process3(stickersCounts, rest, dp));
				 }
			 }
		 }

		 int ans =  min + (min == Integer.MAX_VALUE ? 0 : 1);
		 dp.put(target, ans);
		 return ans;
	 }
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
