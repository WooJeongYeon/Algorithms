import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.30
 * Level : BaekJoon Gold 1
 * Difficulty : 상
 * Why : 버그투성이
 * Time : 2h 24m
 * URL : https://www.acmicpc.net/problem/23290
 * Select1 : 배열에 저장(처음에 리스트에 했다가 나중에 이걸로 바꿈) VS linkedlist에 저장
 * Thinking : map을 linkedlist로 만들 필요 없이, 방향 8가지 저장하는 3차원 배열로 하면 되지 않나... 
 * Method : 시뮬레이션, DFS
 * Error : 수없이 많은 에러가 있었고... 거의 테케마다 오류가 났다ㅠㅠㅠ 문제도 잘못읽고 빠트린것도 많곸ㅋㅋㅋㅋㅋ 바로 풀었더니 이꼴났네
 * Result : 진짜 코테볼때 쉬운걸 먼저 잘 골라서 풀어야겠다... 
 */
public class BJ23290_마법사상어와복제 {
	static int M, S;
	static Point shark;
	static int[][][] map;
	static boolean[][][] fishSmellPosArr; 
	static int[][][] copyMap;
	static String sharkMoveOrder;
	static int maxEatFish;
	static int[] fishDi = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fishDj = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] sharkDi = {-1, 0, 1, 0};
	static int[] sharkDj = {0, -1, 0, 1};
	static final int N = 4;
	static final int SHARK_MOVE_CNT = 3;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		map = new int[N][N][8];
		fishSmellPosArr = new boolean[S][N][N];
		copyMap = new int[N][N][8];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			map[x][y][d]++;
		}
		st = new StringTokenizer(in.readLine(), " ");
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		shark = new Point(x, y);
		//map[shark.i][shark.j] = new int[8]; 	// 14. 초기에 물고기랑 상어랑 같은 위치에 있으면 다 먹는줄 알았는데 아니네...(드디어 끝)
		
		for(int n = 0 ; n < S ; n++) {
			copyMap = copyFishes(map);
			moveFishes(n);
			moveShark(n);
			copyMagic();
		}
		
		System.out.println(getFishCnt());
	}
	private static void copyMagic() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(int d = 0 ; d < 8 ; d++) {
					map[i][j][d] += copyMap[i][j][d];
				}
			}
		}
	}
	private static void moveShark(int n) {	
		maxEatFish = -1;				// 13. 0으로 해놓으니까 물고기 못먹은 경우는 상어 방향 string에서 null 에러남
		dfs(map, 0, shark.i, shark.j, 0, "");
		for(int i = 0 ; i < SHARK_MOVE_CNT ; i++) {
			int d = sharkMoveOrder.charAt(i) - '0';		// 1. '0' 안빼줌
			shark.i += sharkDi[d];
			shark.j += sharkDj[d];		// 8. 얘도 i로 잘못씀(역시 내가 문제)
			for(int k = 0 ; k < 8 ; k++) {
				if(map[shark.i][shark.j][k] > 0) {	// 11. 물고기가 있을때만 물고기 냄새 저장해야 함
					fishSmellPosArr[n][shark.i][shark.j] = true;	// 2. 인덱스 틀림
					map[shark.i][shark.j][k] = 0;
					
				}
			}
		}
	}
	// 7. 원래 위치로 돌아오는 경우도 있음(map 매번 변경)
	private static void dfs(int[][][] arr, int depth, int i, int j, int fishCnt, String dir) {
		if(depth == SHARK_MOVE_CNT) {
			if(maxEatFish < fishCnt) {
				maxEatFish = fishCnt;		// 9. 이거 안해줌ㅋㅋㅋㅋㅋ어쩐지 값이 같더라도 마지막 방향이 저장되더라
				sharkMoveOrder = dir;
			}
			return;
		}
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + sharkDi[d];
			int nj = j + sharkDj[d];
			int cnt = 0;
			if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
			int[][][] newArr = copyFishes(arr);
			for(int k = 0 ; k < 8 ; k++) {
				cnt += arr[ni][nj][k];
				newArr[ni][nj][k] = 0;
			}
			dfs(newArr, depth + 1, ni, nj, fishCnt + cnt, dir + d);
		}
	}
	private static void moveFishes(int n) {
		int[][][] copy = new int[N][N][8];	// 6. 이것도 안하고(왜 map을 카피했지?)
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(int d = 0 ; d < 8 ; d++) {
					if(map[i][j][d] == 0) continue;	// 5. 이것도 안하고...
					boolean isPossible = false;
					for(int k = 0 ; k < 8 ; k++) {
						int newD = (d - k + 8) % 8;
						int ni = i + fishDi[newD];
						int nj = j + fishDj[newD];
						if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
						if(ni == shark.i && nj == shark.j) continue;
						if(n >= 2 && fishSmellPosArr[n - 2][ni][nj]) continue;
						if(n >= 1 && fishSmellPosArr[n - 1][ni][nj]) continue;		// 10. 이것도 포함이네. 생각도 안함ㅋㅋㅋㅋㅋㅋ아나
						isPossible = true;
						copy[ni][nj][newD] += map[i][j][d];		// 3. 이쪽 총체적 난국(왜 map 말고 카피를 저장하고있었지..?)
						break;
					}
					if(!isPossible) copy[i][j][d] += map[i][j][d];	// 12. 방향 갈 수 있는 곳 없는 경우는 원래로 copy에 복사해줘야 하뮤ㅠㅠㅠ
				}
			}
		}
		map = copy;		// 4. 이것도 안하고...
	}
	private static int[][][] copyFishes(int[][][] map) {
		int[][][] copy = new int[N][N][8];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				copy[i][j] = map[i][j].clone();
			}
		}
		return copy;
	}
	private static int getFishCnt() {
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(int d = 0 ; d < 8 ; d++) {
					cnt += map[i][j][d];
				}
			}
		}
		return cnt;
	}
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
