import java.util.*;
/*
 * Date : 2021.12.24
 * Level : Programmers level 2
 * Difficulty : 중
 * Time : 1h 
 * URL : https://programmers.co.kr/learn/courses/30/lessons/86052
 * Thinking : 각 칸에서는 4가지 방향으로 나갈 수 있음 -> 칸개수 * 4만큼 경로 존재 -> visit 체크
 * 			- 방문 안한 경로이면
 * 			1) 해당 경로 방문처리 + 개수증가
 * 			2) 해당 방향으로 위치 이동
 * 			3) 방향 갱신
 * Method : 배열
 * Result : 전에 잠깐 보고 던졌었는데 다시 보니까 생각보다 풀만했다
 */
public class PRO86052_빛의경로사이클 {
	class Solution {
		int[] di = { -1, 0, 1, 0 };	// 상 - 우 - 하 - 좌
		int[] dj = { 0, 1, 0, -1 };
		public int[] solution(String[] grid) {
			int M = grid.length;		// 행 크기
			int N = grid[0].length();	// 열 크기
			int size = M * N;	// 배열 M * N
			PriorityQueue<Integer> lenList = new PriorityQueue<>();	// 사이클 길이 저장할 pq
			char[][] map = new char[M][];
			boolean[][] visited = new boolean[size][4];
			for (int i = 0; i < M; i++) {
				map[i] = grid[i].toCharArray();
			}

			for (int idx = 0; idx < size; idx++) {
				for (int d = 0; d < 4; d++) {
					if (visited[idx][d])		// 방문한 곳이면 패스
						continue;
					int ni = idx / N;			// 인덱스 -> 행/열로 변환
					int nj = idx % N;
					int cnt = 0;				// 사이클 길이 세기
					while (!visited[ni * N + nj][d]) {		// 방문한 경로 나올 때까지 반복
						visited[ni * N + nj][d] = true;		// 방문처리
						cnt++;
						ni = (ni + di[d] + M) % M;			// 해당 방향으로 위치 이동
						nj = (nj + dj[d] + N) % N;
						switch (map[ni][nj]) {				// 방향 전환			
						case 'L':		
							d = (d - 1 + 4) % 4;
							break;
						case 'R':
							d = (d + 1) % 4;
							break;
						}
					}

					lenList.offer(cnt);			// pq에 사이클 길이 추가
				}
			}

			int[] answer = new int[lenList.size()];		// int 배열로 저장
			int idx = 0;
			while (!lenList.isEmpty()) {
				answer[idx++] = lenList.poll();
			}
			return answer;
		}
	}
}
