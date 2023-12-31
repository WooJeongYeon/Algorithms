package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210825
 */
public class BJ1976_여행가자 {
	static int N, M;			// 도시의 수, 여행 계획중인 도시의 수
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		parent = new int[N + 1];	// 각 도시가 속한 집합을 나타냄
		String result = null;
		make();						// 각 도시를 단일집합으로 만듬
		for(int i = 1 ; i <= N ; i++) {	
			st = new StringTokenizer(in.readLine());
			for(int j = 1 ; j <= N ; j++) {
				if(st.nextToken().equals("1")) union(i, j);	// 해당 간선이 있으면 두 도시를 이음(같은 집합으로)
			}
		}
		st = new StringTokenizer(in.readLine());
		int p = find(Integer.parseInt(st.nextToken()));	// 여행 계획중인 첫 도시가 어느 집합에 속해있는지를 저장
		for(int m = 1 ; m < M ; m++) {					
			int n = Integer.parseInt(st.nextToken());
			if(p != find(n)) {							// 계획된 도시 중 길이 없는 도시(같은 집합이 아님)가 있다면
				result = "NO";							// 여행 불가
				break;
			}
		}
		if(result == null) result = "YES";				// 아직 null이면 여행 가능
		System.out.println(result);
		
	}
	
	static void make() {
		for(int i = 1 ; i <= N ; i++)
			parent[i] = i;
	}
	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) parent[rootA] = rootB;
	}
}
