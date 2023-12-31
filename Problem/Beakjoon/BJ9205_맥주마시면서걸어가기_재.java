import java.util.Arrays;
import java.util.Scanner;
/*
 * Date : 2021.12.12(재)
 * Level : BaekJoon Sliver 1
 * URL : https://www.acmicpc.net/problem/9205
 * Method : Floyd Warshall(최단거리)
 * Error1 : 더하면서 INF가 int범위 넘어가버려서 답이 안나왔었네
 * Result : 어떻게 구현해야할지 고민을 좀 했음
 */
public class BJ9205_맥주마시면서걸어가기_재 {
	static int TC, N;
	static int[][] minArr;
	static Point[] beers;
	static final int MAX_LEN = 1000;
	static final int INF = 100000000;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		TC = in.nextInt();
		for(int tc = 0 ; tc < TC ; tc++) {
			N = in.nextInt() + 2;
			beers = new Point[N];
			minArr = new int[N][N];
			for(int n = 0 ; n < N ; n++) {
				int j = in.nextInt();
				int i = in.nextInt();
				beers[n] = new Point(i, j);
				Arrays.fill(minArr[n], INF);
				minArr[n][n] = 0;
			}
			
			makeLen();
			Floyd_Warshall();
			
			if(minArr[0][N - 1] == INF)
				sb.append("sad\n");
			else sb.append("happy\n");
		}
		System.out.println(sb);
	}
	private static void Floyd_Warshall() {
		for(int k = 0 ; k < N ; k++) {
			for(int i = 0 ; i < N ; i++) {
				if(i == k) continue;
				for(int j = 0 ; j < N ; j++) {
					if(j == i || j == k) continue;
					minArr[i][j] = Math.min(minArr[i][j], minArr[i][k] + minArr[k][j]);
				}
			}
		}
	}
	private static void makeLen() {
		for(int i = 0 ; i < N - 1 ; i++) {
			for(int j = i + 1 ; j < N ; j++) {
				int len = Math.abs(beers[i].i - beers[j].i) + Math.abs(beers[i].j - beers[j].j);
				if(len <= MAX_LEN) {
					minArr[i][j] = len;
					minArr[j][i] = len;
				} 
			}
		}
	}
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
