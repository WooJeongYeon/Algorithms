import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 2022.05.01
 * Level : BaekJoon Gold 1
 * Time : 2h 4m
 * Method : 구현
 * Error1 : 여러가지 
 * Plus1 : 예전에는 map을 linkedlist로 만들 필요 없이, 방향 8가지 저장하는 3차원 배열로 하면 되지 않나...
 */

public class BJ23290_마법사상어와복제_재 {
	static int M, S, cnt;
	static String sharkDir;
	static Fish shark;
	static LinkedList<Integer>[][] map;
	static LinkedList<Fish> copyFish;
	static boolean[][][] smellArr;
	static int[] di = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] sharkDi = { -1, 0, 1, 0 };
	static int[] sharkDj = { 0, -1, 0, 1 };
	static final int N = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		map = new LinkedList[N][N];
		smellArr = new boolean[S][N][N];
		copyFish = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<>();
			}
		}
		for (int n = 0; n < M; n++) {
			st = new StringTokenizer(in.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			map[i][j].add(d);
		}
		st = new StringTokenizer(in.readLine());
		shark = new Fish(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		for (int n = 0; n < S; n++) {
			copyFish();
			moveFish(n);
			moveShark(n);
			copyMagic();
		}
		
		System.out.println(fishCnt());
	}

	private static void moveShark(int num) {
		cnt = 0;
		sharkDir = "999";
		Fish[] sharkPos = new Fish[3];
		dfs(0, 0, "", sharkPos, shark.i, shark.j);
		System.out.println(sharkDir);
		int ni = shark.i, nj = shark.j;
		for(int i = 0 ; i < 3 ; i++) {
			int d = sharkDir.charAt(i) - '0';
			ni += sharkDi[d];
			nj += sharkDj[d];
			if(map[ni][nj].size() > 0)		// 3. 무조건 냄새 표시했네. 물고기 죽은 경우만!
				smellArr[num][ni][nj] = true;
			map[ni][nj].clear();
			
		}
		shark.i = ni;
		shark.j = nj;
	}

	private static void dfs(int depth, int sum, String dir, Fish[] sharkPos, int i, int j) {
		if(depth == 3) {
			if(sum > cnt) {
				cnt = sum;
				sharkDir = dir;		// 2. 이거 안함...ㅋㅋㅋㅋ
			} else if(sum == cnt) {
				sharkDir = sharkDir.compareTo(dir) < 0 ? sharkDir : dir;
			}
			return;
		}
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + sharkDi[d];
			int nj = j + sharkDj[d];
			if(isOutOfIdx(ni, nj)) continue;
			Fish[] copySharkPos = sharkPos.clone();
			int newSum = sum + map[ni][nj].size();
			for(int n = 0 ; n < depth ; n++) {
				if(copySharkPos[n].i == ni && copySharkPos[n].j == nj) { 
					newSum -= map[ni][nj].size();
					break;
				}
			}
			copySharkPos[depth] = new Fish(ni, nj, 0);
			dfs(depth + 1, newSum, dir + d, copySharkPos, ni, nj);
		}
	}

	private static void moveFish(int num) {
		int[][] sizeArr = new int[4][4];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				sizeArr[i][j] = map[i][j].size();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = map[i][j].size();
				for (int s = 0; s < sizeArr[i][j]; s++) {
					int dir = map[i][j].poll();
					boolean isPossible = false;
					for (int n = 0; n < 8; n++) {
						int ni = i + di[(dir - n + 8) % 8];
						int nj = j + dj[(dir - n + 8) % 8];
						if (isOutOfIdx(ni, nj) || (ni == shark.i && nj == shark.j))
							continue;
						if (num >= 1 && smellArr[num - 1][ni][nj])		// 같다 표시 빼먹음...
							continue;
						if (num >= 2 && smellArr[num - 2][ni][nj])
							continue;
						dir = (dir - n + 8) % 8;
						isPossible = true;
						map[ni][nj].offerLast(dir);		// 이때 물고기 따로 저장해야 함
						break;
					}
					if(!isPossible)		// 4. 물고기 이동 없으면 다시 넣어야하는데 까먹음
						map[i][j].offerLast(dir);
				}
			}
		}
	}

	private static void copyMagic() {
		for (Fish fish : copyFish) {
			map[fish.i][fish.j].offer(fish.d);
		}
	}

	private static void copyFish() {
		copyFish.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Integer d : map[i][j]) {
					copyFish.offer(new Fish(i, j, d));
				}
			}
		}
	}
	
	private static int fishCnt() {
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				cnt += map[i][j].size();
			}
		}
		return cnt;
	}

	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}

	static class Fish {
		int i, j, d;

		public Fish(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
}
