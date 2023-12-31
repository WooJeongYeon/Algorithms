import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Date : 2021.09.30
 * Plus : 교수님꺼 - 다익스트라로도 풀 수 있대
 */

public class SW1249_보급로_Dijkstra {
	static int TC, N, map[][];
	static final int INF = Integer.MAX_VALUE;
	static int[] di = { -1, 1, 0, 0 }; // 델타배열
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) { // TC만큼 반복
			N = Integer.parseInt(in.readLine());

			map = new int[N][N];

			for (int i = 0; i < N; i++) { // 배열 입력받음
				 char[] ch = in.readLine().toCharArray();
				 for(int j = 0 ; j < N ; j++) {
					 map[i][j] = ch[j] - '0';
				 }
			}

			sb.append("#" + tc + " " + dijkstra(0, 0) + "\n");
		}
		System.out.println(sb); // 답 출력
	}
	
	static int dijkstra(int startR, int startC) {
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		
		// 최소값이 갱신되도록 큰값으로 초기화
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[startR][startC] = 0;
		
		int r = 0, c = 0, minCost = 0, nr, nc;
		while(true) {
			minCost = INF;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(!visited[i][j] && minCost > minTime[i][j]) {
						minCost = minTime[i][j];
						r = i;
						c = j;
					}
				}
			}
			visited[r][c] = true;
			if(r == N - 1 && c == N - 1) return minCost;
			
			// step2 : step1에서 선택된 정점을 경유지로 해서 인접정점 따져보기
			// 이 문제에서는 인접정점 4가지
			
			for(int d = 0 ; d < 4 ; d++) {
				nr = r + di[d];
				nc = c + dj[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minTime[nr][nc] > minCost + map[nr][nc]) {
					minTime[nr][nc] = minCost + map[nr][nc];
				}
			}
		}
	}
}
