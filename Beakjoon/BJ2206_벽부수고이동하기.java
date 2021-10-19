
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Date : 2021.09.08 ~ 2021.09.30
 * Level : BaekJoon
 * Thinking : 생각하는게 어려운듯. 배열을 3차원까지도 생각해볼 필요가 있네..
 * Method : Logic3
 * Logic1 : 벽 전부 저장해두고 조합으로 벽 1개씩 선택해 각 경우를 bfs로 시뮬돌림 -> 시간초과
 * Logic2 : 인접리스트로 Dijkstra 돌리는거 생각만 함 -> 근데 전부 해보는 논리랑 똑같으니까... 안될듯
 * Logic3 : bfs를 먼저 돌려서 가장자리에 있는 벽들만 갈 수 있음을 표시(최소거리 저장) + 시작점부터 위치까지의 최소거리들 저장
 * 			그리고 가능한 벽을 하나씩 선택해 해당 벽부터 시작해 체크하면서 최소거리 저장	-> 시간초과...ㅠㅠㅠ. 여쭤보니까 좀 그리디한 방법이래
 * 			됬다!!!! 벽 하나씩 없애고 최소거리 구하는 부분에서 저장된 최소거리보다 작을떄 하도록 안하고 계속 최소거리 갱신하고있었음
 * Logic4 : 원숭이 문제처럼 출발지부터 도착지까지 가면서 벽 없앨 수 있는 능력 횟수를 같이 Point클래스에 저장해 
 * 			visited 이차원 배열에 현재 칸까지의 능력 사용 최소 횟수가 저장되도록 함(1, 0) 
 * Result : 3번째 방법이 더 시간 덜걸린데 코드가 미침
 * Help : (Logic3 이후)원숭이 문제랑 똑같대..!!! visit 체크만 하면 된다구!(근데 공책에 원숭이랑 비슷하지 않을까 써놨는데...ㅋㅋㅋㅋㅋ)
 */

public class BJ2206_벽부수고이동하기 {
	static int N, M;
	static char[][] map;
	static int[][] minDisMap;
	static ArrayList<Point> walls;
	static int ans;
	static final int MAX = Integer.MAX_VALUE;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		minDisMap = new int[N][M];						// 해당 칸까지 최
		walls = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {				// 벽을 추가해놓음
				if (map[i][j] == '1')
					walls.add(new Point(i, j));
			}
			Arrays.fill(minDisMap[i], MAX);
		}
		if(N == 1 && M == 1) ans = 1;
		else  {
			ans = minDisBfs();
//			print();
			
			for (Point w : walls) {
				// System.out.println(i + " " + j);
				int nowMin = MAX;
				if(minDisMap[w.i][w.j] != MAX) 					// 갈 수 있는데면
					nowMin = bfs(w, minDisMap[w.i][w.j]);
				ans = Integer.min(ans, nowMin);
			}
			if (ans == MAX)
				ans = -1;
		}
		System.out.println(ans);
	}
	
	static int minDisBfs() {
		int FirstLen = MAX;
		int len = 1;
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(0, 0));
		minDisMap[0][0] = len;
		while (!queue.isEmpty()) {
			int size = queue.size();
			len++;
			for (int i = 0; i < size; i++) {
				Point now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if (isOutOfIdx(ni, nj) || minDisMap[ni][nj] <= len) continue;
					if (ni == N - 1 && nj == M - 1)
						FirstLen = len;
					minDisMap[ni][nj] = len;
					if(map[ni][nj] == '0')
						queue.offer(new Point(ni, nj));
				}
			}
		}
		return FirstLen;
	}

	static int bfs(Point s, int startLen) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(s);
		int len = startLen;
		while (!queue.isEmpty()) {
			int size = queue.size();
			len++;
			for (int i = 0; i < size; i++) {
				Point now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if (isOutOfIdx(ni, nj) || map[ni][nj] == '1' || minDisMap[ni][nj] <= len) continue;
					if (ni == N - 1 && nj == M - 1)
						return len;
					minDisMap[ni][nj] = len;
					queue.offer(new Point(ni, nj));
				}
			}
		}

		return Integer.MAX_VALUE;
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= M;
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static void print() {								// 출력해보는 메소드
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(minDisMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
