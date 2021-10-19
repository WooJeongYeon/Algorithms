package day0824;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210824
 */
public class BJ1753_ShortestPath {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int V = Integer.parseInt(st.nextToken()); //정점 갯수
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(in.readLine());
		final int INFINITY = Integer.MAX_VALUE;
		
		Node[] matrix = new Node[V + 1];
		int[] distance = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		
		for(int i=0; i<E; ++i){
			st = new StringTokenizer(in.readLine().trim(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			matrix[s] = new Node(e, w, matrix[s]);
		}
		
		Arrays.fill(distance, INFINITY);
		distance[start] = 0;
		
		int min=0, current=0;
		for(int i=1; i<=V; ++i){
			//a단계 : 방문하지 않은 정점들 중 최소가중치의 정점 선택
			min = INFINITY;
			for(int j=1; j<=V; ++j){
				if(!visited[j] && distance[j] < min){
					min = distance[j];
					current = j;
				}
			}
			visited[current] = true; // 선택 정점 방문 처리
			
			//b단계: current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
			for(Node node = matrix[current] ; node != null ; node = node.link) {
				if(!visited[node.idx] && distance[node.idx] > min+node.w){
					distance[node.idx] = min+node.w;
				}
			}
		}
		for(int i = 1 ; i <= V ; i++) {
			if(distance[i] == INFINITY) System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}
	
	static class Node {
		int idx, w;
		Node link;
		public Node(int idx, int w, Node link) {
			super();
			this.idx = idx;
			this.w = w;
			this.link = link;
		}
	}

}
