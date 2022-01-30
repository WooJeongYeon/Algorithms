import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.30
 * Level : BaekJoon Gold 1
 * Difficulty : ��
 * Why : ����������
 * Time : 2h 24m
 * URL : https://www.acmicpc.net/problem/23290
 * Select1 : �迭�� ����(ó���� ����Ʈ�� �ߴٰ� ���߿� �̰ɷ� �ٲ�) VS linkedlist�� ����
 * Thinking : map�� linkedlist�� ���� �ʿ� ����, ���� 8���� �����ϴ� 3���� �迭�� �ϸ� ���� �ʳ�... 
 * Method : �ùķ��̼�, DFS
 * Error : ������ ���� ������ �־���... ���� ���ɸ��� ������ ���٤ФФ� ������ �߸��а� ��Ʈ���͵� ���줻�������� �ٷ� Ǯ������ �̲ó���
 * Result : ��¥ ���׺��� ����� ���� �� ��� Ǯ��߰ڴ�... 
 */
public class BJ23290_��������ͺ��� {
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
		//map[shark.i][shark.j] = new int[8]; 	// 14. �ʱ⿡ ������ ���� ���� ��ġ�� ������ �� �Դ��� �˾Ҵµ� �ƴϳ�...(���� ��)
		
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
		maxEatFish = -1;				// 13. 0���� �س����ϱ� ����� ������ ���� ��� ���� string���� null ������
		dfs(map, 0, shark.i, shark.j, 0, "");
		for(int i = 0 ; i < SHARK_MOVE_CNT ; i++) {
			int d = sharkMoveOrder.charAt(i) - '0';		// 1. '0' �Ȼ���
			shark.i += sharkDi[d];
			shark.j += sharkDj[d];		// 8. �굵 i�� �߸���(���� ���� ����)
			for(int k = 0 ; k < 8 ; k++) {
				if(map[shark.i][shark.j][k] > 0) {	// 11. ����Ⱑ �������� ����� ���� �����ؾ� ��
					fishSmellPosArr[n][shark.i][shark.j] = true;	// 2. �ε��� Ʋ��
					map[shark.i][shark.j][k] = 0;
					
				}
			}
		}
	}
	// 7. ���� ��ġ�� ���ƿ��� ��쵵 ����(map �Ź� ����)
	private static void dfs(int[][][] arr, int depth, int i, int j, int fishCnt, String dir) {
		if(depth == SHARK_MOVE_CNT) {
			if(maxEatFish < fishCnt) {
				maxEatFish = fishCnt;		// 9. �̰� �����ܤ�����������¾�� ���� ������ ������ ������ ����Ǵ���
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
		int[][][] copy = new int[N][N][8];	// 6. �̰͵� ���ϰ�(�� map�� ī������?)
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(int d = 0 ; d < 8 ; d++) {
					if(map[i][j][d] == 0) continue;	// 5. �̰͵� ���ϰ�...
					boolean isPossible = false;
					for(int k = 0 ; k < 8 ; k++) {
						int newD = (d - k + 8) % 8;
						int ni = i + fishDi[newD];
						int nj = j + fishDj[newD];
						if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
						if(ni == shark.i && nj == shark.j) continue;
						if(n >= 2 && fishSmellPosArr[n - 2][ni][nj]) continue;
						if(n >= 1 && fishSmellPosArr[n - 1][ni][nj]) continue;		// 10. �̰͵� �����̳�. ������ ���Ԥ������������Ƴ�
						isPossible = true;
						copy[ni][nj][newD] += map[i][j][d];		// 3. ���� ��ü�� ����(�� map ���� ī�Ǹ� �����ϰ��־���..?)
						break;
					}
					if(!isPossible) copy[i][j][d] += map[i][j][d];	// 12. ���� �� �� �ִ� �� ���� ���� ������ copy�� ��������� �Ϲ¤ФФ�
				}
			}
		}
		map = copy;		// 4. �̰͵� ���ϰ�...
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
