import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 2021.10.03
 * Level : BaekJoon 골드 5
 * Thinking : BFS, DFS와 다르게 방향을 왼, 왼쪽위, 위로 저장... 현재 위치에서 이전 값들을 검사하므로 
 * 			3차원 배열 사용해 어느 위치에서 온 값인지를 저장(왼쪽, 왼쪽위, 위쪽 -> memo[N][N][3])
 * 			불가능한 위치는 저장X
 * Method : DP
 * Result : DP는 코드가 짧긴한데 예쁘게 짜기 어려운거같다. 매번 if로 뒤덮임...
 * Error1 : 문제 덜읽음 - 대각선 이동 경우, 가려는 칸의 상, 좌가 빈칸이어야 함
 * Error2 : 결과가 int를 벗어나버려서 long처리 해야함
 * Plus1 : 코드 좀 정리하자.. map[i][j]가 1이면 memo[i][j][k]에 저장된 값들은 0이므로 따로 처리 안하고 더해줘도 됨
 * Plus2 : 가장 왼쪽 열은 계산X, 가장 위쪽 열은 왼쪽값만 더해주면 됨 -> 인덱스 처리할 필요X
 * 바꾸기 전인 for문 사용한게 더 나은거같기두...
 */
public class BJ17069_파이프옮기기2 {
	static int N;
	static long ans;

	static int[][] map;
	static long[][][] memo;
	static int[] di = { 0, -1, -1 }; // 왼, 왼위, 위
	static int[] dj = { -1, -1, 0 };
	static final int DIR = 3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new long[N][N][DIR];
		memo[0][1][0] = 1L;																// 초기값 1을 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1 || j == 0) continue;									// 현재 위치가 벽이거나, 맨 왼쪽 열이면 패스
				
				if (i == 0)																// 가장 위쪽 행이면 이전 왼쪽값을 더해줌(=으로 하면 위에 표시한 1이 없어짐)
					memo[i][j][0] += memo[i + di[0]][j + dj[0]][0];
				else {																	// 2번째행부터
					memo[i][j][0] = memo[i + di[0]][j + dj[0]][0] + memo[i + di[0]][j + dj[0]][1];		// 왼쪽에서 온 경우 - 이전 파이프가 왼쪽, 대각선만 가능
					memo[i][j][2] = memo[i + di[2]][j + dj[2]][1] + memo[i + di[2]][j + dj[2]][2];		// 위쪽에서 온 경우 - 이전 파이프가 대각선, 위쪽만 가능
					if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {						// 대각선에서 온 경우 위왼쪽이 빈칸이어야 함
						memo[i][j][1] = memo[i + di[1]][j + dj[1]][0] + memo[i + di[1]][j + dj[1]][1]	// 모두 가능
								+ memo[i + di[1]][j + dj[1]][2];
					}
				}
			}
		}
		ans = memo[N - 1][N - 1][0] + memo[N - 1][N - 1][1] + memo[N - 1][N - 1][2];	// 최종 결과의 합이 답

		System.out.println(ans);
	}

	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
}
