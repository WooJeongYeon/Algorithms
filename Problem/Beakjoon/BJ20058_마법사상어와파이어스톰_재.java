import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/* 2022.04.29 2시간
 * 오래 걸린 이유
 * 1. 90도 회전하는것도 잘못 이해했고(한칸씩 옮기는줄)
 * 2. 인접한거 체크하는거가 아니라 dfs로 1 이상인 애들 다 체크하는줄 알았고
 * 3. 회전도 반시계방향인줄 알았고
 * 4. 회전 까먹었다가 계속 잘못 이해해서 디버깅만 1시간 한거같다.
 * -> 문제 꼼꼼히 읽고 예제 잘 이해하자... 생각을 잘해ㅠㅠㅠ
 * 이전에 푼거 보니까 더 잘풀었는데..? 2의 제곱수 pow 안쓰고 비트 써서 했고 max가 1인 경우도 체크해주고...
 * 코드가 더 짧아지긴 했는데 미치겠다...ㅠㅠㅠ
 */
public class BJ20058_마법사상어와파이어스톰_재 {
	static int N, Q, total, max, cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] di = {1, 0, -1, 0}; // 아래 오 위 왼
	static int[] dj = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		st = new StringTokenizer(in.readLine());
		for(int k = 0 ; k < Q ; k++) {
			int L = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
			int[][] newMap = new int[N][N];
			for(int i = 0 ; i < N ; i += L) {
				for(int j = 0 ; j < N ; j += L) {
					for(int m = 0 ; m < L ; m++) {
						for(int n = 0 ; n < L ; n++) {
							newMap[i + n][L - m - 1 + j] = map[i + m][j + n];							
						}
					}
				}
			}
			map = newMap;
			
			visited = new boolean[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					cnt = 0;
					for(int d = 0 ; d < 4 ; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if(ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] == 0) continue;
						cnt++;
					}
					if(cnt < 3) {
						visited[i][j] = true;
					}
				}
			}
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(visited[i][j] && map[i][j] > 0) {
						map[i][j]--;
						total--;
					}
				}
			}
		}
		visited = new boolean[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				cnt = 0;
				if(visited[i][j] || map[i][j] == 0) continue;
				dfs(i, j);
				max = Math.max(max, cnt);
			}
		}
		System.out.println(total + "\n" + max);
	}

	private static void dfs(int i, int j) {
		visited[i][j] = true;
		cnt++;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] || map[ni][nj] == 0) continue;
			dfs(ni, nj);
		}
	}
}
