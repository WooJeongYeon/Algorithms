import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.02
 * Level : BaekJoon 골드5
 * Method : DFS
 * Result : 터질까 걱정했는데 안터지네.. 이문제 DP로도 풀 수 있다고 하셨다.
 * Error1 : 문제 덜읽음 - 벽 있네
 * Error2 : 문제 덜읽음 - 대각선 이동 경우, 가려는 칸의 상, 좌가 빈칸이어야 함
 */
public class BJ17070_파이프옮기기_DFS {
	static int N, ans;
	static int[][] map;
	static boolean[][] dir = {{true, true, false}, {true, true, true}, {false, true, true}};
	static int[] di = {0, 1, 1};	// 오, 오아래, 아래
	static int[] dj = {1, 1, 0};
	static final int DIR = 3;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int i, int j, int pipe) {
		if(i == N - 1 && j == N - 1) {
			ans++;
			return;
		}
		for(int d = 0 ; d < DIR ; d++) {
			if(dir[pipe][d]) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(isOutOfIdx(ni, nj) || map[ni][nj] == 1) continue;
				if(d == 1 && (map[i + 1][j] == 1 || map[i][j + 1] == 1)) continue;
				dfs(ni, nj, d);
			}
		}
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N; 
	}
}
