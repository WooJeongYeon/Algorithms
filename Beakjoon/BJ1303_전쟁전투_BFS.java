import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210912
 */
public class BJ1303_전쟁전투_BFS {
	static int me, you;
	static int N, M;
	static char[][] map;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < N ; i++) {						// 모든 칸에 대해 시작
			for(int j = 0 ; j < M ; j++) {					
				if(map[i][j] == '-') continue;				// 방문한 칸이면 패스
				char now = map[i][j];						// 현재칸의 팀을 저장
				int sum = 1;								// 병사의 수
				map[i][j] = '-';							// 시작 칸을 방문표시 하고
				Queue<Point> queue = new LinkedList<>();
				queue.offer(new Point(i, j));				// 첫 시작칸을 큐에 넣음
				while(!queue.isEmpty()) {					// BFS~ 큐가 빌때까지 진행
					Point nowP = queue.poll();				// 큐의 요소를 꺼내
					for(int d = 0 ; d < 4 ; d++) {			// 상하좌우로 검사
						int ni = nowP.i + di[d];
						int nj = nowP.j + dj[d];
						if(isPossible(ni, nj) && map[ni][nj] == now) {	// 해당 칸이 갈 수 있고, 같은 팀이라면
							sum++;							// 병사 증가
							map[ni][nj] = '-';				// 방문 표시
							queue.offer(new Point(ni, nj));	// 큐에 넣음
						}
					}
				}
				
				sum = sum * sum;
				//System.out.println(now + " " + sum);
				if(now == 'W') me += sum;
				else you += sum;
			}
		}
		
		System.out.println(me + " " + you);
	}
	static boolean isPossible(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < M; 
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
