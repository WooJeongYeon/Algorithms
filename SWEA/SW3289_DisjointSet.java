package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210824
 */
public class SW3289_DisjointSet {
	static int[] parents;
	static int TC;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			make();
			
			sb.append("#" + tc + " ");
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(in.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(command == 0) {
					union(a, b);
				} else {
					if(find(a) == find(b)) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void make() {			// 각 요소를 집합으로 만듬
		parents = new int[N + 1];
		for(int i = 1 ; i <= N ; i++) {
			parents[i] = i;		
		}
	}
	static int find(int n) {		// 해당 요소가 속한 집합의 루트 요소를 반환(어떤 집합에 속하는지)
		if(n == parents[n]) return n;
		return parents[n] = find(parents[n]);
	}
	static boolean union(int n, int m) {	// 두 요소가 속한 집합을 합함
		int np = find(n);
		int mp = find(m);
		
		if(np == mp) return false;
		parents[np] = mp;
		return true;
	}
}
