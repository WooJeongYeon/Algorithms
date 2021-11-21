import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.21(재)
 * Level : BaekJoon Gold 5	
 * URL : https://www.acmicpc.net/problem/1753
 * Method : 최단경로(Dijkstra)
 */
public class BJ1753_최단경로_재 {
	static int V, E, startV;
	static int[] minLenArr;
	static LinkedList<Edge>[] edgeList;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startV = Integer.parseInt(in.readLine()) - 1;
		edgeList = new LinkedList[V];
		for(int i = 0 ; i < V ; i++) {
			edgeList[i] = new LinkedList<Edge>();
		}
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1; 
			int e = Integer.parseInt(st.nextToken()) - 1; 
			int w = Integer.parseInt(st.nextToken());
			edgeList[s].add(new Edge(e, w));
		}
		
		dijkstra();
		
		for(int i = 0 ; i < minLenArr.length ; i++) {
			String lenStr = minLenArr[i] + "";
			if(minLenArr[i] == INF) {	// 경로가 존재하지 않는 경우 INF
				lenStr = "INF";
			}
			sb.append(lenStr + "\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void dijkstra() {
		boolean[] visited = new boolean[V];
		minLenArr = new int[V];
		Arrays.fill(minLenArr, INF);
		minLenArr[startV] = 0;
		
		int minLen = 0;
		int minVertex = -1;		
		while(true) {
			minLen = INF;
			minVertex = 0;
			// a단계 : 방문하지 않은 정점들 중 최소 거리 정점 선택
			for(int i = 0 ; i < V ; i++) {
				if(!visited[i] && minLen > minLenArr[i]) {
					minLen = minLenArr[i];
					minVertex = i;
				}
			}
			if(minLen == INF) break;		// 더이상 선택할 수 있는 정점이 없는 경우 중단
			visited[minVertex] = true;
			
			// b단계: current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
			for(Edge e : edgeList[minVertex]) {
				if(!visited[e.end]) {
					minLenArr[e.end] = Math.min(minLenArr[e.end], 
									minLen + e.weight);
				}
			}
		}
		
	}
	
	static class Edge {
		int end, weight;
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}
}
