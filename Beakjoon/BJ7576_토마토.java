import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Date : 210924
 * Thinking : bfs! 1 : 익은토마토, 0 : 익지 않은 토마토, -1 : 토마토가 들어있지 않음 
 * 			출력 - 모든 토마토가 익을 때까지의 최소날짜, 처음부터 모든 토마토가 익음 0, 모든 토마토가 못익음 -1
 * Method : bfs로 토마토 익은 토마토로 계속 변경시키기(size로 for문)
 */

public class BJ7576_토마토 {
	static int N, M;
	static int[][] map;
	static Queue<int[]> queue;
	static int result;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)							// 처음에 익은 토마토를 큐에 추가해놓음
					queue.offer(new int[] {i, j});
			}
		}
		while(!queue.isEmpty()) {							// 큐가 비거나
			if(isAllTomatoRed()) break;						// 모든 토마토가 익었으면 중단
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {				// 매번 queue 크기만큼 반복해 일수를 증가시킴
				int[] now = queue.poll();
				for(int d = 0 ; d < 4 ; d++) {
					int ni = now[0] + di[d];
					int nj = now[1] + dj[d];
					if(!isOutOfInedx(ni, nj) && map[ni][nj] == 0) {	// 인덱스를 벗어나지 않고 아직 안익은 토마토라면
						map[ni][nj] = 1;							// 1로 만들고
						queue.offer(new int[] {ni, nj});			// 큐에 추가
					}
				}
			}
			result++;
		}
		if(!isAllTomatoRed()) result = -1;					// 가능한 토마토들은 모두 익었지만 아직 안익은 토마토가 있다면 불가능
		
		System.out.println(result);
	}
	
	static boolean isOutOfInedx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= M;
	}
	static boolean isAllTomatoRed() {						// 모든 토마토가 익었는지를 반환
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
}
