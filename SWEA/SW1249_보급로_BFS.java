import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Date : 2021.09.30
 * Level : SWEA D3
 * Method : BFS
 */

public class SW1249_보급로_BFS {
	static int TC, N; // 배열 크기, tc번호, 답
	static char[][] map;
	static int[][] memo;
	static int[] di = { -1, 1, 0, 0 }; // 델타배열
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) { // TC만큼 반복
			N = Integer.parseInt(in.readLine());

			map = new char[N][N];
			memo = new int[N][N];

			for (int i = 0; i < N; i++) { // 배열 입력받음
				map[i] = in.readLine().toCharArray();
				Arrays.fill(memo[i], Integer.MAX_VALUE); // 현재 위치까지의 최소 합을 저장하기 위해 최댓값으로 저장
			}

			bfs(); // map[0][0]부터 dfs 시작

			sb.append("#" + tc + " " + memo[N - 1][N - 1] + "\n");
		}
		System.out.println(sb); // 답 출력
	}

	static void bfs() { // dfs - 현재 인덱스들, 현재 위치까지의 합
		Queue<Point> queue = new LinkedList<Point>();
		memo[0][0] = 0;
		queue.offer(new Point(0, 0));
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if (isOutOfIdx(ni, nj))	continue;
				int tmp = memo[now.i][now.j] + (map[ni][nj] - '0');
				if (memo[N - 1][N - 1] <= tmp || memo[ni][nj] <= tmp) continue;
				memo[ni][nj] = tmp;
				queue.offer(new Point(ni, nj));
			}
		}
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
}
