import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
/*
 * Date : 2021.09.30
 * Level : SWEA D3
 * Method : BFS, PQ
 */
public class SW1249_보급로_BFS_PQ {
	static int TC, N;
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
				Arrays.fill(memo[i], Integer.MAX_VALUE); // 현재 위치까지의 최소 ㄱㅄ을 저장하기 위해 최댓값으로 저장
			}

			bfs(); // bfs 시작

			sb.append("#" + tc + " " + memo[N - 1][N - 1] + "\n");
		}
		System.out.println(sb); // 답 출력
	}

	static void bfs() { // bfs
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		memo[0][0] = 0;
		pq.offer(new Point(0, 0));
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			for (int d = 0; d < 4; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				
				if (isOutOfIdx(ni, nj))	continue;
				int tmp = memo[now.i][now.j] + (map[ni][nj] - '0');
				if (memo[N - 1][N - 1] <= tmp || memo[ni][nj] <= tmp) continue;
				memo[ni][nj] = tmp;
				pq.offer(new Point(ni, nj));
			}
		}
	}
	
	static class Point implements Comparable<Point> {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public int compareTo(Point o) {						// 우선순위큐에서 내부적으로 정렬시키기 위함
			return memo[this.i][this.j] - memo[o.i][o.j];
		}
	}

	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
}
