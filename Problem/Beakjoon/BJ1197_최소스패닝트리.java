// MST - prim 알고리즘 사용(간선의 개수가 많아서)
package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210829
 */
public class BJ1197_최소스패닝트리 {
	static int V, E;					// 정점 개수, 간선 개수
	static Node[] vertexList;			// 인접리스트
	static int[] minArr;				// 최소 길이의 간선 저장
	static boolean[] visit;				// 해당 정점을 방문했는지를 저장
	static int ans;
	static int INFINITY = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		vertexList = new Node[V + 1];
		minArr = new int[V + 1];
		Arrays.fill(minArr, INFINITY);
		visit = new boolean[V + 1];
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken()); 
			int end = Integer.parseInt(st.nextToken()); 
			int weight = Integer.parseInt(st.nextToken());
			vertexList[start] = new Node(vertexList[start], end, weight);		// 양방향 그래프이므로 양쪽으로 저장
			vertexList[end] = new Node(vertexList[end], start, weight);
		}
		
		int current = -1;
		minArr[1] = 0;
		for(int i = 1 ; i <= V ; i++) {
			// 최소값을 가지는 정점을 찾음
			int min = INFINITY;
			for(int j = 1 ; j <= V ; j++ ) {
				if(!visit[j] && minArr[j] < min) {
					min = minArr[j];
					current = j;
				}
			}
			ans += min;
			visit[current] = true;	// 해당 정점을 포함시키고 지나갔음을 표시
			
			// 포함시킨 정점을 기준으로 최소 간선을 갱신
			for(Node n = vertexList[current] ; n != null ; n = n.next) {
				if(!visit[n.end] && minArr[n.end] > n.weight)
					minArr[n.end] = n.weight;
			}
		}
		
		System.out.println(ans);
	}
	static class Node {
		Node next;
		int end;
		int weight;
		public Node(Node next, int end, int weight) {
			this.next = next;
			this.end = end;
			this.weight = weight;
		}
	}
}
