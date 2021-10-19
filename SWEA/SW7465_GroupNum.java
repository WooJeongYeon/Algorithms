package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210824
 */
public class SW7465_GroupNum {
	static int TC;		// 테스트케이스 수
	static int N, M;	// 마을사람 수, 관계수
	static int ans;		// 답 저장
	static int[] parent;	// 각 마을사람이 속한 집합의 루트
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = N;	// 처음 집합의 개수는 마을사람의 수임
			make();		// 마을사람들에 대해 각각을 집합으로 만듬
			for(int i = 1 ; i <= M ; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(union(a, b)) ans--;		// 두 사람이 합집합을 이룰 수 있다면, 집합 수 1개 감소
			}
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);				// 결과 출력
	}
	static void make() {
		parent = new int[N + 1];
		for(int i = 1 ; i <= N ; i++) {
			parent[i] = i;
		}
	}
	static int find(int a) {
		if(parent[a] == a) return a;		
		return parent[a] = find(parent[a]);	// Path Compression
	}
	static boolean union(int a, int b) {
		int rootA = find(a);			// a의 루트를 찾고
		int rootB = find(b);			// b의 루트를 찾아
		if(rootA == rootB) return false;	// 같다면(같은 집합에 속해있다면) false 반환
		parent[rootA] = rootB;			// a가 속한 집합을 b가 속한 집합에 속하도록 루트의 부모를 바꿈
		return true;					// 합집합을 만들었으므로 true 반환
	}
}
