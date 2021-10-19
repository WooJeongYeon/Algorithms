import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.02
 * Level : BaekJoon 골드5
 * Method : BFS
 * Result : 터짐ㅜㅜ
 * Error1 : 90%가서 시간초과남...
 */
public class BJ17070_파이프옮기기_BFS {
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
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<Pipe> queue = new LinkedList<>();
		queue.offer(new Pipe(0, 1, 0));
		while(!queue.isEmpty()) {
			Pipe now = queue.poll();
			if(now.i == N - 1 && now.j == N - 1) {
				ans++;
				continue;
			}
			for(int d = 0 ; d < DIR ; d++) {
				if(dir[now.kind][d]) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(isOutOfIdx(ni, nj) || map[ni][nj] == 1) continue;
					if(d == 1 && (map[now.i + 1][now.j] == 1 || map[now.i][now.j + 1] == 1)) continue;
					queue.offer(new Pipe(ni, nj, d));
				}
			}
		}
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N; 
	}
	
	static class Pipe {
		int i, j, kind;
		public Pipe(int i, int j, int kind) {
			this.i = i;
			this.j = j;
			this.kind = kind;
		}
	}
}
