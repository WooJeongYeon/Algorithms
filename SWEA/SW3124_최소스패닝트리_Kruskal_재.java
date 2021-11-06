import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.06(재)
 * Level : SWEA D4
 * Time : 30분
 * URL : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
 * Select1 : 
 * Thinking : 
 * Method : 
 * Help : 
 * Error1 : 
 * Result : 
 * Plus1 : 
 */
public class SW3124_최소스패닝트리_Kruskal_재 {
	static int TC, V, E;
	static long ans;
	static ArrayList<Edge> edges; 
	static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		edges = new ArrayList<Edge>();
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			for(int i = 0 ; i < E ; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int s = Integer.parseInt(st.nextToken()); 
				int e = Integer.parseInt(st.nextToken()); 
				int w = Integer.parseInt(st.nextToken()); 
				edges.add(new Edge(s, e, w));
			}
			Collections.sort(edges);
			make();
			
			ans = kruskal();
			sb.append("#" + tc + " " + ans + "\n");
			edges.clear();
		}
		
		System.out.println(sb);
	}
	
	static long kruskal() {
		int cnt = 0;
		long sum = 0;
		for(Edge e : edges) {
			if(union(e.start, e.end)) {
				sum += e.weight;
				if(++cnt == V - 1) {
					break;
				}
			}
		}
		return sum;
	}
	
	static void make() {
		parents = new int[V + 1];
		for(int i = 0 ; i <= V ; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return false;
		parents[parentA] = parentB;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
//			return this.weight - o.weight;
		}
	}
}
