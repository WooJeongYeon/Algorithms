import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210912
 */
public class BJ1303_전쟁전투_DFS {
	static int me, you;
	static int N, M;
	static char[][] map;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int sum = 0;
	static char now;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == '-') continue;		// 이미 방문했으면 패스
				now = map[i][j];					// 현재 팀
				sum = 0;							// 병사의 개수
				bfs(i, j);							// DFS 시작
				
				sum = sum * sum;					// 위력 계산
				//System.out.println(now + " " + sum);
				if(now == 'W') me += sum;			// 해당 팀에 위력을 더함
				else you += sum;
			}
		}
		
		System.out.println(me + " " + you);
	}
	static void bfs(int i, int j) {					// DFS표시
		sum++;										// 병사의 개수 증가
		map[i][j] = '-';							// 방문 표시
		for(int d = 0 ; d < 4 ; d++) {				// 상하좌우에 대해
			int ni = i + di[d];
			int nj = j + dj[d];
			if(isPossible(ni, nj) && map[ni][nj] == now)	// 배열 범위 내에 있고, 같은 팀이라면
				bfs(ni, nj);								// DFS!
		}
	}
	static boolean isPossible(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < M; 
	}
}
