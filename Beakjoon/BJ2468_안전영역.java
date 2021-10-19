import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 210922
 * Thinking : 아무것도 안잠겼을 때의 안전영역 개수는 1, 최소높이부터 최대높이까지 잠기게 하면서 시뮬돌리기
 * 			높이를 매개변수로 줄지 vs map에 표시해줄지(이거 선택)
 * Method : 최소높이부터 최대높이 - 1까지 잠기게 하면서 모든 칸에 대해 dfs를 돌려 안전영역 개수를 구해 최댓값을 저장
 */
public class BJ2468_안전영역 {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int minH, maxH, areaNum, ans;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		minH = Integer.MAX_VALUE;
		maxH = Integer.MIN_VALUE;
		ans = 1;						// 아무 영역도 잠기지 않았을 때의 안전영역 개수는 1이므로
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minH = Math.min(minH, map[i][j]);
				maxH = Math.max(maxH, map[i][j]);
			}
		}
		for(int h = minH ; h < maxH ; h++) {
			areaNum = 0;
			sinkMap(h);
			visit = new boolean[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(!visit[i][j] && map[i][j] != 0) {
						areaNum++;
						dfs(i, j);
					}
				}
			}
			ans = Math.max(ans, areaNum);
		}
		System.out.println(ans);
	}
	static void dfs(int i, int j) {
		visit[i][j] = true;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(!isOutOfIndex(ni, nj) && !visit[ni][nj] && map[ni][nj] != 0) {
				dfs(ni, nj);
			}
		}
	}
	
	static boolean isOutOfIndex(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static void sinkMap(int h) {					// 해당 높이 밑의 영역들을 0으로 표시(중복 0으로 되는 것은 있음)
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] <= h)
					map[i][j] = 0;
			}
		}
	}
}
