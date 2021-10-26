import java.util.Scanner;
/*
 * Date : 2021.10.26
 * Level : BaekJoon Gold 2
 * Difficulty : 어려움 
 * Time : 측정불가(이틀간 정리 + 생각 + 코드작성)
 * URL : https://www.acmicpc.net/problem/19236
 * Select1 : 재귀호출 필요 - 물고기위치들, 상어위치들, map을 매개변수로 줄지(선택) vs 매번 설정했다 뺐다 할지
 * Thinking : 물고기 이동 - 상어이동 최대 15번까지 반복
 * 			- 참조값이므로 매개변수로 전달할 때 새로 객체들 생성해서 넘겨줘야 함
 * 			- map에 물고기 번호 저장, fishes에 해당 인덱스 번호의 물고기 위치, 방향 저장
 * Method : 재귀, 상어에 대해 DFS
 * Error1 : 물고기 이동할 때 map상에서만 물고기 이동하고 fishes의 위치 안 바꿔줌
 * Error2 : 객체 배열은 clone하면 얕은 복사됨 -> 직접 새로 객체생성해서 넣어줘야 함
 * Error3 : 물고기 이동할 때 방향설정 잘못함 d -> (fishes[n].dir + d) % 8
 * Result : 정답률이 높아서 괜찮을 줄 알았는데 아니였다...ㅠㅠㅠ
 * Plus1 : 
 */
public class BJ19236_청소년상어 {
	static int ans;
	static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1}; 	// 위부터 반시계방향으로 8방향
	static int[] dj = {0, -1, -1, -1, 0, 1, 1, 1};
	static final int N = 4;					// map의 크기
	public static void main(String[] args) {
		int sum = 0;
		Point[] fishes = new Point[N * N + 1];		
		int[][] map = new int[N][N];
		Scanner in = new Scanner(System.in);
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = in.nextInt();			 // 맵에 물고기 위치 저장
				int dir = in.nextInt() - 1;			// 물고기 방향
				fishes[map[i][j]] = new Point(i, j, dir);	// 물고기 생성
			}
		}
		sum += map[0][0];				// 처음 상어의 위치 이동해 물고기 번호 더해줌
		Point shark = new Point(0, 0, fishes[map[0][0]].dir);	// 상어 생성
		fishes[map[0][0]] = null;		// 먹은 물고기는 null 저장
		map[0][0] = 0;		// 물고기 먹었으니 map상에서 없어줌
		
		behavior(sum, shark, fishes, map);	// 재귀 ㄱㄱ
		
		System.out.println(ans);
	}
	
	
	static void behavior(int sum, Point shark, Point[] fishes, int[][] map) {
		ans = Math.max(ans, sum);		// 여태까지의 먹은 물고기 번호 합으로 최댓값 저장되도록
//		System.out.println("------------------------------뿌우----------------------------");
//		print(map);
		for(int n = 1 ; n <= N * N ; n++) {			// 물고기 이동
			if(fishes[n] == null) continue;			// 물고기 없으면 다음으로
			for(int d = 0 ; d < 8 ; d++) {			// 8방향까지 검사 가능(반시계방향으로 돌려가면서)
				int ni = fishes[n].i + di[(fishes[n].dir + d) % 8];
				int nj = fishes[n].j + dj[(fishes[n].dir + d) % 8];
				if(isOutOfIdx(ni, nj) || (ni == shark.i && nj == shark.j)) continue;	// 인덱스 나가거나 상어 있으면 불가
				
//				System.out.println("방향 : " + (fishes[n].dir + d) % 8);
//				System.out.println(map[ni][nj]);
				if(map[ni][nj] == 0) {			// 빈칸이면
					map[ni][nj] = n;
					map[fishes[n].i][fishes[n].j] = 0;
				} else {					// 물고기 있으면
					int tmp = map[ni][nj];	// map상에서 서로 바꿔주고 위치 새로 저장
					map[ni][nj] = n;
					map[fishes[n].i][fishes[n].j] = tmp;
					fishes[tmp].i = fishes[n].i;
					fishes[tmp].j = fishes[n].j;
				}
				fishes[n].i = ni;
				fishes[n].j = nj;
				fishes[n].dir = (fishes[n].dir + d) % 8;	// 해당 방향 저장
				break;
			}
//			print(map);
		}
		int ni = shark.i;					// 상어 이동
		int nj = shark.j;
		while(true) {						// 갈 수 있는 모든 위치로 가기 위해
			ni += di[shark.dir];
			nj += dj[shark.dir];
			if(isOutOfIdx(ni, nj)) break;	// 인덱스 나가면 끝
			if(map[ni][nj] == 0) continue;	// 빈칸은 못감
			
			int[][] simulMap = copyMap(map);	// 매개변수로 넘겨주기 위한 변수들 셋팅
			int newSum = sum + simulMap[ni][nj];
			Point[] newFishes = new Point[N * N + 1];
			for(int i = 1 ; i <= N * N ; i++) {
				if(fishes[i] != null) {
					newFishes[i] = new Point(fishes[i].i, fishes[i].j, fishes[i].dir);
				}
			}
			
			Point newShark = new Point(ni, nj, fishes[simulMap[ni][nj]].dir);	// 상어 위치 새로 해주고 물고기 먹음
			newFishes[simulMap[ni][nj]] = null;
			simulMap[ni][nj] = 0;
			behavior(newSum, newShark, newFishes, simulMap);	// 다음 재귀 ㄱㄱ
		}
	}
	
	static boolean isOutOfIdx(int i, int j) {			// 인덱스 나가는지
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static int[][] copyMap(int[][] map) {				// 맵 복사
		int[][] simulMap = new int[N][];
		for(int i = 0 ; i < N ; i++) {
			simulMap[i] = map[i].clone();
		}
		return simulMap;
	}
	
	static void print(int[][] map) {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------");
	}
	static class Point {
		int i, j, dir;
		public Point(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}
}
