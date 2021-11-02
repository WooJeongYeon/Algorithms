import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.01 ~ 2021.11.02
 * Level : BaekJoon Gold 2
 * Difficulty : 중상
 * Why : 
 * Time : 이틀간 그냥 틈틈히 생각함
 * URL : https://www.acmicpc.net/problem/12100
 * Select1 : 
 * Thinking : 
 * Method : 
 * Help : 
 * Error1 : 
 * Result : 
 * Plus1 : 
 */
public class BJ12100_2048_EASY {
	static int N, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int n = 0; n < 4; n++) {
			move(0, copyRotateMap(map, n));
		}

		System.out.println(ans);
	}

	static void move(int depth, int[][] map) {
		int idx = 0;
		int last = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				if (last == map[i][j]) {
					map[i][idx - 1] = last * 2;
					last = -1;
				} else {
					map[i][idx++] = map[i][j];
					last = map[i][j];
				}
			}
			for(int j = idx ; j < N ; j++) {
				map[i][j] = 0;		// 여기 인덱스를 j대신 idx로 했어서 복사하고 있었네
			}
			idx = 0;		// 이거 안했었음. 나머지도 채우게 해야하는디
			last = -1;		// 이것도 안했네
		}
		
//		
//		System.out.println("깊이 : " + depth);
//		print(map);

		
		if (depth == 4) {
			ans = Math.max(ans, maxValue(map));
			return;
		}

		for (int n = 0; n < 4; n++) {
			move(depth + 1, copyRotateMap(map, n));
		}
	}

	static int maxValue(int[][] map) {
		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}

		return max;
	}

	static int[][] copyRotateMap(int[][] map, int cnt) {
		int[][] simulMap = new int[N][N];
		cnt = 3 - cnt;
		for (int n = 0; n < cnt + 1; n++) {		// 무조건 돌리게
			simulMap = new int[N][N];
			for (int i = 0; i < N ; i++) {
				for (int j = 0; j < N; j++) {
					simulMap[j][N - i - 1] = map[i][j];
				}
			}
			map = simulMap;
		}
		return simulMap;
	}
//
//	static void print(int[][] map) {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("----------------------------");
//	}
}
