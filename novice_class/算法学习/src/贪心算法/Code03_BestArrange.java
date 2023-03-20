package 贪心算法;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 	一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲，给你每一个项目开始的时间和结束的时间
 * 	你来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回最多的宣讲场次
 * @author 
 *
 */
public class Code03_BestArrange {
	
	public static class Program{
		public int start;
		public int end;
		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	
	public static class ProgramComparator implements Comparator<Program>{

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o1.end - o2.end;
		}
	}
	
	public static int bestArrange2(Program[] programs) {
		 Arrays.sort(programs, new ProgramComparator());
		 int timeLine = 0;
		 int result = 0;
		 for(int i = 0; i < programs.length; i++) {
			 Program current = programs[i];
			 if(timeLine <= current.start) {
				 timeLine = current.end;
				 result++;
			 }
		 }
		 return result;
	}
	
	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}
	
	
	// 暴力！所有情况都尝试！
	public static int bestArrange1(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		return process(programs, 0, 0);
	}

	// 还剩下的会议都放在programs里
	// done之前已经安排了多少会议的数量
	// timeLine目前来到的时间点是什么
	
	// 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
	// 返回能安排的最多会议数量
	public static int process(Program[] programs, int done, int timeLine) {
		if (programs.length == 0) {
			return done;
		}
		// 还剩下会议
		int max = done;
		// 当前安排的会议是什么会，每一个都枚举
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) {
				Program[] next = copyButExcept(programs, i);
				max = Math.max(max, process(next, done + 1, programs[i].end));
			}
		}
		return max;
	}

	public static Program[] copyButExcept(Program[] programs, int i) {
		Program[] ans = new Program[programs.length - 1];
		int index = 0;
		for (int k = 0; k < programs.length; k++) {
			if (k != i) {
				ans[index++] = programs[k];
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrange1(programs) != bestArrange2(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
