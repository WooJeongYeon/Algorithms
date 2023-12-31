import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 210915
 */
public class SW3124_최소스패닝트리_Prim {
	static int V, E;
	static LinkedList<Edge>[] edgeList;
	static boolean[] visit;
	static int[] minEdge;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(in.readLine());

		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(in.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			visit = new boolean[V + 1];
			minEdge = new int[V + 1];
			Arrays.fill(minEdge, INF);
			edgeList = new LinkedList[V + 1];
			for(int i = 1 ; i <= V ; i++) {
				edgeList[i] = new LinkedList<Edge>();
			}
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[start].add(new Edge(end, weight));
			}
			
			long ans = 0;
			int minVertex = 1;
			minEdge[1] = 0;
			for(int i = 1 ; i <= V ; i++) {
				int min = INF;
				for(int j = 1 ; j <= V ; j++) {
					if(!visit[j] && minEdge[j] < min) {
						min = minEdge[j];
						minVertex = j; 
					}
				}
				
				ans += (long)min;
				visit[minVertex] = true;
				
				for(Edge edge : edgeList[minVertex]) {
					if(!visit[edge.e] && minEdge[edge.e] > edge.w) {
						minEdge[edge.e] = edge.w;
					}
				}
			}
			
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	static class Edge {
		int e, w;
		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
}