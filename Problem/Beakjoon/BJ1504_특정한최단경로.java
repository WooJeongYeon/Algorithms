package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210829
 */
public class BJ1504_특정한최단경로 {
	static int N, E;
	static int v1, v2;
	static Node[] list;
	static int[] minEdge;
	static boolean[] visited;
	static final int INFINITY = Integer.MAX_VALUE;
	static int result1, result2, result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new Node[N + 1];
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[start] = new Node(end, weight, list[start]);	// 양방향이므로 두 방향 모두 포함시킴
			list[end] = new Node(start, weight, list[end]);
		}
		st = new StringTokenizer(in.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		// 1 -> v1 -> v2 -> N인 경우
		if(!dijkstra(1, v1) || !dijkstra(v1, v2) || !dijkstra(v2, N))
			result1 = INFINITY;
		else result1 = result;
		result = 0;
		// 1 -> v2 -> v1 -> N인 경우
		if(!dijkstra(1, v2) || !dijkstra(v2, v1) || !dijkstra(v1, N))
			result2 = INFINITY;
		else result2 = result;
		result = Integer.min(result1, result2);
		if(result == INFINITY) result = -1;
		System.out.println(result);
	}
	
	static boolean dijkstra(int start, int end) {
		int current = -1;
		minEdge = new int[N + 1];
		visited = new boolean[N + 1];
		Arrays.fill(minEdge, INFINITY);
		minEdge[start] = 0;
		while(true) {
			int min = INFINITY;
			// 가장 최소거리를 가지는 정점을 포함시킴
			for(int i = 1 ; i <= N ; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					current = i;
				}
			}
			// 더 이상 각 정점까지의 최소거리가 갱신이 안된다면 false 반환
			if(min == INFINITY) return false;
			// 해당 정점이 end 정점이라면 최소거리 더함
			if(current == end) {
				result += min;
				return true;
			}
			visited[current] = true;
			// 포함시킨 정점을 기준으로 최소거리 갱신
			for(Node node = list[current] ; node != null ; node = node.link) {
				if(!visited[node.end] && minEdge[node.end] > min + node.weight) {
					minEdge[node.end] = min + node.weight;
				}
			}
		}
		
	}
	
	static class Node {
		int end, weight;
		Node link;
		public Node(int end, int weight, Node link) {
			this.end = end;
			this.weight = weight;
			this.link = link;
		}
	}
}
