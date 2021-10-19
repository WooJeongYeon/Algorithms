package day0812;
// 조합 bit로 푸는거 진짜 비효율적

import java.util.Scanner;
/*
 * Date : 210812
 */
public class BJ3040_Dwarf7_Boolean {
	static int[] heights;
	static boolean[] checked;
	static int N = 9;
	static int R = 7;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		heights = new int[N];
		checked = new boolean[N];
		for(int i = 0 ; i < N ; i++)
			heights[i] = in.nextInt();
		subset(0, 0);
	}
	static void subset(int idx, int cnt) {
		if(cnt == R) {
			int sum = 0;
			for(int i = 0 ; i < N ; i++)
				if(checked[i] == true)
					sum += heights[i];
			if(sum == 100) {
				for(int i = 0 ; i < N ; i++)
					if(checked[i] == true)
						System.out.println(heights[i]);
				
			}
			return;
		}
		if(idx == N) {
			return;
		}
		checked[idx] = true;
		subset(idx + 1, cnt + 1);
		checked[idx] = false;
		subset(idx + 1, cnt);
	}
}