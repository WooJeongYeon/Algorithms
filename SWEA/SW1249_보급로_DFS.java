import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * Date : 2021.09.30
 * Level : SWEA D3
 * Method : DFS
 * Result : 얜 시간초과남
 */
public class SW1249_보급로_DFS {
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

			memo[0][0] = map[0][0] - '0';
			dfs(0, 0); // map[0][0]부터 dfs 시작

			sb.append("#" + tc + " " + memo[N - 1][N - 1] + "\n");
		}
		System.out.println(sb); // 답 출력
	}

	static void dfs(int i, int j) { // dfs - 현재 인덱스들, 현재 위치까지의 합
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (isOutOfIdx(ni, nj))	continue;
			int tmp = memo[i][j] + (map[ni][nj] - '0');
			if (memo[N - 1][N - 1] <= tmp || memo[ni][nj] <= tmp) continue;
			memo[ni][nj] = tmp;
			dfs(ni, nj);
		}
	}

	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
}
