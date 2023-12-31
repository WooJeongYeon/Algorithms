import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.01
 * Level : BaekJoon
 * Method : 노가다계산
 */
public class BJ17143_낚시왕 {
	static int R, C, M, ans;
	static Shark[][] map, simulMap;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			s = (d <= 1 ? (s % (2 * (R - 1))) : (s % (2 * (C - 1))));
			map[r][c] = new Shark(s, d, z);
		}

		simul();
		System.out.println(ans);
	}

	static void simul() {
		for (int now = 0; now < C; now++) {
//			print(map);
			for (int i = 0; i < R; i++) {
				if (map[i][now] != null) {
//					System.out.println(map[i][now].size);
					ans += map[i][now].size;
					map[i][now] = null;

					System.gc();
					System.runFinalization();

					break;
				}
			}

			if (Shark.cnt == 0)
				return;
			if (now == C - 1)
				return;

			simulMap = new Shark[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] != null) {
						int r = i;
						int c = j;
						int v = map[i][j].v;
						if(map[i][j].v == 0) {
							r = i; c = j;
						}
						else if (map[i][j].dir == 0) {
							if (i < v && v <= i + R - 1) {		// 0 ~ R - 1
								r = v - i;
								map[i][j].dir = 1;
							} else r = (i - v + 2 * (R - 1)) % (R - 1);	// R ~ i
						} else if (map[i][j].dir == 1) {
							if (R - i - 1 <= v && v < 2 * R - i - 2) {	// R ~ 1
								r = 2 * (R - 1) - v - i;
								map[i][j].dir = 0;
							} else r = (i + v) % (R - 1);				// 0 ~ i - 1

						} else if (map[i][j].dir == 2) {
							if (C - j - 1 <= v && v < 2 * C - j - 2) {
								c = 2 * (C - 1) - v - j;
								map[i][j].dir = 3;
							} else c = (j + v) % (C - 1);
						} else if (map[i][j].dir == 3) {
							if (j < v && v <= j + C - 1) {
								c = v - j;
								map[i][j].dir = 2;
							} else c = (j - v + 2 * (C - 1)) % (C - 1);
						}
						if (simulMap[r][c] != null && simulMap[r][c].size >= map[i][j].size) continue;
						simulMap[r][c] = map[i][j];
					}
				}
			}
//			print(simulMap);
			map = simulMap;
		}

	}

	static class Shark {
		static int cnt;
		int v, dir, size;

		public Shark(int v, int dir, int size) {
			this.v = v;
			this.dir = dir;
			this.size = size;
			this.cnt++;
		}

		protected void finalize() {
			cnt--;
		}
	}
//	static void print(Shark[][] arr) {								// 출력해보는 메소드
//		for(int i = 0 ; i < R ; i++) {
//			for(int j = 0 ; j < C ; j++) {
//				System.out.printf("%8s ", arr[i][j] == null ? 0 + "" : arr[i][j].v + " " + arr[i][j].dir + " " + arr[i][j].size);
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
