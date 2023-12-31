package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210823
 */
public class BJ1260_DFSBFS {
	static int N, M, V;
	static boolean[][] matrix;
	static boolean[] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());			// 정점의 개수
		M = Integer.parseInt(st.nextToken());			// 간선의 개수
		V = Integer.parseInt(st.nextToken());			// 출발점
		matrix = new boolean[N + 1][N + 1];				// 그래프
		visited = new boolean[N + 1];					// 해당 정점을 방문했는지 여부를 저장
		for(int i = 0 ; i < M ; i++) {					
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			matrix[from][to] = true;					// 양방향이므로 양쪽 다 true로 저장해줌
			matrix[to][from] = true;
		}
		DFS(V);											// DFS 실행
		sb.append("\n");
		BFS();											// BFS 실행
		sb.append("\n");
		
		System.out.println(sb);							// 결과 출력
	}
	static void DFS(int current) {						// DFS(재귀 사용)
		visited[current] = true;						// 해당 정점에 방문했음을 표시
		sb.append(current + " ");
		for(int i = 1 ; i <= N ; i++) {					// 모든 정점에 대해 검사
			if(matrix[current][i] && !visited[i]) {		// 간선이 존재하고, 아직 방문하지 않았다면
				DFS(i);									// 해당 정점에 대해 DFS 재귀함수 호출
			}
		}
	}
	static void BFS() {									// BFS(큐 사용)
		boolean[] visited = new boolean[N + 1];		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V);									// 큐에 시작 요소를 넣음
		visited[V] = true;								// 시작 요소에 방문했음을 표시
		while(!queue.isEmpty()) {						// 큐가 전부 빌 때까지 반복
			int current = queue.poll();					// 큐의 첫번째 요소를 꺼냄
			sb.append(current + " ");					// 결과에 방문 요소 추가
			for(int i = 1 ; i <= N ; i++) {				// 모든 정점에 대해 검사
				if(matrix[current][i] && !visited[i]) {	// 간선이 존재하고, 아직 방문하지 않았다면
					visited[i] = true;					// 해당 요소에 방문했음을 표시(중복해서 방문하지 않도록)
					queue.offer(i);						// 큐에 방문할 요소를 넣음
				}
			}
		}
	}
}
