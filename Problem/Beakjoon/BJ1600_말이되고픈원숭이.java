
// 스킬을 먼저 많이 쓰는게 나은지, 아껴두는게 나은지 모름
// 스킬 완전히 똑같이 쓴 애들을 제낌
// ...? 2차원배열로 되는디? 그 스킬 적게 쓴애로... 근데 나 안되던데
// 먼저 온 애가 무조건 이동시간이 빠를 거라는...?(먼저온 애가 먼저 기록 -> 나중에 온 애는...스킬을...음)
// 나중에 온 애가 스킬을 더 적게 썼으면 기록함 -> 늦게오긴 했지만 나중에 될 가능성이 있음
// 스킬을 그것보다 많이 써서 온 애는 컷해줌
// 뭐 잘못했어서 안됬던거임
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210924
 */
public class BJ1600_말이되고픈원숭이 {
	static int K;
	static int H, W;
	static int ans;
	static int[][] map, memo;
	static int[] di = {-1, 1, 0, 0, 1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dj = {0, 0, -1, 1, 2, 1, -1, -2, -2, -1, 1, 2};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		memo = new int[H][W];
		for(int i = 0 ; i < H ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < W ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j] = -1;
			}
		}
		if(H == 1 && W == 1) ans = 0;			// 얘도 있어야하네... 빼니까 100%에서 틀렸다 뜸
		else ans = bfs();
		System.out.println(ans);
	}
	static int bfs() {
		int time = 0;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, K));
		while(!queue.isEmpty()) {
			time++;
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {	
				Point now = queue.poll();
				int k = now.k;
				for(int d = 4 ; d < 12 ; d++) {			// 대각선으로 이동되는지
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					// 인덱스 벗어나거나, 저장되있는 남은 최대 이동횟수보다 현재 나의 남은 이동횟수가 더 적거나,
					// 이동횟수가 없거나, 이동 불가한 지점이면 패스
					if(isOutOfArray(ni, nj) || memo[ni][nj] >= k - 1 || k == 0 || map[ni][nj] == 1) continue;
					if(ni == (H - 1) && nj == (W - 1))  {		// 도착했으면 bfs 중단
						return time;
					}
					memo[ni][nj] = k - 1;
					queue.offer(new Point(ni, nj, k - 1));
				}
				for(int d = 0 ; d < 4 ; d++) {			// 상하좌우로 이동되는지
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(isOutOfArray(ni, nj) || memo[ni][nj] >= k || map[ni][nj] == 1) continue;
					if(ni == (H - 1) && nj == (W - 1)) {
						return time;
					}
					memo[ni][nj] = k;
					queue.offer(new Point(ni, nj, k));
				}
			}
		}
		
		return -1;
	}
	
	static boolean isOutOfArray(int i, int j) {
		return i < 0 || i >= H || j < 0 || j >= W;
	}
	
	static class Point {
		int i, j, k;
		public Point(int i, int j, int k) {
			this.i = i;
			this.j = j;
			this.k = k;
		}
	}
}
