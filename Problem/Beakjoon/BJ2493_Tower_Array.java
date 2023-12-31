package day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210805
 */
public class BJ2493_Tower_Array {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		Match[] match = new Match[500001];
		int top = 0;
		System.out.print("0 ");
		int max = Integer.parseInt(st.nextToken());
		match[top++] = new Match(1, max);
		int idx = -1;
		for(int i = 2 ; i <= N ; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(max <= x) {
				top = 0;
				match[top++] = new Match(i, x);
				max = x;
				idx = 0;
			}
			else {
				while(true) {
					Match m = match[top - 1];
					if(m.value > x) {
						match[top++] = new Match(i, x);
						idx = m.idx;
						break;
					}		
					top--;
				}				
			}
			
			System.out.print(idx + " ");
		}
	}
	public static class Match{
		public int idx;
		public int value;
		public Match(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}
}
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		int[] tower = new int[500001];
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(in.readLine());
//		StringTokenizer st = new StringTokenizer(in.readLine());
//		for(int i = 1 ; i <= N ; i++) {
//			tower[i] = Integer.parseInt(st.nextToken());
//			int ans = 0;
//			for(int j = 1 ; j <= i - 1 ; j++) {
//				int index = i - j;
//				if(tower[index] > tower[i]) {
//					ans = index;
//					break;
//				}
//			}
//			System.out.print(ans + " ");
//		}		
//	}
