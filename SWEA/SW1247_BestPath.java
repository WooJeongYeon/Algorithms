package day0821;

import java.util.Scanner;
/*
 * Date : 210821
 */
public class SW1247_BestPath {
	static int TC;
	static int N;
	static Point com, house;
	static Point[] client;
	static Point[] result;
	static boolean[] select;
	static int min;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		TC = in.nextInt();
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = in.nextInt();
			client = new Point[N]; 
			result = new Point[N]; 
			select = new boolean[N]; 
			com = new Point(in.nextInt(), in.nextInt());
			house = new Point(in.nextInt(), in.nextInt());
			min = Integer.MAX_VALUE;
			for(int i = 0 ; i < N ; i++) {
				client[i] = new Point(in.nextInt(), in.nextInt());
			}
			perm(0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	static void perm(int idx) {
		if(idx == N) {
			int dis = Math.abs(com.i - result[0].i) + Math.abs(com.j - result[0].j);
			for(int i = 1 ; i < N ; i++) {
				dis += Math.abs(result[i].i - result[i - 1].i) + Math.abs(result[i].j - result[i - 1].j);
			}
			dis += Math.abs(house.i - result[N - 1].i) + Math.abs(house.j - result[N - 1].j);
			min = Math.min(min, dis);
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(select[i]) continue;
			select[i] = true;
			result[idx] = client[i];
			perm(idx + 1);
			select[i] = false;
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
