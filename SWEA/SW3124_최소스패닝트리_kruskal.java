import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210915
 */
public class SW3124_최소스패닝트리_kruskal {
	static int V, E;
	static Edge[] edgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(in.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(in.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(start, end, weight);
			}
			
			Arrays.sort(edgeList);
			make();
			
			long ans = 0;
			int eNum = 0;
			for(Edge e : edgeList) {
				if(union(e.s, e.e)) {
					ans += (long)e.w;
					if(++eNum == V - 1) break;
				}
			}

			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	static void make() {
		parents = new int[V + 1];
		for(int i = 0 ; i <= V ; i++) {
			parents[i] = i;
		}
	}
	static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	static boolean union(int s, int e) {
		int sRoot = find(s);
		int eRoot = find(e);
		if(sRoot == eRoot) return false;
		parents[sRoot] = eRoot;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}